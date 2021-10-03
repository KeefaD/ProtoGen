package com.kdsc.protogen.parsetree;

import java.util.Objects;
import java.util.Optional;

public class VersionNode extends BaseParseTreeNode {

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
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, VersionNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, versionNumberNode);
        fieldToFormattedStringField(stringBuilder, genericParametersWithBoundsNode);
        fieldToFormattedStringField(stringBuilder, implementsListNode);
        fieldToFormattedStringField(stringBuilder, fieldsNode);
        return indentString(stringBuilder, indentationLevel);
    }

}