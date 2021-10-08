package com.kdsc.protogen.antlr;

import com.kdsc.protogen.parsetreenodes.FileNode;

import java.util.*;

public final record ParserResults(
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