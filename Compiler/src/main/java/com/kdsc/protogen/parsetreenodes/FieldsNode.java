package com.kdsc.protogen.parsetreenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.parsetreenodes.utils.clone.Lists;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class FieldsNode extends BaseParseTreeNode {

    private final List<FieldNode> fieldNodes;

    public FieldsNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final List<FieldNode> fieldNodes
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(fieldNodes);
        com.kdsc.protogen.utils.parameterchecking.Lists.requireAtLeastOne(fieldNodes);
        this.fieldNodes = Collections.unmodifiableList(fieldNodes);
    }

    public List<FieldNode> getFieldNodes() {
        return fieldNodes;
    }

    @Override
    public String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, FieldsNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(formattedStringOptions, 0), BaseParseTreeNode.class);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, fieldNodes);
        return indentAndReturnString(stringBuilder, formattedStringOptions, indentationLevel);
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
    public boolean equals(final Object object) {
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