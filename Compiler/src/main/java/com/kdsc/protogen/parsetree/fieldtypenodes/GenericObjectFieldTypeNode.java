package com.kdsc.protogen.parsetree.fieldtypenodes;

import com.kdsc.protogen.parsetree.GenericParameterWithoutBoundsNode;

import java.util.Objects;

public class GenericObjectFieldTypeNode extends NonArrayFieldTypeNode {

    private final GenericParameterWithoutBoundsNode genericParameterWithoutBoundsNode;

    public GenericObjectFieldTypeNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final GenericParameterWithoutBoundsNode genericParameterWithoutBoundsNode
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(genericParameterWithoutBoundsNode);
        this.genericParameterWithoutBoundsNode = genericParameterWithoutBoundsNode;
    }

    public GenericParameterWithoutBoundsNode getGenericParameterWithoutBoundsNode() {
        return genericParameterWithoutBoundsNode;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//GenericObjectFieldTypeNode\n");
        stringBuilder.append(genericParameterWithoutBoundsNode.toFormattedString(1));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}