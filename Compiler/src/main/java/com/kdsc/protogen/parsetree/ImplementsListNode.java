package com.kdsc.protogen.parsetree;

import java.util.List;

public class ImplementsListNode extends BaseParseTreeNode {

    private final List<NamespaceNameGenericParametersWithoutBoundsNode> namespaceNameGenericParametersWithoutBoundsNodes;

    public ImplementsListNode(
        List<NamespaceNameGenericParametersWithoutBoundsNode> namespaceNameGenericParametersWithoutBoundsNodes
    ) {
        this.namespaceNameGenericParametersWithoutBoundsNodes = namespaceNameGenericParametersWithoutBoundsNodes;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuffer = new StringBuffer();
        stringBuffer.append("//ImplementsListNode\n");
        if(null != namespaceNameGenericParametersWithoutBoundsNodes) {
            namespaceNameGenericParametersWithoutBoundsNodes.forEach(gpwb -> stringBuffer.append(gpwb.toFormattedString(1)));
        }
        var outputString = stringBuffer.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}