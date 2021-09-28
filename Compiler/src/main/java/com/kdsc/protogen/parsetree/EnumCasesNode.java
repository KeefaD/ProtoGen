package com.kdsc.protogen.parsetree;

import java.util.List;
import java.util.Objects;

public class EnumCasesNode extends BaseNode {

    private final List<EnumNameNode> enumNameNodes;

    public EnumCasesNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final List<EnumNameNode> enumNameNodes
    ) {
        super(sourceFileName, line, charPosition);
        this.enumNameNodes = enumNameNodes;
        //TODO:KMD Check order of all these pre conditions
        Objects.requireNonNull(enumNameNodes);
    }

    public List<EnumNameNode> getEnumNameNodes() {
        return enumNameNodes;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//EnumCasesNode\n");
        enumNameNodes.forEach(ecn -> stringBuilder.append(ecn.toFormattedString(1)));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}