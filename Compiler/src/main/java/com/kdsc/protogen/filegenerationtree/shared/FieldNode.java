package com.kdsc.protogen.filegenerationtree.shared;

import com.kdsc.protogen.filegenerationtree.BaseFileGenerationTreeNode;
import com.kdsc.protogen.filegenerationtree.shared.fieldtypenodes.FieldTypeNode;

public class FieldNode extends BaseFileGenerationTreeNode {

    private final String name;
    private final FieldTypeNode fieldTypeNode;

    public FieldNode(
        final String name,
        final FieldTypeNode fieldTypeNode
    ) {
        this.name = name;
        this.fieldTypeNode = fieldTypeNode;
    }

    public String getName() {
        return name;
    }

    public FieldTypeNode getFieldTypeNode() {
        return fieldTypeNode;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, FieldNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, "Name", name);
        fieldToFormattedStringField(stringBuilder, fieldTypeNode);
        return indentString(stringBuilder, indentationLevel);
    }
}