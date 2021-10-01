package com.kdsc.protogen.semanticanalysis;

import com.kdsc.protogen.parsetree.BaseNode;

//TODO:KMD Do we really need this class?
public class SemanticErrorFactory {

    public static SemanticError createSemanticError(final SemanticErrorType semanticErrorType, final BaseNode node, final Object... arguments) {
        return new SemanticError(semanticErrorType, node.getSourceFileName(), node.getLine(), node.getCharPosition(), arguments);
    }

}