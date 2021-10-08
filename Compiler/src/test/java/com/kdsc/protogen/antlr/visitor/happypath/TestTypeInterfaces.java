package com.kdsc.protogen.antlr.visitor.happypath;

import com.kdsc.protogen.BaseCompilerTest;
import org.junit.jupiter.api.Test;

public final class TestTypeInterfaces extends BaseCompilerTest {

    @Test
    void testSingleTypeInterface() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testSingleTypeInterfaceNestedNamespace() {
        var testProgram = """
            type interface TestNamespace.TestNestedNamespace.TestTypeInterface
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testMultipleTypeInterfaces() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface1
            type interface TestNamespace.TestTypeInterface2
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicTypeInterfaceNoFieldsOrBraces() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicTypeInterfaceNoFieldsWithBraces() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface {}
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicTypeInterfaceNoFieldsWithSplitBraces() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicTypeInterfaceOneField() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface {
                testField : int32
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicTypeInterfaceTwoFields() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface {
                testField1 : int32
                testField2 : int32
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicTypeInterfaceOneImplementsNoFieldsNoBraces() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface : TestNamespace.OtherTypeInterface
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicTypeInterfaceOneImplementsNoFieldsEmptyBraces() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface : TestNamespace.OtherTypeInterface {}
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicTypeInterfaceOneImplementsNoFieldsSplitEmptyBraces() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface : TestNamespace.OtherTypeInterface {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    //This should produce a parse tree but not pass semantic analysis as it doesn't make sense
    @Test
    void testBasicTypeInterfaceOneImplementsNoFieldsNoBracesGenericParameter() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface : TestNamespace.OtherTypeInterface<T>
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    //This should produce a parse tree but not pass semantic analysis as it doesn't make sense
    @Test
    void testBasicTypeInterfaceOneImplementsNoFieldsEmptyBracesGenericParameter() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface : TestNamespace.OtherTypeInterface<T> {}
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    //This should produce a parse tree but not pass semantic analysis as it doesn't make sense
    @Test
    void testBasicTypeInterfaceOneImplementsNoFieldsSplitEmptyBracesGenericParameter() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface : TestNamespace.OtherTypeInterface<T> {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicTypeInterfaceTwoImplementsNoFieldsNoBraces() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface :
                TestNamespace.OtherTypeInterface1,
                TestNamespace.OtherTypeInterface2
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicTypeInterfaceTwoImplementsNoFieldsEmptyBraces() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface :
                TestNamespace.OtherTypeInterface1,
                TestNamespace.OtherTypeInterface2 {}
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicTypeInterfaceTwoImplementsNoFieldsSplitEmptyBraces() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface :
                TestNamespace.OtherTypeInterface1,
                TestNamespace.OtherTypeInterface2 {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    //This should produce a parse tree but not pass semantic analysis as it doesn't make sense
    @Test
    void testBasicTypeInterfaceTwoImplementsNoFieldsNoBracesGenericParameter() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface :
                TestNamespace.OtherTypeInterface1<T>,
                TestNamespace.OtherTypeInterface2<T>
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    //This should produce a parse tree but not pass semantic analysis as it doesn't make sense
    @Test
    void testBasicTypeInterfaceTwoImplementsNoFieldsEmptyBracesGenericParameter() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface :
                TestNamespace.OtherTypeInterface1<T>,
                TestNamespace.OtherTypeInterface2<T> {}
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    //This should produce a parse tree but not pass semantic analysis as it doesn't make sense
    @Test
    void testBasicTypeInterfaceTwoImplementsNoFieldsSplitEmptyBracesGenericParameter() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface :
                TestNamespace.OtherTypeInterface1<T>,
                TestNamespace.OtherTypeInterface2<T> {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicVersionedTypeInterfaceEmptyVersion() {
        var testProgram = """
            type interface TestNamespace.TestVersionedTypeInterface {
                version 1 {}
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicVersionedTypeInterfaceOneVersion() {
        var testProgram = """
            type interface TestNamespace.TestVersionedTypeInterface {
                version 1 {
                    testField : int32
                }
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicVersionedTypeInterfaceTwoVersions() {
        var testProgram = """
            type interface TestNamespace.TestVersionedTypeInterface {
                version 1 {
                    testField : int32
                }
                version 2 {
                    testField : int32
                }
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericTypeInterface() {
        var testProgram = """
            type interface TestNamespace.TestGenericTypeInterface<T> {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericTypeInterfaceWithImplements() {
        var testProgram = """
            type interface TestNamespace.TestGenericTypeInterface<T> : TestNamespace.TestImplementsInterface {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericTypeInterfaceWithImplementsGeneric() {
        var testProgram = """
            type interface TestNamespace.TestGenericTypeInterface<T> : TestNamespace.TestImplementsInterface<T> {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericTypeInterfaceWithTwoImplements() {
        var testProgram = """
            type interface TestNamespace.TestGenericTypeInterface<T> : TestNamespace.TestImplementsInterface1, TestNamespace.TestImplementsInterface2 {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericTypeInterfaceWithTwoImplementsGeneric() {
        var testProgram = """
            type interface TestNamespace.TestGenericTypeInterface<T> : TestNamespace.TestImplementsInterface1<T>, TestNamespace.TestImplementsInterface2<T> {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericTypeInterfaceWithBounds() {
        var testProgram = """
            type interface TestNamespace.TestGenericTypeInterface<T : TestNamespace.TestTypeInterface> {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericTypeInterfaceWithTwoBounds() {
        var testProgram = """
            type interface TestNamespace.TestGenericTypeInterface<T : TestNamespace.TestTypeInterface1 & TestNamespace.TestTypeInterface2 > {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericVersionedTypeInterfaceWithSingleGenericParameters() {
        var testProgram = """
            type interface TestNamespace.TestVersionedGenericTypeInterface<T> {
                version 1 {
                    testField : int32
                }
                version 2 {
                    testField : int32
                }
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericVersionedTypeInterfaceWithSingleGenericParametersWithBounds() {
        var testProgram = """
            type interface TestNamespace.TestVersionedGenericTypeInterface<T : TestNamespace.TestTypeInterface> {
                version 1 {
                    testField : int32
                }
                version 2 {
                    testField : int32
                }
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericVersionedTypeInterfaceWithSingleGenericParametersWithTwoBounds() {
        var testProgram = """
            type interface TestNamespace.TestVersionedGenericTypeInterface<T : TestNamespace.TestTypeInterface1 & TestNamespace.TestTypeInterface2> {
                version 1 {
                    testField : int32
                }
                version 2 {
                    testField : int32
                }
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericVersionedTypeInterfaceWithSingleGenericParametersAndOneEmptyVersion() {
        var testProgram = """
            type interface TestNamespace.TestVersionedGenericTypeInterface<T> {
                version 1 {}
                version 2 {
                    testField : int32
                }
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericVersionedTypeInterfaceWithIndividualGenericParameters() {
        var testProgram = """
            type interface TestNamespace.TestVersionedGenericTypeInterface {
                version 1 <T> {
                    testField : int32
                }
                version 2 <T> {
                    testField : int32
                }
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericVersionedTypeInterfaceWithIndividualGenericParametersAndDifferentOneImplements() {
        var testProgram = """
            type interface TestNamespace.TestVersionedGenericTypeInterface {
                version 1 <T> : TestNamespace.OtherTypeInterface1 {
                    testField : int32
                }
                version 2 <T> : TestNamespace.OtherTypeInterface2 {
                    testField : int32
                }
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericVersionedTypeInterfaceWithIndividualGenericParametersAndDifferentOneImplementsWithOneBounds() {
        var testProgram = """
            type interface TestNamespace.TestVersionedGenericTypeInterface {
                version 1 <T : TestNamespace.TestTypeInterface> : TestNamespace.OtherTypeInterface1 {
                    testField : int32
                }
                version 2 <T : TestNamespace.TestTypeInterface> : TestNamespace.TestTypeInterface, TestNamespace.OtherTypeInterface2 {
                    testField : int32
                }
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericVersionedTypeInterfaceWithIndividualGenericParametersAndDifferentOneImplementsWithTwoBounds() {
        var testProgram = """
            type interface TestNamespace.TestVersionedGenericTypeInterface {
                version 1 <T : TestNamespace.TestTypeInterface1 & TestNamespace.TestTypeInterface2> : TestNamespace.OtherTypeInterface1 {
                    testField : int32
                }
                version 2 <T : TestNamespace.TestTypeInterface1 & TestNamespace.TestTypeInterface2> : TestNamespace.TestTypeInterface, TestNamespace.OtherTypeInterface2 {
                    testField : int32
                }
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericVersionedTypeInterfaceWithIndividualGenericParametersAndDifferentTwoImplements() {
        var testProgram = """
            type interface TestNamespace.TestVersionedGenericTypeInterface {
                version 1 <T> : TestNamespace.OtherTypeInterface1, TestNamespace.OtherTypeInterface2 {
                    testField : int32
                }
                version 2 <T> : TestNamespace.OtherTypeInterface3<T>, TestNamespace.OtherTypeInterface4<T> {
                    testField : int32
                }
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testAllFieldTypeInterfaces() {
        var testProgram = """
            type interface TestNamespace.TestAllFieldTypeInterfaces<T> {
                testDoubleField : double
                testFloatField : float
                testInt32Field : int32
                testInt64Field : int64
                testBoolField : bool
                testStringField : string
                testByteField : bytes
                testDecimalField : decimal
                testDateField : date
                testDatetimeField : datetime
                testMapField : map<int32, int32>
                testSetField : set<int32>
                testValueOrErrorField : valueorerror<int32>
                testArrayField : int32[]
                testTypeInterfaceField : TestNamespace.TestTypeInterface
                testGenericField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testAllFieldTypeInterfacesOptional() {
        var testProgram = """
            type interface TestNamespace.TestAllFieldTypeInterfaces<T> {
                testDoubleField : optional double
                testFloatField : optional float
                testInt32Field : optional int32
                testInt64Field : optional int64
                testBoolField : optional bool
                testStringField : optional string
                testByteField : optional bytes
                testDecimalField : optional decimal
                testDateField : optional date
                testDatetimeField : optional datetime
                testMapField : optional map<int32, int32>
                testSetField : optional set<int32>
                testValueOrErrorField : optional  valueorerror<int32>
                testArrayField : optional int32[]
                testTypeInterfaceField : optional TestNamespace.TestTypeInterface
                testGenericField : optional T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testAllFieldTypeInterfacesOptionalOneLine() {
        var testProgram = """
            type interface TestNamespace.TestAllFieldTypeInterfaces<T> { testDoubleField : optional double testFloatField : optional float testInt32Field : optional int32 testInt64Field : optional int64 testBoolField : optional bool testStringField : optional string testByteField : optional bytes testDecimalField : optional decimal testDateField : optional date testDatetimeField : optional datetime testMapField : optional map<int32, int32> testSetField : optional set<int32> testArrayField : optional int32[] testTypeInterfaceField : optional TestNamespace.TestTypeInterface testGenericField : optional T }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testRecursiveMaps() {
        var testProgram = """
            type interface TestNamespace.TestRecursiveMaps {
                testRecursiveMap : map<map<int32, int32>, map<int32, int32>>
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testRecursiveSets() {
        var testProgram = """
            type interface TestNamespace.TestRecursiveSet {
                testRecursiveSet : set<set<int32>>
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testRecursiveMultiDimensionalArray() {
        var testProgram = """
            type interface TestNamespace.TestMultiDimensionalArray {
                testMultiDimensionalArray : int32[][][]
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testRecursiveNestedMapSetArray() {
        var testProgram = """
            type interface TestNamespace.TestMultiDimensionalArray {
                testNestedMapSetArray : map<set<int32[][]>, set<int32[][]>>
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testSingleLineCommentAtTop() {
        var testProgram = """
            //Comment at top
            type interface TestNamespace.TestComment {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testSingleLineCommentInMiddle() {
        var testProgram = """
            type interface TestNamespace.TestComment {
                //Comment in middle
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testSingleLineCommentAtBottom() {
        var testProgram = """
            type interface TestNamespace.TestComment {
            }
            //Comment at bottom
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testSingleLineCommentWithToken() {
        var testProgram = """
            //type interface int32 string bool
            type interface TestNamespace.TestComment {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testMultiLineCommentAtTop() {
        var testProgram = """
            /*
            Comment at top
            */
            type interface TestNamespace.TestComment {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testMultiLineCommentInMiddle() {
        var testProgram = """
            type interface TestNamespace.TestComment {
                /*
                Comment in middle
                */
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testMultiLineCommentAtBottom() {
        var testProgram = """
            type interface TestNamespace.TestComment {
            }
            /*
            Comment at bottom
            */
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testMultiLineCommentWithToken() {
        var testProgram = """
            /*
            type interface
            int32
            string bool
            */
            type TestNamespace.TestComment {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

}