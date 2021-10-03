package com.kdsc.protogen.filegenerationtree.shared.fieldtypenodes;

import com.kdsc.protogen.filegenerationtree.BaseFileGenerationTreeNode;

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
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, FieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, "IsOptional", isOptional);
        return indentString(stringBuilder, indentationLevel);
    }

}