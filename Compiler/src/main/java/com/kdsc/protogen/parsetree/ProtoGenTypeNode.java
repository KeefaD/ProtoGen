package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.parsetree.commoninterfaces.HasNamespaceName;
import com.kdsc.protogen.parsetree.commoninterfaces.TopLevelObject;

import java.util.Objects;
import java.util.Optional;

public class ProtoGenTypeNode extends BaseParseTreeNode implements TopLevelObject, HasNamespaceName {

    private boolean isInterface;
    private NamespaceNameGenericParametersWithBoundsNode namespaceNameGenericParametersWithBoundsNode;
    private Optional<ImplementsListNode> implementsListNode;
    private Optional<VersionsNode> versionsNode;
    private Optional<FieldsNode> fieldsNode;

    public ProtoGenTypeNode(
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

    @Override
    public boolean isLibraryNode() {
        return false;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, ProtoGenTypeNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, "IsInterface", isInterface);
        fieldToFormattedStringField(stringBuilder, namespaceNameGenericParametersWithBoundsNode);
        fieldToFormattedStringField(stringBuilder, implementsListNode);
        fieldToFormattedStringField(stringBuilder, versionsNode);
        fieldToFormattedStringField(stringBuilder, fieldsNode);
        return indentString(stringBuilder, indentationLevel);
    }

    @Override
    public ProtoGenTypeNode clone() {
        return new ProtoGenTypeNode(
            getSourceFileName(),
            getLine(),
            getCharPosition(),
            isInterface,
            namespaceNameGenericParametersWithBoundsNode.clone(),
            com.kdsc.protogen.parsetree.utils.clone.Optionals.clone(implementsListNode),
            com.kdsc.protogen.parsetree.utils.clone.Optionals.clone(versionsNode),
            com.kdsc.protogen.parsetree.utils.clone.Optionals.clone(fieldsNode)
        );
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        ProtoGenTypeNode that = (ProtoGenTypeNode) object;
        return isInterface == that.isInterface && namespaceNameGenericParametersWithBoundsNode.equals(that.namespaceNameGenericParametersWithBoundsNode) && implementsListNode.equals(that.implementsListNode) && versionsNode.equals(that.versionsNode) && fieldsNode.equals(that.fieldsNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isInterface, namespaceNameGenericParametersWithBoundsNode, implementsListNode, versionsNode, fieldsNode);
    }

}