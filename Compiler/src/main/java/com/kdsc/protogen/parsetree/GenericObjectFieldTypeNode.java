package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.parsetree.fieldtypenodes.NonArrayFieldTypeNode;

public class GenericObjectFieldTypeNode extends NonArrayFieldTypeNode {

    private final GenericParameterWithoutBoundsNode genericParameterWithoutBoundsNode;

    public GenericObjectFieldTypeNode(
        String sourceFileName,
        long line,
        long charPosition,
        GenericParameterWithoutBoundsNode genericParameterWithoutBoundsNode
    ) {
        super(sourceFileName, line, charPosition);
        this.genericParameterWithoutBoundsNode = genericParameterWithoutBoundsNode;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//GenericObjectFieldTypeNode\n");
        stringBuilder.append(genericParameterWithoutBoundsNode.toFormattedString(1));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}