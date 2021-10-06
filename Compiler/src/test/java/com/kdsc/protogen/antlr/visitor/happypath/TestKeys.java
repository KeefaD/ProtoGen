package com.kdsc.protogen.antlr.visitor.happypath;

import com.kdsc.protogen.BaseCompilerTest;
import org.junit.jupiter.api.Test;

public class TestKeys extends BaseCompilerTest {

    @Test
    void testSingleKey() {
        var testProgram = """
            key TestNamespace.TestKey
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testSingleKeyNestedNamespace() {
        var testProgram = """
            key TestNamespace.TestNestedNamespace.TestKey
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testMultipleKeys() {
        var testProgram = """
            key TestNamespace.TestKey1
            key TestNamespace.TestKey2
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicKeyNoFieldsOrBraces() {
        var testProgram = """
            key TestNamespace.TestKey
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicKeyNoFieldsWithBraces() {
        var testProgram = """
            key TestNamespace.TestKey {}
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicKeyNoFieldsWithSplitBraces() {
        var testProgram = """
            key TestNamespace.TestKey {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicKeyOneField() {
        var testProgram = """
            key TestNamespace.TestKey {
                testField : int32
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicKeyTwoFields() {
        var testProgram = """
            key TestNamespace.TestKey {
                testField1 : int32
                testField2 : int32
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicKeyOneImplementsNoFieldsNoBraces() {
        var testProgram = """
            key TestNamespace.TestKey : TestNamespace.OtherKey
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicKeyOneImplementsNoFieldsEmptyBraces() {
        var testProgram = """
            key TestNamespace.TestKey : TestNamespace.OtherKey {}
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicKeyOneImplementsNoFieldsSplitEmptyBraces() {
        var testProgram = """
            key TestNamespace.TestKey : TestNamespace.OtherKey {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    //This should produce a parse tree but not pass semantic analysis as it doesn't make sense
    @Test
    void testBasicKeyOneImplementsNoFieldsNoBracesGenericParameter() {
        var testProgram = """
            key TestNamespace.TestKey : TestNamespace.OtherKey<T>
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    //This should produce a parse tree but not pass semantic analysis as it doesn't make sense
    @Test
    void testBasicKeyOneImplementsNoFieldsEmptyBracesGenericParameter() {
        var testProgram = """
            key TestNamespace.TestKey : TestNamespace.OtherKey<T> {}
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    //This should produce a parse tree but not pass semantic analysis as it doesn't make sense
    @Test
    void testBasicKeyOneImplementsNoFieldsSplitEmptyBracesGenericParameter() {
        var testProgram = """
            key TestNamespace.TestKey : TestNamespace.OtherKey<T> {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicKeyTwoImplementsNoFieldsNoBraces() {
        var testProgram = """
            key TestNamespace.TestKey :
                TestNamespace.OtherKey1,
                TestNamespace.OtherKey2
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicKeyTwoImplementsNoFieldsEmptyBraces() {
        var testProgram = """
            key TestNamespace.TestKey :
                TestNamespace.OtherKey1,
                TestNamespace.OtherKey2 {}
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicKeyTwoImplementsNoFieldsSplitEmptyBraces() {
        var testProgram = """
            key TestNamespace.TestKey :
                TestNamespace.OtherKey1,
                TestNamespace.OtherKey2 {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    //This should produce a parse tree but not pass semantic analysis as it doesn't make sense
    @Test
    void testBasicKeyTwoImplementsNoFieldsNoBracesGenericParameter() {
        var testProgram = """
            key TestNamespace.TestKey :
                TestNamespace.OtherKey1<T>,
                TestNamespace.OtherKey2<T>
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    //This should produce a parse tree but not pass semantic analysis as it doesn't make sense
    @Test
    void testBasicKeyTwoImplementsNoFieldsEmptyBracesGenericParameter() {
        var testProgram = """
            key TestNamespace.TestKey :
                TestNamespace.OtherKey1<T>,
                TestNamespace.OtherKey2<T> {}
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    //This should produce a parse tree but not pass semantic analysis as it doesn't make sense
    @Test
    void testBasicKeyTwoImplementsNoFieldsSplitEmptyBracesGenericParameter() {
        var testProgram = """
            key TestNamespace.TestKey :
                TestNamespace.OtherKey1<T>,
                TestNamespace.OtherKey2<T> {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicVersionedKeyEmptyVersion() {
        var testProgram = """
            key TestNamespace.TestVersionedKey {
                version 1 {}
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicVersionedKeyOneVersion() {
        var testProgram = """
            key TestNamespace.TestVersionedKey {
                version 1 {
                    testField : int32
                }
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicVersionedKeyTwoVersions() {
        var testProgram = """
            key TestNamespace.TestVersionedKey {
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
    void testGenericKey() {
        var testProgram = """
            key TestNamespace.TestGenericKey<T> {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericKeyWithImplements() {
        var testProgram = """
            key TestNamespace.TestGenericKey<T> : TestNamespace.TestImplements {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericKeyWithImplementsGeneric() {
        var testProgram = """
            key TestNamespace.TestGenericKey<T> : TestNamespace.TestImplements<T> {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericKeyWithTwoImplements() {
        var testProgram = """
            key TestNamespace.TestGenericKey<T> : TestNamespace.TestImplements1, TestNamespace.TestImplements2 {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericKeyWithTwoImplementsGeneric() {
        var testProgram = """
            key TestNamespace.TestGenericKey<T> : TestNamespace.TestImplements1<T>, TestNamespace.TestImplements2<T> {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericKeyWithBounds() {
        var testProgram = """
            key TestNamespace.TestGenericKey<T : TestNamespace.TestKey> {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericKeyWithTwoBounds() {
        var testProgram = """
            key TestNamespace.TestGenericKey<T : TestNamespace.TestKey1 & TestNamespace.TestKey2 > {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericVersionedKeyWithSingleGenericParameters() {
        var testProgram = """
            key TestNamespace.TestVersionedGenericKey<T> {
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
    void testGenericVersionedKeyWithSingleGenericParametersWithBounds() {
        var testProgram = """
            key TestNamespace.TestVersionedGenericKey<T : TestNamespace.TestKey> {
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
    void testGenericVersionedKeyWithSingleGenericParametersWithTwoBounds() {
        var testProgram = """
            key TestNamespace.TestVersionedGenericKey<T : TestNamespace.TestKey1 & TestNamespace.TestKey2> {
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
    void testGenericVersionedKeyWithSingleGenericParametersAndOneEmptyVersion() {
        var testProgram = """
            key TestNamespace.TestVersionedGenericKey<T> {
                version 1 {}
                version 2 {
                    testField : int32
                }
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericVersionedKeyWithIndividualGenericParameters() {
        var testProgram = """
            key TestNamespace.TestVersionedGenericKey {
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
    void testGenericVersionedKeyWithIndividualGenericParametersAndDifferentOneImplements() {
        var testProgram = """
            key TestNamespace.TestVersionedGenericKey {
                version 1 <T> : TestNamespace.OtherKey1 {
                    testField : int32
                }
                version 2 <T> : TestNamespace.OtherKey2 {
                    testField : int32
                }
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericVersionedKeyWithIndividualGenericParametersAndDifferentOneImplementsWithOneBounds() {
        var testProgram = """
            key TestNamespace.TestVersionedGenericKey {
                version 1 <T : TestNamespace.TestKey> : TestNamespace.OtherKey1 {
                    testField : int32
                }
                version 2 <T : TestNamespace.TestKey> : TestNamespace.TestKey, TestNamespace.OtherKey2 {
                    testField : int32
                }
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericVersionedKeyWithIndividualGenericParametersAndDifferentOneImplementsWithTwoBounds() {
        var testProgram = """
            key TestNamespace.TestVersionedGenericKey {
                version 1 <T : TestNamespace.TestKey1 & TestNamespace.TestKey2> : TestNamespace.OtherKey1 {
                    testField : int32
                }
                version 2 <T : TestNamespace.TestKey1 & TestNamespace.TestKey2> : TestNamespace.TestKey, TestNamespace.OtherKey2 {
                    testField : int32
                }
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericVersionedKeyWithIndividualGenericParametersAndDifferentTwoImplements() {
        var testProgram = """
            key TestNamespace.TestVersionedGenericKey {
                version 1 <T> : TestNamespace.OtherKey1, TestNamespace.OtherKey2 {
                    testField : int32
                }
                version 2 <T> : TestNamespace.OtherKey3<T>, TestNamespace.OtherKey4<T> {
                    testField : int32
                }
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testAllFieldKeys() {
        var testProgram = """
            key TestNamespace.TestAllFieldKeys<T> {
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
                testKeyField : TestNamespace.TestKey
                testGenericField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testAllFieldKeysOptional() {
        var testProgram = """
            key TestNamespace.TestAllFieldKeys<T> {
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
                testKeyField : optional TestNamespace.TestKey
                testGenericField : optional T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testAllFieldKeysOptionalOneLine() {
        var testProgram = """
            key TestNamespace.TestAllFieldTypes<T> { testDoubleField : optional double testFloatField : optional float testInt32Field : optional int32 testInt64Field : optional int64 testBoolField : optional bool testStringField : optional string testByteField : optional bytes testDecimalField : optional decimal testDateField : optional date testDatetimeField : optional datetime testMapField : optional map<int32, int32> testSetField : optional set<int32> testArrayField : optional int32[] testTypeField : optional TestNamespace.TestType testGenericField : optional T }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testRecursiveMaps() {
        var testProgram = """
            key TestNamespace.TestRecursiveMaps {
                testRecursiveMap : map<map<int32, int32>, map<int32, int32>>
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testRecursiveSets() {
        var testProgram = """
            key TestNamespace.TestRecursiveSet {
                testRecursiveSet : set<set<int32>>
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testRecursiveMultiDimensionalArray() {
        var testProgram = """
            key TestNamespace.TestMultiDimensionalArray {
                testMultiDimensionalArray : int32[][][]
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testRecursiveNestedMapSetArray() {
        var testProgram = """
            key TestNamespace.TestMultiDimensionalArray {
                testNestedMapSetArray : map<set<int32[][]>, set<int32[][]>>
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testSingleLineCommentAtTop() {
        var testProgram = """
            //Comment at top
            key TestNamespace.TestComment {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testSingleLineCommentInMiddle() {
        var testProgram = """
            key TestNamespace.TestComment {
                //Comment in middle
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testSingleLineCommentAtBottom() {
        var testProgram = """
            key TestNamespace.TestComment {
            }
            //Comment at bottom
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testSingleLineCommentWithToken() {
        var testProgram = """
            //type int32 string bool
            key TestNamespace.TestComment {
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
            key TestNamespace.TestComment {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testMultiLineCommentInMiddle() {
        var testProgram = """
            key TestNamespace.TestComment {
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
            key TestNamespace.TestComment {
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
            key TestNamespace.TestComment {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

}