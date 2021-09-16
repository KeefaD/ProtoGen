package com.kdsc.protogen.parsetree;

import java.util.List;
import java.util.Objects;

public class GenericParametersWithoutBoundsNode extends BaseNode {

    private final List<GenericParameterWithoutBoundsNode> genericParameterWithoutBoundsNodes;

    public GenericParametersWithoutBoundsNode(
        String sourceFileName,
        long line,
        long charPosition,
        List<GenericParameterWithoutBoundsNode> genericParameterWithoutBoundsNodes
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(genericParameterWithoutBoundsNodes);
        this.genericParameterWithoutBoundsNodes = genericParameterWithoutBoundsNodes;
    }

    public List<GenericParameterWithoutBoundsNode> getGenericParameterWithoutBoundsNodes() {
        return genericParameterWithoutBoundsNodes;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//GenericParametersWithoutBoundsNode\n");
        if(null != genericParameterWithoutBoundsNodes) {
            genericParameterWithoutBoundsNodes.forEach(gpwb -> stringBuilder.append(gpwb.toFormattedString(1)));
        }
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}