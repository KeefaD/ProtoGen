package com.kdsc.protogen.parsetreenodes.fieldtypenodes;

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
    public String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, DecimalFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(formattedStringOptions, 0), NonArrayFieldTypeNode.class);
        return indentAndReturnString(stringBuilder, formattedStringOptions, indentationLevel);
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