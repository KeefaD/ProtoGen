package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.utils.parameterchecking.Lists;

import java.util.List;
import java.util.Objects;

public class NamespaceNameNode extends BaseNode {

    private final List<NamespaceNode> namespaceNodes;
    private final NameNode nameNode;

    public NamespaceNameNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final List<NamespaceNode> namespaceNodes,
        final NameNode nameNode
    )
    {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(namespaceNodes);
        Lists.requireAtLeastOne(namespaceNodes);
        Objects.requireNonNull(nameNode);
        this.namespaceNodes = namespaceNodes;
        this.nameNode = nameNode;
    }

    public List<NamespaceNode> getNamespaceNodes() {
        return namespaceNodes;
    }

    public NameNode getNameNode() {
        return nameNode;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//NamespaceNameNode\n");
        namespaceNodes.forEach(pgtn -> stringBuilder.append(pgtn.toFormattedString(1)));
        stringBuilder.append(nameNode.toFormattedString(1));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }

}