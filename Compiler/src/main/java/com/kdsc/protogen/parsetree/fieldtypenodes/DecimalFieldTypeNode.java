package com.kdsc.protogen.parsetree.fieldtypenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;

public final class DecimalFieldTypeNode extends NonArrayFieldTypeNode {

    public DecimalFieldTypeNode(
        final String sourceFileName,
        final long line,
        final long charPosition
    ) {
        super(sourceFileName, line, charPosition);
    }

    @Override
    public String toFormattedString(final int indentationLevel, final FormattedStringOptions formattedStringOptions) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, DecimalFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(0, formattedStringOptions), NonArrayFieldTypeNode.class);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
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