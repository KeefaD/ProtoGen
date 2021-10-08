package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.utils.parameterchecking.Strings;

import java.util.Objects;

public final class EnumNameNode extends BaseParseTreeNode {

    private final String enumName;

    public EnumNameNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final String enumName
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(enumName);
        Strings.requireNonBlank(enumName);
        this.enumName = enumName;
    }

    public String getEnumName() {
        return enumName;
    }

    @Override
    public String toFormattedString(final int indentationLevel, final FormattedStringOptions formattedStringOptions) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, EnumNameNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(0, formattedStringOptions), BaseParseTreeNode.class);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "EnumName", enumName);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

    @Override
    public Object clone() {
        return new EnumNameNode(
            getSourceFileName(),
            getLine(),
            getCharPosition(),
            enumName
        );
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        EnumNameNode that = (EnumNameNode) object;
        return Objects.equals(enumName, that.enumName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), enumName);
    }

}