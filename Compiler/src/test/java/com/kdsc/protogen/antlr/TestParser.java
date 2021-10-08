package com.kdsc.protogen.antlr;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestParser {

    private String fullPathToSourceCodeDirectory = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "parsertest" + File.separator;

    @Test
    public void testCreate() {
        new Parser();
    }

    @Test
    public void testCallParseWithNull() {
        var parser = new Parser();
        assertThrows(NullPointerException.class,
            () ->
            parser.parse(null)
        );
    }

    @Test
    public void testCallParseWhenFileDoesNotExist() {
        var fileList = List.of(fullPathToSourceCodeDirectory + "FileDoesNotExist.pg");
        var parser = new Parser();
        assertThrows(RuntimeException.class,
            () -> parser.parse(fileList)
        );
    }

    @Test
    public void testCallParseWhenExpectOneError() {

        //TODO:KMD Need to do something about the reporting of filenames as th message looks really bad and it is inconsistent
        final var filename = fullPathToSourceCodeDirectory + "ExpectOneError.pg";
        var fileList = List.of(filename);
        var parser = new Parser();
        var parserResults = parser.parse(fileList);
        assertTrue(parserResults.hasParserErrorOccurred(), "Expected parser error to occur");
        assertEquals(1, parserResults.parserErrors().size(), "Expected exactly one parser error");
        assertEquals(
            ParserError.PARSER_ERROR_MESSAGE.formatted(filename, 1, 0, "extraneous input 'Nothing' expecting {<EOF>, 'type', 'key', 'enum'}"),
            parserResults.parserErrors().get(0).getFullErrorMessage(),
            "Unexpected parser error message"
        );
    }

    @Test
    public void testCallParseWhenExpectNoError() {
        var fileList = List.of(fullPathToSourceCodeDirectory + "ExpectNoErrors.pg");
        var parser = new Parser();
        var parserResults = parser.parse(fileList);
        assertFalse(parserResults.hasParserErrorOccurred(), "Expected no parser errors to have occurred");
        assertEquals(0, parserResults.parserErrors().size(), "Expected exactly zero parser error");
    }

}