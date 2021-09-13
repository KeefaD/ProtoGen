package com.kdsc.protogen.parsetree;

import java.util.List;

public class NamespaceNameNode extends BaseParseTreeNode {

    private final List<NamespaceNode> namespaceNodes;
    private final NameNode nameNode;

    public NamespaceNameNode(
        List<NamespaceNode> namespaceNodes,
        NameNode nameNode
    )
    {
        this.namespaceNodes = namespaceNodes;
        this.nameNode = nameNode;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuffer = new StringBuffer();
        stringBuffer.append("//NamespaceNameNode\n");
        if(null != namespaceNodes) {
            namespaceNodes.forEach(pgtn -> stringBuffer.append(pgtn.toFormattedString(1)));
        }
        stringBuffer.append(nameNode.toFormattedString(1));
        var outputString = stringBuffer.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }

}
