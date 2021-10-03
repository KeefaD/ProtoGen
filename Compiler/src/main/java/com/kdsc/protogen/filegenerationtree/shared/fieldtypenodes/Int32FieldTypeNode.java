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
        classToFormattedStringTitle(stringBuilder, Int32FieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        return indentString(stringBuilder, indentationLevel);
    }

}