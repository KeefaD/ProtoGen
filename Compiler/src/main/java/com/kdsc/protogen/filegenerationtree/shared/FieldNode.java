package com.kdsc.protogen.filegenerationtree.shared;

import com.kdsc.protogen.filegenerationtree.BaseNode;
import com.kdsc.protogen.filegenerationtree.shared.fieldtypenodes.FieldTypeNode;

public class FieldNode extends BaseNode {

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
        stringBuilder.append("//FieldNode\n");
        stringBuilder.append(oneIndent() + "Name : " + name + "\n");
        stringBuilder.append(fieldTypeNode.toFormattedString(1));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}