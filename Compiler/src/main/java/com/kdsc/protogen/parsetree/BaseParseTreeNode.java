package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.nodes.BaseNode;
import com.kdsc.protogen.utils.parameterchecking.Numbers;
import com.kdsc.protogen.utils.parameterchecking.Strings;

import java.util.Objects;

public abstract class BaseParseTreeNode extends BaseNode {

    private final String sourceFileName;
    private final long line;
    private final long charPosition;

    public BaseParseTreeNode(final String sourceFileName, final long line, final long charPosition) {
        Objects.requireNonNull(sourceFileName);
        Strings.requireNonBlank(sourceFileName);
        Numbers.requireZeroOrGreater(line);
        Numbers.requireZeroOrGreater(charPosition);
        this.sourceFileName = sourceFileName;
        this.line = line;
        this.charPosition = charPosition;
    }

    public String getSourceFileName() {
        return sourceFileName;
    }

    public long getLine() {
        return line;
    }

    public long getCharPosition() {
        return charPosition;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, BaseParseTreeNode.class);
        fieldToFormattedStringField(stringBuilder, "SourceFileName", sourceFileName);
        fieldToFormattedStringField(stringBuilder, "Line", line);
        fieldToFormattedStringField(stringBuilder, "CharPosition", charPosition);
        return indentString(stringBuilder, indentationLevel);
    }

}