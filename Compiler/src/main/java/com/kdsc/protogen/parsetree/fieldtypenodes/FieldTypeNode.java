package com.kdsc.protogen.parsetree.fieldtypenodes;

import com.kdsc.protogen.parsetree.BaseParseTreeNode;

import java.util.Optional;

public class FieldTypeNode extends BaseParseTreeNode {

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
        this.optional = optional;
        this.arrayFieldTypeNode = arrayFieldTypeNode;
        this.nonArrayFieldTypeNode = nonArrayFieldTypeNode;
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