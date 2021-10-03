package com.kdsc.protogen.filegenerationtree.shared.fieldtypenodes;

public class MapFieldTypeNode extends FieldTypeNode {

    private final FieldTypeNode keyFieldTypeNode;
    private final FieldTypeNode valueFieldTypeNode;

    public MapFieldTypeNode(
        final boolean isOptional,
        final FieldTypeNode keyFieldTypeNode,
        final FieldTypeNode valueFieldTypeNode
    ) {
        super(isOptional);
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
        fieldToFormattedStringField(stringBuilder, keyFieldTypeNode);
        fieldToFormattedStringField(stringBuilder, valueFieldTypeNode);
        return indentString(stringBuilder, indentationLevel);
    }

}