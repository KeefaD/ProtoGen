package com.kdsc.protogen.antlr.visitor.happypath;

import com.kdsc.protogen.BaseCompilerTest;
import org.junit.jupiter.api.Test;

public class TestEnums extends BaseCompilerTest {

    @Test
    void basicEmptyEnum() {
        var testProgram = """
            enum TestNamespace.TestEnum
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicEmptyEnumWithBracesOnOneLine() {
        var testProgram = """
            enum TestNamespace.TestEnum {}
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicEmptyEnumWithSplitBraces() {
        var testProgram = """
            enum TestNamespace.TestEnum {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicEnumWithOneCase() {
        var testProgram = """
            enum TestNamespace.TestEnum {
                testEnumCase
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicEnumWithTwoCases() {
        var testProgram = """
            enum TestNamespace.TestEnum {
                testEnumCase1
                testEnumCase2
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
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
        runCompilerToParserCheckNoErrors(testProgram);
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
        runCompilerToParserCheckNoErrors(testProgram);
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
        runCompilerToParserCheckNoErrors(testProgram);
    }
}
