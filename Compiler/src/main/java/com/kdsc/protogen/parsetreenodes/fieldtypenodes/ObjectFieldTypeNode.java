package com.kdsc.protogen.parsetreenodes.fieldtypenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.parsetreenodes.NameNode;
import com.kdsc.protogen.parsetreenodes.NamespaceNameGenericParametersNode;
import com.kdsc.protogen.parsetreenodes.NamespaceNameNode;
import com.kdsc.protogen.parsetreenodes.NamespaceNode;
import com.kdsc.protogen.parsetreenodes.commoninterfaces.HasNamespaceName;

import java.util.List;
import java.util.Objects;

public final class ObjectFieldTypeNode extends NonArrayFieldTypeNode implements HasNamespaceName {

    private final NamespaceNameGenericParametersNode namespaceNameGenericParametersNode;

    public ObjectFieldTypeNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final NamespaceNameGenericParametersNode namespaceNameGenericParametersNode
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(namespaceNameGenericParametersNode);
        this.namespaceNameGenericParametersNode = namespaceNameGenericParametersNode;
    }

    public NamespaceNameGenericParametersNode getNamespaceNameGenericParametersNode() {
        return namespaceNameGenericParametersNode;
    }

    public NamespaceNameNode getNamespaceNameNode() {
        return namespaceNameGenericParametersNode.getNamespaceNameNode();
    }

    public List<NamespaceNode> getNamespaceNodes() {
        return namespaceNameGenericParametersNode.getNamespaceNameNode().getNamespaceNodes();
    }

    public NameNode getNameNode() {
        return namespaceNameGenericParametersNode.getNamespaceNameNode().getNameNode();
    }

    @Override
    public String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, ObjectFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(formattedStringOptions, 0), NonArrayFieldTypeNode.class);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, namespaceNameGenericParametersNode);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

    @Override
    public ObjectFieldTypeNode clone() {
        return new ObjectFieldTypeNode(
            getSourceFileName(),
            getLine(),
            getCharPosition(),
            namespaceNameGenericParametersNode.clone()
        );
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        ObjectFieldTypeNode that = (ObjectFieldTypeNode) object;
        return namespaceNameGenericParametersNode.equals(that.namespaceNameGenericParametersNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), namespaceNameGenericParametersNode);
    }

}