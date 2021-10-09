package com.kdsc.protogen.parsetreenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;

import java.util.Objects;

public final class EnumVersionNode extends BaseParseTreeNode {

    private final VersionNumberNode versionNumberNode;
    private final EnumCasesNode enumCasesNode;

    public EnumVersionNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final VersionNumberNode versionNumberNode,
        final EnumCasesNode enumCasesNode
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

    public EnumCasesNode getEnumCasesNode() {
        return enumCasesNode;
    }

    @Override
    public String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, EnumVersionNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(formattedStringOptions, 0), BaseParseTreeNode.class);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, versionNumberNode);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, enumCasesNode);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

    @Override
    public EnumVersionNode clone() {
        return new EnumVersionNode(
            getSourceFileName(),
            getLine(),
            getCharPosition(),
            versionNumberNode.clone(),
            enumCasesNode.clone()
        );
    }

    @Override
    public boolean equals(final Object object) {
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