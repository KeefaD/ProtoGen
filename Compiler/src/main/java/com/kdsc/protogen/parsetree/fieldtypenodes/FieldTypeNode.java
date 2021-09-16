package com.kdsc.protogen.parsetree.fieldtypenodes;

import com.kdsc.protogen.parsetree.BaseNode;
import com.kdsc.protogen.utils.parameterchecking.Optionals;

import java.util.Objects;
import java.util.Optional;

public class FieldTypeNode extends BaseNode {

    private final boolean optional;
    private final Optional<ArrayFieldTypeNode> arrayFieldTypeNode;
    private final Optional<NonArrayFieldTypeNode> nonArrayFieldTypeNode;

    public FieldTypeNode(
        String sourceFileName,
        long line,
        long charPosition,
        boolean optional,
        Optional<ArrayFieldTypeNode> arrayFieldTypeNode,
        Optional<NonArrayFieldTypeNode> nonArrayFieldTypeNode
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
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//FieldTypeNode\n");
        stringBuilder.append(oneIndent() + "Optional : " + optional + "\n");
        arrayFieldTypeNode.ifPresent(aftn -> stringBuilder.append(aftn.toFormattedString(1)));
        nonArrayFieldTypeNode.ifPresent(naftn -> stringBuilder.append(naftn.toFormattedString(1)));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}