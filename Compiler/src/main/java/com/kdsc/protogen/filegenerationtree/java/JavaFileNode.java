package com.kdsc.protogen.filegenerationtree.java;

import com.kdsc.protogen.filegenerationtree.FileNode;
import com.kdsc.protogen.nodes.FormattedStringOptions;

public class JavaFileNode extends FileNode {

    public JavaFileNode(final String fileName, final String path) {
        super(fileName, path);
    }

    @Override
    public String toFormattedString(final int indentationLevel, final FormattedStringOptions formattedStringOptions) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, JavaFileNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(0, formattedStringOptions));
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

}