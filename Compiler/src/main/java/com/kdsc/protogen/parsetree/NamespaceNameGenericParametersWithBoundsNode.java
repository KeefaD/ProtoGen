package com.kdsc.protogen.parsetree;

import java.util.Objects;
import java.util.Optional;

public class NamespaceNameGenericParametersWithBoundsNode extends BaseNode {

    private final NamespaceNameNode namespaceNameNode;
    private final Optional<GenericParametersWithBoundsNode> genericParametersWithBoundsNode;

    public NamespaceNameGenericParametersWithBoundsNode(
        String sourceFileName,
        long line,
        long charPosition,
        NamespaceNameNode namespaceNameNode,
        Optional<GenericParametersWithBoundsNode> genericParametersWithBoundsNode
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(namespaceNameNode);
        Objects.requireNonNull(genericParametersWithBoundsNode);
        this.namespaceNameNode = namespaceNameNode;
        this.genericParametersWithBoundsNode = genericParametersWithBoundsNode;
    }

    public NamespaceNameNode getNamespaceNameNode() {
        return namespaceNameNode;
    }

    public Optional<GenericParametersWithBoundsNode> getGenericParametersWithBoundsNode() {
        return genericParametersWithBoundsNode;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//NamespaceNameGenericParametersWithBoundsNode\n");
        stringBuilder.append(namespaceNameNode.toFormattedString(1));
        genericParametersWithBoundsNode.ifPresent(parametersWithBoundsNode -> stringBuilder.append(parametersWithBoundsNode.toFormattedString(1)));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}
