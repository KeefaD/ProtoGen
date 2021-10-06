package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.parsetree.utils.clone.Lists;

import java.util.List;
import java.util.Objects;

public class EnumVersionsNode extends BaseParseTreeNode {

    private final List<EnumVersionNode> enumVersionNodes;

    public EnumVersionsNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final List<EnumVersionNode> enumVersionNodes
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(enumVersionNodes);
        this.enumVersionNodes = enumVersionNodes;
    }

    public List<EnumVersionNode> getEnumVersionNodes() {
        return enumVersionNodes;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, EnumVersionsNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, enumVersionNodes);
        return indentString(stringBuilder, indentationLevel);
    }

    @Override
    public EnumVersionsNode clone() {
        return new EnumVersionsNode(
            getSourceFileName(),
            getLine(),
            getCharPosition(),
            Lists.clone(enumVersionNodes)
        );
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        EnumVersionsNode that = (EnumVersionsNode) object;
        return enumVersionNodes.equals(that.enumVersionNodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), enumVersionNodes);
    }

}