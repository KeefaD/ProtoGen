package com.kdsc.protogen.codegeneration.proto;

import com.kdsc.protogen.codegeneration.CodeGeneratorContext;
import com.kdsc.protogen.codegeneration.utils.CodeGenerateUtils;
import com.kdsc.protogen.filegenerationtree.FileNode;
import com.kdsc.protogen.filegenerationtree.proto.EnumFileNode;
import com.kdsc.protogen.filegenerationtree.proto.MessageFileNode;
import com.kdsc.protogen.filegenerationtree.proto.ProtoFileNode;

import java.util.List;

public class CodeGenerator implements com.kdsc.protogen.codegeneration.CodeGenerator {

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
        CodeGenerateUtils.writeStringToPath(codeGeneratorContext.getProtoOutputDirectory() + enumFileNode.getPathAndFileName(), "Test");
    }

    private void generateTypeNode(final CodeGeneratorContext codeGeneratorContext, final MessageFileNode messageFileNode) {
        System.out.println("Writing " + codeGeneratorContext.getProtoOutputDirectory() + messageFileNode.getPathAndFileName());
        CodeGenerateUtils.writeStringToPath(codeGeneratorContext.getProtoOutputDirectory() + messageFileNode.getPathAndFileName(), "Test");
    }

}