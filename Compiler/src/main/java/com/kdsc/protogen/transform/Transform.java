package com.kdsc.protogen.transform;

import com.kdsc.protogen.filegenerationtree.FileNode;
import com.kdsc.protogen.transform.proto.Transformer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Transform implements com.kdsc.protogen.transform.Transformer {

    @Override
    public List<FileNode> transform(TransformerContext transformerContext, List<com.kdsc.protogen.parsetree.FileNode> fileNodes) {
        var protoTransformer = new Transformer();
        var protoFiles = protoTransformer.transform(transformerContext, fileNodes);
        var javaTransformer = new com.kdsc.protogen.transform.java.Transformer();
        var javaFiles = javaTransformer.transform(transformerContext, fileNodes);
        return Stream.concat(
            protoFiles.stream(),
            javaFiles.stream()
        ).collect(Collectors.toList());
    }

}