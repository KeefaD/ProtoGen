package com.kdsc.protogen.parsetreenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.parsetreenodes.utils.clone.Lists;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class ImplementsListNode extends BaseParseTreeNode {

    private final List<NamespaceNameGenericParametersNode> namespaceNameGenericParametersNodes;

    public ImplementsListNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final List<NamespaceNameGenericParametersNode> namespaceNameGenericParametersNodes
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(namespaceNameGenericParametersNodes);
        com.kdsc.protogen.utils.parameterchecking.Lists.requireAtLeastOne(namespaceNameGenericParametersNodes);
        this.namespaceNameGenericParametersNodes = Collections.unmodifiableList(namespaceNameGenericParametersNodes);
    }

    public List<NamespaceNameGenericParametersNode> getNamespaceNameGenericParametersNodes() {
        return namespaceNameGenericParametersNodes;
    }

    @Override
    public String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, ImplementsListNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(formattedStringOptions, 0), BaseParseTreeNode.class);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, namespaceNameGenericParametersNodes);
        return indentAndReturnString(stringBuilder, formattedStringOptions, indentationLevel);
    }

    @Override
    public ImplementsListNode clone() {
        return new ImplementsListNode(
            getSourceFileName(),
            getLine(),
            getCharPosition(),
            Lists.clone(namespaceNameGenericParametersNodes)
        );
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        ImplementsListNode that = (ImplementsListNode) object;
        return namespaceNameGenericParametersNodes.equals(that.namespaceNameGenericParametersNodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), namespaceNameGenericParametersNodes);
    }

}