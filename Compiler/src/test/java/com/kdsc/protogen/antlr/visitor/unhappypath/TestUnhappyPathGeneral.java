package com.kdsc.protogen.antlr.visitor.unhappypath;

import com.kdsc.protogen.antlr.ParserError;
import com.kdsc.protogen.BaseCompilerTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public final class TestUnhappyPathGeneral extends BaseCompilerTest {

    @Test
    void testSingleCharacter() {
        var testProgram = """
            a
        """;
        var parserErrors = runCompilerToParserReturnParserErrors(testProgram);
        assertNotNull(parserErrors, "Parser errors are unexpectedly null");
        assertEquals(1, parserErrors.size(), "Unexpected parser errors size");
        assertEquals(
            ParserError.PARSER_ERROR_MESSAGE.formatted(FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 4, "extraneous input 'a' expecting {<EOF>, 'type', 'key', 'enum'}"),
            parserErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    void testMisspelledTypeKeyWord() {
        var testProgram = """
            ttype TestNamespace.TestType
        """;
        var parserErrors = runCompilerToParserReturnParserErrors(testProgram);
        assertNotNull(parserErrors, "Parser errors are unexpectedly null");
        assertEquals(1, parserErrors.size(), "Unexpected parser errors size");
        assertEquals(
            ParserError.PARSER_ERROR_MESSAGE.formatted(FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 4, "mismatched input 'ttype' expecting {<EOF>, 'type', 'key', 'enum'}"),
            parserErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    void testMissingNamespace() {
        var testProgram = """
            type TestType
        """;
        var parserErrors = runCompilerToParserReturnParserErrors(testProgram);
        assertNotNull(parserErrors, "Parser errors are unexpectedly null");
        assertEquals(1, parserErrors.size(), "Unexpected parser errors size");
        assertEquals(
            ParserError.PARSER_ERROR_MESSAGE.formatted(FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 0, "mismatched input '<EOF>' expecting '.'"),
            parserErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    void testMissingNamespaceWithBraces() {
        var testProgram = """
            type TestType {}
        """;
        var parserErrors = runCompilerToParserReturnParserErrors(testProgram);
        assertNotNull(parserErrors, "Parser errors are unexpectedly null");
        assertEquals(1, parserErrors.size(), "Unexpected parser errors size");
        assertEquals(
            ParserError.PARSER_ERROR_MESSAGE.formatted(FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 18, "mismatched input '{' expecting '.'"),
            parserErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    void testMismatchedBraces() {
        var testProgram = """
            type TestNamespace.TestType {
        """;
        var parserErrors = runCompilerToParserReturnParserErrors(testProgram);
        assertNotNull(parserErrors, "Parser errors are unexpectedly null");
        assertEquals(1, parserErrors.size(), "Unexpected parser errors size");
        assertEquals(
            ParserError.PARSER_ERROR_MESSAGE.formatted(FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 0, "no viable alternative at input '<EOF>'"),
            parserErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    void testMismatchedSquareBracketsForArray() {
        var testProgram = """
            type TestNamespace.TestType {
                testArray : int32[
            }
        """;
        var parserErrors = runCompilerToParserReturnParserErrors(testProgram);
        assertNotNull(parserErrors, "Parser errors are unexpectedly null");
        assertEquals(1, parserErrors.size(), "Unexpected parser errors size");
        assertEquals(
            ParserError.PARSER_ERROR_MESSAGE.formatted(FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 4, "missing ']' at '}'"),
            parserErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    void testBothVersionAndOuterPopulatedForEnum() {
        var testProgram = """
            enum TestNamespace.Enum {
                version 1 {
                    case1
                    case2
                }
                case1
                case2
            }
        """;
        var parserErrors = runCompilerToParserReturnParserErrors(testProgram);
        assertNotNull(parserErrors, "Parser errors are unexpectedly null");
        assertEquals(1, parserErrors.size(), "Unexpected parser errors size");
        assertEquals(
            ParserError.PARSER_ERROR_MESSAGE.formatted(FAKE_SOURCE_FILE_NAME_AND_PATH, 6, 8, "extraneous input 'case1' expecting {'}', 'version'}"),
            parserErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    void testBothVersionAndOuterPopulatedForType() {
        var testProgram = """
            type TestNamespace.Type {
                version 1 {
                    field1 : int32
                    field2 : string
                }
                field1 : int32
                field2 : string
            }
        """;
        var parserErrors = runCompilerToParserReturnParserErrors(testProgram);
        assertNotNull(parserErrors, "Parser errors are unexpectedly null");
        assertEquals(1, parserErrors.size(), "Unexpected parser errors size");
        assertEquals(
            ParserError.PARSER_ERROR_MESSAGE.formatted(FAKE_SOURCE_FILE_NAME_AND_PATH, 6, 8, "extraneous input 'field1' expecting {'}', 'version'}"),
            parserErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    void testBothVersionAndOuterPopulatedForKey() {
        var testProgram = """
            key TestNamespace.Type {
                version 1 {
                    field1 : int32
                    field2 : string
                }
                field1 : int32
                field2 : string
            }
        """;
        var parserErrors = runCompilerToParserReturnParserErrors(testProgram);
        assertNotNull(parserErrors, "Parser errors are unexpectedly null");
        assertEquals(1, parserErrors.size(), "Unexpected parser errors size");
        assertEquals(
            ParserError.PARSER_ERROR_MESSAGE.formatted(FAKE_SOURCE_FILE_NAME_AND_PATH, 6, 8, "extraneous input 'field1' expecting {'}', 'version'}"),
            parserErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    void testMissingNameForEnum() {
        var testProgram = """
            enum {
            }
        """;
        var parserErrors = runCompilerToParserReturnParserErrors(testProgram);
        assertNotNull(parserErrors, "Parser errors are unexpectedly null");
        assertEquals(2, parserErrors.size(), "Unexpected parser errors size");
        assertEquals(
            ParserError.PARSER_ERROR_MESSAGE.formatted(FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 9, "mismatched input '{' expecting IDENTIFIER"),
            parserErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            ParserError.PARSER_ERROR_MESSAGE.formatted(FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 4, "no viable alternative at input '}'"),
            parserErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    void testEnumWithNoCasesOrVersionsNoBraces() {
        var testProgram = """
            enum TestNamespace.TestEnum
        """;
        var parserErrors = runCompilerToParserReturnParserErrors(testProgram);
        assertNotNull(parserErrors, "Parser errors are unexpectedly null");
        assertEquals(1, parserErrors.size(), "Unexpected parser errors size");
        assertEquals(
            ParserError.PARSER_ERROR_MESSAGE.formatted(FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 0, "mismatched input '<EOF>' expecting '{'"),
            parserErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    void testEnumWithNoCasesOrVersionsBraces() {
        var testProgram = """
            enum TestNamespace.TestEnum {}
        """;
        var parserErrors = runCompilerToParserReturnParserErrors(testProgram);
        assertNotNull(parserErrors, "Parser errors are unexpectedly null");
        assertEquals(1, parserErrors.size(), "Unexpected parser errors size");
        assertEquals(
            ParserError.PARSER_ERROR_MESSAGE.formatted(FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 33, "no viable alternative at input '}'"),
            parserErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    void testEnumWithNoCasesOrVersionsSplitBraces() {
        var testProgram = """
            enum TestNamespace.TestEnum {
            }
        """;
        var parserErrors = runCompilerToParserReturnParserErrors(testProgram);
        assertNotNull(parserErrors, "Parser errors are unexpectedly null");
        assertEquals(1, parserErrors.size(), "Unexpected parser errors size");
        assertEquals(
            ParserError.PARSER_ERROR_MESSAGE.formatted(FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 4, "no viable alternative at input '}'"),
            parserErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    void testMissingNameForType() {
        var testProgram = """
            type {
            }
        """;
        var parserErrors = runCompilerToParserReturnParserErrors(testProgram);
        assertNotNull(parserErrors, "Parser errors are unexpectedly null");
        assertEquals(1, parserErrors.size(), "Unexpected parser errors size");
        assertEquals(
            ParserError.PARSER_ERROR_MESSAGE.formatted(FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 9, "mismatched input '{' expecting IDENTIFIER"),
            parserErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    void testMissingNameForTypeInterface() {
        var testProgram = """
            type interface {
            }
        """;
        var parserErrors = runCompilerToParserReturnParserErrors(testProgram);
        assertNotNull(parserErrors, "Parser errors are unexpectedly null");
        assertEquals(1, parserErrors.size(), "Unexpected parser errors size");
        assertEquals(
            ParserError.PARSER_ERROR_MESSAGE.formatted(FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 19, "mismatched input '{' expecting IDENTIFIER"),
            parserErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    void testMissingNameForKey() {
        var testProgram = """
            key {
            }
        """;
        var parserErrors = runCompilerToParserReturnParserErrors(testProgram);
        assertNotNull(parserErrors, "Parser errors are unexpectedly null");
        assertEquals(1, parserErrors.size(), "Unexpected parser errors size");
        assertEquals(
            ParserError.PARSER_ERROR_MESSAGE.formatted(FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 8, "mismatched input '{' expecting IDENTIFIER"),
            parserErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    void testMissingNameForKeyInterface() {
        var testProgram = """
            key interface {
            }
        """;
        var parserErrors = runCompilerToParserReturnParserErrors(testProgram);
        assertNotNull(parserErrors, "Parser errors are unexpectedly null");
        assertEquals(1, parserErrors.size(), "Unexpected parser errors size");
        assertEquals(
            ParserError.PARSER_ERROR_MESSAGE.formatted(FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 18, "mismatched input '{' expecting IDENTIFIER"),
            parserErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

}