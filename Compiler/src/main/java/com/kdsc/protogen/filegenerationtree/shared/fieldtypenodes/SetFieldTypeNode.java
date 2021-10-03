package com.kdsc.protogen.filegenerationtree.shared.fieldtypenodes;

public class SetFieldTypeNode extends FieldTypeNode {

    private final FieldTypeNode fieldTypeNode;

    public SetFieldTypeNode(
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
        classToFormattedStringTitle(stringBuilder, SetFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, fieldTypeNode);
        return indentString(stringBuilder, indentationLevel);
    }

}