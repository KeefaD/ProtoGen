package com.kdsc.protogen.codegeneration.java;

import com.kdsc.protogen.codegeneration.CodeGeneratorContext;
import com.kdsc.protogen.codegeneration.utils.CodeGenerateUtils;
import com.kdsc.protogen.filegenerationtree.FileNode;
import com.kdsc.protogen.filegenerationtree.java.ClassFileNode;
import com.kdsc.protogen.filegenerationtree.java.EnumCaseNode;
import com.kdsc.protogen.filegenerationtree.java.EnumFileNode;
import com.kdsc.protogen.filegenerationtree.java.JavaFileNode;
import com.kdsc.protogen.filegenerationtree.shared.fieldtypenodes.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//TODO:KMD This is a total mess at the moment
public class CodeGenerator implements com.kdsc.protogen.codegeneration.CodeGenerator {

    public static final String ENUM_TEMPLATE_CLASSPATH = "/templates/java/Enum.template";
    public static final String TYPE_TEMPLATE_CLASSPATH = "/templates/java/Type.template";

    @Override
    public void generate(final CodeGeneratorContext codeGeneratorContext, final List<FileNode> fileNodes) {
        fileNodes
            .stream()
            .filter(fn -> fn instanceof JavaFileNode)
            .forEach(
                fn -> {
                    switch (fn) {
                        case EnumFileNode enumFileNode -> generateEnumNode(codeGeneratorContext, enumFileNode);
                        case ClassFileNode typeNode -> generateTypeNode(codeGeneratorContext, typeNode);
                        default -> throw new IllegalStateException("Unexpected type: " + fn.getClass().getName());
                    }
                }
            );
    }

    private void generateEnumNode(final CodeGeneratorContext codeGeneratorContext, final EnumFileNode enumFileNode) {
        System.out.println("Writing " + codeGeneratorContext.getJavaOutputDirectory() + enumFileNode.getPathAndFileName());
        var output = CodeGenerateUtils.readTemplateFromClasspath(ENUM_TEMPLATE_CLASSPATH);
        output = CodeGenerateUtils.replace(output, "[NAMESPACE]", enumFileNode.getNamespace());
        output = CodeGenerateUtils.replaceAndCollapseTwo(output, "[IMPORTS]", "");
        output = CodeGenerateUtils.replace(output, "[ENUM_NAME]", enumFileNode.getName());
        output = CodeGenerateUtils.replaceAndCollapse(output, "[ENUM_CASES]", generateEnumCases(codeGeneratorContext, enumFileNode.getEnumCaseNodes()));
        CodeGenerateUtils.writeStringToPath(codeGeneratorContext.getJavaOutputDirectory() + enumFileNode.getPathAndFileName(), output);
    }

    private void generateTypeNode(final CodeGeneratorContext codeGeneratorContext, final ClassFileNode classFileNode) {
        System.out.println("Writing " + codeGeneratorContext.getJavaOutputDirectory() + classFileNode.getPathAndFileName());
        var output = CodeGenerateUtils.readTemplateFromClasspath(TYPE_TEMPLATE_CLASSPATH);
        output = CodeGenerateUtils.replace(output, "[PACKAGE_NAME]", classFileNode.getPackageName());
        output = CodeGenerateUtils.replaceAndCollapseTwo(output, "[IMPORTS]", generateImportStatements(codeGeneratorContext, classFileNode.getImportStatements()));
        output = CodeGenerateUtils.replace(output, "[TYPE_NAME]", classFileNode.getName());
        output = CodeGenerateUtils.replaceAndCollapse(output, "[PRIVATE_FIELDS]", generatePrivateFields(codeGeneratorContext, classFileNode));
        output = CodeGenerateUtils.replaceAndCollapse(output, "[CONSTRUCTOR]", generateConstructor(codeGeneratorContext, classFileNode));
        output = CodeGenerateUtils.replaceAndCollapse(output, "[GETTERS]", generateGetters(codeGeneratorContext, classFileNode));
        output = CodeGenerateUtils.replaceAndCollapse(output, "[TO_STRING]", generateToString(codeGeneratorContext, classFileNode));
        CodeGenerateUtils.writeStringToPath(codeGeneratorContext.getJavaOutputDirectory() + classFileNode.getPathAndFileName(), output);
    }

    private String generateEnumCases(final CodeGeneratorContext codeGeneratorContext, final List<EnumCaseNode> enumCaseNodes) {
        if(enumCaseNodes.size() == 0) return "";
        var stringBuilder = new StringBuilder();
        var enumCaseNodesString = enumCaseNodes
            .stream()
            .map(ecn -> "\t" + ecn.getName())
            .collect(Collectors.joining(",\n")) + "\n";
        stringBuilder.append(enumCaseNodesString);
        return stringBuilder.toString();
    }

    private String generateImportStatements(final CodeGeneratorContext codeGeneratorContext, final Set<String> importStatements) {
        var stringBuilder = new StringBuilder();
        importStatements
            .forEach(
                is -> stringBuilder.append("import " + is + ";\n")
            );
        if(importStatements.size() > 0) {
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private String generatePrivateFields(final CodeGeneratorContext codeGeneratorContext, final ClassFileNode classFileNode) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        classFileNode
            .getFieldNodes()
            .forEach(
                fn -> stringBuilder.append("\tprivate " + generateFieldType(codeGeneratorContext, fn.getFieldTypeNode()) + " " + fn.getName() + ";\n")
            );
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    private String generateConstructor(final CodeGeneratorContext codeGeneratorContext, final ClassFileNode classFileNode) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("\tpublic " + classFileNode.getName() + "(\n");
        var constructorParameters = classFileNode
            .getFieldNodes()
            .stream()
            .map(fn -> "\t\t" + generateFieldType(codeGeneratorContext, fn.getFieldTypeNode()) + " " + fn.getName())
            .collect(Collectors.joining(",\n")) + "\n";
        stringBuilder.append(constructorParameters);
        stringBuilder.append("\t) {\n");
        classFileNode
            .getFieldNodes()
            .forEach(
                fn -> stringBuilder.append("\t\tthis." + fn.getName() + " = " + fn.getName() + ";\n")
            );
        stringBuilder.append("\t}\n");
        return stringBuilder.toString();
    }

    //TODO:KMD Don't forget about indentation level
    private String generateGetters(final CodeGeneratorContext codeGeneratorContext, final ClassFileNode classFileNode) {

        if(classFileNode.getFieldNodes().size() == 0) return "";

        var stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        classFileNode
            .getFieldNodes()
            .forEach(
                fn -> {
                    stringBuilder.append("\tpublic " + generateFieldType(codeGeneratorContext, fn.getFieldTypeNode()) + " get" + fn.getName() + "() {\n");
                    stringBuilder.append("\t\treturn " + fn.getName() + ";\n");
                    stringBuilder.append("\t}\n");
                    stringBuilder.append("\n");
                }
            );
        return stringBuilder.toString();
    }

    private String generateToString(final CodeGeneratorContext codeGeneratorContext, final ClassFileNode classFileNode) {

        var stringBuilder = new StringBuilder();
        stringBuilder.append("\t@Override\n");
        stringBuilder.append("\tpublic String toString() {\n");
        stringBuilder.append("\t\treturn toFormattedString(0);\n");
        stringBuilder.append("\t}\n");
        stringBuilder.append("\n");
        stringBuilder.append("\tpublic String toFormattedString(final int indentationLevel) {\n");
        stringBuilder.append("\t\tvar stringBuilder = new StringBuilder();\n");
        classFileNode
            .getFieldNodes()
            .forEach(
                fn -> {
                }
            );
        //TODO:KMD I think we need a utility method here
        stringBuilder.append("\t\tstringBuilder.append(\"//" + classFileNode.getPackageName() + "." + classFileNode.getName() + "\\n\");\n");
        stringBuilder.append("\t\treturn stringBuilder.toString().indent(indentationLevel * TO_STRING_INDENTATION_LEVEL);\n");
        stringBuilder.append("\t}\n");
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    private String generateFieldType(final CodeGeneratorContext codeGeneratorContext, final FieldTypeNode fieldTypeNode) {
        return generateFieldType(codeGeneratorContext, fieldTypeNode, false);
    }

    private String generateFieldType(final CodeGeneratorContext codeGeneratorContext, final FieldTypeNode fieldTypeNode, final boolean needsWrappers) {
        if(fieldTypeNode.isOptional()) {
            return switch (fieldTypeNode) {
                case DoubleFieldTypeNode ignored -> "Optional<Double>";
                case FloatFieldTypeNode ignored -> "Optional<Float>";
                case Int32FieldTypeNode ignored -> "Optional<Integer>";
                case Int64FieldTypeNode ignored -> "Optional<Long>";
                case BoolFieldTypeNode ignored -> "Optional<Boolean>";
                case StringFieldTypeNode ignored -> "Optional<String>";
                case DecimalFieldTypeNode ignored -> "Optional<com.kdsc.protogen.runtime.types.Decimal>";
                case DateFieldTypeNode ignored -> "Optional<com.kdsc.protogen.runtime.types.Date>";
                case DateTimeFieldTypeNode ignored -> "Optional<com.kdsc.protogen.runtime.types.DateTime>";
                case LocalDateTimeFieldTypeNode ignored -> "Optional<com.kdsc.protogen.runtime.types.LocalDateTime>";
                case TypeFieldTypeNode typeFieldTypeNode -> "Optional<" + typeFieldTypeNode.getFullyQualifiedName() + ">";
                case ValueOrErrorFieldTypeNode valueOrErrorFieldTypeNode -> "Optional<com.kdsc.protogen.runtime.types.ValueOrError<" + generateFieldType(codeGeneratorContext, valueOrErrorFieldTypeNode.getFieldTypeNode(), true) + ">>";
                case SetFieldTypeNode setFieldTypeNode -> "Optional<com.kdsc.protogen.runtime.types.Set<" + generateFieldType(codeGeneratorContext, setFieldTypeNode.getFieldTypeNode(), true) + ">>";
                case MapFieldTypeNode mapFieldTypeNode -> "Optional<com.kdsc.protogen.runtime.types.Map<" + generateFieldType(codeGeneratorContext, mapFieldTypeNode.getKeyFieldTypeNode(), true) + ", " + generateFieldType(codeGeneratorContext, mapFieldTypeNode.getValueFieldTypeNode(), true) + ">>";
                default -> throw new IllegalStateException("Unexpected value: " + fieldTypeNode);
            };
        } else {
            if(needsWrappers) {
                return switch (fieldTypeNode) {
                    case DoubleFieldTypeNode ignored -> "Double";
                    case FloatFieldTypeNode ignored -> "Float";
                    case Int32FieldTypeNode ignored -> "Integer";
                    case Int64FieldTypeNode ignored -> "Long";
                    case BoolFieldTypeNode ignored -> "Boolean";
                    case StringFieldTypeNode ignored -> "String";
                    case DecimalFieldTypeNode ignored -> "com.kdsc.protogen.runtime.types.Decimal";
                    case DateFieldTypeNode ignored -> "com.kdsc.protogen.runtime.types.Date";
                    case DateTimeFieldTypeNode ignored -> "com.kdsc.protogen.runtime.types.DateTime";
                    case LocalDateTimeFieldTypeNode ignored -> "com.kdsc.protogen.runtime.types.LocalDateTime";
                    case TypeFieldTypeNode typeFieldTypeNode -> typeFieldTypeNode.getFullyQualifiedName();
                    case ValueOrErrorFieldTypeNode valueOrErrorFieldTypeNode -> "com.kdsc.protogen.runtime.types.ValueOrError<" + generateFieldType(codeGeneratorContext, valueOrErrorFieldTypeNode.getFieldTypeNode(), true) + ">";
                    case SetFieldTypeNode setFieldTypeNode -> "com.kdsc.protogen.runtime.types.Set<" + generateFieldType(codeGeneratorContext, setFieldTypeNode.getFieldTypeNode(), true) + ">";
                    case MapFieldTypeNode mapFieldTypeNode -> "com.kdsc.protogen.runtime.types.Map<" + generateFieldType(codeGeneratorContext, mapFieldTypeNode.getKeyFieldTypeNode(), true) + ", " + generateFieldType(codeGeneratorContext, mapFieldTypeNode.getValueFieldTypeNode(), true) + ">";
                    default -> throw new IllegalStateException("Unexpected value: " + fieldTypeNode);
                };
            } else {
                return switch (fieldTypeNode) {
                    case DoubleFieldTypeNode ignored -> "double";
                    case FloatFieldTypeNode ignored -> "float";
                    case Int32FieldTypeNode ignored -> "int";
                    case Int64FieldTypeNode ignored -> "long";
                    case BoolFieldTypeNode ignored -> "boolean";
                    case StringFieldTypeNode ignored -> "String";
                    case DecimalFieldTypeNode ignored -> "com.kdsc.protogen.runtime.types.Decimal";
                    case DateFieldTypeNode ignored -> "com.kdsc.protogen.runtime.types.Date";
                    case DateTimeFieldTypeNode ignored -> "com.kdsc.protogen.runtime.types.DateTime";
                    case LocalDateTimeFieldTypeNode ignored -> "com.kdsc.protogen.runtime.types.LocalDateTime";
                    case TypeFieldTypeNode typeFieldTypeNode -> typeFieldTypeNode.getFullyQualifiedName();
                    case ValueOrErrorFieldTypeNode valueOrErrorFieldTypeNode -> "com.kdsc.protogen.runtime.types.ValueOrError<" + generateFieldType(codeGeneratorContext, valueOrErrorFieldTypeNode.getFieldTypeNode(), true) + ">";
                    case SetFieldTypeNode setFieldTypeNode -> "com.kdsc.protogen.runtime.types.Set<" + generateFieldType(codeGeneratorContext, setFieldTypeNode.getFieldTypeNode(), true) + ">";
                    case MapFieldTypeNode mapFieldTypeNode -> "com.kdsc.protogen.runtime.types.Map<" + generateFieldType(codeGeneratorContext, mapFieldTypeNode.getKeyFieldTypeNode(), true) + ", " + generateFieldType(codeGeneratorContext, mapFieldTypeNode.getValueFieldTypeNode(), true) + ">";
                    default -> throw new IllegalStateException("Unexpected value: " + fieldTypeNode);
                };
            }
        }
    }

}