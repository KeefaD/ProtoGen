package com.kdsc.protogen.parsetree.fieldtypenodes;

public class FloatFieldTypeNode extends NonArrayFieldTypeNode {

    public FloatFieldTypeNode(
        final String sourceFileName,
        final long line,
        final long charPosition
    ) {
        super(sourceFileName, line, charPosition);
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//FloatFieldTypeNode\n");
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}