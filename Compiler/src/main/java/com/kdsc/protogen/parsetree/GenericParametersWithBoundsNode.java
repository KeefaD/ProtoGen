package com.kdsc.protogen.parsetree;

import java.util.List;

public class GenericParametersWithBoundsNode extends BaseNode {

    private final List<GenericParameterWithBoundsNode> genericParameterWithBoundsNodes;

    public GenericParametersWithBoundsNode(
        String sourceFileName,
        long line,
        long charPosition,
        List<GenericParameterWithBoundsNode> genericParameterWithBoundsNodes
    ) {
        super(sourceFileName, line, charPosition);
        this.genericParameterWithBoundsNodes = genericParameterWithBoundsNodes;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//GenericParametersWithBoundsNode\n");
        if(null != genericParameterWithBoundsNodes) {
            genericParameterWithBoundsNodes.forEach(gpwb -> stringBuilder.append(gpwb.toFormattedString(1)));
        }
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}