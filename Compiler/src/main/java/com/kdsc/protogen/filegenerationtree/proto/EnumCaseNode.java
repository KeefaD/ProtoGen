package com.kdsc.protogen.filegenerationtree.proto;

import com.kdsc.protogen.filegenerationtree.BaseFileGenerationTreeNode;

public class EnumCaseNode extends BaseFileGenerationTreeNode {

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
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, EnumCaseNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, "Name", name);
        return indentString(stringBuilder, indentationLevel);
    }

}