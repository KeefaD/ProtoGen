package com.kdsc.protogen.codegeneration;

import com.kdsc.protogen.filegenerationtree.FileNode;
import com.kdsc.protogen.filegenerationtree.java.JavaFileNode;
import com.kdsc.protogen.filegenerationtree.proto.ProtoFileNode;

import java.util.List;
import java.util.stream.Collectors;

public class CodeGenerate implements com.kdsc.protogen.codegeneration.CodeGenerator {

    @Override
    public void generate(final CodeGeneratorContext codeGeneratorContext, final List<FileNode> fileNodes) {
        var javaFileNodes = fileNodes
            .stream()
            .filter(fn -> fn instanceof JavaFileNode)
            .collect(Collectors.toList());
        var javaCodeGenerator = new com.kdsc.protogen.codegeneration.java.CodeGenerator();
        javaCodeGenerator.generate(codeGeneratorContext, javaFileNodes);
        var protoFileNodes = fileNodes
            .stream()
            .filter(fn -> fn instanceof ProtoFileNode)
            .collect(Collectors.toList());
        var protoCodeGenerator = new com.kdsc.protogen.codegeneration.proto.CodeGenerator();
        protoCodeGenerator.generate(codeGeneratorContext, protoFileNodes);
    }
}