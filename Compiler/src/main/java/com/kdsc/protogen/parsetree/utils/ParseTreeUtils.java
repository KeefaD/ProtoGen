package com.kdsc.protogen.parsetree.utils;

import com.kdsc.protogen.parsetree.NamespaceNameNode;
import com.kdsc.protogen.parsetree.NamespaceNode;

import java.util.stream.Collectors;

public class ParseTreeUtils {

    public static final String NAMESPACE_SEPARATOR = ".";

    public static String getNamespaceNameString(final NamespaceNameNode namespaceNameNode) {
        var returnString = namespaceNameNode
            .getNamespaceNodes()
            .stream()
            .map(NamespaceNode::getNamespace)
            .collect(Collectors.joining(NAMESPACE_SEPARATOR));
        return returnString + NAMESPACE_SEPARATOR + namespaceNameNode.getNameNode().getName();
    }

    public static String getNamespaceString(final NamespaceNameNode namespaceNameNode) {
        return namespaceNameNode
            .getNamespaceNodes()
            .stream()
            .map(NamespaceNode::getNamespace)
            .collect(Collectors.joining(NAMESPACE_SEPARATOR));
    }

}