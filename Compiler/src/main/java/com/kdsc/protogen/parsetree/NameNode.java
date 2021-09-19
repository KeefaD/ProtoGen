package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.utils.parameterchecking.Strings;

import java.util.Objects;

public class NameNode extends BaseNode {

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
        stringBuilder.append("//NameNode\n");
        stringBuilder.append(oneIndent() + "Name : " + name);
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }

}
