package com.kdsc.protogen.parsetree.fieldtypenodes;

public class DatetimeFieldTypeNode extends NonArrayFieldTypeNode {

    public DatetimeFieldTypeNode(
        final String sourceFileName,
        final long line,
        final long charPosition
    ) {
        super(sourceFileName, line, charPosition);
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, DatetimeFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        return indentString(stringBuilder, indentationLevel);
    }

}