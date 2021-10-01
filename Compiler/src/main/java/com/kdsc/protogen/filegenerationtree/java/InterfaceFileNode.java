package com.kdsc.protogen.filegenerationtree.java;

public class InterfaceFileNode extends JavaFileNode {

    //TODO:KMD not sure about class / interface / enum file nodes for java, I"m not sure it is going to work
    public InterfaceFileNode(final String fileName, final String path) {
        super(fileName, path);
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//InterfaceFileNode\n");
        stringBuilder.append(super.toFormattedString(1));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }

}