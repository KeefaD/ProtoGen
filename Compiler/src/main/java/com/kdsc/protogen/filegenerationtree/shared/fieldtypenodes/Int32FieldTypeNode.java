package com.kdsc.protogen.filegenerationtree.shared.fieldtypenodes;

public class Int32FieldTypeNode extends FieldTypeNode {

    public Int32FieldTypeNode(
        final boolean isOptional
    ) {
        super(isOptional);
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//Int32FieldTypeNode\n");
        stringBuilder.append(super.toFormattedString(1));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}