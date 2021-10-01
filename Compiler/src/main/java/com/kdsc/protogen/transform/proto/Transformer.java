package com.kdsc.protogen.transform.proto;

import com.kdsc.protogen.filegenerationtree.FileNode;

import com.kdsc.protogen.filegenerationtree.proto.EnumCaseNode;
import com.kdsc.protogen.filegenerationtree.proto.EnumFileNode;
import com.kdsc.protogen.filegenerationtree.proto.MessageFileNode;
import com.kdsc.protogen.parsetree.*;
import com.kdsc.protogen.transform.TransformerContext;
import com.kdsc.protogen.transform.utils.TransformUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Transformer implements com.kdsc.protogen.transform.Transformer {

    @Override
    public List<FileNode> transform(final TransformerContext transformerContext, final List<com.kdsc.protogen.parsetree.FileNode> fileNodes) {
        return fileNodes
            .stream()
            .flatMap(fn -> transformFileNode(transformerContext, fn).stream())
            .collect(Collectors.toList());
    }

    private List<FileNode> transformFileNode(final TransformerContext transformerContext, final com.kdsc.protogen.parsetree.FileNode fileNode) {

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

    //TODO:KMD Starting to not put unit tests everywhere for every class, you should do it even if they are empty to fill them out later
    //TODO:KMD How are we going to represent versions in Proto
    //TODO:KMD We need to do proto name escaping
    //TODO:KMD We need to to do name escaping in general or prevent keywords, need to make up your mind soon, keywords is going to be annoying once you add more languages, as long as the types come out with the right name it is ok
    //TODO:KMD Figure out what to do about these paths
    private FileNode transformEnumNode(final TransformerContext transformerContext, final com.kdsc.protogen.parsetree.ProtoGenEnumNode enumNode) {
        if(enumNode.getEnumCasesNode().isPresent()) {
            return new EnumFileNode(
                TransformUtils.convertNamespaceNameNodeToName(enumNode.getNamespaceNameNode()) + TransformerContext.protoFileExtension,
                "",
                enumNode.getNamespaceNameNode().getNameNode().getName(),
                transformEnumCaseNodes(transformerContext, enumNode.getEnumCasesNode().get())
            );
        } else if(enumNode.getEnumVersionsNode().isPresent()) {
            return new EnumFileNode(
                TransformUtils.convertNamespaceNameNodeToName(enumNode.getNamespaceNameNode()) + TransformerContext.protoFileExtension,
                "",
                enumNode.getNamespaceNameNode().getNameNode().getName(),
                Collections.emptyList()
            );
        }
        return new EnumFileNode(
            TransformUtils.convertNamespaceNameNodeToName(enumNode.getNamespaceNameNode()) + TransformerContext.protoFileExtension,
            "",
            enumNode.getNamespaceNameNode().getNameNode().getName(),
            Collections.emptyList()
        );
    }

    private FileNode transformTypeNode(final TransformerContext transformerContext, final com.kdsc.protogen.parsetree.ProtoGenTypeNode typeNode) {
        return new MessageFileNode(
            TransformUtils.convertNamespaceNameNodeToName(typeNode.getNamespaceNameNode()) + TransformerContext.protoFileExtension,
            ""
        );
    }

    private FileNode transformKeyNode(final TransformerContext transformerContext, final com.kdsc.protogen.parsetree.ProtoGenKeyNode keyNode) {
        return new MessageFileNode(
            TransformUtils.convertNamespaceNameNodeToName(keyNode.getNamespaceNameNode()) + TransformerContext.protoFileExtension,
            ""
        );
    }

    private List<EnumCaseNode> transformEnumCaseNodes(final TransformerContext transformerContext, final EnumCasesNode enumCasesNode) {
        return enumCasesNode
            .getEnumNameNodes()
            .stream()
            .map(enn -> transformEnumCaseNode(transformerContext, enn))
            .collect(Collectors.toList());
    }

    private EnumCaseNode transformEnumCaseNode(final TransformerContext transformerContext, final EnumNameNode enumNameNode) {
        return new EnumCaseNode(
            enumNameNode.getEnumName()
        );
    }

}