package com.kdsc.protogen.codegeneration;

import com.kdsc.protogen.filegenerationtree.FileNode;

import java.util.List;

public interface CodeGenerator {
    void generate(List<FileNode> fileNodes);
}
