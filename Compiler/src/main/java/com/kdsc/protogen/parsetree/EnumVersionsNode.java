package com.kdsc.protogen.parsetree;

import java.util.List;

public class EnumVersionsNode extends BaseParseTreeNode {

    private final List<EnumVersionNode> enumVersionNode;

    public EnumVersionsNode(
        String sourceFileName,
        long line,
        long charPosition,
        List<EnumVersionNode> enumVersionNode
    ) {
        super(sourceFileName, line, charPosition);
        this.enumVersionNode = enumVersionNode;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//EnumVersionsNode\n");
        if(null != enumVersionNode) {
            enumVersionNode.forEach(evn -> stringBuilder.append(evn.toFormattedString(1)));
        }
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}