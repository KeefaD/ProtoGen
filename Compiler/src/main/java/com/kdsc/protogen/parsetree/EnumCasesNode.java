package com.kdsc.protogen.parsetree;

import java.util.List;
import java.util.Objects;

public class EnumCasesNode extends BaseParseTreeNode {

    private final List<EnumNameNode> enumNameNodes;

    public EnumCasesNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final List<EnumNameNode> enumNameNodes
    ) {
        super(sourceFileName, line, charPosition);
        this.enumNameNodes = enumNameNodes;
        //TODO:KMD Check order of all these pre conditions
        Objects.requireNonNull(enumNameNodes);
    }

    public List<EnumNameNode> getEnumNameNodes() {
        return enumNameNodes;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, EnumCasesNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, enumNameNodes);
        return indentString(stringBuilder, indentationLevel);
    }

}