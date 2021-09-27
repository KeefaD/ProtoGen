package com.kdsc.protogen.filegenerationtree.java;

import com.kdsc.protogen.filegenerationtree.FileNode;

public class JavaFileNode extends FileNode {

    public JavaFileNode(final String fileName, final String path) {
        super(fileName, path);
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//JavaFileNode\n");
        stringBuilder.append(super.toFormattedString(1));
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }

}