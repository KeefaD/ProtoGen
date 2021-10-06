package com.kdsc.protogen.filegenerationtree;

import com.kdsc.protogen.nodes.BaseNode;
import com.kdsc.protogen.nodes.FormattedStringOptions;

import static com.kdsc.protogen.filegenerationtree.FileGenerationTreeFormattedStringOptions.defaultFileGenerationTreeFormattedStringOptions;

public abstract class BaseFileGenerationTreeNode extends BaseNode {

    @Override
    public String toFormattedString(final int indentationLevel) {
        return toFormattedString(indentationLevel, defaultFileGenerationTreeFormattedStringOptions);
    }

    @Override
    public String toFormattedString(final int indentationLevel, final FormattedStringOptions formattedStringOptions) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, BaseFileGenerationTreeNode.class);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

}