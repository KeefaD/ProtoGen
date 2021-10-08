package com.kdsc.protogen.antlr;

import com.kdsc.protogen.parsetree.FileNode;

import java.util.*;

public record ParserResults(
    List<ParserError> parserErrors,
    List<FileNode> fileNodes
) {

    public ParserResults {
        Objects.requireNonNull(parserErrors);
        Objects.requireNonNull(fileNodes);
    }

    public boolean hasParserErrorOccurred() {
        return !parserErrors.isEmpty();
    }

}