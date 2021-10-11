package com.kdsc.protogen.parsetreenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.parsetreenodes.utils.clone.Lists;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class VersionsNode extends BaseParseTreeNode {

    private final List<VersionNode> versionNodes;

    public VersionsNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final List<VersionNode> versionNodes
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(versionNodes);
        com.kdsc.protogen.utils.parameterchecking.Lists.requireAtLeastOne(versionNodes);
        this.versionNodes = Collections.unmodifiableList(versionNodes);
    }

    public List<VersionNode> getVersionNodes() {
        return versionNodes;
    }

    @Override
    public String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, VersionsNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(formattedStringOptions, 0), BaseParseTreeNode.class);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, versionNodes);
        return indentAndReturnString(stringBuilder, formattedStringOptions, indentationLevel);
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
    public boolean equals(final Object object) {
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