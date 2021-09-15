package com.kdsc.protogen.parsetree;

public class BytesFieldTypeNode extends NonArrayFieldTypeNode {

    public BytesFieldTypeNode(
        String sourceFileName,
        long line,
        long charPosition
    ) {
        super(sourceFileName, line, charPosition);
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//BytesFieldTypeNode\n");
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}