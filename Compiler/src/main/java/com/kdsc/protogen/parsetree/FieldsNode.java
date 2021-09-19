package com.kdsc.protogen.parsetree;

import java.util.List;
import java.util.Objects;

public class FieldsNode extends BaseNode {

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
        stringBuilder.append("//FieldsNode\n");
        fieldNodes.forEach(fn -> stringBuilder.append(fn.toFormattedString(1)));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}