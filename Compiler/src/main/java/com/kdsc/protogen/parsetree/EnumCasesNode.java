package com.kdsc.protogen.parsetree;

import java.util.List;
import java.util.Objects;

public class EnumCasesNode extends BaseNode {

    private final List<EnumNameNode> enumNameNodes;

    public EnumCasesNode(
        String sourceFileName,
        long line,
        long charPosition,
        List<EnumNameNode> enumNameNodes
    ) {
        super(sourceFileName, line, charPosition);
        this.enumNameNodes = enumNameNodes;
        Objects.requireNonNull(enumNameNodes);
    }

    public List<EnumNameNode> getEnumNameNodes() {
        return enumNameNodes;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//EnumCasesNode\n");
        enumNameNodes.forEach(ecn -> stringBuilder.append(ecn.toFormattedString(1)));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}