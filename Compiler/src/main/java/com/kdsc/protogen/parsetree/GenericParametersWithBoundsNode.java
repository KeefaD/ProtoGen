package com.kdsc.protogen.parsetree;

import java.util.List;
import java.util.Objects;

public class GenericParametersWithBoundsNode extends BaseParseTreeNode {

    private final List<GenericParameterWithBoundsNode> genericParameterWithBoundsNodes;

    public GenericParametersWithBoundsNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final List<GenericParameterWithBoundsNode> genericParameterWithBoundsNodes
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(genericParameterWithBoundsNodes);
        this.genericParameterWithBoundsNodes = genericParameterWithBoundsNodes;
    }

    public List<GenericParameterWithBoundsNode> getGenericParameterWithBoundsNodes() {
        return genericParameterWithBoundsNodes;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, GenericParametersWithBoundsNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, genericParameterWithBoundsNodes);
        return indentString(stringBuilder, indentationLevel);
    }

}