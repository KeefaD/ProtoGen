package com.kdsc.protogen.filegenerationtree;

import com.kdsc.protogen.utils.parameterchecking.Strings;

import java.io.File;
import java.util.Objects;

public class FileNode extends BaseNode {

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
        stringBuilder.append("//FileNode\n");
        stringBuilder.append(oneIndent() + "FileName : " + fileName + "\n");
        stringBuilder.append(oneIndent() + "Path : " + path + "\n");
        //TODO:KMD Not sure about having a derived field here, it might set a precedent but it will be handy
        stringBuilder.append(oneIndent() + "FileNameAndPath : " + getPathAndFileName() + "\n");
        var outputString = stringBuilder.toString();
        return outputString.indent(indentationLevel * INDENTATION_SPACE_COUNT);
    }

}