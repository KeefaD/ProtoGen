package com.kdsc.protogen.filegenerationtree.shared.fieldtypenodes;

public class LocalDateTimeFieldTypeNode extends FieldTypeNode {

    public LocalDateTimeFieldTypeNode(
        final boolean isOptional
    ) {
        super(isOptional);
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, LocalDateTimeFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        return indentString(stringBuilder, indentationLevel);
    }

}