package com.kdsc.protogen.filegenerationtree;

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
    public String toFormattedString(final int indentationLevel) {
        var stringBuilder = new StringBuilder();
        classToFormattedStringTitle(stringBuilder, FileNode.class);
        superToFormattedStringSuper(stringBuilder, super.toFormattedString(0));
        fieldToFormattedStringField(stringBuilder, "FileName", fileName);
        fieldToFormattedStringField(stringBuilder, "Path", path);
        return indentString(stringBuilder, indentationLevel);
    }

}