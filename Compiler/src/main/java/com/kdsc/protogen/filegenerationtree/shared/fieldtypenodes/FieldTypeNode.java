package com.kdsc.protogen.filegenerationtree.shared.fieldtypenodes;

import com.kdsc.protogen.filegenerationtree.BaseNode;

public abstract class FieldTypeNode extends BaseNode {

    private final boolean isOptional;

    public FieldTypeNode(boolean isOptional) {
        this.isOptional = isOptional;
    }

    public boolean isOptional() {
        return isOptional;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//FieldTypeNode\n");
        stringBuilder.append(oneIndent() + "IsOptional : " + isOptional + "\n");
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}