package com.kdsc.protogen.parsetree.fieldtypenodes;

import java.util.Objects;

public class ValueOrErrorFieldTypeNode extends NonArrayFieldTypeNode {

    private final FieldTypeNode entryFieldTypeNode;

    public ValueOrErrorFieldTypeNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final FieldTypeNode entryFieldTypeNode
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(entryFieldTypeNode);
        this.entryFieldTypeNode = entryFieldTypeNode;
    }

    public FieldTypeNode getEntryFieldTypeNode() {
        return entryFieldTypeNode;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//ValueOrErrorFieldTypeNode\n");
        stringBuilder.append(oneIndent() + "//Entry\n");
        stringBuilder.append(entryFieldTypeNode.toFormattedString(2));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}