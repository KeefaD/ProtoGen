package com.kdsc.protogen.parsetreenodes.fieldtypenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;

import java.util.Objects;

public final class MapFieldTypeNode extends NonArrayFieldTypeNode {

    private final FieldTypeNode keyFieldTypeNode;
    private final FieldTypeNode valueFieldTypeNode;

    public MapFieldTypeNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final FieldTypeNode keyFieldTypeNode,
        final FieldTypeNode valueFieldTypeNode
    ) {
        super(sourceFileName, line, charPosition);
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
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(formattedStringOptions, 0), NonArrayFieldTypeNode.class);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "Key", keyFieldTypeNode);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "Value", valueFieldTypeNode);
        return indentAndReturnString(stringBuilder, formattedStringOptions, indentationLevel);
    }

    @Override
    public MapFieldTypeNode clone() {
        return new MapFieldTypeNode(
            getSourceFileName(),
            getLine(),
            getCharPosition(),
            keyFieldTypeNode.clone(),
            valueFieldTypeNode.clone()
        );
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        MapFieldTypeNode that = (MapFieldTypeNode) object;
        return keyFieldTypeNode.equals(that.keyFieldTypeNode) && valueFieldTypeNode.equals(that.valueFieldTypeNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), keyFieldTypeNode, valueFieldTypeNode);
    }

}