package com.kdsc.protogen.transform;

import com.kdsc.protogen.compilerresults.CompilerResults;
import com.kdsc.protogen.filegenerationtreenodes.FileNode;
import com.kdsc.protogen.transform.proto.Transformer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Transform implements com.kdsc.protogen.transform.Transformer {

    @Override
    public List<FileNode> transform(final CompilerResults compilerResults, final TransformerContext transformerContext) {

        var protoTransformer = new Transformer();
        var protoFiles = protoTransformer.transform(compilerResults, transformerContext);

        var javaTransformer = new com.kdsc.protogen.transform.java.Transformer();
        var javaFiles = javaTransformer.transform(compilerResults, transformerContext);

        return Stream.concat(
            protoFiles.stream(),
            javaFiles.stream()
        ).collect(Collectors.toList());
    }

}