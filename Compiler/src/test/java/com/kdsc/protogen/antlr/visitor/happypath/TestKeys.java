package com.kdsc.protogen.antlr.visitor.happypath;

import com.kdsc.protogen.BaseCompilerTest;
import org.junit.jupiter.api.Test;

public class TestKeys extends BaseCompilerTest {

    @Test
    void singleKey() {
        var testProgram = """
            key TestNamespace.TestKey
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void singleKeyNestedNamespace() {
        var testProgram = """
            key TestNamespace.TestNestedNamespace.TestKey
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void multipleKeys() {
        var testProgram = """
            key TestNamespace.TestKey1
            key TestNamespace.TestKey2
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicKeyNoFieldsOrBraces() {
        var testProgram = """
            key TestNamespace.TestKey
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicKeyNoFieldsWithBraces() {
        var testProgram = """
            key TestNamespace.TestKey {}
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicKeyNoFieldsWithSplitBraces() {
        var testProgram = """
            key TestNamespace.TestKey {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicKeyOneField() {
        var testProgram = """
            key TestNamespace.TestKey {
                testField : int32
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicKeyTwoFields() {
        var testProgram = """
            key TestNamespace.TestKey {
                testField1 : int32
                testField2 : int32
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicKeyOneImplementsNoFieldsNoBraces() {
        var testProgram = """
            key TestNamespace.TestKey : TestNamespace.OtherKey
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicKeyOneImplementsNoFieldsEmptyBraces() {
        var testProgram = """
            key TestNamespace.TestKey : TestNamespace.OtherKey {}
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicKeyOneImplementsNoFieldsSplitEmptyBraces() {
        var testProgram = """
            key TestNamespace.TestKey : TestNamespace.OtherKey {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    //This should produce a parse tree but not pass semantic analysis as it doesn't make sense
    @Test
    void basicKeyOneImplementsNoFieldsNoBracesGenericParameter() {
        var testProgram = """
            key TestNamespace.TestKey : TestNamespace.OtherKey<T>
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    //This should produce a parse tree but not pass semantic analysis as it doesn't make sense
    @Test
    void basicKeyOneImplementsNoFieldsEmptyBracesGenericParameter() {
        var testProgram = """
            key TestNamespace.TestKey : TestNamespace.OtherKey<T> {}
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    //This should produce a parse tree but not pass semantic analysis as it doesn't make sense
    @Test
    void basicKeyOneImplementsNoFieldsSplitEmptyBracesGenericParameter() {
        var testProgram = """
            key TestNamespace.TestKey : TestNamespace.OtherKey<T> {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicKeyTwoImplementsNoFieldsNoBraces() {
        var testProgram = """
            key TestNamespace.TestKey :
                TestNamespace.OtherKey1,
                TestNamespace.OtherKey2
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicKeyTwoImplementsNoFieldsEmptyBraces() {
        var testProgram = """
            key TestNamespace.TestKey :
                TestNamespace.OtherKey1,
                TestNamespace.OtherKey2 {}
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicKeyTwoImplementsNoFieldsSplitEmptyBraces() {
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
    void basicKeyTwoImplementsNoFieldsNoBracesGenericParameter() {
        var testProgram = """
            key TestNamespace.TestKey :
                TestNamespace.OtherKey1<T>,
                TestNamespace.OtherKey2<T>
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    //This should produce a parse tree but not pass semantic analysis as it doesn't make sense
    @Test
    void basicKeyTwoImplementsNoFieldsEmptyBracesGenericParameter() {
        var testProgram = """
            key TestNamespace.TestKey :
                TestNamespace.OtherKey1<T>,
                TestNamespace.OtherKey2<T> {}
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    //This should produce a parse tree but not pass semantic analysis as it doesn't make sense
    @Test
    void basicKeyTwoImplementsNoFieldsSplitEmptyBracesGenericParameter() {
        var testProgram = """
            key TestNamespace.TestKey :
                TestNamespace.OtherKey1<T>,
                TestNamespace.OtherKey2<T> {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicVersionedKeyEmptyVersion() {
        var testProgram = """
            key TestNamespace.TestVersionedKey {
                version 1 {}
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicVersionedKeyOneVersion() {
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
    void basicVersionedKeyTwoVersions() {
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
    void genericKey() {
        var testProgram = """
            key TestNamespace.TestGenericKey<T> {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void genericKeyWithImplements() {
        var testProgram = """
            key TestNamespace.TestGenericKey<T> : TestNamespace.TestImplements {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void genericKeyWithImplementsGeneric() {
        var testProgram = """
            key TestNamespace.TestGenericKey<T> : TestNamespace.TestImplements<T> {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void genericKeyWithTwoImplements() {
        var testProgram = """
            key TestNamespace.TestGenericKey<T> : TestNamespace.TestImplements1, TestNamespace.TestImplements2 {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void genericKeyWithTwoImplementsGeneric() {
        var testProgram = """
            key TestNamespace.TestGenericKey<T> : TestNamespace.TestImplements1<T>, TestNamespace.TestImplements2<T> {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void genericKeyWithBounds() {
        var testProgram = """
            key TestNamespace.TestGenericKey<T : TestNamespace.TestKey> {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void genericKeyWithTwoBounds() {
        var testProgram = """
            key TestNamespace.TestGenericKey<T : TestNamespace.TestKey1 & TestNamespace.TestKey2 > {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void genericVersionedKeyWithSingleGenericParameters() {
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
    void genericVersionedKeyWithSingleGenericParametersWithBounds() {
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
    void genericVersionedKeyWithSingleGenericParametersWithTwoBounds() {
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
    void genericVersionedKeyWithSingleGenericParametersAndOneEmptyVersion() {
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
    void genericVersionedKeyWithIndividualGenericParameters() {
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
    void genericVersionedKeyWithIndividualGenericParametersAndDifferentOneImplements() {
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
    void genericVersionedKeyWithIndividualGenericParametersAndDifferentOneImplementsWithOneBounds() {
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
    void genericVersionedKeyWithIndividualGenericParametersAndDifferentOneImplementsWithTwoBounds() {
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
    void genericVersionedKeyWithIndividualGenericParametersAndDifferentTwoImplements() {
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
    void allFieldKeys() {
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
    void allFieldKeysOptional() {
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
    void allFieldKeysOptionalOneLine() {
        var testProgram = """
            key TestNamespace.TestAllFieldTypes<T> { testDoubleField : optional double testFloatField : optional float testInt32Field : optional int32 testInt64Field : optional int64 testBoolField : optional bool testStringField : optional string testByteField : optional bytes testDecimalField : optional decimal testDateField : optional date testDatetimeField : optional datetime testMapField : optional map<int32, int32> testSetField : optional set<int32> testArrayField : optional int32[] testTypeField : optional TestNamespace.TestType testGenericField : optional T }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void recursiveMaps() {
        var testProgram = """
            key TestNamespace.TestRecursiveMaps {
                testRecursiveMap : map<map<int32, int32>, map<int32, int32>>
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void recursiveSets() {
        var testProgram = """
            key TestNamespace.TestRecursiveSet {
                testRecursiveSet : set<set<int32>>
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void recursiveMultiDimensionalArray() {
        var testProgram = """
            key TestNamespace.TestMultiDimensionalArray {
                testMultiDimensionalArray : int32[][][]
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void recursiveNestedMapSetArray() {
        var testProgram = """
            key TestNamespace.TestMultiDimensionalArray {
                testNestedMapSetArray : map<set<int32[][]>, set<int32[][]>>
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void singleLineCommentAtTop() {
        var testProgram = """
            //Comment at top
            key TestNamespace.TestComment {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void singleLineCommentInMiddle() {
        var testProgram = """
            key TestNamespace.TestComment {
                //Comment in middle
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void singleLineCommentAtBottom() {
        var testProgram = """
            key TestNamespace.TestComment {
            }
            //Comment at bottom
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void singleLineCommentWithToken() {
        var testProgram = """
            //type int32 string bool
            key TestNamespace.TestComment {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void multiLineCommentAtTop() {
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
    void multiLineCommentInMiddle() {
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
    void multiLineCommentAtBottom() {
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
    void multiLineCommentWithToken() {
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