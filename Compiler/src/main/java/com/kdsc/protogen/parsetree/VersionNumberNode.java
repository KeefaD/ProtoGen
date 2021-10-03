package com.kdsc.protogen.parsetree;

public class VersionNumberNode extends BaseParseTreeNode {

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
        classToFormattedStringTitle(stringBuilder, VersionNumberNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, "VersionNumber", versionNumber);
        return indentString(stringBuilder, indentationLevel);
    }

}