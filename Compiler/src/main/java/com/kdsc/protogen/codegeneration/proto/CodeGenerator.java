package com.kdsc.protogen.codegeneration.proto;

import com.kdsc.protogen.codegeneration.CodeGeneratorContext;
import com.kdsc.protogen.codegeneration.utils.CodeGenerateUtils;
import com.kdsc.protogen.filegenerationtree.FileNode;
import com.kdsc.protogen.filegenerationtree.proto.EnumCaseNode;
import com.kdsc.protogen.filegenerationtree.proto.EnumFileNode;
import com.kdsc.protogen.filegenerationtree.proto.MessageFileNode;
import com.kdsc.protogen.filegenerationtree.proto.ProtoFileNode;
import com.kdsc.protogen.filegenerationtree.shared.FieldNode;
import com.kdsc.protogen.filegenerationtree.shared.fieldtypenodes.FieldTypeNode;
import com.kdsc.protogen.filegenerationtree.shared.fieldtypenodes.Int32FieldTypeNode;

import java.util.List;
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
        output = CodeGenerateUtils.replace(output, "[ENUM_NAME]", enumFileNode.getEnumName());
        output = CodeGenerateUtils.replaceAndCollapse(output, "[ENUM_CASES]", generateEnumCases(codeGeneratorContext, enumFileNode.getEnumCaseNodes()));
        CodeGenerateUtils.writeStringToPath(codeGeneratorContext.getProtoOutputDirectory() + enumFileNode.getPathAndFileName(), output);
    }

    private void generateTypeNode(final CodeGeneratorContext codeGeneratorContext, final MessageFileNode messageFileNode) {
        System.out.println("Writing " + codeGeneratorContext.getProtoOutputDirectory() + messageFileNode.getPathAndFileName());
        var output = CodeGenerateUtils.readTemplateFromClasspath(MESSAGE_TEMPLATE_CLASSPATH);
        output = CodeGenerateUtils.replaceAndCollapseTwo(output, "[IMPORTS]", "");
        output = CodeGenerateUtils.replace(output, "[MESSAGE_NAME]", messageFileNode.getName());
        output = CodeGenerateUtils.replaceAndCollapse(output, "[FIELDS]", generateFields(codeGeneratorContext, messageFileNode.getFieldNodes()));
        CodeGenerateUtils.writeStringToPath(codeGeneratorContext.getProtoOutputDirectory() + messageFileNode.getPathAndFileName(), output);
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

    private String generateFieldType(final CodeGeneratorContext codeGeneratorContext, final FieldTypeNode fieldTypeNode) {
        return switch (fieldTypeNode) {
            case Int32FieldTypeNode ignored -> "int32";
            default -> throw new IllegalStateException("Unexpected value: " + fieldTypeNode);
        };
    }

}