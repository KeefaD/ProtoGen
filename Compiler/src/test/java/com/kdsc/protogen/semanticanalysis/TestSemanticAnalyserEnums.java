package com.kdsc.protogen.semanticanalysis;

import com.kdsc.protogen.antlr.parser.BaseParserTest;
import com.kdsc.protogen.parsetreepostprocessing.UndetectableNodeReplacer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.kdsc.protogen.semanticanalysis.SemanticError.PARSER_ERROR_MESSAGE;
import static com.kdsc.protogen.semanticanalysis.SemanticErrorType.REDEFINITION_OF_ENUM_CASE;
import static com.kdsc.protogen.semanticanalysis.SemanticErrorType.REDEFINITION_OF_ENUM_VERSION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//TODO:KMD Get all these different types of unit tests into a nice hierarchy
public class TestSemanticAnalyserEnums extends BaseParserTest {

    @Test
    public void testRedefinitionOfVersionNumberOneForEnum() {
        var testProgram = """
            enum TestNamespace.Enum {
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
            PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_ENUM_VERSION.getNumber(), DUMMY_SOURCE_FILE_NAME, 3, 16, REDEFINITION_OF_ENUM_VERSION.getMessage(1)),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testRedefinitionOfVersionNumberTwoForEnum() {
        var testProgram = """
            enum TestNamespace.Enum {
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
            PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_ENUM_VERSION.getNumber(), DUMMY_SOURCE_FILE_NAME, 3, 16, REDEFINITION_OF_ENUM_VERSION.getMessage(2)),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testRedefinitionOfVersionNumberThreeForEnum() {
        var testProgram = """
            enum TestNamespace.Enum {
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
            PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_ENUM_VERSION.getNumber(), DUMMY_SOURCE_FILE_NAME, 4, 16, REDEFINITION_OF_ENUM_VERSION.getMessage(3)),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_ENUM_VERSION.getNumber(), DUMMY_SOURCE_FILE_NAME, 5, 16, REDEFINITION_OF_ENUM_VERSION.getMessage(3)),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testRedefinitionOfEnumCase1NoVersion() {
        var testProgram = """
            enum TestNamespace.Enum {
                enumCase1
                enumCase1
            }
        """;
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Expected one semantic error");
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_ENUM_CASE.getNumber(), DUMMY_SOURCE_FILE_NAME, 3, 8, REDEFINITION_OF_ENUM_CASE.getMessage("enumCase1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testRedefinitionOfEnumCase2NoVersion() {
        var testProgram = """
            enum TestNamespace.Enum {
                enumCase1
                enumCase2
                enumCase2
            }
        """;
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Expected one semantic error");
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_ENUM_CASE.getNumber(), DUMMY_SOURCE_FILE_NAME, 4, 8, REDEFINITION_OF_ENUM_CASE.getMessage("enumCase2")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testRedefinitionOfEnumCase2TwiceNoVersion() {
        var testProgram = """
            enum TestNamespace.Enum {
                enumCase1
                enumCase2
                enumCase2
                enumCase2
            }
        """;
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Expected one semantic error");
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_ENUM_CASE.getNumber(), DUMMY_SOURCE_FILE_NAME, 4, 8, REDEFINITION_OF_ENUM_CASE.getMessage("enumCase2")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_ENUM_CASE.getNumber(), DUMMY_SOURCE_FILE_NAME, 5, 8, REDEFINITION_OF_ENUM_CASE.getMessage("enumCase2")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testRedefinitionOfEnumCase1InVersion() {
        var testProgram = """
            enum TestNamespace.Enum {
                version 1 {
                    enumCase1
                    enumCase1
                }
            }
        """;
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Expected one semantic error");
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_ENUM_CASE.getNumber(), DUMMY_SOURCE_FILE_NAME, 4, 12, REDEFINITION_OF_ENUM_CASE.getMessage("enumCase1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testRedefinitionOfEnumCase2InVersion() {
        var testProgram = """
            enum TestNamespace.Enum {
                version 1 {
                    enumCase1
                    enumCase2
                    enumCase2
                }
            }
        """;
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Expected one semantic error");
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_ENUM_CASE.getNumber(), DUMMY_SOURCE_FILE_NAME, 5, 12, REDEFINITION_OF_ENUM_CASE.getMessage("enumCase2")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testRedefinitionOfEnumCase2TwiceInVersion() {
        var testProgram = """
            enum TestNamespace.Enum {
                version 1 {
                    enumCase1
                    enumCase2
                    enumCase2
                    enumCase2
                }
            }
        """;
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Expected one semantic error");
        assertEquals(PARSER_ERROR_MESSAGE.formatted(
            REDEFINITION_OF_ENUM_CASE.getNumber(), DUMMY_SOURCE_FILE_NAME, 5, 12, REDEFINITION_OF_ENUM_CASE.getMessage("enumCase2")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_ENUM_CASE.getNumber(), DUMMY_SOURCE_FILE_NAME, 6, 12, REDEFINITION_OF_ENUM_CASE.getMessage("enumCase2")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testRedefinitionOfEnumCase2TwiceInMultipleVersions() {
        var testProgram = """
            enum TestNamespace.Enum {
                version 1 {
                    enumCase1
                    enumCase2
                    enumCase2
                    enumCase2
                }
                version 2 {
                    enumCase1
                    enumCase2
                    enumCase2
                    enumCase2
                }
            }
        """;
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(newFileNode));
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(4, semanticErrors.size(), "Expected one semantic error");
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_ENUM_CASE.getNumber(), DUMMY_SOURCE_FILE_NAME, 5, 12, REDEFINITION_OF_ENUM_CASE.getMessage("enumCase2")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_ENUM_CASE.getNumber(), DUMMY_SOURCE_FILE_NAME, 6, 12, REDEFINITION_OF_ENUM_CASE.getMessage("enumCase2")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_ENUM_CASE.getNumber(), DUMMY_SOURCE_FILE_NAME, 11, 12, REDEFINITION_OF_ENUM_CASE.getMessage("enumCase2")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            PARSER_ERROR_MESSAGE.formatted(REDEFINITION_OF_ENUM_CASE.getNumber(), DUMMY_SOURCE_FILE_NAME, 12, 12, REDEFINITION_OF_ENUM_CASE.getMessage("enumCase2")),
            semanticErrors.get(3).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }
}