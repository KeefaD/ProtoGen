package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.utils.parameterchecking.Numbers;

import java.util.Objects;

public class VersionNumberNode extends BaseParseTreeNode {

    private final long versionNumber;

    public VersionNumberNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final long versionNumber
    ) {
        super(sourceFileName, line, charPosition);
        Numbers.requireOneOrGreater(versionNumber);
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

    @Override
    public VersionNumberNode clone() {
        return new VersionNumberNode(
            getSourceFileName(),
            getLine(),
            getCharPosition(),
            versionNumber
        );
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        VersionNumberNode that = (VersionNumberNode) object;
        return versionNumber == that.versionNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), versionNumber);
    }

}