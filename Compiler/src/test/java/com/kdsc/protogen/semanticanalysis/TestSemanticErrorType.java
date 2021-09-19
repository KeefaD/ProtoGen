package com.kdsc.protogen.semanticanalysis;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestSemanticErrorType {

    @Test
    public void testGetNumber() {
        var semanticErrorType = SemanticErrorType.REDEFINITION_OF_OBJECT;
        assertEquals(1, semanticErrorType.getNumber(), "Unexpected semantic error message number");
    }

    @Test
    public void testGetMessage() {
        var semanticErrorType = SemanticErrorType.REDEFINITION_OF_OBJECT;
        assertNotNull(semanticErrorType.getMessage("Dummy argument"), "Got null for semantic error message");
    }

}