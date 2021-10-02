package com.kdsc.protogen.filegenerationtree.shared.fieldtypenodes;

public class BoolFieldTypeNode extends FieldTypeNode {

    public BoolFieldTypeNode(
        final boolean isOptional
    ) {
        super(isOptional);
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//BoolFieldTypeNode\n");
        stringBuilder.append(super.toFormattedString(1));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }

}