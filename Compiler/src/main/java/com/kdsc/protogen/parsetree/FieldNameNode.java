package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.utils.Strings;

import java.util.Objects;

public class FieldNameNode extends BaseNode {

    private final String fieldName;

    public FieldNameNode(
        String sourceFileName,
        long line,
        long charPosition,
        String fieldName
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
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//FieldNameNode\n");
        stringBuilder.append(oneIndent() + "FieldName : " + fieldName + "\n");
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}