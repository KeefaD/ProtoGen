package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.parsetree.utils.clone.Optionals;

import java.util.Objects;
import java.util.Optional;

public class EnumVersionNode extends BaseParseTreeNode {

    private final VersionNumberNode versionNumberNode;
    private final Optional<EnumCasesNode> enumCasesNode;

    public EnumVersionNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final VersionNumberNode versionNumberNode,
        final Optional<EnumCasesNode> enumCasesNode
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(versionNumberNode);
        Objects.requireNonNull(enumCasesNode);
        this.versionNumberNode = versionNumberNode;
        this.enumCasesNode = enumCasesNode;
    }

    public VersionNumberNode getVersionNumberNode() {
        return versionNumberNode;
    }

    public Optional<EnumCasesNode> getEnumCasesNode() {
        return enumCasesNode;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, EnumVersionNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, versionNumberNode);
        fieldToFormattedStringField(stringBuilder, enumCasesNode);
        return indentString(stringBuilder, indentationLevel);
    }

    @Override
    public EnumVersionNode clone() {
        return new EnumVersionNode(
            getSourceFileName(),
            getLine(),
            getCharPosition(),
            versionNumberNode.clone(),
            Optionals.clone(enumCasesNode)
        );
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        EnumVersionNode that = (EnumVersionNode) object;
        return versionNumberNode.equals(that.versionNumberNode) && enumCasesNode.equals(that.enumCasesNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), versionNumberNode, enumCasesNode);
    }

}