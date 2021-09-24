package com.kdsc.protogen.filegenerationtree;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class BaseNode {

    protected static final int INDENTATION_SPACE_COUNT = 4;

    protected String oneIndent() {
        return IntStream.range(0, INDENTATION_SPACE_COUNT).mapToObj(counter -> " ").collect(Collectors.joining());
    }

    @Override
    public String toString() {
        return toFormattedString(0);
    }

    public abstract String toFormattedString(final int indentationLevel);
}
