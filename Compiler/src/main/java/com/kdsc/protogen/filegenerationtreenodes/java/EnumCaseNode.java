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
    public String toFormattedString(final int indentationLevel, final FormattedStringOptions formattedStringOptions) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, EnumCaseNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(0, formattedStringOptions));
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "Name", name);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

}