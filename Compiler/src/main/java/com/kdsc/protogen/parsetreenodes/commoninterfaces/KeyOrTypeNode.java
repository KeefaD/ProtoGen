package com.kdsc.protogen.parsetreenodes.commoninterfaces;

import com.kdsc.protogen.parsetreenodes.*;

import java.util.List;
import java.util.Optional;

public interface KeyOrTypeNode {

    boolean isInterface();

    NamespaceNameGenericParametersWithBoundsNode getNamespaceNameGenericParametersWithBoundsNode();

    Optional<ImplementsListNode> getImplementsListNode();

    Optional<VersionsNode> getVersionsNode();

    Optional<FieldsNode> getFieldsNode();

    NamespaceNameNode getNamespaceNameNode();

    List<NamespaceNode> getNamespaceNodes();

    NameNode getNameNode();

}
