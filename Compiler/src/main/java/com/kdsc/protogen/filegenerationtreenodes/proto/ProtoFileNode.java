package com.kdsc.protogen.filegenerationtreenodes.proto;

import com.kdsc.protogen.filegenerationtreenodes.FileNode;
import com.kdsc.protogen.nodes.FormattedStringOptions;

public abstract class ProtoFileNode extends FileNode {

    public ProtoFileNode(final String fileName, final String path) {
        super(fileName, path);
    }

    @Override
    public String toFormattedString(final FormattedStringOptions formattedStringOptions, final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, ProtoFileNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(formattedStringOptions, 0));
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

}