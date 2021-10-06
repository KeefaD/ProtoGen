package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.parsetree.utils.clone.Lists;

import java.util.List;
import java.util.Objects;

public class FieldsNode extends BaseParseTreeNode {

    private final List<FieldNode> fieldNodes;

    public FieldsNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final List<FieldNode> fieldNodes
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(fieldNodes);
        this.fieldNodes = fieldNodes;
    }

    public List<FieldNode> getFieldNodes() {
        return fieldNodes;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, FieldsNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, fieldNodes);
        return indentString(stringBuilder, indentationLevel);
    }

    @Override
    public FieldsNode clone() {
        return new FieldsNode(
            getSourceFileName(),
            getLine(),
            getCharPosition(),
            Lists.clone(fieldNodes)
        );
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        FieldsNode that = (FieldsNode) object;
        return fieldNodes.equals(that.fieldNodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fieldNodes);
    }

}