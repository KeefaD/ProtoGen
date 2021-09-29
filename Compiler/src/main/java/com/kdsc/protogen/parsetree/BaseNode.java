package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.utils.parameterchecking.Numbers;
import com.kdsc.protogen.utils.parameterchecking.Strings;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class BaseNode {

    protected static final int INDENTATION_SPACE_COUNT = 4;

    private final String sourceFileName;
    private final long line;
    private final long charPosition;

    public BaseNode(final String sourceFileName, final long line, final long charPosition) {
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

    protected String oneIndent() {
        return IntStream.range(0, INDENTATION_SPACE_COUNT).mapToObj(counter -> " ").collect(Collectors.joining());
    }

    @Override
    public String toString() {
        return toFormattedString(0);
    }

    public abstract String toFormattedString(final int indentationLevel);
}