package com.kdsc.protogen.parsetree.fieldtypenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;

public class DateTimeFieldTypeNode extends NonArrayFieldTypeNode {

    public DateTimeFieldTypeNode(
        final String sourceFileName,
        final long line,
        final long charPosition
    ) {
        super(sourceFileName, line, charPosition);
    }

    @Override
    public String toFormattedString(final int indentationLevel, final FormattedStringOptions formattedStringOptions) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, DateTimeFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(0, formattedStringOptions));
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

    @Override
    public DateTimeFieldTypeNode clone() {
        return new DateTimeFieldTypeNode(
            getSourceFileName(),
            getLine(),
            getCharPosition()
        );
    }

}