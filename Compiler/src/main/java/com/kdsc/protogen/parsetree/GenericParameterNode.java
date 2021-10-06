package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.utils.parameterchecking.Strings;

import java.util.Objects;

public class GenericParameterNode extends BaseParseTreeNode {

    private final String identifier;

    public GenericParameterNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final String identifier
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(identifier);
        Strings.requireNonBlank(identifier);
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, GenericParameterNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, "Identifier", identifier);
        return indentString(stringBuilder, indentationLevel);
    }

    @Override
    public GenericParameterNode clone() {
        return new GenericParameterNode(
            getSourceFileName(),
            getLine(),
            getCharPosition(),
            identifier
        );
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        GenericParameterNode that = (GenericParameterNode) object;
        return identifier.equals(that.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), identifier);
    }

}