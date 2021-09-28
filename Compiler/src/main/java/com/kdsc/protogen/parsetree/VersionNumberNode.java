package com.kdsc.protogen.parsetree;

public class VersionNumberNode extends BaseNode {

    private final long versionNumber;

    public VersionNumberNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final long versionNumber
    ) {
        super(sourceFileName, line, charPosition);
        //TODO:KMD Perhaps numbers require positive
        this.versionNumber = versionNumber;
    }

    public long getVersionNumber() {
        return versionNumber;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//VersionNumberNode\n");
        stringBuilder.append(oneIndent() + "VersionNumber : " + versionNumber);
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}