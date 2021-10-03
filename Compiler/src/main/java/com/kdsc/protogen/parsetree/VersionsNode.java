package com.kdsc.protogen.parsetree;

import java.util.List;
import java.util.Objects;

public class VersionsNode extends BaseParseTreeNode {

    private final List<VersionNode> versionNodes;

    public VersionsNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final List<VersionNode> versionNodes
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(versionNodes);
        this.versionNodes = versionNodes;
    }

    public List<VersionNode> getVersionNodes() {
        return versionNodes;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, VersionsNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, versionNodes);
        return indentString(stringBuilder, indentationLevel);
    }

}