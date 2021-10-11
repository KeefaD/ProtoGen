package com.kdsc.protogen.parsetreenodes.fieldtypenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.parsetreenodes.BaseParseTreeNode;
import com.kdsc.protogen.utils.parameterchecking.Optionals;

import java.util.Objects;
import java.util.Optional;

public final class FieldTypeNode extends BaseParseTreeNode {

    private final boolean optional;
    private final Optional<ArrayFieldTypeNode> arrayFieldTypeNode;
    private final Optional<NonArrayFieldTypeNode> nonArrayFieldTypeNode;

    public FieldTypeNode(
        final String sourceFileName,
        final long line,
        final long charPosition,
        final boolean optional,
        final Optional<ArrayFieldTypeNode> arrayFieldTypeNode,
        final Optional<NonArrayFieldTypeNode> nonArrayFieldTypeNode
    ) {
        super(sourceFileName, line, charPosition);
        Objects.requireNonNull(arrayFieldTypeNode);
        Objects.requireNonNull(nonArrayFieldTypeNode);
        Optionals.requireOne(arrayFieldTypeNode, nonArrayFieldTypeNode);
        this.optional = optional;
        this.arrayFieldTypeNode = arrayFieldTypeNode;
        this.nonArrayFieldTypeNode = nonArrayFieldTypeNode;
    }

    public boolean isOptional() {
        return optional;
    }

    public Optional<ArrayFieldTypeNode> getArrayFieldTypeNode() {
        return arrayFieldTypeNode;
    }

    public Optional<NonArrayFieldTypeNode> getNonArrayFieldTypeNode() {
        return nonArrayFieldTypeNode;
    }

    @Override
    public String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, FieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(formattedStringOptions, 0), BaseParseTreeNode.class);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "Optional", optional);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, arrayFieldTypeNode);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, nonArrayFieldTypeNode);
        return indentAndReturnString(stringBuilder, formattedStringOptions, indentationLevel);
    }

    @Override
    public FieldTypeNode clone() {
        return new FieldTypeNode(
            getSourceFileName(),
            getLine(),
            getCharPosition(),
            optional,
            com.kdsc.protogen.parsetreenodes.utils.clone.Optionals.clone(arrayFieldTypeNode),
            com.kdsc.protogen.parsetreenodes.utils.clone.Optionals.clone(nonArrayFieldTypeNode)
        );
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        FieldTypeNode that = (FieldTypeNode) object;
        return optional == that.optional && arrayFieldTypeNode.equals(that.arrayFieldTypeNode) && nonArrayFieldTypeNode.equals(that.nonArrayFieldTypeNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), optional, arrayFieldTypeNode, nonArrayFieldTypeNode);
    }

}