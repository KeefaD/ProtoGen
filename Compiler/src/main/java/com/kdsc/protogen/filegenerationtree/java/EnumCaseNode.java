package com.kdsc.protogen.filegenerationtree.java;

import com.kdsc.protogen.filegenerationtree.BaseNode;

public class EnumCaseNode extends BaseNode {

    private final String name;

    public EnumCaseNode(
        final String name
    ) {
        //TODO:KMD Don't like the name name
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//EnumCaseNode\n");
        stringBuilder.append(oneIndent() + "Name : " + name);
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }

}