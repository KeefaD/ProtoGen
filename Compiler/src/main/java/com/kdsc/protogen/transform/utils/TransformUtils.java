package com.kdsc.protogen.transform.utils;

import com.kdsc.protogen.parsetree.NamespaceNameNode;
import com.kdsc.protogen.parsetree.NamespaceNode;

import java.io.File;
import java.util.stream.Collectors;

//TODO:KMD Needs test
public class TransformUtils {

    //TODO:KMD Should this take namespace name node or a list of namespace nodes?
    public static String convertNamespaceNameNodeToPath(final NamespaceNameNode namespaceNameNode) {
        return namespaceNameNode
            .getNamespaceNodes()
            .stream()
            .map(NamespaceNode::getNamespace)
            //TODO:KMD Think about separator, perhaps I should copy this somewhere else, somewhere central
            .collect(Collectors.joining(File.separator));
    }

}