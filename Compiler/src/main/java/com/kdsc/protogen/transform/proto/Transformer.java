package com.kdsc.protogen.transform.proto;

import com.kdsc.protogen.filegenerationtree.FileNode;

import com.kdsc.protogen.parsetree.ProtoGenEnumNode;
import com.kdsc.protogen.transform.TransformerContext;

import java.util.Collections;
import java.util.List;


public class Transformer implements com.kdsc.protogen.transform.Transformer {

    @Override
    public List<FileNode> transform(TransformerContext transformerContext, List<com.kdsc.protogen.parsetree.FileNode> fileNodes) {
        fileNodes
            .stream().flatMap(fn -> fn.getProtoGenEnumNodes().stream())
            .forEach(
                fn -> {
                    switch (fn) {
                        case ProtoGenEnumNode protoGenEnumNode -> System.out.println("EnumNode");
                    }
                }
            );
        return Collections.emptyList();
    }
}
