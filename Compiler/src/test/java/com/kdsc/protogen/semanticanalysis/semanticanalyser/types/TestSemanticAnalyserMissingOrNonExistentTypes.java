package com.kdsc.protogen.semanticanalysis.semanticanalyser.types;

import com.kdsc.protogen.BaseCompilerTest;
import org.junit.jupiter.api.Test;

import static com.kdsc.protogen.semanticanalysis.SemanticError.SEMANTIC_ERROR_MESSAGE;
import static com.kdsc.protogen.semanticanalysis.SemanticErrorType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public final class TestSemanticAnalyserMissingOrNonExistentTypes extends BaseCompilerTest {

    @Test
    public void testMissingObjectInField() {
        var testProgram = """
            type TestNamespace.Type {
                testField : TestNamespace.TestType1
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(UNKNOWN_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 20, UNKNOWN_OBJECT.getMessage("TestNamespace.TestType1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testMissingObjectInNestedField() {
        var testProgram = """
            type TestNamespace.Type {
                testField : map<TestNamespace.TestType1, TestNamespace.TestType2>
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(UNKNOWN_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 24, UNKNOWN_OBJECT.getMessage("TestNamespace.TestType1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(UNKNOWN_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 49, UNKNOWN_OBJECT.getMessage("TestNamespace.TestType2")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testSameMissingObjectInNestedField() {
        var testProgram = """
            type TestNamespace.Type {
                testField : map<TestNamespace.TestType1, TestNamespace.TestType1>
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(UNKNOWN_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 24, UNKNOWN_OBJECT.getMessage("TestNamespace.TestType1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(UNKNOWN_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 49, UNKNOWN_OBJECT.getMessage("TestNamespace.TestType1")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testMissingObjectInImplementsListField() {
        var testProgram = """
            type TestNamespace.TestType1<T>
            
            type TestNamespace.Type : TestNamespace.TestType1<TestNamespace.TestType2>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(UNKNOWN_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 54, UNKNOWN_OBJECT.getMessage("TestNamespace.TestType2")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testMissingObjectInImplementsListNestedField() {
        var testProgram = """
            type TestNamespace.TestType1<T>
            
            type TestNamespace.Type : TestNamespace.TestType1<map<TestNamespace.TestType2, TestNamespace.TestType3>>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(UNKNOWN_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 58, UNKNOWN_OBJECT.getMessage("TestNamespace.TestType2")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(UNKNOWN_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 83, UNKNOWN_OBJECT.getMessage("TestNamespace.TestType3")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testMissingSameObjectInImplementsListNestedField() {
        var testProgram = """
            type TestNamespace.TestType1<T>
            
            type TestNamespace.Type : TestNamespace.TestType1<map<TestNamespace.TestType2, TestNamespace.TestType2>>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(UNKNOWN_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 58, UNKNOWN_OBJECT.getMessage("TestNamespace.TestType2")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(UNKNOWN_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 83, UNKNOWN_OBJECT.getMessage("TestNamespace.TestType2")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testTypeRefersToNonExistentTypeInImplementsList() {
        var testProgram = """
            type TestNamespace.Type : TestNamespace.Type1
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(TYPE_REFERS_TO_NON_EXISTENT_TYPE_IN_IMPLEMENTS_LIST.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 30, TYPE_REFERS_TO_NON_EXISTENT_TYPE_IN_IMPLEMENTS_LIST.getMessage("TestNamespace.Type", "TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testTypeRefersToNonExistentTypeInSecondPositionInImplementsList() {
        var testProgram = """
            type TestNamespace.Type1
            type TestNamespace.Type : TestNamespace.Type1, TestNamespace.Type2
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(TYPE_REFERS_TO_NON_EXISTENT_TYPE_IN_IMPLEMENTS_LIST.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 51, TYPE_REFERS_TO_NON_EXISTENT_TYPE_IN_IMPLEMENTS_LIST.getMessage("TestNamespace.Type", "TestNamespace.Type2")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testTypeParameterBoundsRefersToNonExistentTypeOnce() {
        var testProgram = """
            type TestNamespace.Type<T : TestNamespace.Type1>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(UNKNOWN_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 32, UNKNOWN_OBJECT.getMessage("TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testTypeParameterBoundsRefersToNonExistentTypeTwice() {
        var testProgram = """
            type TestNamespace.Type<T : TestNamespace.Type1 & TestNamespace.Type2>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(UNKNOWN_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 32, UNKNOWN_OBJECT.getMessage("TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(UNKNOWN_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 54, UNKNOWN_OBJECT.getMessage("TestNamespace.Type2")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

}