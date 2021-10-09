package com.kdsc.protogen.filegenerationtreenodes.shared.fieldtypenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;

public final class StringFieldTypeNode extends FieldTypeNode {

    public StringFieldTypeNode(
        final boolean isOptional
    ) {
        super(isOptional);
    }

    @Override
    public String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, StringFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(formattedStringOptions, 0));
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

}