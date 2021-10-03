package com.kdsc.protogen.parsetree;

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

}