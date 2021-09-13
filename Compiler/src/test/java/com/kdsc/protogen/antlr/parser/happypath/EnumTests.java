package com.kdsc.protogen.antlr.parser.happypath;

import com.kdsc.protogen.antlr.parser.BaseParserTest;
import org.junit.jupiter.api.Test;

public class EnumTests  extends BaseParserTest {

    @Test
    void basicEmptyEnum() {
        var testProgram = """
            enum TestNamespace.TestEnum
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicEmptyEnumWithBracesOnOneLine() {
        var testProgram = """
            enum TestNamespace.TestEnum {}
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicEmptyEnumWithSplitBraces() {
        var testProgram = """
            enum TestNamespace.TestEnum {
            }
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicEnumWithOneCase() {
        var testProgram = """
            enum TestNamespace.TestEnum {
                testEnumCase
            }
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicEnumWithTwoCases() {
        var testProgram = """
            enum TestNamespace.TestEnum {
                testEnumCase1
                testEnumCase2
            }
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void versionedEnumWithOneCase() {
        var testProgram = """
            enum TestNamespace.TestEnum {
                version 1 {
                    testEnumCase
                }
            }
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void versionedEnumWithTwoCases() {
        var testProgram = """
            enum TestNamespace.TestEnum {
                version 1 {
                    testEnumCase1
                    testEnumCase2
                }
                version 2 {
                    testEnumCase1
                    testEnumCase2
                }
            }
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void versionedEnumWithTwoCasesOneEmpty() {
        var testProgram = """
            enum TestNamespace.TestEnum {
                version 1 {
                    test EnumCase1
                    testEnumCase2
                }
                version 2 {
                }
            }
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }
}
