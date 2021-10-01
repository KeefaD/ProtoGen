package com.kdsc.protogen.antlr;

import com.kdsc.protogen.parsetree.FileNode;

import java.util.ArrayList;
import java.util.List;

public class ParserResults {

    private final List<ParserError> parserErrors = new ArrayList<>();
    private final List<FileNode> fileNodes = new ArrayList<>();

    public List<ParserError> getParserErrors() {
        return parserErrors;
    }

    public List<FileNode> getFileNodes() {
        return fileNodes;
    }

    public boolean hasParserErrorOccurred() {
        return parserErrors.size() != 0;
    }

}