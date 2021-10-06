package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.parsetree.utils.clone.Lists;

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

    @Override
    public VersionsNode clone() {
        return new VersionsNode(
            getSourceFileName(),
            getLine(),
            getCharPosition(),
            Lists.clone(versionNodes)
        );
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        VersionsNode that = (VersionsNode) object;
        return versionNodes.equals(that.versionNodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), versionNodes);
    }

}