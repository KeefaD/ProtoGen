package com.kdsc.protogen.parsetree.fieldtypenodes;

public class FloatFieldTypeNode extends NonArrayFieldTypeNode {

    public FloatFieldTypeNode(
        final String sourceFileName,
        final long line,
        final long charPosition
    ) {
        super(sourceFileName, line, charPosition);
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, FloatFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        return indentString(stringBuilder, indentationLevel);
    }

    @Override
    public FloatFieldTypeNode clone() {
        return new FloatFieldTypeNode(
            getSourceFileName(),
            getLine(),
            getCharPosition()
        );
    }

}