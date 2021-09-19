package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.parsetree.fieldtypenodes.FieldTypeNode;

import java.util.Objects;

public class FieldNode extends BaseNode {

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
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//FieldNode\n");
        stringBuilder.append(fieldNameNode.toFormattedString(1));
        stringBuilder.append(fieldTypeNode.toFormattedString(1));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}