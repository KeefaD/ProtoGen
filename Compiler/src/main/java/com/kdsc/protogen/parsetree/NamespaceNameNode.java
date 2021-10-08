package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.utils.parameterchecking.Lists;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class NamespaceNameNode extends BaseParseTreeNode {

    private final List<NamespaceNode> namespaceNodes;
    private final NameNode nameNode;

    public NamespaceNameNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final List<NamespaceNode> namespaceNodes,
        final NameNode nameNode
    )
    {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(namespaceNodes);
        Lists.requireAtLeastOne(namespaceNodes);
        Objects.requireNonNull(nameNode);
        this.namespaceNodes = Collections.unmodifiableList(namespaceNodes);
        this.nameNode = nameNode;
    }

    public List<NamespaceNode> getNamespaceNodes() {
        return namespaceNodes;
    }

    public NameNode getNameNode() {
        return nameNode;
    }

    @Override
    public String toFormattedString(final int indentationLevel, final FormattedStringOptions formattedStringOptions) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, NamespaceNameNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(0, formattedStringOptions), BaseParseTreeNode.class);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, namespaceNodes);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, nameNode);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

    @Override
    public NamespaceNameNode clone() {
        return new NamespaceNameNode(
            getSourceFileName(),
            getLine(),
            getCharPosition(),
            com.kdsc.protogen.parsetree.utils.clone.Lists.clone(namespaceNodes),
            nameNode.clone()
        );
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        NamespaceNameNode that = (NamespaceNameNode) object;
        return namespaceNodes.equals(that.namespaceNodes) && nameNode.equals(that.nameNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), namespaceNodes, nameNode);
    }

}