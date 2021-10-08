package com.kdsc.protogen.antlr;

import com.kdsc.protogen.parsetree.TestFileNode;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestParserResults {

    @Test
    public void testCreate() {
        new ParserResults(
            Collections.emptyList(),
            Collections.emptyList()
        );
    }

    @Test
    public void testInvalidConstructorCall() {

        assertThrows(NullPointerException.class,
            () ->
            new ParserResults(
                null,
                Collections.emptyList()
            )
        );

        assertThrows(NullPointerException.class,
            () ->
            new ParserResults(
                Collections.emptyList(),
                null
            )
        );

    }

    @Test
    public void testHasParserErrorOccurredTrue() {
        var parserError = new ParserError("DummySourceFileName.pg", 1, 0, "Dummy message");
        var parserResults = new ParserResults(
            List.of(parserError),
            Collections.emptyList()
        );
        assertTrue(parserResults.hasParserErrorOccurred(), "Expected parser error to have occurred");
        assertEquals(1, parserResults.parserErrors().size(), "Expected one parser error to have occurred");
        assertEquals(
            ParserError.PARSER_ERROR_MESSAGE.formatted("DummySourceFileName.pg", 1, 0, "Dummy message"),
            parserResults.parserErrors().get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testHasParserErrorOccurredFalse() {
        var parserResults = new ParserResults(
            Collections.emptyList(),
            Collections.emptyList()
        );
        assertFalse(parserResults.hasParserErrorOccurred(), "Expected parser error not to have occurred");
        assertEquals(0, parserResults.parserErrors().size(), "Expected zero parser error to have occurred");
    }

    @Test
    public void testGetFileNodes() {
        var fileNode = TestFileNode.createPopulatedTestNode();
        var parserResults = new ParserResults(
            Collections.emptyList(),
            List.of(fileNode)
        );
        assertEquals(1, parserResults.fileNodes().size(), "Expected one filenode");
        assertEquals(fileNode, parserResults.fileNodes().get(0), "Expected filenode to be equal");
    }

}