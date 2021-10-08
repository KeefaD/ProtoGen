package com.kdsc.protogen.semanticanalysis;

import com.kdsc.protogen.parsetree.BaseTestNode;
import com.kdsc.protogen.parsetree.TestProtoGenEnumNode;
import org.junit.jupiter.api.Test;

import static com.kdsc.protogen.semanticanalysis.SemanticError.SEMANTIC_ERROR_MESSAGE;
import static com.kdsc.protogen.semanticanalysis.SemanticErrorType.REDEFINITION_OF_OBJECT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public final class TestSemanticErrorFactory {

    @Test
    public void testCreateSemanticError() {
        var semanticError = SemanticErrorFactory.createSemanticError(SemanticErrorType.REDEFINITION_OF_OBJECT, TestProtoGenEnumNode.createPopulatedTestNode(), "Namespace.Name");
        assertNotNull(semanticError.semanticErrorType(), "Unexpected null for semantic error type");
        assertEquals(BaseTestNode.fileName, semanticError.sourceFileName(), "Unexpected file name");
        assertEquals(BaseTestNode.line, semanticError.line(), "Unexpected line number");
        assertEquals(BaseTestNode.charPosition, semanticError.charPosition(), "Unexpected file name");
        assertEquals(SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_OBJECT.getNumber(), BaseTestNode.fileName, 1, 0, REDEFINITION_OF_OBJECT.getMessage("Namespace.Name")), semanticError.getFullErrorMessage(), "Unexpected semantic error message");
    }
    
}