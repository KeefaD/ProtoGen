package com.kdsc.protogen.semanticanalysis;

import com.kdsc.protogen.BaseCompilerTest;
import org.junit.jupiter.api.Test;

import static com.kdsc.protogen.semanticanalysis.SemanticError.SEMANTIC_ERROR_MESSAGE;
import static com.kdsc.protogen.semanticanalysis.SemanticErrorType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public final class TestSemanticAnalyserTypes extends BaseCompilerTest {

    @Test
    public void testDirectInheritanceLoop() {
        var testProgram = """            
            type TestNamespace.Type : TestNamespace.Type {
                version 1
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type->TestNamespace.Type", "TestNamespace.Type")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testIndirectInheritanceLoop() {
        var testProgram = """
            type TestNamespace.Type1 : TestNamespace.Type
                   
            type TestNamespace.Type : TestNamespace.Type1
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type1->TestNamespace.Type->TestNamespace.Type1", "TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type->TestNamespace.Type1->TestNamespace.Type", "TestNamespace.Type")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testIndirectInheritanceLoopTwoLevels() {
        var testProgram = """
            type TestNamespace.Type1 : TestNamespace.Type
            
            type TestNamespace.Type2 : TestNamespace.Type1
                   
            type TestNamespace.Type : TestNamespace.Type2
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(3, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type1->TestNamespace.Type->TestNamespace.Type2->TestNamespace.Type1", "TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type2->TestNamespace.Type1->TestNamespace.Type->TestNamespace.Type2", "TestNamespace.Type2")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type->TestNamespace.Type2->TestNamespace.Type1->TestNamespace.Type", "TestNamespace.Type")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testInheritanceLoopSecondInterface() {
        var testProgram = """
            type interface TestNamespace.Type1
            
            type interface TestNamespace.Type2 : TestNamespace.Type
            
            type interface TestNamespace.Type3 : TestNamespace.Type2
            
            type interface TestNamespace.Type4 : TestNamespace.Type3
                   
            type interface TestNamespace.Type : TestNamespace.Type1, TestNamespace.Type3
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(4, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type2->TestNamespace.Type->TestNamespace.Type3->TestNamespace.Type2", "TestNamespace.Type2")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type3->TestNamespace.Type2->TestNamespace.Type->TestNamespace.Type3", "TestNamespace.Type3")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 7, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type4->TestNamespace.Type3->TestNamespace.Type2->TestNamespace.Type->TestNamespace.Type3", "TestNamespace.Type4")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 9, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type->TestNamespace.Type3->TestNamespace.Type2->TestNamespace.Type", "TestNamespace.Type")),
            semanticErrors.get(3).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

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
    public void testRedefinitionOfVersionGenericParameterOnce() {
        var testProgram = """
            type TestNamespace.Type<T, T>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_GENERIC_PARAMETER.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 31, REDEFINITION_OF_GENERIC_PARAMETER.getMessage("TestNamespace.Type", "T")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testRedefinitionOfVersionGenericParameterTwice() {
        var testProgram = """
            type TestNamespace.Type<T, T, T>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_GENERIC_PARAMETER.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 31, REDEFINITION_OF_GENERIC_PARAMETER.getMessage("TestNamespace.Type", "T")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_GENERIC_PARAMETER.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 34, REDEFINITION_OF_GENERIC_PARAMETER.getMessage("TestNamespace.Type", "T")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testTypeParameterBoundsRefersToNonExistentTypeOnce() {
        var testProgram = """
            type TestNamespace.Type<T & TestNamespace.Type1>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_BOUNDS_REFERS_TO_NON_EXISTENT_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 32, GENERIC_PARAMETER_BOUNDS_REFERS_TO_NON_EXISTENT_TYPE.getMessage("T", "TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testTypeParameterBoundsRefersToNonExistentTypeTwice() {
        var testProgram = """
            type TestNamespace.Type<T & TestNamespace.Type1 & TestNamespace.Type2>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_BOUNDS_REFERS_TO_NON_EXISTENT_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 32, GENERIC_PARAMETER_BOUNDS_REFERS_TO_NON_EXISTENT_TYPE.getMessage("T", "TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_BOUNDS_REFERS_TO_NON_EXISTENT_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 54, GENERIC_PARAMETER_BOUNDS_REFERS_TO_NON_EXISTENT_TYPE.getMessage("T", "TestNamespace.Type2")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testTypeParameterBoundsRefersToSameTypeTwice() {
        var testProgram = """
            type TestNamespace.Type1
            type TestNamespace.Type<T & TestNamespace.Type1 & TestNamespace.Type1>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 54, GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getMessage("T", "TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testTypeParameterBoundsRefersToSameTypeThreeTimes() {
        var testProgram = """
            type TestNamespace.Type1
            type TestNamespace.Type<T & TestNamespace.Type1 & TestNamespace.Type1 & TestNamespace.Type1>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 54, GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getMessage("T", "TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 76, GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getMessage("T", "TestNamespace.Type1")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterHasNotBeenDefinedInTypeOnce() {
        var testProgram = """
            type TestNamespace.Type1<T>
            type TestNamespace.Type<T> : TestNamespace.Type1<T2>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 53, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterHasNotBeenDefinedInTypeOnceDouble() {
        var testProgram = """
            type TestNamespace.Type1<T1, T2>
            type TestNamespace.Type<T> : TestNamespace.Type1<T2, T3>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 53, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 57, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T3", "TestNamespace.Type")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterHasNotBeenDefinedInTypeTwice() {
        var testProgram = """
            type TestNamespace.Type1<T>
            type interface TestNamespace.Type2<T>
            type TestNamespace.Type<T> : TestNamespace.Type1<T2>, TestNamespace.Type2<T2>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 53, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 78, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterHasNotBeenDefinedInTypeTwiceDouble() {
        var testProgram = """
            type TestNamespace.Type1<T1, T2>
            type interface TestNamespace.Type2<T1, T2>
            type TestNamespace.Type<T> : TestNamespace.Type1<T2, T3>, TestNamespace.Type2<T2, T4>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(4, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 53, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 57, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T3", "TestNamespace.Type")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 82, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 86, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T4", "TestNamespace.Type")),
            semanticErrors.get(3).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterHasNotBeenDefinedInTypeTwiceDoubleMap() {
        var testProgram = """
            type TestNamespace.Type1<T1, T2>
            type interface TestNamespace.Type2<T1, T2>
            type TestNamespace.Type<T> : TestNamespace.Type1<map<T2, T2>, map<T3, T3>>, TestNamespace.Type2<map<T2, T2>, map<T4, T4>>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(8, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 57, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 61, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 70, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T3", "TestNamespace.Type")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 74, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T3", "TestNamespace.Type")),
            semanticErrors.get(3).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 104, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type")),
            semanticErrors.get(4).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 108, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type")),
            semanticErrors.get(5).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 117, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T4", "TestNamespace.Type")),
            semanticErrors.get(6).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 121, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T4", "TestNamespace.Type")),
            semanticErrors.get(7).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testExtendNonInterfaceTypeWithInterface() {
        var testProgram = """
            type TestNamespace.Type1
            type interface TestNamespace.Type : TestNamespace.Type1
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(EXTENDING_INTERFACE_WITH_NON_INTERFACE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 40, EXTENDING_INTERFACE_WITH_NON_INTERFACE.getMessage("TestNamespace.Type", "TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testExtendNonInterfaceTypeWithInterfaceTwice() {
        var testProgram = """
            type TestNamespace.Type1
            type TestNamespace.Type2
            type interface TestNamespace.Type : TestNamespace.Type1, TestNamespace.Type2
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(EXTENDING_INTERFACE_WITH_NON_INTERFACE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 40, EXTENDING_INTERFACE_WITH_NON_INTERFACE.getMessage("TestNamespace.Type", "TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(EXTENDING_INTERFACE_WITH_NON_INTERFACE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 61, EXTENDING_INTERFACE_WITH_NON_INTERFACE.getMessage("TestNamespace.Type", "TestNamespace.Type2")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testExtendNonInterfaceTypeWithInterfaceSecondInList() {
        var testProgram = """
            type interface TestNamespace.Type1
            type TestNamespace.Type2
            type interface TestNamespace.Type : TestNamespace.Type1, TestNamespace.Type2
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(EXTENDING_INTERFACE_WITH_NON_INTERFACE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 61, EXTENDING_INTERFACE_WITH_NON_INTERFACE.getMessage("TestNamespace.Type", "TestNamespace.Type2")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testMoreThanOneNonInterfaceTypeInImplementsListTwo() {
        var testProgram = """
            type TestNamespace.Type1
            type TestNamespace.Type2
            type TestNamespace.Type : TestNamespace.Type1, TestNamespace.Type2
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 30, MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE.getMessage("TestNamespace.Type", "TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 51, MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE.getMessage("TestNamespace.Type", "TestNamespace.Type2")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testMoreThanOneNonInterfaceTypeInImplementsListThree() {
        var testProgram = """
            type TestNamespace.Type1
            type TestNamespace.Type2
            type TestNamespace.Type3
            type TestNamespace.Type : TestNamespace.Type1, TestNamespace.Type2, TestNamespace.Type3
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(3, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 30, MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE.getMessage("TestNamespace.Type", "TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 51, MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE.getMessage("TestNamespace.Type", "TestNamespace.Type2")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 72, MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE.getMessage("TestNamespace.Type", "TestNamespace.Type3")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testNumberOfGenericParametersInImplementsListItemDoesNotMatchTypeDefinitionZeroOnDefinitionOneInImplementsList() {
        var testProgram = """
            type TestNamespace.Type1
            type TestNamespace.Type<T> : TestNamespace.Type1<T>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 33, NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getMessage("TestNamespace.Type1", 1, 0)),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testNumberOfGenericParametersInImplementsListItemDoesNotMatchTypeDefinitionOneOnDefinitionZeroInImplementsList() {
        var testProgram = """
            type TestNamespace.Type1<T>
            type TestNamespace.Type<T> : TestNamespace.Type1
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 33, NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getMessage("TestNamespace.Type1", 0, 1)),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testNumberOfGenericParametersInImplementsListItemDoesNotMatchTypeDefinitionTwoOnDefinitionZeroInImplementsList() {
        var testProgram = """
            type TestNamespace.Type1<T1, T2>
            type TestNamespace.Type<T> : TestNamespace.Type1
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 33, NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getMessage("TestNamespace.Type1", 0, 2)),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testNumberOfGenericParametersInImplementsListItemDoesNotMatchTypeDefinitionThreeOnDefinitionTwoInImplementsList() {
        var testProgram = """
            type TestNamespace.Type1<T1, T2, T3>
            type TestNamespace.Type<T1, T2> : TestNamespace.Type1<T1, T2>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 38, NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getMessage("TestNamespace.Type1", 2, 3)),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testNumberOfGenericParametersInImplementsListItemDoesNotMatchTypeDefinitionZeroOnDefinitionOneInImplementsListSecondImplements() {
        var testProgram = """
            type interface TestNamespace.Type1
            type TestNamespace.Type2
            type TestNamespace.Type<T> : TestNamespace.Type1, TestNamespace.Type2<T>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 54, NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getMessage("TestNamespace.Type2", 1, 0)),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testNumberOfGenericParametersInImplementsListItemDoesNotMatchTypeDefinitionOneOnDefinitionZeroInImplementsListSecondImplements() {
        var testProgram = """
            type interface TestNamespace.Type1<T>
            type TestNamespace.Type2<T>
            type TestNamespace.Type<T> : TestNamespace.Type1<T>, TestNamespace.Type2
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3,57, NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getMessage("TestNamespace.Type2", 0, 1)),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testNumberOfGenericParametersInImplementsListItemDoesNotMatchTypeDefinitionTwoOnDefinitionZeroInImplementsListSecondImplements() {
        var testProgram = """
            type interface TestNamespace.Type1<T1, T2>
            type TestNamespace.Type2<T1, T2>
            type TestNamespace.Type<T1, T2> : TestNamespace.Type1<T1, T2>, TestNamespace.Type2
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 67, NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getMessage("TestNamespace.Type2", 0, 2)),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testNumberOfGenericParametersInImplementsListItemDoesNotMatchTypeDefinitionThreeOnDefinitionTwoInImplementsListSecondImplements() {
        var testProgram = """
            type interface TestNamespace.Type1<T1, T2, T3>
            type TestNamespace.Type2<T1, T2, T3>
            type TestNamespace.Type<T1, T2, T3> : TestNamespace.Type1<T1, T2, T3>, TestNamespace.Type2<T1, T2>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 75, NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getMessage("TestNamespace.Type2", 2, 3)),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testNumberOfGenericParametersInImplementsListItemDoesNotMatchTypeDefinitionOneNestedSetOnDefinitionTwoInImplementsList() {
        var testProgram = """
            type TestNamespace.Type1<T1, T2, T3>
            type TestNamespace.Type<T1, T2> : TestNamespace.Type1<set<T1>, T2>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 38, NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getMessage("TestNamespace.Type1", 2, 3)),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testNumberOfGenericParametersInImplementsListItemDoesNotMatchTypeDefinitionOneNestedMapOnDefinitionTwoInImplementsList() {
        var testProgram = """
            type TestNamespace.Type1<T1, T2, T3, T4>
            type TestNamespace.Type<T1, T2, T3> : TestNamespace.Type1<map<T1, T1>, T2, T3>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 42, NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getMessage("TestNamespace.Type1", 3, 4)),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testNumberOfGenericParametersInImplementsListItemDoesNotMatchTypeDefinitionOneNestedMapOnDefinitionTwoInImplementsListReusedParameters() {
        var testProgram = """
            type TestNamespace.Type1<T1, T2, T3>
            type TestNamespace.Type<T1, T2> : TestNamespace.Type1<map<T1, T1>, T2>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 38, NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getMessage("TestNamespace.Type1", 2, 3)),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterUsageDoesNotSatisfyTypeBounds() {
        var testProgram = """
            type interface TestNamespace.TypeInterface
            
            type TestNamespace.Type1<T1 : TestNamespace.TypeInterface>
            
            type TestNamespace.Type2
            
            type TestNamespace.Type : TestNamespace.Type1<TestNamespace.Type2>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 7, 50, SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getMessage("TestNamespace.Type2", "TestNamespace.TypeInterface")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterUsageDoesNotSatisfyTypeBoundsTwice() {
        var testProgram = """
            type interface TestNamespace.TypeInterface
            
            type interface TestNamespace.Type1<T1 : TestNamespace.TypeInterface>
            
            type TestNamespace.Type2
            
            type TestNamespace.Type3
            
            type TestNamespace.Type : TestNamespace.Type1<TestNamespace.Type2>, TestNamespace.Type1<TestNamespace.Type3>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 9, 50, SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getMessage("TestNamespace.Type2", "TestNamespace.TypeInterface")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 9, 92, SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getMessage("TestNamespace.Type3", "TestNamespace.TypeInterface")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterUsageDoesNotSatisfyTypeBoundsAsItIsNotATypeFieldTypeNode() {
        var testProgram = """
            type interface TestNamespace.TypeInterface
            
            type TestNamespace.Type1<T1 : TestNamespace.TypeInterface>
            
            type TestNamespace.Type : TestNamespace.Type1<int32>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(SPECIFIED_GENERIC_PARAMETER_MUST_BE_A_TYPE_AS_THE_PARAMETER_HAS_BOUNDS.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 50, SPECIFIED_GENERIC_PARAMETER_MUST_BE_A_TYPE_AS_THE_PARAMETER_HAS_BOUNDS.getMessage("int32", "TestNamespace.TypeInterface")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterUsageDoesNotSatisfyTwoTypeBounds() {
        var testProgram = """
            type interface TestNamespace.TypeInterface1
            
            type interface TestNamespace.TypeInterface2
            
            type TestNamespace.Type1<T1 : TestNamespace.TypeInterface1 & TestNamespace.TypeInterface2>
            
            type TestNamespace.Type2
            
            type TestNamespace.Type : TestNamespace.Type1<TestNamespace.Type2>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 9, 50, SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getMessage("TestNamespace.Type2", "TestNamespace.TypeInterface1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 9, 50, SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getMessage("TestNamespace.Type2", "TestNamespace.TypeInterface2")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterUsageDoesNotSatisfyTwoTypeBoundsTwice() {
        var testProgram = """
            type interface TestNamespace.TypeInterface1
            
            type interface TestNamespace.TypeInterface2
            
            type interface TestNamespace.Type1<T1 : TestNamespace.TypeInterface1 & TestNamespace.TypeInterface2>
            
            type TestNamespace.Type2
            
            type TestNamespace.Type3
            
            type TestNamespace.Type : TestNamespace.Type1<TestNamespace.Type2>, TestNamespace.Type1<TestNamespace.Type3>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertEquals(4, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 11, 50, SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getMessage("TestNamespace.Type2", "TestNamespace.TypeInterface1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 11, 50, SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getMessage("TestNamespace.Type2", "TestNamespace.TypeInterface2")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 11, 92, SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getMessage("TestNamespace.Type3", "TestNamespace.TypeInterface1")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 11, 92, SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getMessage("TestNamespace.Type3", "TestNamespace.TypeInterface2")),
            semanticErrors.get(3).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterUsageDoesNotSatisfyTwoTypeBoundsAsTheyAreNotATypeFieldTypeNode() {
        var testProgram = """
            type interface TestNamespace.TypeInterface1
            
            type interface TestNamespace.TypeInterface2
            
            type interface TestNamespace.Type1<T1 : TestNamespace.TypeInterface1 & TestNamespace.TypeInterface2>
            
            type TestNamespace.Type : TestNamespace.Type1<int32>, TestNamespace.Type1<string>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertEquals(4, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(SPECIFIED_GENERIC_PARAMETER_MUST_BE_A_TYPE_AS_THE_PARAMETER_HAS_BOUNDS.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 7, 50, SPECIFIED_GENERIC_PARAMETER_MUST_BE_A_TYPE_AS_THE_PARAMETER_HAS_BOUNDS.getMessage("int32", "TestNamespace.TypeInterface1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(SPECIFIED_GENERIC_PARAMETER_MUST_BE_A_TYPE_AS_THE_PARAMETER_HAS_BOUNDS.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 7, 50, SPECIFIED_GENERIC_PARAMETER_MUST_BE_A_TYPE_AS_THE_PARAMETER_HAS_BOUNDS.getMessage("int32", "TestNamespace.TypeInterface2")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(SPECIFIED_GENERIC_PARAMETER_MUST_BE_A_TYPE_AS_THE_PARAMETER_HAS_BOUNDS.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 7, 78, SPECIFIED_GENERIC_PARAMETER_MUST_BE_A_TYPE_AS_THE_PARAMETER_HAS_BOUNDS.getMessage("string", "TestNamespace.TypeInterface1")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(SPECIFIED_GENERIC_PARAMETER_MUST_BE_A_TYPE_AS_THE_PARAMETER_HAS_BOUNDS.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 7, 78, SPECIFIED_GENERIC_PARAMETER_MUST_BE_A_TYPE_AS_THE_PARAMETER_HAS_BOUNDS.getMessage("string", "TestNamespace.TypeInterface2")),
            semanticErrors.get(3).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

}