package com.kdsc.protogen.parsetree.fieldtypenodes;

public class Int32FieldTypeNode extends NonArrayFieldTypeNode {

    public Int32FieldTypeNode(
        final String sourceFileName,
        final long line,
        final long charPosition
    ) {
        super(sourceFileName, line, charPosition);
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, Int32FieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        return indentString(stringBuilder, indentationLevel);
    }

    @Override
    public Int32FieldTypeNode clone() {
        return new Int32FieldTypeNode(
            getSourceFileName(),
            getLine(),
            getCharPosition()
        );
    }

}