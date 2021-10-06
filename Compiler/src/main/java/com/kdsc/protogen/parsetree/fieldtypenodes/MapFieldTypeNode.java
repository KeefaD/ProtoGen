package com.kdsc.protogen.parsetree.fieldtypenodes;

import java.util.Objects;

public class MapFieldTypeNode extends NonArrayFieldTypeNode {

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
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, MapFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, "Key", keyFieldTypeNode);
        fieldToFormattedStringField(stringBuilder, "Value", valueFieldTypeNode);
        return indentString(stringBuilder, indentationLevel);
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
    public boolean equals(Object object) {
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