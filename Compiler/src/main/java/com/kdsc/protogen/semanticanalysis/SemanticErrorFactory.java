package com.kdsc.protogen.semanticanalysis;

import com.kdsc.protogen.parsetree.BaseNode;

public class SemanticErrorFactory {

    public static SemanticError createSemanticError(SemanticErrorType semanticErrorType, BaseNode node, Object... arguments) {
        return new SemanticError(semanticErrorType, node.getSourceFileName(), node.getLine(), node.getCharPosition(), arguments);
    }
}
