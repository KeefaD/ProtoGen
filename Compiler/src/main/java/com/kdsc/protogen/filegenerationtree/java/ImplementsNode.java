package com.kdsc.protogen.filegenerationtree.java;

import com.kdsc.protogen.filegenerationtree.BaseFileGenerationTreeNode;
import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.utils.parameterchecking.Strings;

public class ImplementsNode extends BaseFileGenerationTreeNode {

    private final String packageName;
    private final String name;

    public ImplementsNode(
        final String packageName,
        final String name
    ) {
        //TODO:KMD Don't like the name name
        Strings.requireNonBlank(packageName);
        Strings.requireNonBlank(name);
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
    public String toFormattedString(final int indentationLevel, final FormattedStringOptions formattedStringOptions) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, ImplementsNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(0, formattedStringOptions));
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "PackageName", name);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "Name", name);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

}