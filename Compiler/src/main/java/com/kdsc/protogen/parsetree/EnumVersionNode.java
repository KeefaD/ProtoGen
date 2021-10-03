package com.kdsc.protogen.parsetree;

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

}