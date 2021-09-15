package com.kdsc.protogen.parsetree;

public class EnumNameNode extends BaseParseTreeNode {

    private final String enumName;

    public EnumNameNode(
        String sourceFileName,
        long line,
        long charPosition,
        String enumName
    ) {
        super(sourceFileName, line, charPosition);
        this.enumName = enumName;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//EnumCaseNode\n");
        stringBuilder.append(oneIndent() + "EnumName : " + enumName);
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}