package com.kdsc.protogen.parsetree;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class BaseParseTreeNode {

    protected static final int INDENTATION_SPACE_COUNT = 4;

    private final String sourceFileName;
    private final long line;
    private final long charPosition;

    public BaseParseTreeNode(String sourceFileName, long line, long charPosition) {
        this.sourceFileName = sourceFileName;
        this.line = line;
        this.charPosition = charPosition;
    }

    public long getLine() {
        return line;
    }

    public long getCharPosition() {
        return charPosition;
    }

    protected String oneIndent() {
        return IntStream.range(0, INDENTATION_SPACE_COUNT).mapToObj(counter -> " ").collect(Collectors.joining());
    }

    @Override
    public String toString() {
        return toFormattedString(0);
    }

    public abstract String toFormattedString(int indentationLevel);
}
