package com.kdsc.protogen.parsetree;

import java.util.List;
import java.util.Objects;

public class GenericParametersWithBoundsNode extends BaseNode {

    private final List<GenericParameterWithBoundsNode> genericParameterWithBoundsNodes;

    public GenericParametersWithBoundsNode(
        String sourceFileName,
        long line,
        long charPosition,
        List<GenericParameterWithBoundsNode> genericParameterWithBoundsNodes
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(genericParameterWithBoundsNodes);
        this.genericParameterWithBoundsNodes = genericParameterWithBoundsNodes;
    }

    public List<GenericParameterWithBoundsNode> getGenericParameterWithBoundsNodes() {
        return genericParameterWithBoundsNodes;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//GenericParametersWithBoundsNode\n");
        genericParameterWithBoundsNodes.forEach(gpwb -> stringBuilder.append(gpwb.toFormattedString(1)));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}