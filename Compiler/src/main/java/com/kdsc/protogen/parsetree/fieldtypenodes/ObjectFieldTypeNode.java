package com.kdsc.protogen.parsetree.fieldtypenodes;

import com.kdsc.protogen.parsetree.NamespaceNameGenericParametersNode;
import com.kdsc.protogen.parsetree.NamespaceNameNode;
import com.kdsc.protogen.parsetree.commoninterfaces.HasNamespaceName;

import java.util.Objects;

public class ObjectFieldTypeNode extends NonArrayFieldTypeNode implements HasNamespaceName {

    private final NamespaceNameGenericParametersNode namespaceNameGenericParametersNode;

    public ObjectFieldTypeNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final NamespaceNameGenericParametersNode namespaceNameGenericParametersNode
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(namespaceNameGenericParametersNode);
        this.namespaceNameGenericParametersNode = namespaceNameGenericParametersNode;
    }

    public NamespaceNameGenericParametersNode getNamespaceNameGenericParametersNode() {
        return namespaceNameGenericParametersNode;
    }

    public NamespaceNameNode getNamespaceNameNode() {
        return namespaceNameGenericParametersNode.getNamespaceNameNode();
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, ObjectFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, namespaceNameGenericParametersNode);
        return indentString(stringBuilder, indentationLevel);
    }

}