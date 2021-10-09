package com.kdsc.protogen.filegenerationtreenodes.shared.fieldtypenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;

public final class DateTimeFieldTypeNode extends FieldTypeNode {

    public DateTimeFieldTypeNode(
        final boolean isOptional
    ) {
        super(isOptional);
    }

    @Override
    public String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, DateTimeFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(formattedStringOptions, 0));
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

}