package com.kdsc.protogen.parsetree.fieldtypenodes;

import com.kdsc.protogen.parsetree.GenericParameterNode;

import java.util.Objects;

public class GenericObjectFieldTypeNode extends NonArrayFieldTypeNode {

    private final GenericParameterNode genericParameterNode;

    public GenericObjectFieldTypeNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final GenericParameterNode genericParameterNode
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(genericParameterNode);
        this.genericParameterNode = genericParameterNode;
    }

    public GenericParameterNode getGenericParameterNode() {
        return genericParameterNode;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, GenericObjectFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, genericParameterNode);
        return indentString(stringBuilder, indentationLevel);
    }

}