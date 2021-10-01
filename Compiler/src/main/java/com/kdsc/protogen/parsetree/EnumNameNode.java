package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.utils.parameterchecking.Strings;

import java.util.Objects;

public class EnumNameNode extends BaseNode {

    private final String enumName;

    public EnumNameNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final String enumName
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(enumName);
        Strings.requireNonBlank(enumName);
        this.enumName = enumName;
    }

    public String getEnumName() {
        return enumName;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//EnumNameNode\n");
        stringBuilder.append(oneIndent() + "EnumName : " + enumName);
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }

}