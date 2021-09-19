package com.kdsc.protogen.parsetree;

import java.util.List;
import java.util.Objects;

public class EnumVersionsNode extends BaseNode {

    private final List<EnumVersionNode> enumVersionNodes;

    public EnumVersionsNode(
        String sourceFileName,
        long line,
        long charPosition,
        List<EnumVersionNode> enumVersionNodes
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(enumVersionNodes);
        this.enumVersionNodes = enumVersionNodes;
    }

    public List<EnumVersionNode> getEnumVersionNodes() {
        return enumVersionNodes;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//EnumVersionsNode\n");
        //TODO:KMD Don't need any of these null checks any more
        if(null != enumVersionNodes) {
            enumVersionNodes.forEach(evn -> stringBuilder.append(evn.toFormattedString(1)));
        }
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}