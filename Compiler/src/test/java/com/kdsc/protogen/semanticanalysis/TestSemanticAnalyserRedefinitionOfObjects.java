package com.kdsc.protogen.semanticanalysis;

import com.kdsc.protogen.BaseCompilerTest;
import org.junit.jupiter.api.Test;

import static com.kdsc.protogen.semanticanalysis.SemanticError.SEMANTIC_ERROR_MESSAGE;
import static com.kdsc.protogen.semanticanalysis.SemanticErrorType.REDEFINITION_OF_OBJECT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public final class TestSemanticAnalyserRedefinitionOfObjects extends BaseCompilerTest {

    @Test
    public void testRedefinitionOfType() {
        var testProgram = """
            type TestNamespace.Type
            type TestNamespace.Type
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 4, REDEFINITION_OF_OBJECT.getMessage("TestNamespace.Type")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testRedefinitionOfKey() {
        var testProgram = """
            key TestNamespace.Key
            key TestNamespace.Key
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 4, REDEFINITION_OF_OBJECT.getMessage("TestNamespace.Key")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testRedefinitionOfEnum() {
        var testProgram = """
            enum TestNamespace.Enum {
                enumCase1
            }
            enum TestNamespace.Enum {
                enumCase1
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 4, REDEFINITION_OF_OBJECT.getMessage("TestNamespace.Enum")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testRedefinitionOfTypeTwice() {
        var testProgram = """
            type TestNamespace.Type
            type TestNamespace.Type
            type TestNamespace.Type
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 4, REDEFINITION_OF_OBJECT.getMessage("TestNamespace.Type")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 4, REDEFINITION_OF_OBJECT.getMessage("TestNamespace.Type")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testRedefinitionOfKeyTwice() {
        var testProgram = """
            key TestNamespace.Key
            key TestNamespace.Key
            key TestNamespace.Key
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 4, REDEFINITION_OF_OBJECT.getMessage("TestNamespace.Key")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 4, REDEFINITION_OF_OBJECT.getMessage("TestNamespace.Key")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testRedefinitionOfEnumTwice() {
        var testProgram = """
            enum TestNamespace.Enum {
                enumCase1
            }
            enum TestNamespace.Enum {
                enumCase1
            }
            enum TestNamespace.Enum {
                enumCase1
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 4, REDEFINITION_OF_OBJECT.getMessage("TestNamespace.Enum")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 7, 4, REDEFINITION_OF_OBJECT.getMessage("TestNamespace.Enum")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testRedefinitionOfTypeAsKey() {
        var testProgram = """
            type TestNamespace.Type
            key TestNamespace.Type
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 4, REDEFINITION_OF_OBJECT.getMessage("TestNamespace.Type")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testRedefinitionOfTypeAsEnum() {
        var testProgram = """
            type TestNamespace.Type
            enum TestNamespace.Type {
                enumCase1
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 4, REDEFINITION_OF_OBJECT.getMessage("TestNamespace.Type")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testRedefinitionOfKeyAsType() {
        var testProgram = """
            key TestNamespace.Key
            type TestNamespace.Key
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 4, REDEFINITION_OF_OBJECT.getMessage("TestNamespace.Key")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testRedefinitionOfKeyAsEnum() {
        var testProgram = """
            key TestNamespace.Key
            enum TestNamespace.Key {
                enumCase1
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 4, REDEFINITION_OF_OBJECT.getMessage("TestNamespace.Key")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testRedefinitionOfEnumAsType() {
        var testProgram = """
            enum TestNamespace.Enum {
                enumCase1
            }
            type TestNamespace.Enum
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 4, REDEFINITION_OF_OBJECT.getMessage("TestNamespace.Enum")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testRedefinitionOfEnumAsKey() {
        var testProgram = """
            enum TestNamespace.Enum {
                enumCase1
            }
            key TestNamespace.Enum
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 4, REDEFINITION_OF_OBJECT.getMessage("TestNamespace.Enum")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

}