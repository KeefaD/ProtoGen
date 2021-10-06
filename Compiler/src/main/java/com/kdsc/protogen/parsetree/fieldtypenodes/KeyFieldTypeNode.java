package com.kdsc.protogen.parsetree.fieldtypenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.parsetree.NamespaceNameGenericParametersNode;

import java.util.Objects;

public class KeyFieldTypeNode extends NonArrayFieldTypeNode {

    private final NamespaceNameGenericParametersNode namespaceNameGenericParametersNode;

    public KeyFieldTypeNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        NamespaceNameGenericParametersNode namespaceNameGenericParametersNode
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(namespaceNameGenericParametersNode);
        this.namespaceNameGenericParametersNode = namespaceNameGenericParametersNode;
    }

    public NamespaceNameGenericParametersNode getNamespaceNameGenericParametersNode() {
        return namespaceNameGenericParametersNode;
    }

    @Override
    public String toFormattedString(final int indentationLevel, final FormattedStringOptions formattedStringOptions) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, KeyFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(0, formattedStringOptions));
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, namespaceNameGenericParametersNode);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

    @Override
    public KeyFieldTypeNode clone() {
        return new KeyFieldTypeNode(
            getSourceFileName(),
            getLine(),
            getCharPosition(),
            namespaceNameGenericParametersNode.clone()
        );
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        KeyFieldTypeNode that = (KeyFieldTypeNode) object;
        return namespaceNameGenericParametersNode.equals(that.namespaceNameGenericParametersNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), namespaceNameGenericParametersNode);
    }

}