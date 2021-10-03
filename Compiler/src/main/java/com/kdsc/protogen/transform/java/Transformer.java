package com.kdsc.protogen.transform.java;

import com.kdsc.protogen.filegenerationtree.FileNode;
import com.kdsc.protogen.filegenerationtree.java.ClassFileNode;
import com.kdsc.protogen.filegenerationtree.java.EnumCaseNode;
import com.kdsc.protogen.filegenerationtree.java.EnumFileNode;
import com.kdsc.protogen.parsetree.EnumCasesNode;
import com.kdsc.protogen.parsetree.EnumNameNode;
import com.kdsc.protogen.parsetree.ProtoGenEnumNode;
import com.kdsc.protogen.parsetree.ProtoGenTypeNode;
import com.kdsc.protogen.transform.FileContext;
import com.kdsc.protogen.transform.TransformerContext;
import com.kdsc.protogen.transform.shared.FieldTransformer;
import com.kdsc.protogen.transform.utils.TransformUtils;
import com.kdsc.protogen.utils.Streams;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//TODO:KMD This is a total mess at the moment
public class Transformer implements com.kdsc.protogen.transform.Transformer {

    @Override
    public List<FileNode> transform(TransformerContext transformerContext, List<com.kdsc.protogen.parsetree.FileNode> fileNodes) {
        return fileNodes
            .stream()
            .flatMap(fn -> transformFileNode(transformerContext, fn).stream())
            .collect(Collectors.toList());
    }

    private List<FileNode> transformFileNode(final TransformerContext transformerContext, final com.kdsc.protogen.parsetree.FileNode fileNode) {
        return Streams.concat(
            fileNode
                .getProtoGenEnumNodes()
                .stream()
                .map(en -> transformEnumNode(transformerContext, en)),
            fileNode
                .getProtoGenTypeNodes()
                .stream()
                .map(en -> transformTypeNode(transformerContext, en))
        ).collect(Collectors.toList());
    }

    private FileNode transformEnumNode(final TransformerContext transformerContext, final ProtoGenEnumNode enumNode) {
        if(enumNode.getEnumCasesNode().isPresent()) {
            return new EnumFileNode(
                enumNode.getNamespaceNameNode().getNameNode().getName() + TransformerContext.javaFileExtension,
                TransformUtils.convertNamespaceNameNodeToPath(enumNode.getNamespaceNameNode()),
                TransformUtils.convertNamespaceNameNodeToNamespace(enumNode.getNamespaceNameNode()),
                enumNode.getNamespaceNameNode().getNameNode().getName(),
                transformEnumCaseNodes(transformerContext, enumNode.getEnumCasesNode().get())
            );
        }
        return new EnumFileNode(
            enumNode.getNamespaceNameNode().getNameNode().getName() + TransformerContext.javaFileExtension,
            TransformUtils.convertNamespaceNameNodeToPath(enumNode.getNamespaceNameNode()),
            TransformUtils.convertNamespaceNameNodeToNamespace(enumNode.getNamespaceNameNode()),
            enumNode.getNamespaceNameNode().getNameNode().getName(),
            Collections.emptyList()
        );
    }

    private FileNode transformTypeNode(final TransformerContext transformerContext, final ProtoGenTypeNode typeNode) {

        var fileContext = new FileContext();

        //TODO:KMD Obviously we need to do this all nicely
        fileContext.addJavaImport("com.kdsc.protogen.runtime.ProtoGenType");

        var fieldTransformer = new FieldTransformer();

        var fieldNodes = fieldTransformer.transformFieldsNodes(transformerContext, fileContext, typeNode.getFieldsNode().get());

        if(typeNode.getFieldsNode().isPresent()) {
            return new ClassFileNode(
                typeNode.getNamespaceNameNode().getNameNode().getName() + TransformerContext.javaFileExtension,
                TransformUtils.convertNamespaceNameNodeToPath(typeNode.getNamespaceNameNode()),
                TransformUtils.convertNamespaceNameNodeToNamespace(typeNode.getNamespaceNameNode()),
                typeNode.getNamespaceNameNode().getNameNode().getName(),
                fileContext.getJavaImportStatements(),
                fieldNodes
            );
        }
        return new ClassFileNode(
            typeNode.getNamespaceNameNode().getNameNode().getName() + TransformerContext.javaFileExtension,
            TransformUtils.convertNamespaceNameNodeToPath(typeNode.getNamespaceNameNode()),
            TransformUtils.convertNamespaceNameNodeToNamespace(typeNode.getNamespaceNameNode()),
            typeNode.getNamespaceNameNode().getNameNode().getName(),
            Collections.emptySet(),
            Collections.emptyList()
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