package com.kdsc.protogen.parsetree.fieldtypenodes;

public class DecimalFieldTypeNode extends NonArrayFieldTypeNode {

    public DecimalFieldTypeNode(
        final String sourceFileName,
        final long line,
        final long charPosition
    ) {
        super(sourceFileName, line, charPosition);
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, DecimalFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        return indentString(stringBuilder, indentationLevel);
    }

    @Override
    public DecimalFieldTypeNode clone() {
        return new DecimalFieldTypeNode(
            getSourceFileName(),
            getLine(),
            getCharPosition()
        );
    }

}