package com.kdsc.protogen.transform.proto;

import com.kdsc.protogen.filegenerationtree.FileNode;

import com.kdsc.protogen.filegenerationtree.proto.EnumFileNode;
import com.kdsc.protogen.filegenerationtree.proto.MessageFileNode;
import com.kdsc.protogen.parsetree.ProtoGenEnumNode;
import com.kdsc.protogen.parsetree.ProtoGenKeyNode;
import com.kdsc.protogen.parsetree.ProtoGenTypeNode;
import com.kdsc.protogen.transform.TransformerContext;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Transformer implements com.kdsc.protogen.transform.Transformer {

    @Override
    public List<FileNode> transform(TransformerContext transformerContext, List<com.kdsc.protogen.parsetree.FileNode> fileNodes) {
        fileNodes
            .forEach(fn -> transformFileNode(transformerContext, fn));
        return Collections.emptyList();
    }

    private List<FileNode> transformFileNode(TransformerContext transformerContext, com.kdsc.protogen.parsetree.FileNode fileNode) {

        //TODO:KMD Don't need to to it this way but I'm messing around at the moment
        return Stream.of(
            fileNode.getProtoGenEnumNodes().stream(),
            fileNode.getProtoGenTypeNodes().stream(),
            fileNode.getProtoGenKeyNodes().stream()
        )
        .flatMap(s -> s)
        .map(
            tln -> switch (tln) {
                case ProtoGenEnumNode protoGenEnumNode -> transformEnumNode(transformerContext, protoGenEnumNode);
                case ProtoGenTypeNode protoGenTypeNode -> transformTypeNode(transformerContext, protoGenTypeNode);
                case ProtoGenKeyNode protoGenKeyNode -> transformKeyNode(transformerContext, protoGenKeyNode);
                default -> throw new IllegalStateException("Unexpected value: " + tln);
            }
        )
        .collect(Collectors.toList());
    }

    private FileNode transformEnumNode(TransformerContext transformerContext, com.kdsc.protogen.parsetree.ProtoGenEnumNode enumNode) {
        return new EnumFileNode(
            enumNode.getNamespaceNameNode().getNameNode().getName() + TransformerContext.protoFileExtension,
            "Temporary"
        );
    }

    private FileNode transformTypeNode(TransformerContext transformerContext, com.kdsc.protogen.parsetree.ProtoGenTypeNode typeNode) {
        return new MessageFileNode(
            typeNode.getNamespaceNameNode().getNameNode().getName() + TransformerContext.protoFileExtension,
            "Temporary"
        );
    }

    private FileNode transformKeyNode(TransformerContext transformerContext, com.kdsc.protogen.parsetree.ProtoGenKeyNode keyNode) {
        return new MessageFileNode(
            keyNode.getNamespaceNameNode().getNameNode().getName() + TransformerContext.protoFileExtension,
            "Temporary"
        );
    }
}
