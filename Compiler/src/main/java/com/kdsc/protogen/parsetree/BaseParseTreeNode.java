package com.kdsc.protogen.parsetree;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class BaseParseTreeNode {

    protected static final int INDENTATION_SPACE_COUNT = 4;

    public abstract String toFormattedString(int indentationLevel);

    protected String oneIndent() {
        return IntStream.range(0, INDENTATION_SPACE_COUNT).mapToObj(counter -> " ").collect(Collectors.joining());
    }

    @Override
    public String toString() {
        return toFormattedString(0);
    }
}
