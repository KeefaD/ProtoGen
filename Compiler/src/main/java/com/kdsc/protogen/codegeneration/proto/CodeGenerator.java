package com.kdsc.protogen.codegeneration.proto;

import com.kdsc.protogen.codegeneration.CodeGeneratorContext;
import com.kdsc.protogen.codegeneration.utils.CodeGenerateUtils;
import com.kdsc.protogen.filegenerationtreenodes.FileNode;
import com.kdsc.protogen.filegenerationtreenodes.proto.EnumCaseNode;
import com.kdsc.protogen.filegenerationtreenodes.proto.EnumFileNode;
import com.kdsc.protogen.filegenerationtreenodes.proto.MessageFileNode;
import com.kdsc.protogen.filegenerationtreenodes.proto.ProtoFileNode;
import com.kdsc.protogen.filegenerationtreenodes.shared.FieldNode;
import com.kdsc.protogen.filegenerationtreenodes.shared.fieldtypenodes.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

//TODO:KMD This is a total mess at the moment
public class CodeGenerator implements com.kdsc.protogen.codegeneration.CodeGenerator {

    public static final String ENUM_TEMPLATE_CLASSPATH = "/templates/proto/Enum.template";
    public static final String MESSAGE_TEMPLATE_CLASSPATH = "/templates/proto/Message.template";

    @Override
    public void generate(final CodeGeneratorContext codeGeneratorContext, final List<FileNode> fileNodes) {
        fileNodes
            .stream()
            .filter(fn -> fn instanceof ProtoFileNode)
            .forEach(
                fn -> {
                    switch (fn) {
                        case EnumFileNode enumFileNode -> generateEnumNode(codeGeneratorContext, enumFileNode);
                        case MessageFileNode messageFileNode -> generateTypeNode(codeGeneratorContext, messageFileNode);
                        default -> throw new IllegalStateException("Unexpected type: " + fn.getClass().getName());
                    }
                }
            );
    }

    private void generateEnumNode(final CodeGeneratorContext codeGeneratorContext, final EnumFileNode enumFileNode) {
        System.out.println("Writing " + codeGeneratorContext.getProtoOutputDirectory() + enumFileNode.getPathAndFileName());
        var output = CodeGenerateUtils.readTemplateFromClasspath(ENUM_TEMPLATE_CLASSPATH);
        output = CodeGenerateUtils.replace(output, "[BANNER]", CodeGenerateUtils.getBanner());
        output = CodeGenerateUtils.replaceAndCollapse(output, "[PACKAGE_NAME]", "package " + enumFileNode.getPackageName() + ";\n");
        output = CodeGenerateUtils.replace(output, "[ENUM_NAME]", enumFileNode.getEnumName());
        output = CodeGenerateUtils.replaceAndCollapse(output, "[ENUM_CASES]", generateEnumCases(codeGeneratorContext, enumFileNode.getEnumCaseNodes()));
        CodeGenerateUtils.writeStringToPath(codeGeneratorContext.getProtoOutputDirectory() + enumFileNode.getPathAndFileName(), output);
    }

    private void generateTypeNode(final CodeGeneratorContext codeGeneratorContext, final MessageFileNode messageFileNode) {
        System.out.println("Writing " + codeGeneratorContext.getProtoOutputDirectory() + messageFileNode.getPathAndFileName());
        var output = CodeGenerateUtils.readTemplateFromClasspath(MESSAGE_TEMPLATE_CLASSPATH);
        output = CodeGenerateUtils.replace(output, "[BANNER]", CodeGenerateUtils.getBanner());
        output = CodeGenerateUtils.replaceAndCollapse(output, "[PACKAGE_NAME]", "package " + messageFileNode.getPackageName() + ";\n");
        output = CodeGenerateUtils.replaceAndCollapseTwo(output, "[IMPORTS]", generateImportStatements(codeGeneratorContext, messageFileNode.getImportStatements()));
        output = CodeGenerateUtils.replace(output, "[MESSAGE_NAME]", messageFileNode.getName());
        output = CodeGenerateUtils.replaceAndCollapse(output, "[FIELDS]", generateFields(codeGeneratorContext, messageFileNode.getFieldNodes()));
        CodeGenerateUtils.writeStringToPath(codeGeneratorContext.getProtoOutputDirectory() + messageFileNode.getPathAndFileName(), output);
    }

    private String generateImportStatements(final CodeGeneratorContext codeGeneratorContext, final Set<String> importStatements) {
        var stringBuilder = new StringBuilder();
        importStatements
            .forEach(
                //TODO:KMD This .proto in here is wrong
                is -> stringBuilder.append("import \"" + is + ".proto\";\n")
            );
        if(importStatements.size() > 0) {
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private String generateEnumCases(final CodeGeneratorContext codeGeneratorContext, final List<EnumCaseNode> enumCaseNodes) {
        var stringBuilder = new StringBuilder();
        AtomicInteger fieldNumber = new AtomicInteger();
        enumCaseNodes
            .forEach(ecn -> stringBuilder.append("\t" + ecn.getName() + " = " + fieldNumber.getAndIncrement() + ";\n"));
        return stringBuilder.toString();
    }

    private String generateFields(final CodeGeneratorContext codeGeneratorContext, final List<FieldNode> fieldNodes) {
        var stringBuilder = new StringBuilder();
        AtomicInteger fieldNumber = new AtomicInteger(1);
        fieldNodes
            .forEach(fn -> stringBuilder.append("\t" + generateFieldType(codeGeneratorContext, fn.getFieldTypeNode()) + " " + fn.getName() + " = " + fieldNumber.getAndIncrement() + ";\n"));
        return stringBuilder.toString();
    }

    //TODO:KMD Obviously we are ignoring optional here
    private String generateFieldType(final CodeGeneratorContext codeGeneratorContext, final FieldTypeNode fieldTypeNode) {
        return switch (fieldTypeNode) {
            case DoubleFieldTypeNode ignored -> "double";
            case FloatFieldTypeNode ignored -> "float";
            case Int32FieldTypeNode ignored -> "int32";
            case Int64FieldTypeNode ignored -> "int64";
            case BoolFieldTypeNode ignored -> "bool";
            case StringFieldTypeNode ignored -> "string";
            case TypeFieldTypeNode typeFieldTypeNode -> typeFieldTypeNode.getFullyQualifiedName();
            case EnumFieldTypeNode enumFieldTypeNode -> enumFieldTypeNode.getFullyQualifiedName();
            case BytesFieldTypeNode ignored -> "protogentypes.Bytes";
            case DecimalFieldTypeNode ignored -> "protogentypes.Decimal";
            case DateFieldTypeNode ignored -> "protogentypes.Date";
            case DateTimeFieldTypeNode ignored -> "protogentypes.DateTime";
            case LocalDateFieldTypeNode ignored -> "protogentypes.LocalDate";
            case LocalDateTimeFieldTypeNode ignored -> "protogentypes.LocalDateTime";
            case ValueOrErrorFieldTypeNode ignored -> "protogentypes.ValueOrError";
            case SetFieldTypeNode ignored -> "protogentypes.Set";
            case ListFieldTypeNode ignored -> "protogentypes.List";
            case MapFieldTypeNode ignored -> "protogentypes.Map";
            case ArrayFieldTypeNode ignored -> "protogentypes.Array";
            default -> throw new IllegalStateException("Unexpected value: " + fieldTypeNode);
        };
    }

}