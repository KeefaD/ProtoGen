package com.kdsc.protogen.parsetree.fieldtypenodes;

import com.kdsc.protogen.parsetree.NamespaceNameGenericParametersNode;

import java.util.Objects;

public class TypeFieldTypeNode extends NonArrayFieldTypeNode {

    private final NamespaceNameGenericParametersNode namespaceNameGenericParametersNode;

    public TypeFieldTypeNode(
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

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, TypeFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, namespaceNameGenericParametersNode);
        return indentString(stringBuilder, indentationLevel);
    }

}