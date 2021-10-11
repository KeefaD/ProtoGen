package com.kdsc.protogen.parsetreenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.parsetreenodes.utils.clone.Lists;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class EnumVersionsNode extends BaseParseTreeNode {

    private final List<EnumVersionNode> enumVersionNodes;

    public EnumVersionsNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final List<EnumVersionNode> enumVersionNodes
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(enumVersionNodes);
        com.kdsc.protogen.utils.parameterchecking.Lists.requireAtLeastOne(enumVersionNodes);
        this.enumVersionNodes = Collections.unmodifiableList(enumVersionNodes);
    }

    public List<EnumVersionNode> getEnumVersionNodes() {
        return enumVersionNodes;
    }

    @Override
    public String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, EnumVersionsNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(formattedStringOptions, 0), BaseParseTreeNode.class);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, enumVersionNodes);
        return indentAndReturnString(stringBuilder, formattedStringOptions, indentationLevel);
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
    public boolean equals(final Object object) {
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