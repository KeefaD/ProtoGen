package com.kdsc.protogen.parsetree;

import java.util.List;

public class ImplementsListNode extends BaseParseTreeNode {

    private final List<NamespaceNameGenericParametersWithoutBoundsNode> namespaceNameGenericParametersWithoutBoundsNodes;

    public ImplementsListNode(
        String sourceFileName,
        long line,
        long charPosition,
        List<NamespaceNameGenericParametersWithoutBoundsNode> namespaceNameGenericParametersWithoutBoundsNodes
    ) {
        super(sourceFileName, line, charPosition);
        this.namespaceNameGenericParametersWithoutBoundsNodes = namespaceNameGenericParametersWithoutBoundsNodes;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//ImplementsListNode\n");
        if(null != namespaceNameGenericParametersWithoutBoundsNodes) {
            namespaceNameGenericParametersWithoutBoundsNodes.forEach(gpwb -> stringBuilder.append(gpwb.toFormattedString(1)));
        }
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}