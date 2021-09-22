package com.kdsc.protogen.semanticanalysis;

import com.kdsc.protogen.antlr.parser.BaseParserTest;
import com.kdsc.protogen.parsetreepostprocessing.UndetectableNodeReplacer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.kdsc.protogen.semanticanalysis.SemanticError.PARSER_ERROR_MESSAGE;
import static com.kdsc.protogen.semanticanalysis.SemanticErrorType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestSemanticAnalyserTypes extends BaseParserTest {

    @Test
    public void testRedefinitionOfVersionNumberOneForType() {
        var testProgram = """
            type TestNamespace.Type {
                version 1
                version 1
            }
        """;
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Expected one semantic error");
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_TYPE_VERSION.getNumber(), DUMMY_SOURCE_FILE_NAME, 3, 16, REDEFINITION_OF_TYPE_VERSION.getMessage(1)),
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
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Expected one semantic error");
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_TYPE_VERSION.getNumber(), DUMMY_SOURCE_FILE_NAME, 3, 16, REDEFINITION_OF_TYPE_VERSION.getMessage(2)),
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
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Expected one semantic error");
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_TYPE_VERSION.getNumber(), DUMMY_SOURCE_FILE_NAME, 4, 16, REDEFINITION_OF_TYPE_VERSION.getMessage(3)),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_TYPE_VERSION.getNumber(), DUMMY_SOURCE_FILE_NAME, 5, 16, REDEFINITION_OF_TYPE_VERSION.getMessage(3)),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testTypeRefersToNonExistentTypeInImplementsList() {
        var testProgram = """
            type TestNamespace.Type : TestNamespace.Type1
        """;
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Expected one semantic error");
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(TYPE_REFERS_TO_NON_EXISTENT_TYPE_IN_IMPLEMENTS_LIST.getNumber(), DUMMY_SOURCE_FILE_NAME, 1, 30, TYPE_REFERS_TO_NON_EXISTENT_TYPE_IN_IMPLEMENTS_LIST.getMessage("TestNamespace.Type", "TestNamespace.Type1")),
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
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Expected one semantic error");
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(TYPE_REFERS_TO_NON_EXISTENT_TYPE_IN_IMPLEMENTS_LIST.getNumber(), DUMMY_SOURCE_FILE_NAME, 2, 51, TYPE_REFERS_TO_NON_EXISTENT_TYPE_IN_IMPLEMENTS_LIST.getMessage("TestNamespace.Type", "TestNamespace.Type2")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testRedefinitionOfVersionGenericParameterOnce() {
        var testProgram = """
            type TestNamespace.Type<T, T>
        """;
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Expected one semantic error");
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_GENERIC_PARAMETER.getNumber(), DUMMY_SOURCE_FILE_NAME, 1, 31, REDEFINITION_OF_GENERIC_PARAMETER.getMessage("TestNamespace.Type", "T")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testRedefinitionOfVersionGenericParameterTwice() {
        var testProgram = """
            type TestNamespace.Type<T, T, T>
        """;
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Expected one semantic error");
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_GENERIC_PARAMETER.getNumber(), DUMMY_SOURCE_FILE_NAME, 1, 31, REDEFINITION_OF_GENERIC_PARAMETER.getMessage("TestNamespace.Type", "T")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_GENERIC_PARAMETER.getNumber(), DUMMY_SOURCE_FILE_NAME, 1, 34, REDEFINITION_OF_GENERIC_PARAMETER.getMessage("TestNamespace.Type", "T")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testTypeParameterBoundsRefersToNonExistentTypeOnce() {
        var testProgram = """
            type TestNamespace.Type<T & TestNamespace.Type1>
        """;
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Expected one semantic error");
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_BOUNDS_REFERS_TO_NON_EXISTENT_TYPE.getNumber(), DUMMY_SOURCE_FILE_NAME, 1, 32, GENERIC_PARAMETER_BOUNDS_REFERS_TO_NON_EXISTENT_TYPE.getMessage("T", "TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testTypeParameterBoundsRefersToNonExistentTypeTwice() {
        var testProgram = """
            type TestNamespace.Type<T & TestNamespace.Type1 & TestNamespace.Type2>
        """;
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Expected one semantic error");
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_BOUNDS_REFERS_TO_NON_EXISTENT_TYPE.getNumber(), DUMMY_SOURCE_FILE_NAME, 1, 32, GENERIC_PARAMETER_BOUNDS_REFERS_TO_NON_EXISTENT_TYPE.getMessage("T", "TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_BOUNDS_REFERS_TO_NON_EXISTENT_TYPE.getNumber(), DUMMY_SOURCE_FILE_NAME, 1, 54, GENERIC_PARAMETER_BOUNDS_REFERS_TO_NON_EXISTENT_TYPE.getMessage("T", "TestNamespace.Type2")),
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
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Expected one semantic error");
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getNumber(), DUMMY_SOURCE_FILE_NAME, 2, 54, GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getMessage("T", "TestNamespace.Type1")),
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
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Expected one semantic error");
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getNumber(), DUMMY_SOURCE_FILE_NAME, 2, 54, GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getMessage("T", "TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getNumber(), DUMMY_SOURCE_FILE_NAME, 2, 76, GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES.getMessage("T", "TestNamespace.Type1")),
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
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Expected one semantic error");
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), DUMMY_SOURCE_FILE_NAME, 2, 53, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type")),
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
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Expected one semantic error");
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), DUMMY_SOURCE_FILE_NAME, 2, 53, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), DUMMY_SOURCE_FILE_NAME, 2, 57, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T3", "TestNamespace.Type")),
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
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Expected one semantic error");
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), DUMMY_SOURCE_FILE_NAME, 3, 53, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), DUMMY_SOURCE_FILE_NAME, 3, 78, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type")),
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
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(4, semanticErrors.size(), "Expected one semantic error");
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), DUMMY_SOURCE_FILE_NAME, 3, 53, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), DUMMY_SOURCE_FILE_NAME, 3, 57, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T3", "TestNamespace.Type")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), DUMMY_SOURCE_FILE_NAME, 3, 82, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), DUMMY_SOURCE_FILE_NAME, 3, 86, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T4", "TestNamespace.Type")),
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
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(8, semanticErrors.size(), "Expected one semantic error");
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), DUMMY_SOURCE_FILE_NAME, 3, 57, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), DUMMY_SOURCE_FILE_NAME, 3, 61, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), DUMMY_SOURCE_FILE_NAME, 3, 70, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T3", "TestNamespace.Type")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), DUMMY_SOURCE_FILE_NAME, 3, 74, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T3", "TestNamespace.Type")),
            semanticErrors.get(3).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), DUMMY_SOURCE_FILE_NAME, 3, 104, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type")),
            semanticErrors.get(4).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), DUMMY_SOURCE_FILE_NAME, 3, 108, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type")),
            semanticErrors.get(5).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), DUMMY_SOURCE_FILE_NAME, 3, 117, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T4", "TestNamespace.Type")),
            semanticErrors.get(6).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), DUMMY_SOURCE_FILE_NAME, 3, 121, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T4", "TestNamespace.Type")),
            semanticErrors.get(7).getFullErrorMessage(),
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
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Expected one semantic error");
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE.getNumber(), DUMMY_SOURCE_FILE_NAME, 3, 30, MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE.getMessage("TestNamespace.Type", "TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE.getNumber(), DUMMY_SOURCE_FILE_NAME, 3, 51, MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE.getMessage("TestNamespace.Type", "TestNamespace.Type2")),
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
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(3, semanticErrors.size(), "Expected one semantic error");
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE.getNumber(), DUMMY_SOURCE_FILE_NAME, 4, 30, MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE.getMessage("TestNamespace.Type", "TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE.getNumber(), DUMMY_SOURCE_FILE_NAME, 4, 51, MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE.getMessage("TestNamespace.Type", "TestNamespace.Type2")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE.getNumber(), DUMMY_SOURCE_FILE_NAME, 4, 72, MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE.getMessage("TestNamespace.Type", "TestNamespace.Type3")),
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
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Expected one semantic error");
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getNumber(), DUMMY_SOURCE_FILE_NAME, 2, 33, NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getMessage("TestNamespace.Type1", 1, 0)),
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
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Expected one semantic error");
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getNumber(), DUMMY_SOURCE_FILE_NAME, 2, 33, NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getMessage("TestNamespace.Type1", 0, 1)),
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
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Expected one semantic error");
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getNumber(), DUMMY_SOURCE_FILE_NAME, 2, 33, NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getMessage("TestNamespace.Type1", 0, 2)),
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
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Expected one semantic error");
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getNumber(), DUMMY_SOURCE_FILE_NAME, 2, 38, NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getMessage("TestNamespace.Type1", 2, 3)),
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
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Expected one semantic error");
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getNumber(), DUMMY_SOURCE_FILE_NAME, 3, 54, NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getMessage("TestNamespace.Type2", 1, 0)),
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
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Expected one semantic error");
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getNumber(), DUMMY_SOURCE_FILE_NAME, 3,57, NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getMessage("TestNamespace.Type2", 0, 1)),
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
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Expected one semantic error");
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getNumber(), DUMMY_SOURCE_FILE_NAME, 3, 67, NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getMessage("TestNamespace.Type2", 0, 2)),
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
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Expected one semantic error");
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getNumber(), DUMMY_SOURCE_FILE_NAME, 3, 75, NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getMessage("TestNamespace.Type2", 2, 3)),
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
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Expected one semantic error");
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getNumber(), DUMMY_SOURCE_FILE_NAME, 2, 38, NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getMessage("TestNamespace.Type1", 2, 3)),
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
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Expected one semantic error");
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getNumber(), DUMMY_SOURCE_FILE_NAME, 2, 42, NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getMessage("TestNamespace.Type1", 3, 4)),
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
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Expected one semantic error");
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getNumber(), DUMMY_SOURCE_FILE_NAME, 2, 38, NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION.getMessage("TestNamespace.Type1", 2, 3)),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

}