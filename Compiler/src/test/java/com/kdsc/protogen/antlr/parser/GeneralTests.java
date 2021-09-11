package com.kdsc.protogen.antlr.parser;

import org.junit.jupiter.api.Test;

public class GeneralTests  extends BaseParserTest {
    @Test
    void emptyFile() {
        var testProgram = """
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }
}
