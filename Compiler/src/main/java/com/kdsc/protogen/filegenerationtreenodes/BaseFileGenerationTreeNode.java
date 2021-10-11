package com.kdsc.protogen.filegenerationtreenodes;

import com.kdsc.protogen.nodes.BaseNode;
import com.kdsc.protogen.nodes.FormattedStringOptions;

import static com.kdsc.protogen.filegenerationtreenodes.FileGenerationTreeFormattedStringOptions.defaultFileGenerationTreeFormattedStringOptions;

public abstract class BaseFileGenerationTreeNode extends BaseNode {

    @Override
    public String toFormattedString(final int indentationLevel) {
        return toFormattedString(defaultFileGenerationTreeFormattedStringOptions, indentationLevel);
    }

    @Override
    public String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, BaseFileGenerationTreeNode.class);
        return indentAndReturnString(stringBuilder, formattedStringOptions, indentationLevel);
    }

}