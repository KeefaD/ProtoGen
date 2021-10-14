package com.kdsc.protogen.semanticanalysis.semanticanalyser.types;

import com.kdsc.protogen.BaseCompilerTest;
import org.junit.jupiter.api.Test;

import static com.kdsc.protogen.semanticanalysis.SemanticError.SEMANTIC_ERROR_MESSAGE;
import static com.kdsc.protogen.semanticanalysis.SemanticErrorType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public final class TestSemanticAnalyserTypeVersionsMissingOrNonExistentTypes extends BaseCompilerTest {

    @Test
    public void testMissingObjectInVersionField() {
        var testProgram = """
            type TestNamespace.Type {
                version 1 {
                    testField : TestNamespace.TestType1
                }
                version 2 {
                    testField : TestNamespace.TestType1
                }
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(UNKNOWN_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 24, UNKNOWN_OBJECT.getMessage("TestNamespace.TestType1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(UNKNOWN_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 6, 24, UNKNOWN_OBJECT.getMessage("TestNamespace.TestType1")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testMissingObjectInNestedVersionField() {
        var testProgram = """
            type TestNamespace.Type {
                version 1 {
                    testField : map<TestNamespace.TestType1, TestNamespace.TestType2>
                }
                version 2 {
                    testField : map<TestNamespace.TestType1, TestNamespace.TestType2>
                }
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(4, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(UNKNOWN_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 28, UNKNOWN_OBJECT.getMessage("TestNamespace.TestType1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(UNKNOWN_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 53, UNKNOWN_OBJECT.getMessage("TestNamespace.TestType2")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(UNKNOWN_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 6, 28, UNKNOWN_OBJECT.getMessage("TestNamespace.TestType1")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(UNKNOWN_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 6, 53, UNKNOWN_OBJECT.getMessage("TestNamespace.TestType2")),
            semanticErrors.get(3).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testMissingObjectInVersionImplementsListField() {
        var testProgram = """
            type TestNamespace.TestType1<T>
            
            type TestNamespace.Type {
                version 1 : TestNamespace.TestType1<TestNamespace.TestType2>
                version 2 : TestNamespace.TestType1<TestNamespace.TestType3>
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(UNKNOWN_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 44, UNKNOWN_OBJECT.getMessage("TestNamespace.TestType2")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(UNKNOWN_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 44, UNKNOWN_OBJECT.getMessage("TestNamespace.TestType3")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testMissingObjectInVersionImplementsListNestedField() {
        var testProgram = """
            type TestNamespace.TestType1<T>
            
            type TestNamespace.Type {
                version 1 : TestNamespace.TestType1<map<TestNamespace.TestType2, TestNamespace.TestType3>>
                version 2 : TestNamespace.TestType1<map<TestNamespace.TestType3, TestNamespace.TestType4>>
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(4, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(UNKNOWN_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 48, UNKNOWN_OBJECT.getMessage("TestNamespace.TestType2")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(UNKNOWN_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 73, UNKNOWN_OBJECT.getMessage("TestNamespace.TestType3")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(UNKNOWN_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 48, UNKNOWN_OBJECT.getMessage("TestNamespace.TestType3")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(UNKNOWN_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 73, UNKNOWN_OBJECT.getMessage("TestNamespace.TestType4")),
            semanticErrors.get(3).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

}