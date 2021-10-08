package com.kdsc.protogen.parsetreenodes.commoninterfaces;

import com.kdsc.protogen.parsetreenodes.NameNode;
import com.kdsc.protogen.parsetreenodes.NamespaceNameNode;
import com.kdsc.protogen.parsetreenodes.NamespaceNode;

import java.util.List;

public interface HasNamespaceName {

    NamespaceNameNode getNamespaceNameNode();

    List<NamespaceNode> getNamespaceNodes();

    NameNode getNameNode();

}