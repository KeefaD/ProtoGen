package com.kdsc.protogen.parsetree.fieldtypenodes;

import com.kdsc.protogen.parsetree.BaseNode;
import com.kdsc.protogen.parsetree.commoninterfaces.AllowableOutputFieldTypeNode;
import com.kdsc.protogen.utils.parameterchecking.Optionals;

import java.util.Objects;
import java.util.Optional;

public class FieldTypeNode extends BaseNode {

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

    //TODO:KMD Needs test
    public AllowableOutputFieldTypeNode getAllowableOutputFieldTypeNode() {
        if(arrayFieldTypeNode.isPresent() && nonArrayFieldTypeNode.isPresent()) {
            throw new RuntimeException("FieldTypeNode should not be able to have ArrayFieldTypeNode and NonArrayFieldTypeNode set at the same time");
        }
        if(arrayFieldTypeNode.isEmpty() && nonArrayFieldTypeNode.isEmpty()) {
            throw new RuntimeException("FieldTypeNode should have either ArrayFieldTypeNode or NonArrayFieldTypeNode set");
        }
        if(arrayFieldTypeNode.isPresent()) return arrayFieldTypeNode.get();
        return (AllowableOutputFieldTypeNode) nonArrayFieldTypeNode.get();
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//FieldTypeNode\n");
        stringBuilder.append(oneIndent() + "Optional : " + optional + "\n");
        arrayFieldTypeNode.ifPresent(aftn -> stringBuilder.append(aftn.toFormattedString(1)));
        nonArrayFieldTypeNode.ifPresent(naftn -> stringBuilder.append(naftn.toFormattedString(1)));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}