package com.kdsc.protogen.antlr.parser.unhappypath;

import com.kdsc.protogen.antlr.errors.ProtoGenErrorListener;
import com.kdsc.protogen.antlr.parser.BaseParserTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestUnhappyPathGeneral extends BaseParserTest {

    @Test
    void singleCharacter() {
        var testProgram = """
            a
        """;
        var parserErrors = compileTestProgramReturnParserErrors(testProgram);
        assertNotNull(parserErrors, "Parser errors are unexpectedly null");
        assertEquals(parserErrors.size(), 1, "Unexpected parser errors size");
        assertEquals(parserErrors.get(0), ProtoGenErrorListener.PARSER_ERROR_MESSAGE.formatted(DUMMY_SOURCE_FILE_NAME, 1, 4, "extraneous input 'a' expecting {<EOF>, 'type', 'key', 'enum'}"));
    }

    @Test
    void misspelledTypeKeyWord() {
        var testProgram = """
            ttype TestNamespace.TestType
        """;
        var parserErrors = compileTestProgramReturnParserErrors(testProgram);
        assertNotNull(parserErrors, "Parser errors are unexpectedly null");
        assertEquals(parserErrors.size(), 1, "Unexpected parser errors size");
        assertEquals(parserErrors.get(0), ProtoGenErrorListener.PARSER_ERROR_MESSAGE.formatted(DUMMY_SOURCE_FILE_NAME, 1, 4, "mismatched input 'ttype' expecting {<EOF>, 'type', 'key', 'enum'}"));
    }

    @Test
    void missingNamespace() {
        var testProgram = """
            type TestType
        """;
        var parserErrors = compileTestProgramReturnParserErrors(testProgram);
        assertNotNull(parserErrors, "Parser errors are unexpectedly null");
        assertEquals(parserErrors.size(), 1, "Unexpected parser errors size");
        assertEquals(parserErrors.get(0), ProtoGenErrorListener.PARSER_ERROR_MESSAGE.formatted(DUMMY_SOURCE_FILE_NAME, 2, 0, "mismatched input '<EOF>' expecting '.'"));
    }

    @Test
    void missingNamespaceWithBraces() {
        var testProgram = """
            type TestType {}
        """;
        var parserErrors = compileTestProgramReturnParserErrors(testProgram);
        assertNotNull(parserErrors, "Parser errors are unexpectedly null");
        assertEquals(parserErrors.size(), 1, "Unexpected parser errors size");
        assertEquals(parserErrors.get(0), ProtoGenErrorListener.PARSER_ERROR_MESSAGE.formatted(DUMMY_SOURCE_FILE_NAME, 1, 18, "mismatched input '{' expecting '.'"));
    }

    @Test
    void mismatchedBraces() {
        var testProgram = """
            type TestNamespace.TestType {
        """;
        var parserErrors = compileTestProgramReturnParserErrors(testProgram);
        assertNotNull(parserErrors, "Parser errors are unexpectedly null");
        assertEquals(parserErrors.size(), 1, "Unexpected parser errors size");
        assertEquals(parserErrors.get(0), ProtoGenErrorListener.PARSER_ERROR_MESSAGE.formatted(DUMMY_SOURCE_FILE_NAME, 2, 0, "no viable alternative at input '<EOF>'"));
    }

    @Test
    void mismatchedSquareBracketsForArray() {
        var testProgram = """
            type TestNamespace.TestType {
                testArray : int32[
            }
        """;
        var parserErrors = compileTestProgramReturnParserErrors(testProgram);
        assertNotNull(parserErrors, "Parser errors are unexpectedly null");
        assertEquals(parserErrors.size(), 1, "Unexpected parser errors size");
        assertEquals(parserErrors.get(0), ProtoGenErrorListener.PARSER_ERROR_MESSAGE.formatted(DUMMY_SOURCE_FILE_NAME, 3, 4, "missing ']' at '}'"));
    }

}