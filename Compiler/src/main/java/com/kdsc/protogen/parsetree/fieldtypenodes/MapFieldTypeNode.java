package com.kdsc.protogen.parsetree.fieldtypenodes;

import java.util.Objects;

public class MapFieldTypeNode extends NonArrayFieldTypeNode {

    private final FieldTypeNode keyFieldTypeNode;
    private final FieldTypeNode valueFieldTypeNode;

    public MapFieldTypeNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final FieldTypeNode keyFieldTypeNode,
        final FieldTypeNode valueFieldTypeNode
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(keyFieldTypeNode);
        Objects.requireNonNull(valueFieldTypeNode);
        this.keyFieldTypeNode = keyFieldTypeNode;
        this.valueFieldTypeNode = valueFieldTypeNode;
    }

    public FieldTypeNode getKeyFieldTypeNode() {
        return keyFieldTypeNode;
    }

    public FieldTypeNode getValueFieldTypeNode() {
        return valueFieldTypeNode;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//MapFieldTypeNode\n");
        stringBuilder.append(oneIndent() + "//Key\n");
        stringBuilder.append(keyFieldTypeNode.toFormattedString(2));
        stringBuilder.append(oneIndent() + "//Value\n");
        stringBuilder.append(valueFieldTypeNode.toFormattedString(2));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }

}