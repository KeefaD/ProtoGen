package com.kdsc.protogen.parsetree.commoninterfaces;

import com.kdsc.protogen.parsetree.NameNode;
import com.kdsc.protogen.parsetree.NamespaceNameNode;
import com.kdsc.protogen.parsetree.NamespaceNode;

import java.util.List;

public interface HasNamespaceName {

    NamespaceNameNode getNamespaceNameNode();

    List<NamespaceNode> getNamespaceNodes();

    NameNode getNameNode();

}