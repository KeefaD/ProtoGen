package com.kdsc.protogen.semanticanalysis.semanticanalyser.types;

import com.kdsc.protogen.BaseCompilerTest;
import org.junit.jupiter.api.Test;

import static com.kdsc.protogen.semanticanalysis.SemanticError.SEMANTIC_ERROR_MESSAGE;
import static com.kdsc.protogen.semanticanalysis.SemanticErrorType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public final class TestSemanticAnalyserTypesInheritance extends BaseCompilerTest {

    @Test
    public void testDirectInheritanceLoop() {
        var testProgram = """            
            type TestNamespace.Type : TestNamespace.Type {
                version 1
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type->TestNamespace.Type", "TestNamespace.Type")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testIndirectInheritanceLoop() {
        var testProgram = """
            type TestNamespace.Type1 : TestNamespace.Type
                   
            type TestNamespace.Type : TestNamespace.Type1
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type1->TestNamespace.Type->TestNamespace.Type1", "TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type->TestNamespace.Type1->TestNamespace.Type", "TestNamespace.Type")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testIndirectInheritanceLoopTwoLevels() {
        var testProgram = """
            type TestNamespace.Type1 : TestNamespace.Type
            
            type TestNamespace.Type2 : TestNamespace.Type1
                   
            type TestNamespace.Type : TestNamespace.Type2
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(3, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 1, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type1->TestNamespace.Type->TestNamespace.Type2->TestNamespace.Type1", "TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type2->TestNamespace.Type1->TestNamespace.Type->TestNamespace.Type2", "TestNamespace.Type2")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type->TestNamespace.Type2->TestNamespace.Type1->TestNamespace.Type", "TestNamespace.Type")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testInheritanceLoopSecondInterface() {
        var testProgram = """
            type interface TestNamespace.Type1
            
            type interface TestNamespace.Type2 : TestNamespace.Type
            
            type interface TestNamespace.Type3 : TestNamespace.Type2
            
            type interface TestNamespace.Type4 : TestNamespace.Type3
                   
            type interface TestNamespace.Type : TestNamespace.Type1, TestNamespace.Type3
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(4, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type2->TestNamespace.Type->TestNamespace.Type3->TestNamespace.Type2", "TestNamespace.Type2")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type3->TestNamespace.Type2->TestNamespace.Type->TestNamespace.Type3", "TestNamespace.Type3")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 7, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type4->TestNamespace.Type3->TestNamespace.Type2->TestNamespace.Type->TestNamespace.Type3", "TestNamespace.Type4")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(INHERITANCE_LOOP_DETECTED.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 9, 4, INHERITANCE_LOOP_DETECTED.getMessage("TestNamespace.Type->TestNamespace.Type3->TestNamespace.Type2->TestNamespace.Type", "TestNamespace.Type")),
            semanticErrors.get(3).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testExtendNonInterfaceTypeWithInterface() {
        var testProgram = """
            type TestNamespace.Type1
            type interface TestNamespace.Type : TestNamespace.Type1
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(EXTENDING_INTERFACE_WITH_NON_INTERFACE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 40, EXTENDING_INTERFACE_WITH_NON_INTERFACE.getMessage("TestNamespace.Type", "TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testExtendNonInterfaceTypeWithInterfaceTwice() {
        var testProgram = """
            type TestNamespace.Type1
            type TestNamespace.Type2
            type interface TestNamespace.Type : TestNamespace.Type1, TestNamespace.Type2
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(EXTENDING_INTERFACE_WITH_NON_INTERFACE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 40, EXTENDING_INTERFACE_WITH_NON_INTERFACE.getMessage("TestNamespace.Type", "TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(EXTENDING_INTERFACE_WITH_NON_INTERFACE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 61, EXTENDING_INTERFACE_WITH_NON_INTERFACE.getMessage("TestNamespace.Type", "TestNamespace.Type2")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testExtendNonInterfaceTypeWithInterfaceSecondInList() {
        var testProgram = """
            type interface TestNamespace.Type1
            type TestNamespace.Type2
            type interface TestNamespace.Type : TestNamespace.Type1, TestNamespace.Type2
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(EXTENDING_INTERFACE_WITH_NON_INTERFACE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 61, EXTENDING_INTERFACE_WITH_NON_INTERFACE.getMessage("TestNamespace.Type", "TestNamespace.Type2")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testMoreThanOneNonInterfaceTypeInImplementsListTwo() {
        var testProgram = """
            type TestNamespace.Type1
            type TestNamespace.Type2
            type TestNamespace.Type : TestNamespace.Type1, TestNamespace.Type2
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 30, MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE.getMessage("TestNamespace.Type", "TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 51, MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE.getMessage("TestNamespace.Type", "TestNamespace.Type2")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testMoreThanOneNonInterfaceTypeInImplementsListThree() {
        var testProgram = """
            type TestNamespace.Type1
            type TestNamespace.Type2
            type TestNamespace.Type3
            type TestNamespace.Type : TestNamespace.Type1, TestNamespace.Type2, TestNamespace.Type3
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(3, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 30, MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE.getMessage("TestNamespace.Type", "TestNamespace.Type1")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 51, MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE.getMessage("TestNamespace.Type", "TestNamespace.Type2")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 72, MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE.getMessage("TestNamespace.Type", "TestNamespace.Type3")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

}