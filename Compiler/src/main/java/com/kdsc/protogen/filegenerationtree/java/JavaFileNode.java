package com.kdsc.protogen.filegenerationtree.java;

import com.kdsc.protogen.filegenerationtree.FileNode;

public class JavaFileNode extends FileNode {

    public JavaFileNode(final String fileName, final String path) {
        super(fileName, path);
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, JavaFileNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        return indentString(stringBuilder, indentationLevel);
    }

}