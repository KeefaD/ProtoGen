package com.kdsc.protogen.parsetree.fieldtypenodes;

public class LocalDateTimeFieldTypeNode extends NonArrayFieldTypeNode {

    public LocalDateTimeFieldTypeNode(
        final String sourceFileName,
        final long line,
        final long charPosition
    ) {
        super(sourceFileName, line, charPosition);
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, LocalDateTimeFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        return indentString(stringBuilder, indentationLevel);
    }

}