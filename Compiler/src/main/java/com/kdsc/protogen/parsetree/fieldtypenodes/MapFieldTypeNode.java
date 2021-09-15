package com.kdsc.protogen.parsetree.fieldtypenodes;

public class MapFieldTypeNode extends NonArrayFieldTypeNode {

    private final FieldTypeNode keyFieldTypeNode;
    private final FieldTypeNode valueFieldTypeNode;

    public MapFieldTypeNode(
        String sourceFileName,
        long line,
        long charPosition,
        FieldTypeNode keyFieldTypeNode,
        FieldTypeNode valueFieldTypeNode
    ) {
        super(sourceFileName, line, charPosition);
        this.keyFieldTypeNode = keyFieldTypeNode;
        this.valueFieldTypeNode = valueFieldTypeNode;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//MapFieldTypeNode\n");
        stringBuilder.append(oneIndent() + "//Key\n");
        stringBuilder.append(keyFieldTypeNode.toFormattedString(2));
        stringBuilder.append(oneIndent() + "//Value\n");
        stringBuilder.append(valueFieldTypeNode.toFormattedString(2));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }
}