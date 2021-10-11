package com.kdsc.protogen.filegenerationtreenodes.shared.fieldtypenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;

import java.util.Objects;

public final class MapFieldTypeNode extends FieldTypeNode {

    private final FieldTypeNode keyFieldTypeNode;
    private final FieldTypeNode valueFieldTypeNode;

    public MapFieldTypeNode(
        final boolean isOptional,
        final FieldTypeNode keyFieldTypeNode,
        final FieldTypeNode valueFieldTypeNode
    ) {
        super(isOptional);
        Objects.requireNonNull(keyFieldTypeNode);
        Objects.requireNonNull(valueFieldTypeNode);
        this.keyFieldTypeNode = keyFieldTypeNode;
        this.valueFieldTypeNode = valueFieldTypeNode;
    }

    public FieldTypeNode getKeyFieldTypeNode() {
        return keyFieldTypeNode;
    }

    public FieldTypeNode getValueFieldTypeNode() {
        return valueFieldTypeNode;
    }

    @Override
    public String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, MapFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(formattedStringOptions, 0));
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, keyFieldTypeNode);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, valueFieldTypeNode);
        return indentAndReturnString(stringBuilder, formattedStringOptions, indentationLevel);
    }

}