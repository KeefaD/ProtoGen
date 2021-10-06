package com.kdsc.protogen.filegenerationtree.shared.fieldtypenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;

public class DateFieldTypeNode extends FieldTypeNode {

    public DateFieldTypeNode(
        final boolean isOptional
    ) {
        super(isOptional);
    }

    @Override
    public String toFormattedString(final int indentationLevel, final FormattedStringOptions formattedStringOptions) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, DateFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(0, formattedStringOptions));
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

}