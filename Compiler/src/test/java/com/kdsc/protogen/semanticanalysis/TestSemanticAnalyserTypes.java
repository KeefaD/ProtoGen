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
            type TestNamespace.Type1
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
            type TestNamespace.Type1
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
            type TestNamespace.Type1
            type TestNamespace.Type2
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
            type TestNamespace.Type1
            type TestNamespace.Type2
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

}