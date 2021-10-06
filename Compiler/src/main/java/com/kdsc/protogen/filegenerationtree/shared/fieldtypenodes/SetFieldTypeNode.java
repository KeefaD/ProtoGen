package com.kdsc.protogen.filegenerationtree.shared.fieldtypenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;

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
    public String toFormattedString(final int indentationLevel, final FormattedStringOptions formattedStringOptions) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, SetFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(0, formattedStringOptions));
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, fieldTypeNode);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

}