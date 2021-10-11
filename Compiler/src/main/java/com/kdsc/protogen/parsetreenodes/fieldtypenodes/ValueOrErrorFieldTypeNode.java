package com.kdsc.protogen.parsetreenodes.fieldtypenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;

import java.util.Objects;

public final class ValueOrErrorFieldTypeNode extends NonArrayFieldTypeNode {

    private final FieldTypeNode fieldTypeNode;

    public ValueOrErrorFieldTypeNode(
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
    public String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, ValueOrErrorFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(formattedStringOptions, 0), NonArrayFieldTypeNode.class);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, fieldTypeNode);
        return indentAndReturnString(stringBuilder, formattedStringOptions, indentationLevel);
    }

    @Override
    public ValueOrErrorFieldTypeNode clone() {
        return new ValueOrErrorFieldTypeNode(
            getSourceFileName(),
            getLine(),
            getCharPosition(),
            fieldTypeNode.clone()
        );
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        ValueOrErrorFieldTypeNode that = (ValueOrErrorFieldTypeNode) object;
        return fieldTypeNode.equals(that.fieldTypeNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fieldTypeNode);
    }

}