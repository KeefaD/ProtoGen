package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.parsetree.commoninterfaces.HasNamespaceName;
import com.kdsc.protogen.parsetree.utils.clone.Optionals;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class NamespaceNameGenericParametersNode extends BaseParseTreeNode implements HasNamespaceName {

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

    public List<NamespaceNode> getNamespaceNodes() {
        return namespaceNameNode.getNamespaceNodes();
    }

    public NameNode getNameNode() {
        return namespaceNameNode.getNameNode();
    }

    @Override
    public String toFormattedString(final int indentationLevel, final FormattedStringOptions formattedStringOptions) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, NamespaceNameGenericParametersNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(0, formattedStringOptions), BaseParseTreeNode.class);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, namespaceNameNode);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, genericParametersNode);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

    @Override
    public NamespaceNameGenericParametersNode clone() {
        return new NamespaceNameGenericParametersNode(
            getSourceFileName(),
            getLine(),
            getCharPosition(),
            namespaceNameNode.clone(),
            Optionals.clone(genericParametersNode)
        );
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        NamespaceNameGenericParametersNode that = (NamespaceNameGenericParametersNode) object;
        return namespaceNameNode.equals(that.namespaceNameNode) && genericParametersNode.equals(that.genericParametersNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), namespaceNameNode, genericParametersNode);
    }

}