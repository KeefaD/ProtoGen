package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.utils.parameterchecking.Strings;

import java.util.Objects;

public class NameNode extends BaseParseTreeNode {

    private final String name;

    public NameNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final String name
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(name);
        Strings.requireNonBlank(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, NameNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, "Name", name);
        return indentString(stringBuilder, indentationLevel);
    }

}