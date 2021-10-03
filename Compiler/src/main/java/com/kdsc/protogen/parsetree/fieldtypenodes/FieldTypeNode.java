package com.kdsc.protogen.parsetree.fieldtypenodes;

import com.kdsc.protogen.parsetree.BaseParseTreeNode;
import com.kdsc.protogen.utils.parameterchecking.Optionals;

import java.util.Objects;
import java.util.Optional;

public class FieldTypeNode extends BaseParseTreeNode {

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
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, FieldTypeNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, "Optional", optional);
        fieldToFormattedStringField(stringBuilder, arrayFieldTypeNode);
        fieldToFormattedStringField(stringBuilder, nonArrayFieldTypeNode);
        return indentString(stringBuilder, indentationLevel);
    }

}