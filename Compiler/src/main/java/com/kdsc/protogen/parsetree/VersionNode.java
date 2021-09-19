package com.kdsc.protogen.parsetree;

import java.util.Objects;
import java.util.Optional;

public class VersionNode extends BaseNode {

    private final VersionNumberNode versionNumberNode;
    private final Optional<GenericParametersWithBoundsNode> genericParametersWithBoundsNode;
    private final Optional<ImplementsListNode> implementsListNode;
    private final Optional<FieldsNode> fieldsNode;

    public VersionNode(
        String sourceFileName,
        long line,
        long charPosition,
        VersionNumberNode versionNumberNode,
        Optional<GenericParametersWithBoundsNode> genericParametersWithBoundsNode,
        Optional<ImplementsListNode> implementsListNode,
        Optional<FieldsNode> fieldsNode
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
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//VersionNode\n");
        stringBuilder.append(versionNumberNode.toFormattedString(1));
        genericParametersWithBoundsNode.ifPresent(gbwp -> stringBuilder.append(gbwp.toFormattedString(1)));
        implementsListNode.ifPresent(iln -> stringBuilder.append(iln.toFormattedString(1)));
        fieldsNode.ifPresent(fieldsNode -> stringBuilder.append(fieldsNode.toFormattedString(1)));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}