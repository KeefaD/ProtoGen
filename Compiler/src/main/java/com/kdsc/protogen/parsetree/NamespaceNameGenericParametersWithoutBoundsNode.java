package com.kdsc.protogen.parsetree;

import java.util.Optional;

public class NamespaceNameGenericParametersWithoutBoundsNode extends BaseNode {

    private final NamespaceNameNode namespaceNameNode;
    private final Optional<GenericParametersWithoutBoundsNode> genericParametersWithoutBoundsNode;

    public NamespaceNameGenericParametersWithoutBoundsNode(
        String sourceFileName,
        long line,
        long charPosition,
        NamespaceNameNode namespaceNameNode,
        Optional<GenericParametersWithoutBoundsNode> genericParametersWithoutBoundsNode
    ) {
        super(sourceFileName, line, charPosition);
        this.namespaceNameNode = namespaceNameNode;
        this.genericParametersWithoutBoundsNode = genericParametersWithoutBoundsNode;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//NamespaceNameGenericParametersWithoutBoundsNode\n");
        stringBuilder.append(namespaceNameNode.toFormattedString(1));
        genericParametersWithoutBoundsNode.ifPresent(parametersWithoutBoundsNode -> stringBuilder.append(parametersWithoutBoundsNode.toFormattedString(1)));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}
