package com.kdsc.protogen.filegenerationtree;

import com.kdsc.protogen.nodes.BaseNode;

public abstract class BaseFileGenerationTreeNode extends BaseNode {

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, BaseFileGenerationTreeNode.class);
        return indentString(stringBuilder, indentationLevel);
    }

}