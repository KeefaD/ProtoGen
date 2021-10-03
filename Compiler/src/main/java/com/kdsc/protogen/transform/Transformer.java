package com.kdsc.protogen.transform;

import com.kdsc.protogen.filegenerationtree.FileNode;

import java.util.List;

public interface Transformer {

    List<FileNode> transform(TransformerContext transformerContext, List<com.kdsc.protogen.parsetree.FileNode> fileNodes);

}