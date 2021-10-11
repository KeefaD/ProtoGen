package com.kdsc.protogen.codegeneration;

import com.kdsc.protogen.filegenerationtreenodes.FileNode;

import java.util.List;

public class CodeGenerate implements com.kdsc.protogen.codegeneration.CodeGenerator {

    @Override
    public void generate(final CodeGeneratorContext codeGeneratorContext, final List<FileNode> fileNodes) {

        var javaCodeGenerator = new com.kdsc.protogen.codegeneration.java.CodeGenerator();
        javaCodeGenerator.generate(codeGeneratorContext, fileNodes);

        var protoCodeGenerator = new com.kdsc.protogen.codegeneration.proto.CodeGenerator();
        protoCodeGenerator.generate(codeGeneratorContext, fileNodes);

    }

}