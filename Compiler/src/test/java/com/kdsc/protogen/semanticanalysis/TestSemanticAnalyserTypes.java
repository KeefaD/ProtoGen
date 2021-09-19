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
}