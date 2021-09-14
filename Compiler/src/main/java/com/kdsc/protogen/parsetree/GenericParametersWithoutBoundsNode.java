package com.kdsc.protogen.parsetree;

import java.util.List;

public class GenericParametersWithoutBoundsNode extends BaseParseTreeNode {

    private final List<GenericParameterWithoutBoundsNode> genericParameterWithoutBoundsNodes;

    public GenericParametersWithoutBoundsNode(
        String sourceFileName,
        long line,
        long charPosition,
        List<GenericParameterWithoutBoundsNode> genericParameterWithoutBoundsNodes
    ) {
        super(sourceFileName, line, charPosition);
        this.genericParameterWithoutBoundsNodes = genericParameterWithoutBoundsNodes;
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