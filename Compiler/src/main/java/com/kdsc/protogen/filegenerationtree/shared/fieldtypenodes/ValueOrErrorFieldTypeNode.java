package com.kdsc.protogen.filegenerationtree.shared.fieldtypenodes;

public class ValueOrErrorFieldTypeNode extends FieldTypeNode {

    private final FieldTypeNode fieldTypeNode;

    public ValueOrErrorFieldTypeNode(
        final boolean isOptional,
        final FieldTypeNode fieldTypeNode
    ) {
        super(isOptional);
        this.fieldTypeNode = fieldTypeNode;
    }

    public FieldTypeNode getFieldTypeNode() {
        return fieldTypeNode;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, ValueOrErrorFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, fieldTypeNode);
        return indentString(stringBuilder, indentationLevel);
    }

}