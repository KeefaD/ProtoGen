package com.kdsc.protogen.parsetree;

import java.util.Optional;

public class EnumVersionNode extends BaseParseTreeNode {

    private final VersionNumberNode versionNumberNode;
    private final Optional<EnumCasesNode> enumCasesNode;

    public EnumVersionNode(
        String sourceFileName,
        long line,
        long charPosition,
        VersionNumberNode versionNumberNode,
        Optional<EnumCasesNode> enumCasesNode
    ) {
        super(sourceFileName, line, charPosition);
        this.versionNumberNode = versionNumberNode;
        this.enumCasesNode = enumCasesNode;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//EnumVersionNode\n");
        stringBuilder.append(versionNumberNode.toFormattedString(1));
        enumCasesNode.ifPresent(ecn -> stringBuilder.append(ecn.toFormattedString(1)));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}