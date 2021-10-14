package com.kdsc.protogen.semanticanalysis.semanticanalyser.types;

import com.kdsc.protogen.BaseCompilerTest;
import org.junit.jupiter.api.Test;

import static com.kdsc.protogen.semanticanalysis.SemanticError.SEMANTIC_ERROR_MESSAGE;
import static com.kdsc.protogen.semanticanalysis.SemanticErrorType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public final class TestSemanticAnalyserTypeVersionsGenericParameters extends BaseCompilerTest {

    @Test
    public void testGenericParametersOnOuterTypeAndVersions() {
        var testProgram = """
            type TestNamespace.Type<T> {
                version 1 <T>
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(CANNOT_HAVE_GENERIC_PARAMETERS_ON_OUTER_TYPE_AND_VERSION_AT_THE_SAME_TIME.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 19, CANNOT_HAVE_GENERIC_PARAMETERS_ON_OUTER_TYPE_AND_VERSION_AT_THE_SAME_TIME.getMessage("T", "TestNamespace.Type")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParametersOnOuterTypeAndVersionsTwoVersions() {
        var testProgram = """
            type TestNamespace.Type<T> {
                version 1 <T>
                version 2 <T1, T2>
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(3, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(CANNOT_HAVE_GENERIC_PARAMETERS_ON_OUTER_TYPE_AND_VERSION_AT_THE_SAME_TIME.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 2, 19, CANNOT_HAVE_GENERIC_PARAMETERS_ON_OUTER_TYPE_AND_VERSION_AT_THE_SAME_TIME.getMessage("T", "TestNamespace.Type")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(CANNOT_HAVE_GENERIC_PARAMETERS_ON_OUTER_TYPE_AND_VERSION_AT_THE_SAME_TIME.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 19, CANNOT_HAVE_GENERIC_PARAMETERS_ON_OUTER_TYPE_AND_VERSION_AT_THE_SAME_TIME.getMessage("T1", "TestNamespace.Type")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(CANNOT_HAVE_GENERIC_PARAMETERS_ON_OUTER_TYPE_AND_VERSION_AT_THE_SAME_TIME.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 23, CANNOT_HAVE_GENERIC_PARAMETERS_ON_OUTER_TYPE_AND_VERSION_AT_THE_SAME_TIME.getMessage("T2", "TestNamespace.Type")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterHasNotBeenDefinedInTypeOnce() {
        var testProgram = """
            type TestNamespace.Type1<T>
            type TestNamespace.Type<T> {
                version 1 : TestNamespace.Type1<T2>
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 40, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterHasNotBeenDefinedInTypeOnceDouble() {
        var testProgram = """
            type TestNamespace.Type1<T1, T2>
            type TestNamespace.Type<T> {
                version 1 : TestNamespace.Type1<T2, T3>
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 40, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 44, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T3", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterHasNotBeenDefinedInTypeTwice() {
        var testProgram = """
            type TestNamespace.Type1<T>
            type interface TestNamespace.Type2<T>
            type TestNamespace.Type<T> {
                version 1 : TestNamespace.Type1<T2>, TestNamespace.Type2<T2>
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 40, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 65, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterHasNotBeenDefinedInTypeTwiceDouble() {
        var testProgram = """
            type TestNamespace.Type1<T1, T2>
            type interface TestNamespace.Type2<T1, T2>
            type TestNamespace.Type<T> {
                version 1 : TestNamespace.Type1<T2, T3>, TestNamespace.Type2<T2, T4>
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(4, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 40, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 44, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T3", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 69, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 73, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T4", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(3).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterHasNotBeenDefinedInTypeTwiceDoubleMap() {
        var testProgram = """
            type TestNamespace.Type1<T1, T2>
            type interface TestNamespace.Type2<T1, T2>
            type TestNamespace.Type<T> {
                version 1 : TestNamespace.Type1<map<T2, T2>, map<T3, T3>>, TestNamespace.Type2<map<T2, T2>, map<T4, T4>>
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(8, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 44, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 48, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 57, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T3", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 61, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T3", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(3).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 91, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(4).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 95, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(5).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 104, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T4", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(6).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 108, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T4", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(7).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterHasNotBeenDefinedInTypeVersion1Once() {
        var testProgram = """
            type TestNamespace.Type1<T>
            type TestNamespace.Type {
                version 1 <T> : TestNamespace.Type1<T2>
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 44, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterHasNotBeenDefinedInTypeVersion1OnceDouble() {
        var testProgram = """
            type TestNamespace.Type1<T1, T2>
            type TestNamespace.Type {
                version 1 <T> : TestNamespace.Type1<T2, T3>
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 44, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 48, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T3", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterHasNotBeenDefinedInTypeVersion1Twice() {
        var testProgram = """
            type TestNamespace.Type1<T>
            type interface TestNamespace.Type2<T>
            type TestNamespace.Type {
                version 1 <T> : TestNamespace.Type1<T2>, TestNamespace.Type2<T2>
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 44, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 69, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterHasNotBeenDefinedInTypeVersion1TwiceDouble() {
        var testProgram = """
            type TestNamespace.Type1<T1, T2>
            type interface TestNamespace.Type2<T1, T2>
            type TestNamespace.Type {
                version 1 <T> : TestNamespace.Type1<T2, T3>, TestNamespace.Type2<T2, T4>
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(4, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 44, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 48, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T3", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 73, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 77, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T4", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(3).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterHasNotBeenDefinedInTypeVersion1TwiceDoubleMap() {
        var testProgram = """
            type TestNamespace.Type1<T1, T2>
            type interface TestNamespace.Type2<T1, T2>
            type TestNamespace.Type {
                version 1 <T> : TestNamespace.Type1<map<T2, T2>, map<T3, T3>>, TestNamespace.Type2<map<T2, T2>, map<T4, T4>>
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(8, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 48, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 52, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 61, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T3", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 65, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T3", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(3).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 95, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(4).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 99, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(5).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 108, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T4", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(6).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 112, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T4", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(7).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterHasNotBeenDefinedInTypeVersion2Once() {
        var testProgram = """
            type TestNamespace.Type1<T>
            type TestNamespace.Type {
                version 1
                version 2 <T> : TestNamespace.Type1<T2>
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(1, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 44, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterHasNotBeenDefinedInTypeVersion2OnceDouble() {
        var testProgram = """
            type TestNamespace.Type1<T1, T2>
            type TestNamespace.Type {
                version 1
                version 2 <T> : TestNamespace.Type1<T2, T3>
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 44, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 48, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T3", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterHasNotBeenDefinedInTypeVersion2Twice() {
        var testProgram = """
            type TestNamespace.Type1<T>
            type interface TestNamespace.Type2<T>
            type TestNamespace.Type {
                version 1
                version 2 <T> : TestNamespace.Type1<T2>, TestNamespace.Type2<T2>
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 44, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 69, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterHasNotBeenDefinedInTypeVersion2TwiceDouble() {
        var testProgram = """
            type TestNamespace.Type1<T1, T2>
            type interface TestNamespace.Type2<T1, T2>
            type TestNamespace.Type {
                version 1
                version 2 <T> : TestNamespace.Type1<T2, T3>, TestNamespace.Type2<T2, T4>
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(4, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 44, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 48, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T3", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 73, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 77, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T4", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(3).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterHasNotBeenDefinedInTypeVersion2TwiceDoubleMap() {
        var testProgram = """
            type TestNamespace.Type1<T1, T2>
            type interface TestNamespace.Type2<T1, T2>
            type TestNamespace.Type {
                version 1
                version 2 <T> : TestNamespace.Type1<map<T2, T2>, map<T3, T3>>, TestNamespace.Type2<map<T2, T2>, map<T4, T4>>
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(8, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 48, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 52, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 61, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T3", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 65, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T3", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(3).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 95, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(4).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 99, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(5).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 108, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T4", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(6).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 112, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T4", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(7).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterHasNotBeenDefinedInTypeVersion1AndVersion2Once() {
        var testProgram = """
            type TestNamespace.Type1<T>
            type TestNamespace.Type {
                version 1 <T> : TestNamespace.Type1<T2>
                version 2 <T> : TestNamespace.Type1<T2>
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(2, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 44, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 44, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterHasNotBeenDefinedInTypeVersion1AndVersion2OnceDouble() {
        var testProgram = """
            type TestNamespace.Type1<T1, T2>
            type TestNamespace.Type {
                version 1 <T> : TestNamespace.Type1<T2, T3>
                version 2 <T> : TestNamespace.Type1<T2, T3>
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(4, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 44, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 3, 48, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T3", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 44, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 48, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T3", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(3).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterHasNotBeenDefinedInTypeVersion1AndVersion2Twice() {
        var testProgram = """
            type TestNamespace.Type1<T>
            type interface TestNamespace.Type2<T>
            type TestNamespace.Type {
                version 1 <T> : TestNamespace.Type1<T2>, TestNamespace.Type2<T2>
                version 2 <T> : TestNamespace.Type1<T2>, TestNamespace.Type2<T2>
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(4, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 44, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 69, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 44, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 69, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(3).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterHasNotBeenDefinedInTypeVersion1AndVersion2TwiceDouble() {
        var testProgram = """
            type TestNamespace.Type1<T1, T2>
            type interface TestNamespace.Type2<T1, T2>
            type TestNamespace.Type {
                version 1 <T> : TestNamespace.Type1<T2, T3>, TestNamespace.Type2<T2, T4>
                version 2 <T> : TestNamespace.Type1<T2, T3>, TestNamespace.Type2<T2, T4>
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(8, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 44, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 48, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T3", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 73, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 77, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T4", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(3).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 44, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(4).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 48, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T3", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(5).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 73, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(6).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 77, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T4", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(7).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

    @Test
    public void testGenericParameterHasNotBeenDefinedInTypeVersion1AndVersion2TwiceDoubleMap() {
        var testProgram = """
            type TestNamespace.Type1<T1, T2>
            type interface TestNamespace.Type2<T1, T2>
            type TestNamespace.Type {
                version 1 <T> : TestNamespace.Type1<map<T2, T2>, map<T3, T3>>, TestNamespace.Type2<map<T2, T2>, map<T4, T4>>
                version 2 <T> : TestNamespace.Type1<map<T2, T2>, map<T3, T3>>, TestNamespace.Type2<map<T2, T2>, map<T4, T4>>
            }
        """;
        var semanticErrors = runCompilerToSemanticAnalyserReturnSemanticErrors(testProgram);
        assertNotNull(semanticErrors, "SemanticErrors list is null");
        assertEquals(16, semanticErrors.size(), "Unexpected parser errors size");
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 48, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(0).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 52, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(1).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 61, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T3", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(2).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 65, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T3", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(3).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 95, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(4).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 99, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(5).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 108, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T4", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(6).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 4, 112, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T4", "TestNamespace.Type(Version 1)")),
            semanticErrors.get(7).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 48, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(8).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 52, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(9).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 61, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T3", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(10).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 65, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T3", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(11).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 95, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(12).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 99, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T2", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(13).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 108, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T4", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(14).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
        assertEquals(
            SEMANTIC_ERROR_MESSAGE.formatted(GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getNumber(), FAKE_SOURCE_FILE_NAME_AND_PATH, 5, 112, GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE.getMessage("T4", "TestNamespace.Type(Version 2)")),
            semanticErrors.get(15).getFullErrorMessage(),
            "Unexpected semantic error message"
        );
    }

}