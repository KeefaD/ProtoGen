package com.kdsc.protogen.transform.proto;

import com.kdsc.protogen.filegenerationtree.FileNode;

import com.kdsc.protogen.filegenerationtree.proto.EnumCaseNode;
import com.kdsc.protogen.filegenerationtree.proto.EnumFileNode;
import com.kdsc.protogen.filegenerationtree.proto.MessageFileNode;
import com.kdsc.protogen.transform.TransformerContext;
import com.kdsc.protogen.transform.java.FileContext;
import com.kdsc.protogen.transform.shared.FieldTransformer;
import com.kdsc.protogen.transform.utils.TransformUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//TODO:KMD This is a total mess at the moment
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
                case com.kdsc.protogen.parsetree.ProtoGenEnumNode protoGenEnumNode -> transformEnumNode(transformerContext, protoGenEnumNode);
                case com.kdsc.protogen.parsetree.ProtoGenTypeNode protoGenTypeNode -> transformTypeNode(transformerContext, protoGenTypeNode);
                case com.kdsc.protogen.parsetree.ProtoGenKeyNode protoGenKeyNode -> transformKeyNode(transformerContext, protoGenKeyNode);
                default -> throw new IllegalStateException("Unexpected value: " + tln);
            }
        )
        .collect(Collectors.toList());
    }

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
        var fieldTransformer = new FieldTransformer();

        var fileContext = new FileContext();

        return new MessageFileNode(
            TransformUtils.convertNamespaceNameNodeToName(typeNode.getNamespaceNameNode()) + TransformerContext.protoFileExtension,
            "",
            typeNode.getNamespaceNameNode().getNameNode().getName(),
            //TODO:KMD Warning here
            fieldTransformer.transformFieldsNodes(transformerContext, fileContext, typeNode.getFieldsNode().get())
        );
    }

    private FileNode transformKeyNode(final TransformerContext transformerContext, final com.kdsc.protogen.parsetree.ProtoGenKeyNode keyNode) {
        return new MessageFileNode(
            TransformUtils.convertNamespaceNameNodeToName(keyNode.getNamespaceNameNode()) + TransformerContext.protoFileExtension,
            "",
            keyNode.getNamespaceNameNode().getNameNode().getName(),
            Collections.emptyList()
        );
    }

    private List<EnumCaseNode> transformEnumCaseNodes(final TransformerContext transformerContext, final com.kdsc.protogen.parsetree.EnumCasesNode enumCasesNode) {
        return enumCasesNode
            .getEnumNameNodes()
            .stream()
            .map(enn -> transformEnumCaseNode(transformerContext, enn))
            .collect(Collectors.toList());
    }

    private EnumCaseNode transformEnumCaseNode(final TransformerContext transformerContext, final com.kdsc.protogen.parsetree.EnumNameNode enumNameNode) {
        return new EnumCaseNode(
            enumNameNode.getEnumName()
        );
    }

}