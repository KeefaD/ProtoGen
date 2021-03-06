package com.kdsc.protogen.parsetreenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.parsetreenodes.commoninterfaces.HasNamespaceName;
import com.kdsc.protogen.parsetreenodes.commoninterfaces.KeyOrTypeNode;
import com.kdsc.protogen.parsetreenodes.commoninterfaces.TopLevelObject;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class TypeNode extends BaseParseTreeNode implements TopLevelObject, KeyOrTypeNode, HasNamespaceName {

    private final boolean isInterface;
    private final NamespaceNameGenericParametersWithBoundsNode namespaceNameGenericParametersWithBoundsNode;
    private final Optional<ImplementsListNode> implementsListNode;
    private final Optional<VersionsNode> versionsNode;
    private final Optional<FieldsNode> fieldsNode;

    public TypeNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final boolean isInterface,
        final NamespaceNameGenericParametersWithBoundsNode namespaceNameGenericParametersWithBoundsNode,
        final Optional<ImplementsListNode> implementsListNode,
        final Optional<VersionsNode> versionsNode,
        final Optional<FieldsNode> fieldsNode
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(namespaceNameGenericParametersWithBoundsNode);
        Objects.requireNonNull(implementsListNode);
        Objects.requireNonNull(versionsNode);
        Objects.requireNonNull(fieldsNode);
        this.isInterface = isInterface;
        this.namespaceNameGenericParametersWithBoundsNode = namespaceNameGenericParametersWithBoundsNode;
        this.implementsListNode = implementsListNode;
        this.versionsNode = versionsNode;
        this.fieldsNode = fieldsNode;
    }

    public boolean isInterface() {
        return isInterface;
    }

    public NamespaceNameGenericParametersWithBoundsNode getNamespaceNameGenericParametersWithBoundsNode() {
        return namespaceNameGenericParametersWithBoundsNode;
    }

    public Optional<ImplementsListNode> getImplementsListNode() {
        return implementsListNode;
    }

    public Optional<VersionsNode> getVersionsNode() {
        return versionsNode;
    }

    public Optional<FieldsNode> getFieldsNode() {
        return fieldsNode;
    }

    public NamespaceNameNode getNamespaceNameNode() {
        return namespaceNameGenericParametersWithBoundsNode.getNamespaceNameNode();
    }

    public List<NamespaceNode> getNamespaceNodes() {
        return namespaceNameGenericParametersWithBoundsNode.getNamespaceNameNode().getNamespaceNodes();
    }

    public NameNode getNameNode() {
        return namespaceNameGenericParametersWithBoundsNode.getNamespaceNameNode().getNameNode();
    }

    @Override
    public boolean isLibraryNode() {
        return false;
    }

    @Override
    public String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, TypeNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(formattedStringOptions, 0), BaseParseTreeNode.class);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "IsInterface", isInterface);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, namespaceNameGenericParametersWithBoundsNode);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, implementsListNode);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, versionsNode);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, fieldsNode);
        return indentAndReturnString(stringBuilder, formattedStringOptions, indentationLevel);
    }

    @Override
    public TypeNode clone() {
        return new TypeNode(
            getSourceFileName(),
            getLine(),
            getCharPosition(),
            isInterface,
            namespaceNameGenericParametersWithBoundsNode.clone(),
            com.kdsc.protogen.parsetreenodes.utils.clone.Optionals.clone(implementsListNode),
            com.kdsc.protogen.parsetreenodes.utils.clone.Optionals.clone(versionsNode),
            com.kdsc.protogen.parsetreenodes.utils.clone.Optionals.clone(fieldsNode)
        );
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        TypeNode that = (TypeNode) object;
        return isInterface == that.isInterface && namespaceNameGenericParametersWithBoundsNode.equals(that.namespaceNameGenericParametersWithBoundsNode) && implementsListNode.equals(that.implementsListNode) && versionsNode.equals(that.versionsNode) && fieldsNode.equals(that.fieldsNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isInterface, namespaceNameGenericParametersWithBoundsNode, implementsListNode, versionsNode, fieldsNode);
    }

}