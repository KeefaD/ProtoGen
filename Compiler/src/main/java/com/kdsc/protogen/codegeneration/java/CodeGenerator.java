package com.kdsc.protogen.codegeneration.java;

import com.kdsc.protogen.codegeneration.CodeGeneratorContext;
import com.kdsc.protogen.codegeneration.utils.CodeGenerateUtils;
import com.kdsc.protogen.filegenerationtreenodes.FileNode;
import com.kdsc.protogen.filegenerationtreenodes.java.*;
import com.kdsc.protogen.filegenerationtreenodes.shared.fieldtypenodes.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//TODO:KMD This is a total mess at the moment
public class CodeGenerator implements com.kdsc.protogen.codegeneration.CodeGenerator {

    public static final String ENUM_TEMPLATE_CLASSPATH = "/templates/java/Enum.template";
    public static final String TYPE_TEMPLATE_CLASSPATH = "/templates/java/Type.template";
    public static final String TYPE_INTERFACE_TEMPLATE_CLASSPATH = "/templates/java/TypeInterface.template";

    @Override
    public void generate(final CodeGeneratorContext codeGeneratorContext, final List<FileNode> fileNodes) {
        fileNodes
            .stream()
            .filter(fn -> fn instanceof JavaFileNode)
            .forEach(
                fn -> {
                    switch (fn) {
                        case EnumFileNode enumFileNode -> generateEnumNode(codeGeneratorContext, enumFileNode);
                        case TypeFileNode typeFileNode -> generateTypeNode(codeGeneratorContext, typeFileNode);
                        case TypeInterfaceFileNode typeInterfaceFileNode -> generateTypeInterfaceNode(codeGeneratorContext, typeInterfaceFileNode);
                        default -> throw new IllegalStateException("Unexpected type: " + fn.getClass().getName());
                    }
                }
            );
    }

    private void generateEnumNode(final CodeGeneratorContext codeGeneratorContext, final EnumFileNode enumFileNode) {
        System.out.println("Writing " + codeGeneratorContext.getJavaOutputDirectory() + enumFileNode.getPathAndFileName());
        var output = CodeGenerateUtils.readTemplateFromClasspath(ENUM_TEMPLATE_CLASSPATH);
        output = CodeGenerateUtils.replace(output, "[BANNER]", CodeGenerateUtils.getBanner());
        output = CodeGenerateUtils.replace(output, "[PACKAGE_NAME]", enumFileNode.getNamespace());
        output = CodeGenerateUtils.replace(output, "[ENUM_NAME]", enumFileNode.getName());
        output = CodeGenerateUtils.replaceAndCollapse(output, "[ENUM_CASES]", generateEnumCases(codeGeneratorContext, enumFileNode.getEnumCaseNodes()));
        CodeGenerateUtils.writeStringToPath(codeGeneratorContext.getJavaOutputDirectory() + enumFileNode.getPathAndFileName(), output);
    }

    private void generateTypeNode(final CodeGeneratorContext codeGeneratorContext, final TypeFileNode typeFileNode) {
        System.out.println("Writing " + codeGeneratorContext.getJavaOutputDirectory() + typeFileNode.getPathAndFileName());
        var output = CodeGenerateUtils.readTemplateFromClasspath(TYPE_TEMPLATE_CLASSPATH);
        output = CodeGenerateUtils.replace(output, "[BANNER]", CodeGenerateUtils.getBanner());
        output = CodeGenerateUtils.replace(output, "[PACKAGE_NAME]", typeFileNode.getPackageName());
        output = CodeGenerateUtils.replaceAndCollapseTwo(output, "[IMPORTS]", generateImportStatements(codeGeneratorContext, typeFileNode.getImportStatements()));
        output = CodeGenerateUtils.replace(output, "[TYPE_NAME]", typeFileNode.getName());
        output = CodeGenerateUtils.replace(output, "[IMPLEMENTS_LIST]", generateImplementsList(codeGeneratorContext, typeFileNode.getImplementsNodes()));
        output = CodeGenerateUtils.replaceAndCollapse(output, "[PRIVATE_FIELDS]", generatePrivateFields(codeGeneratorContext, typeFileNode));
        output = CodeGenerateUtils.replaceAndCollapse(output, "[CONSTRUCTOR]", generateConstructor(codeGeneratorContext, typeFileNode));
        output = CodeGenerateUtils.replaceAndCollapse(output, "[GETTERS]", generateGetters(codeGeneratorContext, typeFileNode));
        output = CodeGenerateUtils.replaceAndCollapse(output, "[TO_STRING]", generateToString(codeGeneratorContext, typeFileNode));
        output = CodeGenerateUtils.replaceAndCollapse(output, "[EQUALS_HASH_CODE]", generateEqualsHashCode(codeGeneratorContext, typeFileNode));
        CodeGenerateUtils.writeStringToPath(codeGeneratorContext.getJavaOutputDirectory() + typeFileNode.getPathAndFileName(), output);
    }

    private void generateTypeInterfaceNode(final CodeGeneratorContext codeGeneratorContext, final TypeInterfaceFileNode typeInterfaceFileNode) {
        System.out.println("Writing " + codeGeneratorContext.getJavaOutputDirectory() + typeInterfaceFileNode.getPathAndFileName());
        var output = CodeGenerateUtils.readTemplateFromClasspath(TYPE_INTERFACE_TEMPLATE_CLASSPATH);
        output = CodeGenerateUtils.replace(output, "[BANNER]", CodeGenerateUtils.getBanner());
        output = CodeGenerateUtils.replace(output, "[PACKAGE_NAME]", typeInterfaceFileNode.getPackageName());
        output = CodeGenerateUtils.replaceAndCollapseTwo(output, "[IMPORTS]", generateImportStatements(codeGeneratorContext, typeInterfaceFileNode.getImportStatements()));
        output = CodeGenerateUtils.replace(output, "[TYPE_NAME]", typeInterfaceFileNode.getName());
        output = CodeGenerateUtils.replace(output, "[IMPLEMENTS_LIST]", generateImplementsList(codeGeneratorContext, typeInterfaceFileNode.getImplementsNodes()));
        output = CodeGenerateUtils.replaceAndCollapse(output, "[GETTERS]", generateInterfaceGetters(codeGeneratorContext, typeInterfaceFileNode));
        CodeGenerateUtils.writeStringToPath(codeGeneratorContext.getJavaOutputDirectory() + typeInterfaceFileNode.getPathAndFileName(), output);
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

    private String generateImplementsList(final CodeGeneratorContext codeGeneratorContext, final List<ImplementsNode> implementsNodes) {
        var stringBuilder = new StringBuilder();
        implementsNodes
            .forEach(
                //TODO:KMD This "." in here is crap
                in -> stringBuilder.append(in.getPackageName() + "." + in.getName() + ", ")
            );
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

    private String generatePrivateFields(final CodeGeneratorContext codeGeneratorContext, final TypeFileNode typeFileNode) {
        if(typeFileNode.getFieldNodes().isEmpty()) {
            return "\n";
        }

        var stringBuilder = new StringBuilder();

        stringBuilder.append("\n");
        typeFileNode
            .getFieldNodes()
            .forEach(
                fn -> stringBuilder.append("\tprivate final " + generateFieldType(codeGeneratorContext, fn.getFieldTypeNode()) + " " + fn.getName() + ";\n")
            );
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    private String generateConstructor(final CodeGeneratorContext codeGeneratorContext, final TypeFileNode typeFileNode) {
        var stringBuilder = new StringBuilder();
        if(typeFileNode.getFieldNodes().isEmpty()) {
            stringBuilder.append("\tpublic " + typeFileNode.getName() + "()\n");
            stringBuilder.append("\t{\n");
            stringBuilder.append("\t}\n");
            stringBuilder.append("\n");
            return stringBuilder.toString();
        }
        stringBuilder.append("\tpublic " + typeFileNode.getName() + "(\n");
        var constructorParameters = typeFileNode
            .getFieldNodes()
            .stream()
            .map(fn -> "\t\tfinal " + generateFieldType(codeGeneratorContext, fn.getFieldTypeNode()) + " " + fn.getName())
            .collect(Collectors.joining(",\n")) + "\n";
        stringBuilder.append(constructorParameters);
        stringBuilder.append("\t) {\n");
        typeFileNode
            .getFieldNodes()
            .forEach(
                fn -> stringBuilder.append("\t\tthis." + fn.getName() + " = " + fn.getName() + ";\n")
            );
        stringBuilder.append("\t}\n");
        return stringBuilder.toString();
    }

    //TODO:KMD Don't forget about indentation level
    private String generateGetters(final CodeGeneratorContext codeGeneratorContext, final TypeFileNode typeFileNode) {

        if(typeFileNode.getFieldNodes().size() == 0) return "";

        var stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        typeFileNode
            .getFieldNodes()
            .forEach(
                tnf -> {
                    stringBuilder.append("\tpublic " + generateFieldType(codeGeneratorContext, tnf.getFieldTypeNode()) + " get" + tnf.getName() + "() {\n");
                    stringBuilder.append("\t\treturn " + tnf.getName() + ";\n");
                    stringBuilder.append("\t}\n");
                    stringBuilder.append("\n");
                }
            );
        return stringBuilder.toString();
    }

    private String generateInterfaceGetters(final CodeGeneratorContext codeGeneratorContext, final TypeInterfaceFileNode typeInterfaceFileNode) {

        if(typeInterfaceFileNode.getFieldNodes().size() == 0) return "";

        var stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        typeInterfaceFileNode
            .getFieldNodes()
            .forEach(tifn -> stringBuilder.append("\t" + generateFieldType(codeGeneratorContext, tifn.getFieldTypeNode()) + " get" + tifn.getName() + "();\n"));
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    private String generateToString(final CodeGeneratorContext codeGeneratorContext, final TypeFileNode typeFileNode) {

        var stringBuilder = new StringBuilder();
        stringBuilder.append("\t@Override\n");
        stringBuilder.append("\tpublic String toString() {\n");
        stringBuilder.append("\t\treturn toFormattedString(ToStringOptions.defaultToStringOptions, 0);\n");
        stringBuilder.append("\t}\n");
        stringBuilder.append("\n");
        stringBuilder.append("\tpublic String toFormattedString(final ToStringOptions toStringOptions, final int indentationLevel) {\n");
        stringBuilder.append("\t\tvar stringBuilder = new StringBuilder();\n");
        typeFileNode
            .getFieldNodes()
            .forEach(
                fn -> {
                }
            );
        //TODO:KMD I think we need a utility method here
        stringBuilder.append("\t\tstringBuilder.append(\"//" + typeFileNode.getPackageName() + "." + typeFileNode.getName() + "\\n\");\n");
        stringBuilder.append("\t\treturn stringBuilder.toString().indent(indentationLevel * TO_STRING_INDENTATION_LEVEL);\n");
        stringBuilder.append("\t}\n");
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    private String generateEqualsHashCode(final CodeGeneratorContext codeGeneratorContext, final TypeFileNode typeFileNode) {

        var stringBuilder = new StringBuilder();
        stringBuilder.append("\t@Override\n");
        stringBuilder.append("\tpublic boolean equals(final Object object) {\n");
        stringBuilder.append("\t\treturn equals(EqualsHashCodeOptions.defaultEqualsHashCodeOptions, object);\n");
        stringBuilder.append("\t}\n");
        stringBuilder.append("\n");
        stringBuilder.append("\tpublic boolean equals(final EqualsHashCodeOptions equalsHashCodeOptions, final Object object) {\n");

        //TODO:KMD Just return false for now
        stringBuilder.append("\t\treturn false;\n");
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
                case BytesFieldTypeNode ignored -> "Optional<com.kdsc.protogen.runtime.types.Bytes>";
                case DecimalFieldTypeNode ignored -> "Optional<com.kdsc.protogen.runtime.types.Decimal>";
                case DateFieldTypeNode ignored -> "Optional<com.kdsc.protogen.runtime.types.Date>";
                case DateTimeFieldTypeNode ignored -> "Optional<com.kdsc.protogen.runtime.types.DateTime>";
                case LocalDateFieldTypeNode ignored -> "Optional<com.kdsc.protogen.runtime.types.LocalDate>";
                case LocalDateTimeFieldTypeNode ignored -> "Optional<com.kdsc.protogen.runtime.types.LocalDateTime>";
                case TypeFieldTypeNode typeFieldTypeNode -> "Optional<" + typeFieldTypeNode.getFullyQualifiedName() + ">";
                case EnumFieldTypeNode enumFieldTypeNode -> "Optional<" + enumFieldTypeNode.getFullyQualifiedName() + ">";
                case ValueOrErrorFieldTypeNode valueOrErrorFieldTypeNode -> "Optional<com.kdsc.protogen.runtime.types.ValueOrError<" + generateFieldType(codeGeneratorContext, valueOrErrorFieldTypeNode.getFieldTypeNode(), true) + ">>";
                case SetFieldTypeNode setFieldTypeNode -> "Optional<com.kdsc.protogen.runtime.types.Set<" + generateFieldType(codeGeneratorContext, setFieldTypeNode.getFieldTypeNode(), true) + ">>";
                case ListFieldTypeNode listFieldTypeNode -> "Optional<com.kdsc.protogen.runtime.types.List<" + generateFieldType(codeGeneratorContext, listFieldTypeNode.getFieldTypeNode(), true) + ">>";
                case MapFieldTypeNode mapFieldTypeNode -> "Optional<com.kdsc.protogen.runtime.types.Map<" + generateFieldType(codeGeneratorContext, mapFieldTypeNode.getKeyFieldTypeNode(), true) + ", " + generateFieldType(codeGeneratorContext, mapFieldTypeNode.getValueFieldTypeNode(), true) + ">>";
                case ArrayFieldTypeNode arrayFieldTypeNode -> "Optional<com.kdsc.protogen.runtime.types.Array<" + generateFieldType(codeGeneratorContext, arrayFieldTypeNode.getFieldTypeNode(), true) + ">>";
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
                    case BytesFieldTypeNode ignored -> "com.kdsc.protogen.runtime.types.Bytes";
                    case DecimalFieldTypeNode ignored -> "com.kdsc.protogen.runtime.types.Decimal";
                    case DateFieldTypeNode ignored -> "com.kdsc.protogen.runtime.types.Date";
                    case DateTimeFieldTypeNode ignored -> "com.kdsc.protogen.runtime.types.DateTime";
                    case LocalDateFieldTypeNode ignored -> "com.kdsc.protogen.runtime.types.LocalDate";
                    case LocalDateTimeFieldTypeNode ignored -> "com.kdsc.protogen.runtime.types.LocalDateTime";
                    case TypeFieldTypeNode typeFieldTypeNode -> typeFieldTypeNode.getFullyQualifiedName();
                    case EnumFieldTypeNode enumFieldTypeNode -> enumFieldTypeNode.getFullyQualifiedName();
                    case ValueOrErrorFieldTypeNode valueOrErrorFieldTypeNode -> "com.kdsc.protogen.runtime.types.ValueOrError<" + generateFieldType(codeGeneratorContext, valueOrErrorFieldTypeNode.getFieldTypeNode(), true) + ">";
                    case SetFieldTypeNode setFieldTypeNode -> "com.kdsc.protogen.runtime.types.Set<" + generateFieldType(codeGeneratorContext, setFieldTypeNode.getFieldTypeNode(), true) + ">";
                    case ListFieldTypeNode listFieldTypeNode -> "com.kdsc.protogen.runtime.types.List<" + generateFieldType(codeGeneratorContext, listFieldTypeNode.getFieldTypeNode(), true) + ">";
                    case MapFieldTypeNode mapFieldTypeNode -> "com.kdsc.protogen.runtime.types.Map<" + generateFieldType(codeGeneratorContext, mapFieldTypeNode.getKeyFieldTypeNode(), true) + ", " + generateFieldType(codeGeneratorContext, mapFieldTypeNode.getValueFieldTypeNode(), true) + ">";
                    case ArrayFieldTypeNode arrayFieldTypeNode -> "com.kdsc.protogen.runtime.types.Array<" + generateFieldType(codeGeneratorContext, arrayFieldTypeNode.getFieldTypeNode(), true) + ">";
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
                    case BytesFieldTypeNode ignored -> "com.kdsc.protogen.runtime.types.Bytes";
                    case DecimalFieldTypeNode ignored -> "com.kdsc.protogen.runtime.types.Decimal";
                    case DateFieldTypeNode ignored -> "com.kdsc.protogen.runtime.types.Date";
                    case DateTimeFieldTypeNode ignored -> "com.kdsc.protogen.runtime.types.DateTime";
                    case LocalDateFieldTypeNode ignored -> "com.kdsc.protogen.runtime.types.LocalDate";
                    case LocalDateTimeFieldTypeNode ignored -> "com.kdsc.protogen.runtime.types.LocalDateTime";
                    case TypeFieldTypeNode typeFieldTypeNode -> typeFieldTypeNode.getFullyQualifiedName();
                    case EnumFieldTypeNode enumFieldTypeNode -> enumFieldTypeNode.getFullyQualifiedName();
                    case ValueOrErrorFieldTypeNode valueOrErrorFieldTypeNode -> "com.kdsc.protogen.runtime.types.ValueOrError<" + generateFieldType(codeGeneratorContext, valueOrErrorFieldTypeNode.getFieldTypeNode(), true) + ">";
                    case SetFieldTypeNode setFieldTypeNode -> "com.kdsc.protogen.runtime.types.Set<" + generateFieldType(codeGeneratorContext, setFieldTypeNode.getFieldTypeNode(), true) + ">";
                    case ListFieldTypeNode listFieldTypeNode -> "com.kdsc.protogen.runtime.types.List<" + generateFieldType(codeGeneratorContext, listFieldTypeNode.getFieldTypeNode(), true) + ">";
                    case MapFieldTypeNode mapFieldTypeNode -> "com.kdsc.protogen.runtime.types.Map<" + generateFieldType(codeGeneratorContext, mapFieldTypeNode.getKeyFieldTypeNode(), true) + ", " + generateFieldType(codeGeneratorContext, mapFieldTypeNode.getValueFieldTypeNode(), true) + ">";
                    case ArrayFieldTypeNode arrayFieldTypeNode -> "com.kdsc.protogen.runtime.types.Array<" + generateFieldType(codeGeneratorContext, arrayFieldTypeNode.getFieldTypeNode(), true) + ">";
                    default -> throw new IllegalStateException("Unexpected value: " + fieldTypeNode);
                };
            }
        }
    }

}