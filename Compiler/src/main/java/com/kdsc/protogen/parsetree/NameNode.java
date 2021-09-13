package com.kdsc.protogen.parsetree;

public class NameNode extends BaseParseTreeNode {

    private final String name;

    public NameNode(String name) {
        this.name = name;
    }

    @Override
    public String toFormattedString(int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//NameNode\n");
        stringBuilder.append(oneIndent() + "Name : " + name);
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }

}