package com.kdsc.protogen.parsetreenodes.fieldtypenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.parsetreenodes.BaseParseTreeNode;

public abstract class NonArrayFieldTypeNode extends BaseParseTreeNode {

    public NonArrayFieldTypeNode(
        final String sourceFileName,
        final long line,
        final long charPosition
    ) {
        super(sourceFileName, line, charPosition);
    }

    @Override
    public String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, NonArrayFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(formattedStringOptions, 0), BaseParseTreeNode.class);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

    public abstract NonArrayFieldTypeNode clone();

}