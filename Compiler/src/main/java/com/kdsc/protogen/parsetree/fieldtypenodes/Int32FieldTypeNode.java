package com.kdsc.protogen.parsetree.fieldtypenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;

public final class Int32FieldTypeNode extends NonArrayFieldTypeNode {

    public Int32FieldTypeNode(
        final String sourceFileName,
        final long line,
        final long charPosition
    ) {
        super(sourceFileName, line, charPosition);
    }

    @Override
    public String toFormattedString(final int indentationLevel, final FormattedStringOptions formattedStringOptions) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, Int32FieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(0, formattedStringOptions), NonArrayFieldTypeNode.class);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
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