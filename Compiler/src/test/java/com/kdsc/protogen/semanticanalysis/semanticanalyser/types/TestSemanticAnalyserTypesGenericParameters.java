package com.kdsc.protogen.semanticanalysis.semanticanalyser.types;

import com.kdsc.protogen.BaseCompilerTest;
import org.junit.jupiter.api.Test;

import static com.kdsc.protogen.semanticanalysis.SemanticError.SEMANTIC_ERROR_MESSAGE;
import static com.kdsc.protogen.semanticanalysis.SemanticErrorType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public final class TestSemanticAnalyserTypesGenericParameters extends BaseCompilerTest {

    @Test
    public void testRedefinitionOfGenericParameterOnce() {
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
    public void testRedefinitionOfGenericParameterTwice() {
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
    public void testTypeParameterBoundsRefersToSameTypeTwice() {
        var testProgram = """
            type TestNamespace.Type1
            type TestNamespace.Type<T : TestNamespace.Type1 & TestNamespace.Type1>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 32, GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getMessage("T", "TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 54, GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getMessage("T", "TestNamespace.Type1")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testTypeParameterBoundsRefersToSameNestedSetTypeTwice() {
        var testProgram = """
            type TestNamespace.Type1
            type TestNamespace.Type<T : set<TestNamespace.Type1> & set<TestNamespace.Type1>>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 32, GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getMessage("T", "set<TestNamespace.Type1>")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 59, GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getMessage("T", "set<TestNamespace.Type1>")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testTypeParameterBoundsRefersToSameNestedListTypeTwice() {
        var testProgram = """
            type TestNamespace.Type1
            type TestNamespace.Type<T : list<TestNamespace.Type1> & list<TestNamespace.Type1>>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 32, GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getMessage("T", "list<TestNamespace.Type1>")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 60, GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getMessage("T", "list<TestNamespace.Type1>")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testTypeParameterBoundsRefersToSameNestedValueOrErrorTypeTwice() {
        var testProgram = """
            type TestNamespace.Type1
            type TestNamespace.Type<T : valueorerror<TestNamespace.Type1> & valueorerror<TestNamespace.Type1>>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 32, GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getMessage("T", "valueorerror<TestNamespace.Type1>")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 68, GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getMessage("T", "valueorerror<TestNamespace.Type1>")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testTypeParameterBoundsRefersToSameNestedMapTypeTwice() {
        var testProgram = """
            type TestNamespace.Type1
            type TestNamespace.Type<T : map<TestNamespace.Type1, TestNamespace.Type1> & map<TestNamespace.Type1, TestNamespace.Type1>>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 32, GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getMessage("T", "map<TestNamespace.Type1, TestNamespace.Type1>")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 80, GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getMessage("T", "map<TestNamespace.Type1, TestNamespace.Type1>")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testTypeParameterBoundsRefersToSameNestedGenericParameterTwice() {
        var testProgram = """
            type TestNamespace.Type1
            type TestNamespace.Type<T : set<T> & set<T>>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 32, GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getMessage("T", "set<T>")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 41, GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getMessage("T", "set<T>")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testTypeParameterBoundsRefersToSameTypeThreeTimes() {
        var testProgram = """
            type TestNamespace.Type1
            type TestNamespace.Type<T : TestNamespace.Type1 & TestNamespace.Type1 & TestNamespace.Type1>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(3, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 32, GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getMessage("T", "TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 54, GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getMessage("T", "TestNamespace.Type1")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 76, GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getMessage("T", "TestNamespace.Type1")),
            semanticErrors.get(2).getFullErrorMessage(),
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
            SEMANTIC_ERROR_MESSAGE.formatted(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 50, SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getMessage("int32", "TestNamespace.TypeInterface")),
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
            
            type interface TestNamespace.Type1<T : TestNamespace.TypeInterface1 & TestNamespace.TypeInterface2>
            
            type TestNamespace.Type : TestNamespace.Type1<int32>, TestNamespace.Type1<string>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertEquals(4, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 7, 50, SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getMessage("int32", "TestNamespace.TypeInterface1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 7, 50, SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getMessage("int32", "TestNamespace.TypeInterface2")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 7, 78, SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getMessage("string", "TestNamespace.TypeInterface1")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 7, 78, SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getMessage("string", "TestNamespace.TypeInterface2")),
            semanticErrors.get(3).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterUsageDoesNotSatisfyTypeBoundsNestedGeneric() {
        var testProgram = """          
            type interface TestNamespace.Type1<T : set<T>>
            
            type TestNamespace.Type2
            
            type TestNamespace.Type : TestNamespace.Type1<TestNamespace.Type2>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 50, SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getMessage("TestNamespace.Type2", "set<T>")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterUsageDoesNotSatisfyTypeBoundsNestedType() {
        var testProgram = """
            type interface TestNamespace.TypeInterface1
            
            type interface TestNamespace.Type1<T : set<TestNamespace.TypeInterface1>>
            
            type TestNamespace.Type2
            
            type TestNamespace.Type : TestNamespace.Type1<TestNamespace.Type2>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 7, 50, SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getMessage("TestNamespace.Type2", "set<TestNamespace.TypeInterface1>")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterUsageDoesNotSatisfyTypeBoundsDoubleNestedType() {
        var testProgram = """
            type interface TestNamespace.TypeInterface1
            
            type interface TestNamespace.Type1<T : set<set<TestNamespace.TypeInterface1>>>
            
            type TestNamespace.Type2
            
            type TestNamespace.Type : TestNamespace.Type1<TestNamespace.Type2>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 7, 50, SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getMessage("TestNamespace.Type2", "set<set<TestNamespace.TypeInterface1>>")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterUsageDoesNotSatisfyTypeBoundsNestedTypeNonExistentType() {
        var testProgram = """
            type interface TestNamespace.Type1<T : set<TestNamespace.TypeInterface1>>
            
            type TestNamespace.Type2
            
            type TestNamespace.Type : TestNamespace.Type1<TestNamespace.Type2>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(UNKNOWN_OBJECT.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 47, UNKNOWN_OBJECT.getMessage("TestNamespace.TypeInterface1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 50, SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getMessage("TestNamespace.Type2", "set<UnknownObject(TestNamespace.TypeInterface1)>")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterUsageDoesNotSatisfyTypeBoundsNestedTypeNonExistentGenericParameter() {
        //TODO:KMD Create another test for this T1 isn't a type parameter
        var testProgram = """
            type interface TestNamespace.Type1<T : set<T>>
            
            type TestNamespace.Type2
            
            type TestNamespace.Type : TestNamespace.Type1<TestNamespace.Type2>
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 50, SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS.getMessage("TestNamespace.Type2", "set<T>")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

}