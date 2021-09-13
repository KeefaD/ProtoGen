package com.kdsc.protogen.parsetree;

import java.util.Optional;

public class NamespaceNameGenericParametersWithoutBoundsNode extends BaseParseTreeNode {

    private final NamespaceNameNode namespaceNameNode;
    private final Optional<GenericParametersWithoutBoundsNode> genericParametersWithoutBoundsNode;

    public NamespaceNameGenericParametersWithoutBoundsNode(
        long line,
        long charPosition,
        NamespaceNameNode namespaceNameNode,
        Optional<GenericParametersWithoutBoundsNode> genericParametersWithoutBoundsNode
    ) {
        super(line, charPosition);
        this.namespaceNameNode = namespaceNameNode;
        this.genericParametersWithoutBoundsNode = genericParametersWithoutBoundsNode;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuffer = new StringBuffer();
        stringBuffer.append("//NamespaceNameGenericParametersWithoutBoundsNode\n");
        stringBuffer.append(namespaceNameNode.toFormattedString(1));
        genericParametersWithoutBoundsNode.ifPresent(parametersWithoutBoundsNode -> stringBuffer.append(parametersWithoutBoundsNode.toFormattedString(1)));
        var outputString = stringBuffer.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}
