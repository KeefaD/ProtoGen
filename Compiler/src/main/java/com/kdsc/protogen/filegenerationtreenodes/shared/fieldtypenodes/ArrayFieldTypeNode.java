package com.kdsc.protogen.filegenerationtreenodes.shared.fieldtypenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;

import java.util.Objects;

public final class ArrayFieldTypeNode extends FieldTypeNode {

    private final FieldTypeNode fieldTypeNode;

    public ArrayFieldTypeNode(
        final boolean isOptional,
        final FieldTypeNode fieldTypeNode
    ) {
        super(isOptional);
        Objects.requireNonNull(fieldTypeNode);
        this.fieldTypeNode = fieldTypeNode;
    }

    public FieldTypeNode getFieldTypeNode() {
        return fieldTypeNode;
    }

    @Override
    public String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, ArrayFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(formattedStringOptions, 0));
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, fieldTypeNode);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

}