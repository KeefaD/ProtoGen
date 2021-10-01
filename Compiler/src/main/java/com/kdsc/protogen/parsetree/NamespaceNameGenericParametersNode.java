package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.parsetree.commoninterfaces.HasNamespaceName;

import java.util.Objects;
import java.util.Optional;

public class NamespaceNameGenericParametersNode extends BaseNode implements HasNamespaceName {

    private final NamespaceNameNode namespaceNameNode;
    private final Optional<GenericParametersNode> genericParametersNode;

    public NamespaceNameGenericParametersNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final NamespaceNameNode namespaceNameNode,
        final Optional<GenericParametersNode> genericParametersNode
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(namespaceNameNode);
        Objects.requireNonNull(genericParametersNode);
        this.namespaceNameNode = namespaceNameNode;
        this.genericParametersNode = genericParametersNode;
    }

    public NamespaceNameNode getNamespaceNameNode() {
        return namespaceNameNode;
    }

    public Optional<GenericParametersNode> getGenericParametersNode() {
        return genericParametersNode;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//NamespaceNameGenericParametersNode\n");
        stringBuilder.append(namespaceNameNode.toFormattedString(1));
        genericParametersNode.ifPresent(parametersNode -> stringBuilder.append(parametersNode.toFormattedString(1)));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }

}