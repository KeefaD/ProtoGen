package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.nodes.FormattedStringOptions;
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
    public String toFormattedString(final int indentationLevel, final FormattedStringOptions formattedStringOptions) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, FieldNameNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(0, formattedStringOptions));
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "FieldName", fieldName);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

    @Override
    public FieldNameNode clone() {
        return new FieldNameNode(
            getSourceFileName(),
            getLine(),
            getCharPosition(),
            fieldName
        );
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        FieldNameNode that = (FieldNameNode) object;
        return fieldName.equals(that.fieldName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fieldName);
    }

}