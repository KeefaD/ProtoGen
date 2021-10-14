package com.kdsc.protogen.semanticanalysis.semanticanalyser.types;

import com.kdsc.protogen.BaseCompilerTest;
import org.junit.jupiter.api.Test;

import static com.kdsc.protogen.semanticanalysis.SemanticError.SEMANTIC_ERROR_MESSAGE;
import static com.kdsc.protogen.semanticanalysis.SemanticErrorType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public final class TestSemanticAnalyserTypeVersionsInheritance extends BaseCompilerTest {

    @Test
    public void testDirectInheritanceLoopVersion1() {
        var testProgram = """            
            type TestNamespace.Type  {
                version 1 : TestNamespace.Type
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type(Version 1)->TestNamespace.Type(Version 1)", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testIndirectInheritanceLoopVersion1() {
        var testProgram = """
            type TestNamespace.Type1 : TestNamespace.Type
                   
            type TestNamespace.Type {
                version 1 : TestNamespace.Type1
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type1->TestNamespace.Type(Version 1)->TestNamespace.Type1", "TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type(Version 1)->TestNamespace.Type1->TestNamespace.Type(Version 1)", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testIndirectInheritanceLoopTwoLevelsVersion1() {
        var testProgram = """
            type TestNamespace.Type1 : TestNamespace.Type
            
            type TestNamespace.Type2 : TestNamespace.Type1
                   
            type TestNamespace.Type {
                version 1 : TestNamespace.Type2
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(3, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type1->TestNamespace.Type(Version 1)->TestNamespace.Type2->TestNamespace.Type1", "TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type2->TestNamespace.Type1->TestNamespace.Type(Version 1)->TestNamespace.Type2", "TestNamespace.Type2")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type(Version 1)->TestNamespace.Type2->TestNamespace.Type1->TestNamespace.Type(Version 1)", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testInheritanceLoopSecondInterfaceVersion1() {
        var testProgram = """
            type interface TestNamespace.Type1
            
            type interface TestNamespace.Type2 : TestNamespace.Type
            
            type interface TestNamespace.Type3 : TestNamespace.Type2
            
            type interface TestNamespace.Type4 : TestNamespace.Type3
                   
            type interface TestNamespace.Type {
                version 1 : TestNamespace.Type1, TestNamespace.Type3
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(4, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type2->TestNamespace.Type(Version 1)->TestNamespace.Type3->TestNamespace.Type2", "TestNamespace.Type2")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type3->TestNamespace.Type2->TestNamespace.Type(Version 1)->TestNamespace.Type3", "TestNamespace.Type3")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 7, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type4->TestNamespace.Type3->TestNamespace.Type2->TestNamespace.Type(Version 1)->TestNamespace.Type3", "TestNamespace.Type4")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 9, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type(Version 1)->TestNamespace.Type3->TestNamespace.Type2->TestNamespace.Type(Version 1)", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(3).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testDirectInheritanceLoopVersion2() {
        var testProgram = """            
            type TestNamespace.Type  {
                version 1
                version 2 : TestNamespace.Type
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type(Version 2)->TestNamespace.Type(Version 2)", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testIndirectInheritanceLoopVersion2() {
        var testProgram = """
            type TestNamespace.Type1 : TestNamespace.Type
                   
            type TestNamespace.Type {
                version 1
                version 2 : TestNamespace.Type1
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type1->TestNamespace.Type(Version 2)->TestNamespace.Type1", "TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type(Version 2)->TestNamespace.Type1->TestNamespace.Type(Version 2)", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testIndirectInheritanceLoopTwoLevelsVersion2() {
        var testProgram = """
            type TestNamespace.Type1 : TestNamespace.Type
            
            type TestNamespace.Type2 : TestNamespace.Type1
                   
            type TestNamespace.Type {
                version 1
                version 2 : TestNamespace.Type2
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(3, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type1->TestNamespace.Type(Version 2)->TestNamespace.Type2->TestNamespace.Type1", "TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type2->TestNamespace.Type1->TestNamespace.Type(Version 2)->TestNamespace.Type2", "TestNamespace.Type2")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type(Version 2)->TestNamespace.Type2->TestNamespace.Type1->TestNamespace.Type(Version 2)", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testInheritanceLoopSecondInterfaceVersion2() {
        var testProgram = """
            type interface TestNamespace.Type1
            
            type interface TestNamespace.Type2 : TestNamespace.Type
            
            type interface TestNamespace.Type3 : TestNamespace.Type2
            
            type interface TestNamespace.Type4 : TestNamespace.Type3
                   
            type interface TestNamespace.Type {
                version 1
                version 2 : TestNamespace.Type1, TestNamespace.Type3
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(4, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type2->TestNamespace.Type(Version 2)->TestNamespace.Type3->TestNamespace.Type2", "TestNamespace.Type2")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type3->TestNamespace.Type2->TestNamespace.Type(Version 2)->TestNamespace.Type3", "TestNamespace.Type3")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 7, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type4->TestNamespace.Type3->TestNamespace.Type2->TestNamespace.Type(Version 2)->TestNamespace.Type3", "TestNamespace.Type4")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 9, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type(Version 2)->TestNamespace.Type3->TestNamespace.Type2->TestNamespace.Type(Version 2)", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(3).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testDirectInheritanceLoopVersion1AndVersion2() {
        var testProgram = """            
            type TestNamespace.Type  {
                version 1 : TestNamespace.Type
                version 2 : TestNamespace.Type
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type(Version 1)->TestNamespace.Type(Version 2)", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type(Version 2)->TestNamespace.Type(Version 2)", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testIndirectInheritanceLoopVersion1AndVersion2() {
        var testProgram = """
            type TestNamespace.Type1 : TestNamespace.Type
                   
            type TestNamespace.Type {
                version 1 : TestNamespace.Type1
                version 2 : TestNamespace.Type1
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(3, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type1->TestNamespace.Type(Version 2)->TestNamespace.Type1", "TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type(Version 1)->TestNamespace.Type1->TestNamespace.Type(Version 2)", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type(Version 2)->TestNamespace.Type1->TestNamespace.Type(Version 2)", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testIndirectInheritanceLoopTwoLevelsVersion1AndVersion2() {
        var testProgram = """
            type TestNamespace.Type1 : TestNamespace.Type
            
            type TestNamespace.Type2 : TestNamespace.Type1
                   
            type TestNamespace.Type {
                version 1 : TestNamespace.Type2
                version 2 : TestNamespace.Type2
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(4, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type1->TestNamespace.Type(Version 2)->TestNamespace.Type2->TestNamespace.Type1", "TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type2->TestNamespace.Type1->TestNamespace.Type(Version 2)->TestNamespace.Type2", "TestNamespace.Type2")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type(Version 1)->TestNamespace.Type2->TestNamespace.Type1->TestNamespace.Type(Version 2)", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type(Version 2)->TestNamespace.Type2->TestNamespace.Type1->TestNamespace.Type(Version 2)", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(3).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testInheritanceLoopSecondInterfaceVersion1AndVersion2() {
        var testProgram = """
            type interface TestNamespace.Type1
            
            type interface TestNamespace.Type2 : TestNamespace.Type
            
            type interface TestNamespace.Type3 : TestNamespace.Type2
            
            type interface TestNamespace.Type4 : TestNamespace.Type3
                   
            type interface TestNamespace.Type {
                version 1 : TestNamespace.Type1, TestNamespace.Type3
                version 2 : TestNamespace.Type1, TestNamespace.Type3
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(5, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type2->TestNamespace.Type(Version 2)->TestNamespace.Type3->TestNamespace.Type2", "TestNamespace.Type2")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type3->TestNamespace.Type2->TestNamespace.Type(Version 2)->TestNamespace.Type3", "TestNamespace.Type3")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 7, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type4->TestNamespace.Type3->TestNamespace.Type2->TestNamespace.Type(Version 2)->TestNamespace.Type3", "TestNamespace.Type4")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 9, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type(Version 1)->TestNamespace.Type3->TestNamespace.Type2->TestNamespace.Type(Version 2)", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(3).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 9, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type(Version 2)->TestNamespace.Type3->TestNamespace.Type2->TestNamespace.Type(Version 2)", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(4).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testImplementsListOnOuterTypeAndVersions() {
        var testProgram = """
            type TestNamespace.Type1
            
            type TestNamespace.Type : TestNamespace.Type1 {
                version 1 : TestNamespace.Type1
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(CANNOT_HAVE_IMPLEMENTS_LIST_ON_OUTER_TYPE_AND_VERSION_AT_THE_SAME_TIME.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 20, CANNOT_HAVE_IMPLEMENTS_LIST_ON_OUTER_TYPE_AND_VERSION_AT_THE_SAME_TIME.getMessage("TestNamespace.Type1", "TestNamespace.Type")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testImplementsListOnOuterTypeAndVersionsTwice() {
        var testProgram = """
            type TestNamespace.Type1
            type interface TestNamespace.Type2
            
            type TestNamespace.Type : TestNamespace.Type1, TestNamespace.Type2 {
                version 1 : TestNamespace.Type1, TestNamespace.Type2
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(CANNOT_HAVE_IMPLEMENTS_LIST_ON_OUTER_TYPE_AND_VERSION_AT_THE_SAME_TIME.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 20, CANNOT_HAVE_IMPLEMENTS_LIST_ON_OUTER_TYPE_AND_VERSION_AT_THE_SAME_TIME.getMessage("TestNamespace.Type1", "TestNamespace.Type")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(CANNOT_HAVE_IMPLEMENTS_LIST_ON_OUTER_TYPE_AND_VERSION_AT_THE_SAME_TIME.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 41, CANNOT_HAVE_IMPLEMENTS_LIST_ON_OUTER_TYPE_AND_VERSION_AT_THE_SAME_TIME.getMessage("TestNamespace.Type2", "TestNamespace.Type")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testImplementsListOnOuterTypeAndVersionsTwoVersions() {
        var testProgram = """
            type TestNamespace.Type1
            
            type TestNamespace.Type : TestNamespace.Type1 {
                version 1 : TestNamespace.Type1
                version 2 : TestNamespace.Type1
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(CANNOT_HAVE_IMPLEMENTS_LIST_ON_OUTER_TYPE_AND_VERSION_AT_THE_SAME_TIME.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 20, CANNOT_HAVE_IMPLEMENTS_LIST_ON_OUTER_TYPE_AND_VERSION_AT_THE_SAME_TIME.getMessage("TestNamespace.Type1", "TestNamespace.Type")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(CANNOT_HAVE_IMPLEMENTS_LIST_ON_OUTER_TYPE_AND_VERSION_AT_THE_SAME_TIME.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 20, CANNOT_HAVE_IMPLEMENTS_LIST_ON_OUTER_TYPE_AND_VERSION_AT_THE_SAME_TIME.getMessage("TestNamespace.Type1", "TestNamespace.Type")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testImplementsListOnOuterTypeAndVersionsTwoVersionsTwice() {
        var testProgram = """
            type TestNamespace.Type1
            type interface TestNamespace.Type2
            
            type TestNamespace.Type : TestNamespace.Type1, TestNamespace.Type2 {
                version 1 : TestNamespace.Type1, TestNamespace.Type2
                version 2 : TestNamespace.Type1, TestNamespace.Type2
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(4, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(CANNOT_HAVE_IMPLEMENTS_LIST_ON_OUTER_TYPE_AND_VERSION_AT_THE_SAME_TIME.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 20, CANNOT_HAVE_IMPLEMENTS_LIST_ON_OUTER_TYPE_AND_VERSION_AT_THE_SAME_TIME.getMessage("TestNamespace.Type1", "TestNamespace.Type")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(CANNOT_HAVE_IMPLEMENTS_LIST_ON_OUTER_TYPE_AND_VERSION_AT_THE_SAME_TIME.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 41, CANNOT_HAVE_IMPLEMENTS_LIST_ON_OUTER_TYPE_AND_VERSION_AT_THE_SAME_TIME.getMessage("TestNamespace.Type2", "TestNamespace.Type")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(CANNOT_HAVE_IMPLEMENTS_LIST_ON_OUTER_TYPE_AND_VERSION_AT_THE_SAME_TIME.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 6, 20, CANNOT_HAVE_IMPLEMENTS_LIST_ON_OUTER_TYPE_AND_VERSION_AT_THE_SAME_TIME.getMessage("TestNamespace.Type1", "TestNamespace.Type")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(CANNOT_HAVE_IMPLEMENTS_LIST_ON_OUTER_TYPE_AND_VERSION_AT_THE_SAME_TIME.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 6, 41, CANNOT_HAVE_IMPLEMENTS_LIST_ON_OUTER_TYPE_AND_VERSION_AT_THE_SAME_TIME.getMessage("TestNamespace.Type2", "TestNamespace.Type")),
            semanticErrors.get(3).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testExtendNonInterfaceTypeWithInterfaceInVersion1() {
        var testProgram = """
            type TestNamespace.Type1
            type interface TestNamespace.Type {
                version 1 : TestNamespace.Type1
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(EXTENDING_INTERFACE_WITH_NON_INTERFACE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 20, EXTENDING_INTERFACE_WITH_NON_INTERFACE.getMessage("TestNamespace.Type(Version 1)", "TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testExtendNonInterfaceTypeWithInterfaceTwiceInVersion1() {
        var testProgram = """
            type TestNamespace.Type1
            type TestNamespace.Type2
            type interface TestNamespace.Type {
                version 1 : TestNamespace.Type1, TestNamespace.Type2
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(EXTENDING_INTERFACE_WITH_NON_INTERFACE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 20, EXTENDING_INTERFACE_WITH_NON_INTERFACE.getMessage("TestNamespace.Type(Version 1)", "TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(EXTENDING_INTERFACE_WITH_NON_INTERFACE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 41, EXTENDING_INTERFACE_WITH_NON_INTERFACE.getMessage("TestNamespace.Type(Version 1)", "TestNamespace.Type2")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testExtendNonInterfaceTypeWithInterfaceSecondInListInVersion1() {
        var testProgram = """
            type interface TestNamespace.Type1
            type TestNamespace.Type2
            type interface TestNamespace.Type {
                version 1 : TestNamespace.Type1, TestNamespace.Type2 {
                }
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(EXTENDING_INTERFACE_WITH_NON_INTERFACE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 41, EXTENDING_INTERFACE_WITH_NON_INTERFACE.getMessage("TestNamespace.Type(Version 1)", "TestNamespace.Type2")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testExtendNonInterfaceTypeWithInterfaceInVersion2() {
        var testProgram = """
            type TestNamespace.Type1
            type interface TestNamespace.Type{
                version 1
                version 2 : TestNamespace.Type1
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(EXTENDING_INTERFACE_WITH_NON_INTERFACE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 20, EXTENDING_INTERFACE_WITH_NON_INTERFACE.getMessage("TestNamespace.Type(Version 2)", "TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testExtendNonInterfaceTypeWithInterfaceTwiceInVersion2() {
        var testProgram = """
            type TestNamespace.Type1
            type TestNamespace.Type2
            type interface TestNamespace.Type {
                version 1
                version 2 : TestNamespace.Type1, TestNamespace.Type2
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(EXTENDING_INTERFACE_WITH_NON_INTERFACE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 20, EXTENDING_INTERFACE_WITH_NON_INTERFACE.getMessage("TestNamespace.Type(Version 2)", "TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(EXTENDING_INTERFACE_WITH_NON_INTERFACE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 41, EXTENDING_INTERFACE_WITH_NON_INTERFACE.getMessage("TestNamespace.Type(Version 2)", "TestNamespace.Type2")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testExtendNonInterfaceTypeWithInterfaceSecondInListInVersion2() {
        var testProgram = """
            type interface TestNamespace.Type1
            type TestNamespace.Type2
            type interface TestNamespace.Type {
                version 1 {
                }
                version 2 : TestNamespace.Type1, TestNamespace.Type2 {
                }
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(EXTENDING_INTERFACE_WITH_NON_INTERFACE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 6, 41, EXTENDING_INTERFACE_WITH_NON_INTERFACE.getMessage("TestNamespace.Type(Version 2)", "TestNamespace.Type2")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

}