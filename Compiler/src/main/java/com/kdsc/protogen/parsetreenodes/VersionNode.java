package com.kdsc.protogen.parsetreenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.parsetreenodes.utils.clone.Optionals;

import java.util.Objects;
import java.util.Optional;

public final class VersionNode extends BaseParseTreeNode {

    private final VersionNumberNode versionNumberNode;
    private final Optional<GenericParametersWithBoundsNode> genericParametersWithBoundsNode;
    private final Optional<ImplementsListNode> implementsListNode;
    private final Optional<FieldsNode> fieldsNode;

    public VersionNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final VersionNumberNode versionNumberNode,
        final Optional<GenericParametersWithBoundsNode> genericParametersWithBoundsNode,
        final Optional<ImplementsListNode> implementsListNode,
        final Optional<FieldsNode> fieldsNode
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(versionNumberNode);
        Objects.requireNonNull(genericParametersWithBoundsNode);
        Objects.requireNonNull(implementsListNode);
        Objects.requireNonNull(fieldsNode);
        this.versionNumberNode = versionNumberNode;
        this.genericParametersWithBoundsNode = genericParametersWithBoundsNode;
        this.implementsListNode = implementsListNode;
        this.fieldsNode = fieldsNode;
    }

    public VersionNumberNode getVersionNumberNode() {
        return versionNumberNode;
    }

    public Optional<GenericParametersWithBoundsNode> getGenericParametersWithBoundsNode() {
        return genericParametersWithBoundsNode;
    }

    public Optional<ImplementsListNode> getImplementsListNode() {
        return implementsListNode;
    }

    public Optional<FieldsNode> getFieldsNode() {
        return fieldsNode;
    }

    @Override
    public String toFormattedString(final int indentationLevel, final FormattedStringOptions formattedStringOptions) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, VersionNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(0, formattedStringOptions), BaseParseTreeNode.class);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, versionNumberNode);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, genericParametersWithBoundsNode);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, implementsListNode);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, fieldsNode);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

    @Override
    public VersionNode clone() {
        return new VersionNode(
            getSourceFileName(),
            getLine(),
            getCharPosition(),
            versionNumberNode.clone(),
            Optionals.clone(genericParametersWithBoundsNode),
            Optionals.clone(implementsListNode),
            Optionals.clone(fieldsNode)
        );
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        VersionNode that = (VersionNode) object;
        return versionNumberNode.equals(that.versionNumberNode) && genericParametersWithBoundsNode.equals(that.genericParametersWithBoundsNode) && implementsListNode.equals(that.implementsListNode) && fieldsNode.equals(that.fieldsNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), versionNumberNode, genericParametersWithBoundsNode, implementsListNode, fieldsNode);
    }

}