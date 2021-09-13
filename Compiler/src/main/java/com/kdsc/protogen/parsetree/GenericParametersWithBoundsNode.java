package com.kdsc.protogen.parsetree;

import java.util.List;

public class GenericParametersWithBoundsNode extends BaseParseTreeNode {

    private final List<GenericParameterWithBoundsNode> genericParameterWithBoundsNodes;

    public GenericParametersWithBoundsNode(
        long line,
        long charPosition,
        List<GenericParameterWithBoundsNode> genericParameterWithBoundsNodes
    ) {
        super(line, charPosition);
        this.genericParameterWithBoundsNodes = genericParameterWithBoundsNodes;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuffer = new StringBuffer();
        stringBuffer.append("//GenericParametersWithBoundsNode\n");
        if(null != genericParameterWithBoundsNodes) {
            genericParameterWithBoundsNodes.forEach(gpwb -> stringBuffer.append(gpwb.toFormattedString(1)));
        }
        var outputString = stringBuffer.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}