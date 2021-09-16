package com.kdsc.protogen.parsetree;

import java.util.Objects;
import java.util.Optional;

public class ProtoGenEnumNode extends BaseNode {

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
        Objects.requireNonNull(namespaceNameNode);
        Objects.requireNonNull(enumVersionsNode);
        Objects.requireNonNull(enumCasesNode);
        this.namespaceNameNode = namespaceNameNode;
        this.enumVersionsNode = enumVersionsNode;
        this.enumCasesNode = enumCasesNode;
    }

    public NamespaceNameNode getNamespaceNameNode() {
        return namespaceNameNode;
    }

    public Optional<EnumVersionsNode> getEnumVersionsNode() {
        return enumVersionsNode;
    }

    public Optional<EnumCasesNode> getEnumCasesNode() {
        return enumCasesNode;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//ProtoGenEnumNode\n");
        stringBuilder.append(namespaceNameNode.toFormattedString(1));
        enumVersionsNode.ifPresent(evn -> stringBuilder.append(evn.toFormattedString(1)));
        enumCasesNode.ifPresent(ecn -> stringBuilder.append(ecn.toFormattedString(1)));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }

}
