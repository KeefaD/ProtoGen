package com.kdsc.protogen.filegenerationtree.shared.fieldtypenodes;

public class DecimalFieldTypeNode extends FieldTypeNode {

    public DecimalFieldTypeNode(
        final boolean isOptional
    ) {
        super(isOptional);
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, DecimalFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        return indentString(stringBuilder, indentationLevel);
    }

}