package com.kdsc.protogen.antlr.visitor.happypath;

import com.kdsc.protogen.BaseCompilerTest;
import org.junit.jupiter.api.Test;

public class TestEnums extends BaseCompilerTest {

    @Test
    void testBasicEnumWithOneCase() {
        var testProgram = """
            enum TestNamespace.TestEnum {
                testEnumCase
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicEnumWithTwoCases() {
        var testProgram = """
            enum TestNamespace.TestEnum {
                testEnumCase1
                testEnumCase2
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testVersionedEnumWithOneCase() {
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
    void testVersionedEnumWithTwoCases() {
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
    void testVersionedEnumWithTwoCasesOneEmpty() {
        var testProgram = """
            enum TestNamespace.TestEnum {
                version 1 {
                    testEnumCase1
                    testEnumCase2
                }
                version 2 {
                    testEnumCase1
                }
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

}