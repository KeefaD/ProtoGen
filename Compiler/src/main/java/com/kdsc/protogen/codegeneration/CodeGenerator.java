package com.kdsc.protogen.codegeneration;

import com.kdsc.protogen.filegenerationtreenodes.FileNode;

import java.util.List;

public interface CodeGenerator {

    void generate(final CodeGeneratorContext codeGeneratorContext, final List<FileNode> fileNodes);

}