package com.kdsc.protogen.codegeneration.proto;

import com.kdsc.protogen.codegeneration.CodeGeneratorContext;
import com.kdsc.protogen.codegeneration.utils.CodeGenerateUtils;
import com.kdsc.protogen.filegenerationtree.FileNode;
import com.kdsc.protogen.filegenerationtree.proto.EnumCaseNode;
import com.kdsc.protogen.filegenerationtree.proto.EnumFileNode;
import com.kdsc.protogen.filegenerationtree.proto.MessageFileNode;
import com.kdsc.protogen.filegenerationtree.proto.ProtoFileNode;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
        var template = CodeGenerateUtils.readTemplateFromClasspath(ENUM_TEMPLATE_CLASSPATH);
        template = CodeGenerateUtils.replace(template, "[ENUM_NAME]", enumFileNode.getEnumName());
        template = CodeGenerateUtils.replaceAndCollapseTwo(template, "[ENUM_CASES]", generateEnumCases(codeGeneratorContext, enumFileNode.getEnumCaseNodes()));
        CodeGenerateUtils.writeStringToPath(codeGeneratorContext.getProtoOutputDirectory() + enumFileNode.getPathAndFileName(), template);
    }

    private void generateTypeNode(final CodeGeneratorContext codeGeneratorContext, final MessageFileNode messageFileNode) {
        System.out.println("Writing " + codeGeneratorContext.getProtoOutputDirectory() + messageFileNode.getPathAndFileName());
        var template = CodeGenerateUtils.readTemplateFromClasspath(MESSAGE_TEMPLATE_CLASSPATH);
        CodeGenerateUtils.writeStringToPath(codeGeneratorContext.getProtoOutputDirectory() + messageFileNode.getPathAndFileName(), template);
    }

    private String generateEnumCases(final CodeGeneratorContext codeGeneratorContext, final List<EnumCaseNode> enumCaseNodes) {
        var stringBuilder = new StringBuilder();
        AtomicInteger fieldNumber = new AtomicInteger();
        enumCaseNodes
            .forEach(ecn -> stringBuilder.append("\t" + ecn.getName() + " = " + fieldNumber.getAndIncrement() + ";\n"));
        return stringBuilder.toString();
    }

}