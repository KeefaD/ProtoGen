package com.kdsc.protogen.antlr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public final class TestParserErrorListener {

    public static final String TEST_FILE_NAME = "TestFileName.pg";
    public static final String TEST_MESSAGE = "Test Message";
    public static final long TEST_LINE = 1;
    public static final long TEST_CHAR = 0;

    @Test
    public void testCreate() {
        new ParserErrorListener(
            TEST_FILE_NAME
        );
    }

    @Test
    public void testInvalidConstructorCall() {

        assertThrows(IllegalArgumentException.class,
            () ->
            new ParserErrorListener(
                null
            )
        );

        assertThrows(IllegalArgumentException.class,
            () ->
            new ParserErrorListener(
                ""
            )
        );

    }

    @Test
    public void testNoError() {
        var errorListener = new ParserErrorListener(
            TEST_FILE_NAME
        );
        assertFalse(errorListener.errorOccurred(), "Expected errorOccurred to be false when no errors have been added");
    }

    @Test
    public void testOneErrors() {
        var errorListener = new ParserErrorListener(
            TEST_FILE_NAME
        );
        errorListener.syntaxError(null, null, 1, 0, TEST_MESSAGE, null);
        assertTrue(errorListener.errorOccurred(), "Expected errorOccurred to be false when no errors have been added");
        assertEquals(1, errorListener.getErrors().size(), "Unexpected number of parser errors");
        assertEquals(
            ParserError.PARSER_ERROR_MESSAGE.formatted(TEST_FILE_NAME, TEST_LINE, TEST_CHAR, TEST_MESSAGE),
            errorListener.getErrors().get(0).getFullErrorMessage(),
            "Unexpected parser error message"
        );
    }

}