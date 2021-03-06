package com.kdsc.protogen.parsetreenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.parsetreenodes.utils.clone.Optionals;

import java.util.Objects;
import java.util.Optional;

public final class NamespaceNameGenericParametersWithBoundsNode extends BaseParseTreeNode {

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
    public String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, NamespaceNameGenericParametersWithBoundsNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(formattedStringOptions, 0), BaseParseTreeNode.class);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, namespaceNameNode);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, genericParametersWithBoundsNode);
        return indentAndReturnString(stringBuilder, formattedStringOptions, indentationLevel);
    }

    @Override
    public NamespaceNameGenericParametersWithBoundsNode clone() {
        return new NamespaceNameGenericParametersWithBoundsNode(
            getSourceFileName(),
            getLine(),
            getCharPosition(),
            namespaceNameNode.clone(),
            Optionals.clone(genericParametersWithBoundsNode)
        );
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        NamespaceNameGenericParametersWithBoundsNode that = (NamespaceNameGenericParametersWithBoundsNode) object;
        return namespaceNameNode.equals(that.namespaceNameNode) && genericParametersWithBoundsNode.equals(that.genericParametersWithBoundsNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), namespaceNameNode, genericParametersWithBoundsNode);
    }

}