package com.kdsc.protogen.parsetree;

import java.util.List;

public class NamespaceNameNode extends BaseNode {

    private final List<NamespaceNode> namespaceNodes;
    private final NameNode nameNode;

    public NamespaceNameNode(
        String sourceFileName,
        long line,
        long charPosition,
        List<NamespaceNode> namespaceNodes,
        NameNode nameNode
    )
    {
        super(sourceFileName, line, charPosition);
        this.namespaceNodes = namespaceNodes;
        this.nameNode = nameNode;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//NamespaceNameNode\n");
        if(null != namespaceNodes) {
            namespaceNodes.forEach(pgtn -> stringBuilder.append(pgtn.toFormattedString(1)));
        }
        stringBuilder.append(nameNode.toFormattedString(1));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }

}
