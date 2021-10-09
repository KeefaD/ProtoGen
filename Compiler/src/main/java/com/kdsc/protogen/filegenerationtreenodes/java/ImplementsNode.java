package com.kdsc.protogen.filegenerationtreenodes.java;

import com.kdsc.protogen.filegenerationtreenodes.BaseFileGenerationTreeNode;
import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.utils.parameterchecking.Strings;

public final class ImplementsNode extends BaseFileGenerationTreeNode {

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
    public String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, ImplementsNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(formattedStringOptions, 0));
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "PackageName", name);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "Name", name);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

}