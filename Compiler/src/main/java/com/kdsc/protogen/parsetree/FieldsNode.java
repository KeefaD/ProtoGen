package com.kdsc.protogen.parsetree;

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

}