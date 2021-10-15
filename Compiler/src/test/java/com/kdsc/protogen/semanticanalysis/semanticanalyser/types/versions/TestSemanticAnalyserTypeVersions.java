package com.kdsc.protogen.semanticanalysis.semanticanalyser.types.versions;

import com.kdsc.protogen.BaseCompilerTest;
import org.junit.jupiter.api.Test;

import static com.kdsc.protogen.semanticanalysis.SemanticError.SEMANTIC_ERROR_MESSAGE;
import static com.kdsc.protogen.semanticanalysis.SemanticErrorType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public final class TestSemanticAnalyserTypeVersions extends BaseCompilerTest {

    @Test
    public void testRedefinitionOfVersionNumberOneForType() {
        var testProgram = """
            type TestNamespace.Type {
                version 1
                version 1
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_TYPE_VERSION.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 16, REDEFINITION_OF_TYPE_VERSION.getMessage(1)),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testRedefinitionOfVersionNumberTwoForType() {
        var testProgram = """
            type TestNamespace.Enum {
                version 2
                version 2
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_TYPE_VERSION.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 16, REDEFINITION_OF_TYPE_VERSION.getMessage(2)),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testRedefinitionOfVersionNumberThreeForType() {
        var testProgram = """
            type TestNamespace.Enum {
                version 1
                version 3
                version 3
                version 3
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_TYPE_VERSION.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 16, REDEFINITION_OF_TYPE_VERSION.getMessage(3)),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_TYPE_VERSION.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 16, REDEFINITION_OF_TYPE_VERSION.getMessage(3)),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

}