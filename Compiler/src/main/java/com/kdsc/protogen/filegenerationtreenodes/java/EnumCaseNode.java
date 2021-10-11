package com.kdsc.protogen.filegenerationtreenodes.java;

import com.kdsc.protogen.filegenerationtreenodes.BaseFileGenerationTreeNode;
import com.kdsc.protogen.nodes.FormattedStringOptions;

public final class EnumCaseNode extends BaseFileGenerationTreeNode {

    private final String name;

    public EnumCaseNode(
        final String name
    ) {
        //TODO:KMD Don't like the name name
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, EnumCaseNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(formattedStringOptions, 0));
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "Name", name);
        return indentAndReturnString(stringBuilder, formattedStringOptions, indentationLevel);
    }

}