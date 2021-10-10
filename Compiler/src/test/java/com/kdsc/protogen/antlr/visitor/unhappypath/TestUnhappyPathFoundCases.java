package com.kdsc.protogen.antlr.visitor.unhappypath;

import com.kdsc.protogen.antlr.ParserError;
import com.kdsc.protogen.BaseCompilerTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public final class TestUnhappyPathFoundCases extends BaseCompilerTest {

    @Test
    void testMissingNameBlowsUpParser() {
        var testProgram = """
            type TestNamespace.Type1
            type interface {
                version 1 TestNamespace.Type : TestNamespace.Type1
            }
        """;
        var parserErrors = runCompilerToParserReturnParserErrors(testProgram);
        assertNotNull(parserErrors, "Parser errors are unexpectedly null");
        assertEquals(2, parserErrors.size(), "Unexpected parser errors size");
        assertEquals(
            ParserError.PARSER_ERROR_MESSAGE.formatted(FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 19, "mismatched input '{' expecting IDENTIFIER"),
            parserErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            ParserError.PARSER_ERROR_MESSAGE.formatted(FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 18, "extraneous input 'TestNamespace' expecting {'}', 'version'}"),
            parserErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    void testZeroVersionInEnum() {
        var testProgram = """
            enum TestNamespace.Enum {
                version 0 {
                }
            }
        """;
        var parserErrors = runCompilerToParserReturnParserErrors(testProgram);
        assertNotNull(parserErrors, "Parser errors are unexpectedly null");
        assertEquals(2, parserErrors.size(), "Unexpected parser errors size");
        assertEquals(
            ParserError.PARSER_ERROR_MESSAGE.formatted(FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 16, "mismatched input '0' expecting VERSION_NUMBER"),
            parserErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            ParserError.PARSER_ERROR_MESSAGE.formatted(FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 8, "mismatched input '}' expecting IDENTIFIER"),
            parserErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    void testNegativeVersionInEnum() {
        var testProgram = """
            enum TestNamespace.Enum {
                version -1 {
                }
            }
        """;
        var parserErrors = runCompilerToParserReturnParserErrors(testProgram);
        assertNotNull(parserErrors, "Parser errors are unexpectedly null");
        assertEquals(2, parserErrors.size(), "Unexpected parser errors size");
        assertEquals(
            ParserError.PARSER_ERROR_MESSAGE.formatted(FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 16, "extraneous input '-' expecting VERSION_NUMBER"),
            parserErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            ParserError.PARSER_ERROR_MESSAGE.formatted(FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 8, "mismatched input '}' expecting IDENTIFIER"),
            parserErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

}