package com.kdsc.protogen.parsetreenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.parsetreenodes.fieldtypenodes.FieldTypeNode;

import java.util.Objects;

public final class FieldNode extends BaseParseTreeNode {

    private final FieldNameNode fieldNameNode;
    private final FieldTypeNode fieldTypeNode;

    public FieldNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final FieldNameNode fieldNameNode,
        final FieldTypeNode fieldTypeNode
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(fieldNameNode);
        Objects.requireNonNull(fieldTypeNode);
        this.fieldNameNode = fieldNameNode;
        this.fieldTypeNode = fieldTypeNode;
    }

    public FieldNameNode getFieldNameNode() {
        return fieldNameNode;
    }

    public FieldTypeNode getFieldTypeNode() {
        return fieldTypeNode;
    }

    @Override
    public String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, FieldNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(formattedStringOptions, 0), BaseParseTreeNode.class);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, fieldNameNode);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, fieldTypeNode);
        return indentAndReturnString(stringBuilder, formattedStringOptions, indentationLevel);
    }

    @Override
    public FieldNode clone() {
        return new FieldNode(
            getSourceFileName(),
            getLine(),
            getCharPosition(),
            fieldNameNode.clone(),
            fieldTypeNode.clone()
        );
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        FieldNode fieldNode = (FieldNode) object;
        return fieldNameNode.equals(fieldNode.fieldNameNode) && fieldTypeNode.equals(fieldNode.fieldTypeNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fieldNameNode, fieldTypeNode);
    }

}