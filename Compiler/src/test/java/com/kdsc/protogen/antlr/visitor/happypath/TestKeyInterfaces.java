package com.kdsc.protogen.antlr.visitor.happypath;

import com.kdsc.protogen.BaseCompilerTest;
import org.junit.jupiter.api.Test;

public final class TestKeyInterfaces extends BaseCompilerTest {

    @Test
    void testSingleKeyInterface() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testSingleKeyInterfaceNestedNamespace() {
        var testProgram = """
            key interface TestNamespace.TestNestedNamespace.TestKeyInterface
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testMultipleKeyInterfaces() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface1
            key interface TestNamespace.TestKeyInterface2
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicKeyInterfaceNoFieldsOrBraces() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicKeyInterfaceNoFieldsWithBraces() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface {}
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicKeyInterfaceNoFieldsWithSplitBraces() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicKeyInterfaceOneField() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface {
                testField : int32
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicKeyInterfaceTwoFields() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface {
                testField1 : int32
                testField2 : int32
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicKeyInterfaceOneImplementsNoFieldsNoBraces() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface : TestNamespace.OtherKeyInterface
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicKeyInterfaceOneImplementsNoFieldsEmptyBraces() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface : TestNamespace.OtherKeyInterface {}
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicKeyInterfaceOneImplementsNoFieldsSplitEmptyBraces() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface : TestNamespace.OtherKeyInterface {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    //This should produce a parse tree but not pass semantic analysis as it doesn't make sense
    @Test
    void testBasicKeyInterfaceOneImplementsNoFieldsNoBracesGenericParameter() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface : TestNamespace.OtherKeyInterface<T>
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    //This should produce a parse tree but not pass semantic analysis as it doesn't make sense
    @Test
    void testBasicKeyInterfaceOneImplementsNoFieldsEmptyBracesGenericParameter() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface : TestNamespace.OtherKeyInterface<T> {}
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    //This should produce a parse tree but not pass semantic analysis as it doesn't make sense
    @Test
    void testBasicKeyInterfaceOneImplementsNoFieldsSplitEmptyBracesGenericParameter() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface : TestNamespace.OtherKeyInterface<T> {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicKeyInterfaceTwoImplementsNoFieldsNoBraces() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface :
                TestNamespace.OtherKeyInterface1,
                TestNamespace.OtherKeyInterface2
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicKeyInterfaceTwoImplementsNoFieldsEmptyBraces() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface :
                TestNamespace.OtherKeyInterface1,
                TestNamespace.OtherKeyInterface2 {}
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicKeyInterfaceTwoImplementsNoFieldsSplitEmptyBraces() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface :
                TestNamespace.OtherKeyInterface1,
                TestNamespace.OtherKeyInterface2 {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    //This should produce a parse tree but not pass semantic analysis as it doesn't make sense
    @Test
    void testBasicKeyInterfaceTwoImplementsNoFieldsNoBracesGenericParameter() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface :
                TestNamespace.OtherKeyInterface1<T>,
                TestNamespace.OtherKeyInterface2<T>
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    //This should produce a parse tree but not pass semantic analysis as it doesn't make sense
    @Test
    void testBasicKeyInterfaceTwoImplementsNoFieldsEmptyBracesGenericParameter() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface :
                TestNamespace.OtherKeyInterface1<T>,
                TestNamespace.OtherKeyInterface2<T> {}
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    //This should produce a parse tree but not pass semantic analysis as it doesn't make sense
    @Test
    void testBasicKeyInterfaceTwoImplementsNoFieldsSplitEmptyBracesGenericParameter() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface :
                TestNamespace.OtherKeyInterface1<T>,
                TestNamespace.OtherKeyInterface2<T> {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicVersionedKeyInterfaceEmptyVersion() {
        var testProgram = """
            key interface TestNamespace.TestVersionedKeyInterface {
                version 1 {}
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicVersionedKeyInterfaceOneVersion() {
        var testProgram = """
            key interface TestNamespace.TestVersionedKeyInterface {
                version 1 {
                    testField : int32
                }
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicVersionedKeyInterfaceTwoVersions() {
        var testProgram = """
            key interface TestNamespace.TestVersionedKeyInterface {
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
    void testGenericKeyInterface() {
        var testProgram = """
            key interface TestNamespace.TestGenericKeyInterface<T> {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericKeyInterfaceWithImplements() {
        var testProgram = """
            key interface TestNamespace.TestGenericKeyInterface<T> : TestNamespace.TestImplementsInterface {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericKeyInterfaceWithImplementsGeneric() {
        var testProgram = """
            key interface TestNamespace.TestGenericKeyInterface<T> : TestNamespace.TestImplementsInterface<T> {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericKeyInterfaceWithTwoImplements() {
        var testProgram = """
            key interface TestNamespace.TestGenericKeyInterface<T> : TestNamespace.TestImplementsInterface1, TestNamespace.TestImplementsInterface2 {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericKeyInterfaceWithTwoImplementsGeneric() {
        var testProgram = """
            key interface TestNamespace.TestGenericKeyInterface<T> : TestNamespace.TestImplementsInterface1<T>, TestNamespace.TestImplementsInterface2<T> {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericKeyInterfaceWithBounds() {
        var testProgram = """
            key interface TestNamespace.TestGenericKeyInterface<T : TestNamespace.TestKeyInterface> {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericKeyInterfaceWithTwoBounds() {
        var testProgram = """
            key interface TestNamespace.TestGenericKeyInterface<T : TestNamespace.TestKeyInterface1 & TestNamespace.TestKeyInterface2 > {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericVersionedKeyInterfaceWithSingleGenericParameters() {
        var testProgram = """
            key interface TestNamespace.TestVersionedGenericKeyInterface<T> {
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
    void testGenericVersionedKeyInterfaceWithSingleGenericParametersWithBounds() {
        var testProgram = """
            key interface TestNamespace.TestVersionedGenericKeyInterface<T : TestNamespace.TestKeyInterface> {
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
    void testGenericVersionedKeyInterfaceWithSingleGenericParametersWithTwoBounds() {
        var testProgram = """
            key interface TestNamespace.TestVersionedGenericKeyInterface<T : TestNamespace.TestKeyInterface1 & TestNamespace.TestKeyInterface2> {
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
    void testGenericVersionedKeyInterfaceWithSingleGenericParametersAndOneEmptyVersion() {
        var testProgram = """
            key interface TestNamespace.TestVersionedGenericKeyInterface<T> {
                version 1 {}
                version 2 {
                    testField : int32
                }
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericVersionedKeyInterfaceWithIndividualGenericParameters() {
        var testProgram = """
            key interface TestNamespace.TestVersionedGenericKeyInterface {
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
    void testGenericVersionedKeyInterfaceWithIndividualGenericParametersAndDifferentOneImplements() {
        var testProgram = """
            key interface TestNamespace.TestVersionedGenericKeyInterface {
                version 1 <T> : TestNamespace.OtherKeyInterface1 {
                    testField : int32
                }
                version 2 <T> : TestNamespace.OtherKeyInterface2 {
                    testField : int32
                }
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericVersionedKeyInterfaceWithIndividualGenericParametersAndDifferentOneImplementsWithOneBounds() {
        var testProgram = """
            key interface TestNamespace.TestVersionedGenericKeyInterface {
                version 1 <T : TestNamespace.TestKeyInterface> : TestNamespace.OtherKeyInterface1 {
                    testField : int32
                }
                version 2 <T : TestNamespace.TestKeyInterface> : TestNamespace.TestKeyInterface, TestNamespace.OtherKeyInterface2 {
                    testField : int32
                }
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericVersionedKeyInterfaceWithIndividualGenericParametersAndDifferentOneImplementsWithTwoBounds() {
        var testProgram = """
            key interface TestNamespace.TestVersionedGenericKeyInterface {
                version 1 <T : TestNamespace.TestKeyInterface1 & TestNamespace.TestKeyInterface2> : TestNamespace.OtherKeyInterface1 {
                    testField : int32
                }
                version 2 <T : TestNamespace.TestKeyInterface1 & TestNamespace.TestKeyInterface2> : TestNamespace.TestKeyInterface, TestNamespace.OtherKeyInterface2 {
                    testField : int32
                }
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericVersionedKeyInterfaceWithIndividualGenericParametersAndDifferentTwoImplements() {
        var testProgram = """
            key interface TestNamespace.TestVersionedGenericKeyInterface {
                version 1 <T> : TestNamespace.OtherKeyInterface1, TestNamespace.OtherKeyInterface2 {
                    testField : int32
                }
                version 2 <T> : TestNamespace.OtherKeyInterface3<T>, TestNamespace.OtherKeyInterface4<T> {
                    testField : int32
                }
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testAllFieldKeyInterfaces() {
        var testProgram = """
            key interface TestNamespace.TestAllFieldKeyInterfaces<T> {
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
                testValueOrError : valueorerror<int32>
                testArrayField : int32[]
                testKeyInterfaceField : TestNamespace.TestKeyInterface
                testGenericField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testAllFieldKeyInterfacesOptional() {
        var testProgram = """
            key interface TestNamespace.TestAllFieldKeyInterfaces<T> {
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
                testValueOrError : optional valueorerror<int32>
                testArrayField : optional int32[]
                testKeyInterfaceField : optional TestNamespace.TestKeyInterface
                testGenericField : optional T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testAllFieldKeyInterfacesOptionalOneLine() {
        var testProgram = """
            key interface TestNamespace.TestAllFieldTypes<T> { testDoubleField : optional double testFloatField : optional float testInt32Field : optional int32 testInt64Field : optional int64 testBoolField : optional bool testStringField : optional string testByteField : optional bytes testDecimalField : optional decimal testDateField : optional date testDatetimeField : optional datetime testMapField : optional map<int32, int32> testSetField : optional set<int32> testArrayField : optional int32[] testTypeField : optional TestNamespace.TestType testGenericField : optional T }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testRecursiveMaps() {
        var testProgram = """
            key interface TestNamespace.TestRecursiveMaps {
                testRecursiveMap : map<map<int32, int32>, map<int32, int32>>
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testRecursiveSets() {
        var testProgram = """
            key interface TestNamespace.TestRecursiveSet {
                testRecursiveSet : set<set<int32>>
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testRecursiveMultiDimensionalArray() {
        var testProgram = """
            key interface TestNamespace.TestMultiDimensionalArray {
                testMultiDimensionalArray : int32[][][]
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testRecursiveNestedMapSetArray() {
        var testProgram = """
            key interface TestNamespace.TestMultiDimensionalArray {
                testNestedMapSetArray : map<set<int32[][]>, set<int32[][]>>
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testSingleLineCommentAtTop() {
        var testProgram = """
            //Comment at top
            key interface TestNamespace.TestComment {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testSingleLineCommentInMiddle() {
        var testProgram = """
            key interface TestNamespace.TestComment {
                //Comment in middle
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testSingleLineCommentAtBottom() {
        var testProgram = """
            key interface TestNamespace.TestComment {
            }
            //Comment at bottom
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testSingleLineCommentWithToken() {
        var testProgram = """
            //type int32 string bool
            key interface TestNamespace.TestComment {
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
            key interface TestNamespace.TestComment {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testMultiLineCommentInMiddle() {
        var testProgram = """
            key interface TestNamespace.TestComment {
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
            key interface TestNamespace.TestComment {
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
            type
            int32
            string bool
            */
            key interface TestNamespace.TestComment {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

}