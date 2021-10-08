package com.kdsc.protogen.semanticanalysis;

import com.kdsc.protogen.parsetreenodes.BaseParseTreeNode;

public final class SemanticErrorFactory {

    public static SemanticError createSemanticError(final SemanticErrorType semanticErrorType, final BaseParseTreeNode node, final Object... arguments) {
        return new SemanticError(semanticErrorType, node.getSourceFileName(), node.getLine(), node.getCharPosition(), arguments);
    }

}