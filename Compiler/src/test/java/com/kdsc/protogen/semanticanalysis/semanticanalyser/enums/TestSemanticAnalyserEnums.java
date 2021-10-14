package com.kdsc.protogen.semanticanalysis.semanticanalyser.enums;

import com.kdsc.protogen.BaseCompilerTest;
import org.junit.jupiter.api.Test;

import static com.kdsc.protogen.semanticanalysis.SemanticError.SEMANTIC_ERROR_MESSAGE;
import static com.kdsc.protogen.semanticanalysis.SemanticErrorType.REDEFINITION_OF_ENUM_CASE;
import static com.kdsc.protogen.semanticanalysis.SemanticErrorType.REDEFINITION_OF_ENUM_VERSION;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public final class TestSemanticAnalyserEnums extends BaseCompilerTest {

    @Test
    public void testRedefinitionOfVersionNumberOneForEnum() {
        var testProgram = """
            enum TestNamespace.Enum {
                version 1 {
                    enumCase1
                }
                version 1 {
                    enumCase1
                }
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_ENUM_VERSION.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 16, REDEFINITION_OF_ENUM_VERSION.getMessage(1)),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testRedefinitionOfVersionNumberTwoForEnum() {
        var testProgram = """
            enum TestNamespace.Enum {
                version 2 {
                    enumCase1
                }
                version 2 {
                    enumCase1
                }
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_ENUM_VERSION.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 16, REDEFINITION_OF_ENUM_VERSION.getMessage(2)),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testRedefinitionOfVersionNumberThreeForEnum() {
        var testProgram = """
            enum TestNamespace.Enum {
                version 1 {
                    enumCase1
                }
                version 3 {
                    enumCase1
                }
                version 3 {
                    enumCase1
                }
                version 3 {
                    enumCase1
                }
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_ENUM_VERSION.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 8, 16, REDEFINITION_OF_ENUM_VERSION.getMessage(3)),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_ENUM_VERSION.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 11, 16, REDEFINITION_OF_ENUM_VERSION.getMessage(3)),
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
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_ENUM_CASE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 8, REDEFINITION_OF_ENUM_CASE.getMessage("enumCase1")),
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
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_ENUM_CASE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 8, REDEFINITION_OF_ENUM_CASE.getMessage("enumCase2")),
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
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_ENUM_CASE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 8, REDEFINITION_OF_ENUM_CASE.getMessage("enumCase2")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_ENUM_CASE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 8, REDEFINITION_OF_ENUM_CASE.getMessage("enumCase2")),
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
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_ENUM_CASE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 12, REDEFINITION_OF_ENUM_CASE.getMessage("enumCase1")),
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
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_ENUM_CASE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 12, REDEFINITION_OF_ENUM_CASE.getMessage("enumCase2")),
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
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(SEMANTIC_ERROR_MESSAGE.formatted(
            REDEFINITION_OF_ENUM_CASE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 12, REDEFINITION_OF_ENUM_CASE.getMessage("enumCase2")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_ENUM_CASE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 6, 12, REDEFINITION_OF_ENUM_CASE.getMessage("enumCase2")),
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
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(4, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_ENUM_CASE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 12, REDEFINITION_OF_ENUM_CASE.getMessage("enumCase2")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_ENUM_CASE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 6, 12, REDEFINITION_OF_ENUM_CASE.getMessage("enumCase2")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_ENUM_CASE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 11, 12, REDEFINITION_OF_ENUM_CASE.getMessage("enumCase2")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(REDEFINITION_OF_ENUM_CASE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 12, 12, REDEFINITION_OF_ENUM_CASE.getMessage("enumCase2")),
            semanticErrors.get(3).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

}