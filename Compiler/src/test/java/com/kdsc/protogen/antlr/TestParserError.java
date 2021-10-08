package com.kdsc.protogen.antlr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class TestParserError {

    public static final String TEST_FILE_NAME = "TestFileName.pg";
    public static final String TEST_MESSAGE = "Test Message";
    public static final long TEST_LINE = 1;
    public static final long TEST_CHAR = 0;

    @Test
    public void testCreate() {
        new ParserError(
            TEST_FILE_NAME,
            TEST_LINE,
            TEST_CHAR,
            TEST_MESSAGE
        );
    }

    @Test
    public void testInvalidConstructorCall() {

        assertThrows(IllegalArgumentException.class,
            () ->
            new ParserError(
                null,
                TEST_LINE,
                TEST_CHAR,
                TEST_MESSAGE
            )
        );

        assertThrows(IllegalArgumentException.class,
            () ->
            new ParserError(
                "",
                TEST_LINE,
                TEST_CHAR,
                TEST_MESSAGE
            )
        );

        assertThrows(IllegalArgumentException.class,
            () ->
            new ParserError(
                TEST_FILE_NAME,
                0,
                TEST_CHAR,
                TEST_MESSAGE
            )
        );

        assertThrows(IllegalArgumentException.class,
            () ->
            new ParserError(
                TEST_FILE_NAME,
                TEST_LINE,
                -1,
                TEST_MESSAGE
            )
        );

        assertThrows(IllegalArgumentException.class,
            () ->
            new ParserError(
                TEST_FILE_NAME,
                TEST_LINE,
                TEST_CHAR,
                null
            )
        );

        assertThrows(IllegalArgumentException.class,
            () ->
            new ParserError(
                TEST_FILE_NAME,
                TEST_LINE,
                TEST_CHAR,
                ""
            )
        );

    }

    @Test
    public void testGetters() {

        var parserError = new ParserError(
            TEST_FILE_NAME ,
            TEST_LINE,
            TEST_CHAR,
            TEST_MESSAGE
        );

        assertEquals(
            ParserError.PARSER_ERROR_MESSAGE.formatted(TEST_FILE_NAME, 1, 0, TEST_MESSAGE),
            parserError.getFullErrorMessage(),
            "Unexpected parser error message"
        );

    }

    @Test
    public void testToString() {

        var parserError = new ParserError(
            TEST_FILE_NAME,
            TEST_LINE,
            TEST_CHAR,
            TEST_MESSAGE
        );

        assertEquals(
            ParserError.PARSER_ERROR_MESSAGE.formatted(TEST_FILE_NAME, 1, 0, TEST_MESSAGE),
            parserError.getFullErrorMessage(),
            "Unexpected parser error message"
        );

    }
}