package com.kdsc.protogen.parsetreenodes.fieldtypenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;

public final class FloatFieldTypeNode extends NonArrayFieldTypeNode {

    public FloatFieldTypeNode(
        final String sourceFileName,
        final long line,
        final long charPosition
    ) {
        super(sourceFileName, line, charPosition);
    }

    @Override
    public String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, FloatFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(formattedStringOptions, 0), NonArrayFieldTypeNode.class);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
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