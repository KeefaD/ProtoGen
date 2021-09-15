package com.kdsc.protogen.parsetree;

public class FloatFieldTypeNode extends NonArrayFieldTypeNode {

    public FloatFieldTypeNode(
        String sourceFileName,
        long line,
        long charPosition
    ) {
        super(sourceFileName, line, charPosition);
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//FloatFieldTypeNode\n");
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}