package com.kdsc.protogen.transform.java;

import com.kdsc.protogen.filegenerationtree.FileNode;
import com.kdsc.protogen.transform.TransformerContext;

import java.util.Collections;
import java.util.List;

public class Transformer implements com.kdsc.protogen.transform.Transformer {

    @Override
    public List<FileNode> transform(TransformerContext transformerContext, List<com.kdsc.protogen.parsetree.FileNode> fileNodes) {
        return Collections.emptyList();
    }
}
