package com.kdsc.protogen.parsetree.fieldtypenodes;

import com.kdsc.protogen.parsetree.BaseParseTreeNode;

public class ArrayFieldTypeNode extends BaseParseTreeNode {

    private final NonArrayFieldTypeNode nonArrayFieldTypeNode;
    private final long dimensions;

    public ArrayFieldTypeNode(
        String sourceFileName,
        long line,
        long charPosition,
        NonArrayFieldTypeNode nonArrayFieldTypeNode,
        long dimensions
    ) {
        super(sourceFileName, line, charPosition);
        this.nonArrayFieldTypeNode = nonArrayFieldTypeNode;
        this.dimensions = dimensions;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//ArrayFieldTypeNode\n");
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }

}