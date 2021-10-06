package com.kdsc.protogen.parsetree.fieldtypenodes;

public class BytesFieldTypeNode extends NonArrayFieldTypeNode {

    public BytesFieldTypeNode(
        final String sourceFileName,
        final long line,
        final long charPosition
    ) {
        super(sourceFileName, line, charPosition);
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, BytesFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        return indentString(stringBuilder, indentationLevel);
    }

    @Override
    public BytesFieldTypeNode clone() {
        return new BytesFieldTypeNode(
            getSourceFileName(),
            getLine(),
            getCharPosition()
        );
    }

}