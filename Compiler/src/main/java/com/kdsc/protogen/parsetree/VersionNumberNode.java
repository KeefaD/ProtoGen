package com.kdsc.protogen.parsetree;

public class VersionNumberNode extends BaseParseTreeNode {

    private final long versionNumber;

    public VersionNumberNode(
        String sourceFileName,
        long line,
        long charPosition,
        long versionNumber
    ) {
        super(sourceFileName, line, charPosition);
        this.versionNumber = versionNumber;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//VersionNumberNode\n");
        stringBuilder.append(oneIndent() + "VersionNumber : " + versionNumber);
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}