package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.utils.parameterchecking.Strings;

import java.util.Objects;

public class FieldNameNode extends BaseParseTreeNode {

    private final String fieldName;

    public FieldNameNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final String fieldName
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(fieldName);
        Strings.requireNonBlank(fieldName);
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, FieldNameNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, "FieldName", fieldName);
        return indentString(stringBuilder, indentationLevel);
    }

}