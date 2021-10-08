package com.kdsc.protogen.parsetreenodes.fieldtypenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.parsetreenodes.GenericParameterNode;

import java.util.Objects;

public final class GenericObjectFieldTypeNode extends NonArrayFieldTypeNode {

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
    public String toFormattedString(final int indentationLevel, final FormattedStringOptions formattedStringOptions) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, GenericObjectFieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(0, formattedStringOptions), NonArrayFieldTypeNode.class);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, genericParameterNode);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

    @Override
    public GenericObjectFieldTypeNode clone() {
        return new GenericObjectFieldTypeNode(
            getSourceFileName(),
            getLine(),
            getCharPosition(),
            genericParameterNode.clone()
        );
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        GenericObjectFieldTypeNode that = (GenericObjectFieldTypeNode) object;
        return genericParameterNode.equals(that.genericParameterNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), genericParameterNode);
    }

}