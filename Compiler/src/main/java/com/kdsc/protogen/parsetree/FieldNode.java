package com.kdsc.protogen.parsetree;

public class FieldNode extends BaseParseTreeNode {

    private final FieldNameNode fieldNameNode;
    private final FieldTypeNode fieldTypeNode;

    public FieldNode(
        String sourceFileName,
        long line,
        long charPosition,
        FieldNameNode fieldNameNode,
        FieldTypeNode fieldTypeNode
    ) {
        super(sourceFileName, line, charPosition);
        this.fieldNameNode = fieldNameNode;
        this.fieldTypeNode = fieldTypeNode;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//FieldNode\n");
        stringBuilder.append(fieldNameNode.toFormattedString(1));
        stringBuilder.append(fieldTypeNode.toFormattedString(1));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}