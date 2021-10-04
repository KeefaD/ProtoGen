package com.kdsc.protogen.filegenerationtree.java;

import com.kdsc.protogen.filegenerationtree.BaseFileGenerationTreeNode;

public class ImplementsNode extends BaseFileGenerationTreeNode {

    private final String packageName;
    private final String name;

    public ImplementsNode(
        final String packageName,
        final String name
    ) {
        //TODO:KMD Don't like the name name
        this.packageName = packageName;
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, ImplementsNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, "PackageName", name);
        fieldToFormattedStringField(stringBuilder, "Name", name);
        return indentString(stringBuilder, indentationLevel);
    }

}