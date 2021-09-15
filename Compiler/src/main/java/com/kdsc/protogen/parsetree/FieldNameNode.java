package com.kdsc.protogen.parsetree;

public class FieldNameNode extends BaseParseTreeNode {

    private final String fieldName;

    public FieldNameNode(
        String sourceFileName,
        long line,
        long charPosition,
        String fieldName
    ) {
        super(sourceFileName, line, charPosition);
        this.fieldName = fieldName;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//FieldNameNode\n");
        stringBuilder.append(oneIndent() + "FieldName : " + fieldName + "\n");
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}