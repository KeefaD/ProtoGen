package com.kdsc.protogen.transform.utils;

import com.kdsc.protogen.parsetreenodes.NamespaceNameNode;
import com.kdsc.protogen.parsetreenodes.NamespaceNode;

import java.io.File;
import java.util.stream.Collectors;

public final class TransformUtils {

    public static final String NAMESPACE_IN_FILENAME_SEPARATOR = ".";

    public static String convertNamespaceNameNodeToName(final NamespaceNameNode namespaceNameNode) {
//TODO:KMD Sort this
//        var baseNamespaceAsPath =
//            baseNamespace == null || baseNamespace.isBlank()
//                ? baseNamespace
//                : baseNamespace.replace(".", File.separator) + File.separator;
        return namespaceNameNode
            .getNamespaceNodes()
            .stream()
            .map(NamespaceNode::getNamespace)
            //TODO:KMD Think about separator, perhaps I should copy this somewhere else, somewhere central
            .collect(Collectors.joining(".")) + NAMESPACE_IN_FILENAME_SEPARATOR + namespaceNameNode.getNameNode().getName();
    }

    public static String convertNamespaceNameNodeToPath(final NamespaceNameNode namespaceNameNode) {
        return namespaceNameNode
            .getNamespaceNodes()
            .stream()
            .map(NamespaceNode::getNamespace)
            .collect(Collectors.joining(File.separator));
    }

    public static String convertNamespaceNameNodeToNamespace(final NamespaceNameNode namespaceNameNode) {
        return namespaceNameNode
            .getNamespaceNodes()
            .stream()
            .map(NamespaceNode::getNamespace)
            .collect(Collectors.joining(NAMESPACE_IN_FILENAME_SEPARATOR));
    }

}