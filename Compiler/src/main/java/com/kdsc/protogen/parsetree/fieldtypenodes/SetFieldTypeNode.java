package com.kdsc.protogen.parsetree.fieldtypenodes;

import java.util.Objects;

public class SetFieldTypeNode extends NonArrayFieldTypeNode {

    private final FieldTypeNode fieldTypeNode;

    public SetFieldTypeNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final FieldTypeNode fieldTypeNode
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(fieldTypeNode);
        this.fieldTypeNode = fieldTypeNode;
    }

    public FieldTypeNode getFieldTypeNode() {
        return fieldTypeNode;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, SetFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, fieldTypeNode);
        return indentString(stringBuilder, indentationLevel);
    }

}