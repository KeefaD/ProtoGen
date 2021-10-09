package com.kdsc.protogen.filegenerationtreenodes.shared;

import com.kdsc.protogen.filegenerationtreenodes.BaseFileGenerationTreeNode;
import com.kdsc.protogen.filegenerationtreenodes.shared.fieldtypenodes.FieldTypeNode;
import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.utils.parameterchecking.Strings;

import java.util.Objects;

public final class FieldNode extends BaseFileGenerationTreeNode {

    private final String name;
    private final FieldTypeNode fieldTypeNode;

    public FieldNode(
        final String name,
        final FieldTypeNode fieldTypeNode
    ) {
        Strings.requireNonBlank(name);
        Objects.requireNonNull(fieldTypeNode);
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
    public String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, FieldNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(formattedStringOptions, 0));
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "Name", name);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, fieldTypeNode);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

}