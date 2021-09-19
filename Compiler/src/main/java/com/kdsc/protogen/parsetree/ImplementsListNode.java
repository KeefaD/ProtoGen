package com.kdsc.protogen.parsetree;

import java.util.List;
import java.util.Objects;

public class ImplementsListNode extends BaseNode {

    private final List<NamespaceNameGenericParametersWithoutBoundsNode> namespaceNameGenericParametersWithoutBoundsNodes;

    public ImplementsListNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final List<NamespaceNameGenericParametersWithoutBoundsNode> namespaceNameGenericParametersWithoutBoundsNodes
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(namespaceNameGenericParametersWithoutBoundsNodes);
        this.namespaceNameGenericParametersWithoutBoundsNodes = namespaceNameGenericParametersWithoutBoundsNodes;
    }

    public List<NamespaceNameGenericParametersWithoutBoundsNode> getNamespaceNameGenericParametersWithoutBoundsNodes() {
        return namespaceNameGenericParametersWithoutBoundsNodes;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//ImplementsListNode\n");
        namespaceNameGenericParametersWithoutBoundsNodes.forEach(gpwb -> stringBuilder.append(gpwb.toFormattedString(1)));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}