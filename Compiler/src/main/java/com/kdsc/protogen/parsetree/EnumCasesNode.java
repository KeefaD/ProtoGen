package com.kdsc.protogen.parsetree;

import java.util.List;

public class EnumCasesNode extends BaseParseTreeNode {

    private final List<EnumNameNode> enumCasesNodes;

    public EnumCasesNode(
        String sourceFileName,
        long line,
        long charPosition,
        List<EnumNameNode> enumCasesNodes
    ) {
        super(sourceFileName, line, charPosition);
        this.enumCasesNodes = enumCasesNodes;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//EnumCasesNode\n");
        if(null != enumCasesNodes) {
            enumCasesNodes.forEach(ecn -> stringBuilder.append(ecn.toFormattedString(1)));
        }
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}