package com.kdsc.protogen.parsetree;

import java.util.List;
import java.util.Objects;

public class ImplementsListNode extends BaseParseTreeNode {

    private final List<NamespaceNameGenericParametersNode> namespaceNameGenericParametersNodes;

    public ImplementsListNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final List<NamespaceNameGenericParametersNode> namespaceNameGenericParametersNodes
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(namespaceNameGenericParametersNodes);
        this.namespaceNameGenericParametersNodes = namespaceNameGenericParametersNodes;
    }

    public List<NamespaceNameGenericParametersNode> getNamespaceNameGenericParametersNodes() {
        return namespaceNameGenericParametersNodes;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, ImplementsListNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, namespaceNameGenericParametersNodes);
        return indentString(stringBuilder, indentationLevel);
    }

}