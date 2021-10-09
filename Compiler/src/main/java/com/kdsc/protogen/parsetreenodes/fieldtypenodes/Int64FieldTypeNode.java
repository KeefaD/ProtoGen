package com.kdsc.protogen.parsetreenodes.fieldtypenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;

public final class Int64FieldTypeNode extends NonArrayFieldTypeNode {

    public Int64FieldTypeNode(
        final String sourceFileName,
        final long line,
        final long charPosition
    ) {
        super(sourceFileName, line, charPosition);
    }

    @Override
    public String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, Int64FieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(formattedStringOptions, 0), NonArrayFieldTypeNode.class);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

    @Override
    public Int64FieldTypeNode clone() {
        return new Int64FieldTypeNode(
            getSourceFileName(),
            getLine(),
            getCharPosition()
        );
    }

}