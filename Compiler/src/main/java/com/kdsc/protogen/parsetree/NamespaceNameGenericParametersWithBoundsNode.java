package com.kdsc.protogen.parsetree;

import java.util.Optional;

public class NamespaceNameGenericParametersWithBoundsNode extends BaseParseTreeNode {

    private final NamespaceNameNode namespaceNameNode;
    private final Optional<GenericParametersWithBoundsNode> genericParametersWithBoundsNode;

    public NamespaceNameGenericParametersWithBoundsNode(
        long line,
        long charPosition,
        NamespaceNameNode namespaceNameNode,
        Optional<GenericParametersWithBoundsNode> genericParametersWithBoundsNode
    ) {
        super(line, charPosition);
        this.namespaceNameNode = namespaceNameNode;
        this.genericParametersWithBoundsNode = genericParametersWithBoundsNode;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuffer = new StringBuffer();
        stringBuffer.append("//NamespaceNameGenericParametersWithBoundsNode\n");
        stringBuffer.append(namespaceNameNode.toFormattedString(1));
        genericParametersWithBoundsNode.ifPresent(parametersWithBoundsNode -> stringBuffer.append(parametersWithBoundsNode.toFormattedString(1)));
        var outputString = stringBuffer.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}
