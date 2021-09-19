package com.kdsc.protogen.parsetree.utils;

import com.kdsc.protogen.parsetree.NamespaceNameNode;
import com.kdsc.protogen.parsetree.NamespaceNode;

import java.util.stream.Collectors;

public class ParseTreeUtils {

    public static final String DELIMITER = ".";

    public static String getNamespaceNameString(NamespaceNameNode namespaceNameNode) {
        var returnString = namespaceNameNode
            .getNamespaceNodes()
            .stream()
            .map(NamespaceNode::getNamespace)
            .collect(Collectors.joining(DELIMITER));
        returnString += "." + namespaceNameNode.getNameNode().getName();
        return returnString;
    }
}
