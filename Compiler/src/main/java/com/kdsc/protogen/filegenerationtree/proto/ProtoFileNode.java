package com.kdsc.protogen.filegenerationtree.proto;

import com.kdsc.protogen.filegenerationtree.FileNode;

public abstract class ProtoFileNode extends FileNode {

    public ProtoFileNode(final String fileName, final String path) {
        super(fileName, path);
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, ProtoFileNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        return indentString(stringBuilder, indentationLevel);
    }

}