package com.kdsc.protogen.filegenerationtree.shared.fieldtypenodes;

import com.kdsc.protogen.filegenerationtree.BaseFileGenerationTreeNode;
import com.kdsc.protogen.nodes.FormattedStringOptions;

public abstract class FieldTypeNode extends BaseFileGenerationTreeNode {

    private final boolean isOptional;

    public FieldTypeNode(
        boolean isOptional
    ) {
        this.isOptional = isOptional;
    }

    public boolean isOptional() {
        return isOptional;
    }

    @Override
    public String toFormattedString(final int indentationLevel, final FormattedStringOptions formattedStringOptions) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, FieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(0, formattedStringOptions));
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "IsOptional", isOptional);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

}