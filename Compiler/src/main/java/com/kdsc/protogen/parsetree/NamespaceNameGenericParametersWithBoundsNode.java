package com.kdsc.protogen.parsetree;

import java.util.Objects;
import java.util.Optional;

public class NamespaceNameGenericParametersWithBoundsNode extends BaseParseTreeNode {

    private final NamespaceNameNode namespaceNameNode;
    private final Optional<GenericParametersWithBoundsNode> genericParametersWithBoundsNode;

    public NamespaceNameGenericParametersWithBoundsNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final NamespaceNameNode namespaceNameNode,
        final Optional<GenericParametersWithBoundsNode> genericParametersWithBoundsNode
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
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, NamespaceNameGenericParametersWithBoundsNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, namespaceNameNode);
        fieldToFormattedStringField(stringBuilder, genericParametersWithBoundsNode);
        return indentString(stringBuilder, indentationLevel);
    }

}