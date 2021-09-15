package com.kdsc.protogen.parsetree;

public class Int64FieldTypeNode extends NonArrayFieldTypeNode {

    public Int64FieldTypeNode(
        String sourceFileName,
        long line,
        long charPosition
    ) {
        super(sourceFileName, line, charPosition);
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//Int64FieldTypeNode\n");
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}