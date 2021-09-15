package com.kdsc.protogen.parsetree;

import java.util.Optional;

public class ProtoGenEnumNode extends BaseParseTreeNode {

    private NamespaceNameNode namespaceNameNode;
    private Optional<EnumVersionsNode> enumVersionsNode;
    private Optional<EnumCasesNode> enumCasesNode;

    public ProtoGenEnumNode(
        String sourceFileName,
        long line,
        long charPosition,
        NamespaceNameNode namespaceNameNode,
        Optional<EnumVersionsNode> enumVersionsNode,
        Optional<EnumCasesNode> enumCasesNode
    ) {
        super(sourceFileName, line, charPosition);
        this.namespaceNameNode = namespaceNameNode;
        this.enumVersionsNode = enumVersionsNode;
        this.enumCasesNode = enumCasesNode;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//ProtoGenEnumNode\n");
        stringBuilder.append(namespaceNameNode.toFormattedString(1) + "\n");
        enumVersionsNode.ifPresent(evn -> stringBuilder.append(evn.toFormattedString(1)));
        enumCasesNode.ifPresent(ecn -> stringBuilder.append(ecn.toFormattedString(1)));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }

}
