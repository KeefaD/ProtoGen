package com.kdsc.protogen.transform.utils;

import com.kdsc.protogen.parsetree.NamespaceNameNode;
import com.kdsc.protogen.parsetree.NamespaceNode;

import java.util.stream.Collectors;

//TODO:KMD Check isProtoGen type for types that have generic parameters, it's all going to go to shit if they are not
//TODO:KMD Need to test moving all the directories around
//TODO:KMD Need to be able to do a comparison on all the examples at the same time or it is pointless
//TODO:KMD Needs test
public class TransformUtils {

    public static final String NAMESPACE_IN_FILENAME_SEPARATOR = ".";

    //TODO:KMD Should this take namespace name node or a list of namespace nodes?
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

}