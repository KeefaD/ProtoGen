package com.kdsc.protogen.parsetree;

import java.util.List;

public class GenericParametersWithoutBoundsNode extends BaseParseTreeNode {

    private final List<GenericParameterWithoutBoundsNode> genericParameterWithoutBoundsNodes;

    public GenericParametersWithoutBoundsNode(
        long line,
        long charPosition,
        List<GenericParameterWithoutBoundsNode> genericParameterWithoutBoundsNodes
    ) {
        super(line, charPosition);
        this.genericParameterWithoutBoundsNodes = genericParameterWithoutBoundsNodes;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuffer = new StringBuffer();
        stringBuffer.append("//GenericParametersWithoutBoundsNode\n");
        if(null != genericParameterWithoutBoundsNodes) {
            genericParameterWithoutBoundsNodes.forEach(gpwb -> stringBuffer.append(gpwb.toFormattedString(1)));
        }
        var outputString = stringBuffer.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}