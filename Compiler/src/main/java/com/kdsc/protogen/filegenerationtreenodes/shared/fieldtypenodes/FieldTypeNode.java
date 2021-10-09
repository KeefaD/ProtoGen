package com.kdsc.protogen.filegenerationtreenodes.shared.fieldtypenodes;

import com.kdsc.protogen.filegenerationtreenodes.BaseFileGenerationTreeNode;
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
    public String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, FieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(formattedStringOptions, 0));
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "IsOptional", isOptional);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

}