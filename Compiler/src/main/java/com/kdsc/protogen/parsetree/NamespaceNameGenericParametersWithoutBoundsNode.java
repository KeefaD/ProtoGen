package com.kdsc.protogen.parsetree;

import java.util.Objects;
import java.util.Optional;

public class NamespaceNameGenericParametersWithoutBoundsNode extends BaseNode {

    private final NamespaceNameNode namespaceNameNode;
    private final Optional<GenericParametersWithoutBoundsNode> genericParametersWithoutBoundsNode;

    public NamespaceNameGenericParametersWithoutBoundsNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final NamespaceNameNode namespaceNameNode,
        final Optional<GenericParametersWithoutBoundsNode> genericParametersWithoutBoundsNode
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(namespaceNameNode);
        Objects.requireNonNull(genericParametersWithoutBoundsNode);
        this.namespaceNameNode = namespaceNameNode;
        this.genericParametersWithoutBoundsNode = genericParametersWithoutBoundsNode;
    }

    public NamespaceNameNode getNamespaceNameNode() {
        return namespaceNameNode;
    }

    public Optional<GenericParametersWithoutBoundsNode> getGenericParametersWithoutBoundsNode() {
        return genericParametersWithoutBoundsNode;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//NamespaceNameGenericParametersWithoutBoundsNode\n");
        stringBuilder.append(namespaceNameNode.toFormattedString(1));
        genericParametersWithoutBoundsNode.ifPresent(parametersWithoutBoundsNode -> stringBuilder.append(parametersWithoutBoundsNode.toFormattedString(1)));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}
