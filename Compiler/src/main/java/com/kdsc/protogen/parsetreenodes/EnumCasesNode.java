package com.kdsc.protogen.parsetreenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.parsetreenodes.utils.clone.Lists;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class EnumCasesNode extends BaseParseTreeNode {

    private final List<EnumNameNode> enumNameNodes;

    public EnumCasesNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final List<EnumNameNode> enumNameNodes
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(enumNameNodes);
        com.kdsc.protogen.utils.parameterchecking.Lists.requireAtLeastOne(enumNameNodes);
        this.enumNameNodes = Collections.unmodifiableList(enumNameNodes);
    }

    public List<EnumNameNode> getEnumNameNodes() {
        return enumNameNodes;
    }

    @Override
    public String toFormattedString(final int indentationLevel, final FormattedStringOptions formattedStringOptions) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, EnumCasesNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(0, formattedStringOptions), BaseParseTreeNode.class);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, enumNameNodes);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

    @Override
    public EnumCasesNode clone() {
        return new EnumCasesNode(
            getSourceFileName(),
            getLine(),
            getCharPosition(),
            Lists.clone(enumNameNodes)
        );
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        EnumCasesNode that = (EnumCasesNode) object;
        return enumNameNodes.equals(that.enumNameNodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), enumNameNodes);
    }

}