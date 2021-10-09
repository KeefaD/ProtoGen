package com.kdsc.protogen.filegenerationtreenodes.shared.fieldtypenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;

public final class DecimalFieldTypeNode extends FieldTypeNode {

    public DecimalFieldTypeNode(
        final boolean isOptional
    ) {
        super(isOptional);
    }

    @Override
    public String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, DecimalFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(formattedStringOptions, 0));
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

}