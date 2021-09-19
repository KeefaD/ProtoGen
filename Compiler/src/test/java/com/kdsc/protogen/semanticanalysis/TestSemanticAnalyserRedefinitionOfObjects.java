package com.kdsc.protogen.semanticanalysis;

import com.kdsc.protogen.antlr.parser.BaseParserTest;
import com.kdsc.protogen.parsetreepostprocessing.UndetectableNodeReplacer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.kdsc.protogen.semanticanalysis.SemanticError.PARSER_ERROR_MESSAGE;
import static com.kdsc.protogen.semanticanalysis.SemanticErrorType.REDEFINITION_OF_OBJECT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestSemanticAnalyserRedefinitionOfObjects extends BaseParserTest {

    @Test
    public void testRedefinitionOfType() {
        var testProgram = """
            type TestNamespace.Type
            type TestNamespace.Type
        """;
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Expected one semantic error");
        assertEquals(PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_OBJECT.getNumber(), DUMMY_SOURCE_FILE_NAME, 2, 4, REDEFINITION_OF_OBJECT.getMessage("TestNamespace.Type")), semanticErrors.get(0).getFullErrorMessage(), "Unexpected semantic error message");
    }

    @Test
    public void testRedefinitionOfKey() {
        var testProgram = """
            key TestNamespace.Key
            key TestNamespace.Key
        """;
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Expected one semantic error");
        assertEquals(PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_OBJECT.getNumber(), DUMMY_SOURCE_FILE_NAME, 2, 4, REDEFINITION_OF_OBJECT.getMessage("TestNamespace.Key")), semanticErrors.get(0).getFullErrorMessage(), "Unexpected semantic error message");
    }

    @Test
    public void testRedefinitionOfEnum() {
        var testProgram = """
            enum TestNamespace.Enum
            enum TestNamespace.Enum
        """;
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Expected one semantic error");
        assertEquals(PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_OBJECT.getNumber(), DUMMY_SOURCE_FILE_NAME, 2, 4, REDEFINITION_OF_OBJECT.getMessage("TestNamespace.Enum")), semanticErrors.get(0).getFullErrorMessage(), "Unexpected semantic error message");
    }

    @Test
    public void testRedefinitionOfTypeTwice() {
        var testProgram = """
            type TestNamespace.Type
            type TestNamespace.Type
            type TestNamespace.Type
        """;
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Expected one semantic error");
        assertEquals(PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_OBJECT.getNumber(), DUMMY_SOURCE_FILE_NAME, 2, 4, REDEFINITION_OF_OBJECT.getMessage("TestNamespace.Type")), semanticErrors.get(0).getFullErrorMessage(), "Unexpected semantic error message");
        assertEquals(PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_OBJECT.getNumber(), DUMMY_SOURCE_FILE_NAME, 3, 4, REDEFINITION_OF_OBJECT.getMessage("TestNamespace.Type")), semanticErrors.get(1).getFullErrorMessage(), "Unexpected semantic error message");
    }

    @Test
    public void testRedefinitionOfKeyTwice() {
        var testProgram = """
            key TestNamespace.Key
            key TestNamespace.Key
            key TestNamespace.Key
        """;
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Expected one semantic error");
        assertEquals(PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_OBJECT.getNumber(), DUMMY_SOURCE_FILE_NAME, 2, 4, REDEFINITION_OF_OBJECT.getMessage("TestNamespace.Key")), semanticErrors.get(0).getFullErrorMessage(), "Unexpected semantic error message");
        assertEquals(PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_OBJECT.getNumber(), DUMMY_SOURCE_FILE_NAME, 3, 4, REDEFINITION_OF_OBJECT.getMessage("TestNamespace.Key")), semanticErrors.get(1).getFullErrorMessage(), "Unexpected semantic error message");
    }

    @Test
    public void testRedefinitionOfEnumTwice() {
        var testProgram = """
            enum TestNamespace.Enum
            enum TestNamespace.Enum
            enum TestNamespace.Enum
        """;
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Expected one semantic error");
        assertEquals(PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_OBJECT.getNumber(), DUMMY_SOURCE_FILE_NAME, 2, 4, REDEFINITION_OF_OBJECT.getMessage("TestNamespace.Enum")), semanticErrors.get(0).getFullErrorMessage(), "Unexpected semantic error message");
        assertEquals(PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_OBJECT.getNumber(), DUMMY_SOURCE_FILE_NAME, 3, 4, REDEFINITION_OF_OBJECT.getMessage("TestNamespace.Enum")), semanticErrors.get(1).getFullErrorMessage(), "Unexpected semantic error message");
    }

    @Test
    public void testRedefinitionOfTypeAsKey() {
        var testProgram = """
            type TestNamespace.Type
            key TestNamespace.Type
        """;
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Expected one semantic error");
        assertEquals(PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_OBJECT.getNumber(), DUMMY_SOURCE_FILE_NAME, 2, 4, REDEFINITION_OF_OBJECT.getMessage("TestNamespace.Type")), semanticErrors.get(0).getFullErrorMessage(), "Unexpected semantic error message");
    }

    @Test
    public void testRedefinitionOfTypeAsEnum() {
        var testProgram = """
            type TestNamespace.Type
            enum TestNamespace.Type
        """;
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Expected one semantic error");
        assertEquals(PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_OBJECT.getNumber(), DUMMY_SOURCE_FILE_NAME, 2, 4, REDEFINITION_OF_OBJECT.getMessage("TestNamespace.Type")), semanticErrors.get(0).getFullErrorMessage(), "Unexpected semantic error message");
    }

    @Test
    public void testRedefinitionOfKeyAsType() {
        var testProgram = """
            key TestNamespace.Key
            type TestNamespace.Key
        """;
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Expected one semantic error");
        assertEquals(PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_OBJECT.getNumber(), DUMMY_SOURCE_FILE_NAME, 1, 4, REDEFINITION_OF_OBJECT.getMessage("TestNamespace.Key")), semanticErrors.get(0).getFullErrorMessage(), "Unexpected semantic error message");
    }

    @Test
    public void testRedefinitionOfKeyAsEnum() {
        var testProgram = """
            key TestNamespace.Key
            enum TestNamespace.Key
        """;
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Expected one semantic error");
        assertEquals(PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_OBJECT.getNumber(), DUMMY_SOURCE_FILE_NAME, 2, 4, REDEFINITION_OF_OBJECT.getMessage("TestNamespace.Key")), semanticErrors.get(0).getFullErrorMessage(), "Unexpected semantic error message");
    }

    @Test
    public void testRedefinitionOfEnumAsType() {
        var testProgram = """
            enum TestNamespace.Enum
            type TestNamespace.Enum
        """;
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Expected one semantic error");
        assertEquals(PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_OBJECT.getNumber(), DUMMY_SOURCE_FILE_NAME, 1, 4, REDEFINITION_OF_OBJECT.getMessage("TestNamespace.Enum")), semanticErrors.get(0).getFullErrorMessage(), "Unexpected semantic error message");
    }

    @Test
    public void testRedefinitionOfEnumAsKey() {
        var testProgram = """
            enum TestNamespace.Enum
            key TestNamespace.Enum
        """;
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Expected one semantic error");
        assertEquals(PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_OBJECT.getNumber(), DUMMY_SOURCE_FILE_NAME, 1, 4, REDEFINITION_OF_OBJECT.getMessage("TestNamespace.Enum")), semanticErrors.get(0).getFullErrorMessage(), "Unexpected semantic error message");
    }

}