package com.kdsc.protogen.filegenerationtree.proto;

public class EnumFileNode extends ProtoFileNode {

    public EnumFileNode(final String fileName, final String path) {
        super(fileName, path);
        //TODO:KMD Pre-conditions
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//EnumFileNode\n");
        stringBuilder.append(super.toFormattedString(1));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }

}