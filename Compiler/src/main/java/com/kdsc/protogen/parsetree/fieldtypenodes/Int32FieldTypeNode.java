package com.kdsc.protogen.parsetree.fieldtypenodes;

public class Int32FieldTypeNode extends NonArrayFieldTypeNode {

    public Int32FieldTypeNode(
        final String sourceFileName,
        final long line,
        final long charPosition
    ) {
        super(sourceFileName, line, charPosition);
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//Int32FieldTypeNode\n");
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}