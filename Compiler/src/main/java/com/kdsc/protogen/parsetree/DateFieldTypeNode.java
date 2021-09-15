package com.kdsc.protogen.parsetree;

public class DateFieldTypeNode extends NonArrayFieldTypeNode {

    public DateFieldTypeNode(
        String sourceFileName,
        long line,
        long charPosition
    ) {
        super(sourceFileName, line, charPosition);
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//DateFieldTypeNode\n");
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}