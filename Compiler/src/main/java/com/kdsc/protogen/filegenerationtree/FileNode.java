package com.kdsc.protogen.filegenerationtree;

import com.kdsc.protogen.utils.parameterchecking.Strings;

import java.util.Objects;

public class FileNode extends BaseNode {

    private final String fileName;
    private final String path;

    public FileNode(final String fileName, final String path) {
        Objects.requireNonNull(fileName);
        Objects.requireNonNull(path);
        Strings.requireNonBlank(fileName);
        Strings.requireNonBlank(path);
        this.fileName = fileName;
        this.path = path;
    }

    @Override
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("//FileNode\n");
        stringBuilder.append(oneIndent() + "FileName : " + fileName + "\n");
        stringBuilder.append(oneIndent() + "Path : " + path + "\n");
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }

}