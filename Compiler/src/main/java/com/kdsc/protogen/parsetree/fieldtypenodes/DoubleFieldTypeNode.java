package com.kdsc.protogen.parsetree.fieldtypenodes;

public class DoubleFieldTypeNode extends NonArrayFieldTypeNode {

    public DoubleFieldTypeNode(
        final String sourceFileName,
        final long line,
        final long charPosition
    ) {
        super(sourceFileName, line, charPosition);
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, DoubleFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        return indentString(stringBuilder, indentationLevel);
    }

    @Override
    public DoubleFieldTypeNode clone() {
        return new DoubleFieldTypeNode(
            getSourceFileName(),
            getLine(),
            getCharPosition()
        );
    }

}