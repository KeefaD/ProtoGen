package com.kdsc.protogen.parsetreenodes.fieldtypenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;

public final class BoolFieldTypeNode extends NonArrayFieldTypeNode {

    public BoolFieldTypeNode(
        final String sourceFileName,
        final long line,
        final long charPosition
    ) {
        super(sourceFileName, line, charPosition);
    }

    @Override
    public String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, BoolFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(formattedStringOptions, 0), NonArrayFieldTypeNode.class);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

    @Override
    public BoolFieldTypeNode clone() {
        return new BoolFieldTypeNode(
            getSourceFileName(),
            getLine(),
            getCharPosition()
        );
    }

}