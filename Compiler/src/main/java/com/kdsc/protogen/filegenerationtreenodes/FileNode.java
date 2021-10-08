package com.kdsc.protogen.filegenerationtreenodes;

import com.kdsc.protogen.nodes.FormattedStringOptions;
import com.kdsc.protogen.utils.parameterchecking.Strings;

import java.io.File;
import java.util.Objects;

public class FileNode extends BaseFileGenerationTreeNode {

    private final String fileName;
    private final String path;

    public FileNode(final String fileName, final String path) {
        Objects.requireNonNull(fileName);
        Objects.requireNonNull(path);
        Strings.requireNonBlank(fileName);
        //TODO:KMD Not sure about non blank for path, perhaps it is acceptable
//        Strings.requireNonBlank(path);
        this.fileName = fileName;
        //TODO:KMD Need to be careful with this path, it should be machine independent as in not matter about windows / linux otherwise it could cause problems with type libraries
        this.path = path;
    }

    //TODO:KMD Perhaps we should centralise this separator
    //TODO:KMD Shit name
    public String getPathAndFileName() {
        return path == null || path.isBlank()
            ? fileName
            : path + File.separator + fileName;
    }

    @Override
    public String toFormattedString(final int indentationLevel, final FormattedStringOptions formattedStringOptions) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, formattedStringOptions, FileNode.class);
        superToFormattedStringSuper(stringBuilder, formattedStringOptions, super.toFormattedString(0, formattedStringOptions));
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "FileName", fileName);
        fieldToFormattedStringField(stringBuilder, formattedStringOptions, "Path", path);
        return indentString(stringBuilder, formattedStringOptions, indentationLevel);
    }

}