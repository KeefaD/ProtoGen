package com.kdsc.protogen.antlr.parser.unhappypath;

import com.kdsc.protogen.antlr.errors.ProtoGenErrorListener;
import com.kdsc.protogen.antlr.parser.BaseParserTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestUnhappyPathFoundCases extends BaseParserTest {

    @Test
    void testMissingNameBlowsUpParser() {
        var testProgram = """
            type TestNamespace.Type1
            type interface {
                version 1 TestNamespace.Type : TestNamespace.Type1
            }
        """;
        var parserErrors = compileTestProgramReturnParserErrors(testProgram);
        assertNotNull(parserErrors, "Parser errors are unexpectedly null");
        assertEquals(2, parserErrors.size(), "Unexpected parser errors size");
        assertEquals(
            ProtoGenErrorListener.PARSER_ERROR_MESSAGE.formatted(DUMMY_SOURCE_FILE_NAME, 2,19, "mismatched input '{' expecting IDENTIFIER"),
            parserErrors.get(0),
            "Unexpected semantic error message"
        );
        assertEquals(
            ProtoGenErrorListener.PARSER_ERROR_MESSAGE.formatted(DUMMY_SOURCE_FILE_NAME, 3,18, "extraneous input 'TestNamespace' expecting {'}', 'version'}"),
            parserErrors.get(1),
            "Unexpected semantic error message"
        );
    }

}