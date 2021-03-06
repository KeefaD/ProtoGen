package com.kdsc.protogen.semanticanalysis;

import org.junit.jupiter.api.Test;

import static com.kdsc.protogen.semanticanalysis.SemanticError.SEMANTIC_ERROR_MESSAGE;
import static com.kdsc.protogen.semanticanalysis.SemanticErrorType.REDEFINITION_OF_OBJECT;
import static org.junit.jupiter.api.Assertions.*;

public final class TestSemanticError {

    public static final String TEST_FILE_NAME = "TestFileName";

    @Test
    //CANTFIX IntelliJ seems to be getting confused about var args here
    public void testInvalidConstructorCall() {
        assertThrows(NullPointerException.class,
            () ->
            new SemanticError(
                null,
                "TestFileName",
                1,
                0
            )
        );

        assertThrows(NullPointerException.class,
            () ->
            new SemanticError(
                REDEFINITION_OF_OBJECT,
                null,
                1,
                0
            )
        );

        assertThrows(IllegalArgumentException.class,
            () ->
            new SemanticError(
                REDEFINITION_OF_OBJECT,
                TEST_FILE_NAME,
                0,
                0
            )
        );

        assertThrows(IllegalArgumentException.class,
            () ->
            new SemanticError(
                REDEFINITION_OF_OBJECT,
                TEST_FILE_NAME,
                0,
                -1
            )
        );
    }

    @Test
    public void testGetFullErrorMessage() {

         var semanticError = new SemanticError(
             REDEFINITION_OF_OBJECT,
            "TestFileName",
            1,
            0,
            "Test Message Argument"
         );

         assertEquals(SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_OBJECT.getNumber(), TEST_FILE_NAME, 1, 0, REDEFINITION_OF_OBJECT.getMessage("Test Message Argument")), semanticError.getFullErrorMessage(), "Unexpected semantic error message");
    }

}