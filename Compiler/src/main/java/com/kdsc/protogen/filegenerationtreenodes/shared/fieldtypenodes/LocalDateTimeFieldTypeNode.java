package com.kdsc.protogen.filegenerationtreenodes.shared.fieldtypenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;

public final class LocalDateTimeFieldTypeNode extends FieldTypeNode {

    public LocalDateTimeFieldTypeNode(
        final boolean isOptional
    ) {
        super(isOptional);
    }

    @Override
    public String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, LocalDateTimeFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(formattedStringOptions, 0));
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

}