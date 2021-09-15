package com.kdsc.protogen.parsetree;

public abstract class NonArrayFieldTypeNode extends BaseParseTreeNode {

    public NonArrayFieldTypeNode(
        String sourceFileName,
        long line,
        long charPosition
    ) {
        super(sourceFileName, line, charPosition);
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//NonArrayFieldTypeNode\n");
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}