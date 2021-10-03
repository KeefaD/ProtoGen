package com.kdsc.protogen.parsetree.utils;

import com.kdsc.protogen.parsetree.NamespaceNameNode;
import com.kdsc.protogen.parsetree.NamespaceNode;

import java.util.stream.Collectors;

public class ParseTreeUtils {

    //TODO:KMD Is delimiter the right name
    public static final String DELIMITER = ".";

    public static String getNamespaceNameString(final NamespaceNameNode namespaceNameNode) {
        var returnString = namespaceNameNode
            .getNamespaceNodes()
            .stream()
            .map(NamespaceNode::getNamespace)
            .collect(Collectors.joining(DELIMITER));
        return returnString + "." + namespaceNameNode.getNameNode().getName();
    }

    public static String getNamespaceString(final NamespaceNameNode namespaceNameNode) {
        return namespaceNameNode
            .getNamespaceNodes()
            .stream()
            .map(NamespaceNode::getNamespace)
            .collect(Collectors.joining(DELIMITER));
    }

}