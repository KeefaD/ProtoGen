package com.kdsc.protogen.parsetree.fieldtypenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;

import java.util.Objects;

public final class SetFieldTypeNode extends NonArrayFieldTypeNode {

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
    public String toFormattedString(final int indentationLevel, final FormattedStringOptions formattedStringOptions) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, SetFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(0, formattedStringOptions), NonArrayFieldTypeNode.class);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, fieldTypeNode);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

    @Override
    public SetFieldTypeNode clone() {
        return new SetFieldTypeNode(
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
        SetFieldTypeNode that = (SetFieldTypeNode) object;
        return fieldTypeNode.equals(that.fieldTypeNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fieldTypeNode);
    }

}