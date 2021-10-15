package com.kdsc.protogen.antlr.visitor.happypath;

import com.kdsc.protogen.BaseCompilerTest;
import org.junit.jupiter.api.Test;

public final class TestTypes extends BaseCompilerTest {

    @Test
    void testSingleType() {
        var testProgram = """
            type TestNamespace.TestType
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testSingleTypeNestedNamespace() {
        var testProgram = """
            type TestNamespace.TestNestedNamespace.TestType
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testMultipleTypes() {
        var testProgram = """
            type TestNamespace.TestType1
            type TestNamespace.TestType2
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicTypeNoFieldsOrBraces() {
        var testProgram = """
            type TestNamespace.TestType
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicTypeNoFieldsWithBraces() {
        var testProgram = """
            type TestNamespace.TestType {}
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicTypeNoFieldsWithSplitBraces() {
        var testProgram = """
            type TestNamespace.TestType {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicTypeOneField() {
        var testProgram = """
            type TestNamespace.TestType {
                testField : int32
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicTypeTwoFields() {
        var testProgram = """
            type TestNamespace.TestType {
                testField1 : int32
                testField2 : int32
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicTypeOneImplementsNoFieldsNoBraces() {
        var testProgram = """
            type TestNamespace.TestType : TestNamespace.OtherType
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicTypeOneImplementsNoFieldsEmptyBraces() {
        var testProgram = """
            type TestNamespace.TestType : TestNamespace.OtherType {}
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicTypeOneImplementsNoFieldsSplitEmptyBraces() {
        var testProgram = """
            type TestNamespace.TestType : TestNamespace.OtherType {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    //This should produce a parse tree but not pass semantic analysis as it doesn't make sense
    @Test
    void testBasicTypeOneImplementsNoFieldsNoBracesGenericParameter() {
        var testProgram = """
            type TestNamespace.TestType : TestNamespace.OtherType<T>
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    //This should produce a parse tree but not pass semantic analysis as it doesn't make sense
    @Test
    void testBasicTypeOneImplementsNoFieldsEmptyBracesGenericParameter() {
        var testProgram = """
            type TestNamespace.TestType : TestNamespace.OtherType<T> {}
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    //This should produce a parse tree but not pass semantic analysis as it doesn't make sense
    @Test
    void testBasicTypeOneImplementsNoFieldsSplitEmptyBracesGenericParameter() {
        var testProgram = """
            type TestNamespace.TestType : TestNamespace.OtherType<T> {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicTypeTwoImplementsNoFieldsNoBraces() {
        var testProgram = """
            type TestNamespace.TestType :
                TestNamespace.OtherType1,
                TestNamespace.OtherType2
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicTypeTwoImplementsNoFieldsEmptyBraces() {
        var testProgram = """
            type TestNamespace.TestType :
                TestNamespace.OtherType1,
                TestNamespace.OtherType2 {}
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicTypeTwoImplementsNoFieldsSplitEmptyBraces() {
        var testProgram = """
            type TestNamespace.TestType :
                TestNamespace.OtherType1,
                TestNamespace.OtherType2 {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    //This should produce a parse tree but not pass semantic analysis as it doesn't make sense
    @Test
    void testBasicTypeTwoImplementsNoFieldsNoBracesGenericParameter() {
        var testProgram = """
            type TestNamespace.TestType :
                TestNamespace.OtherType1<T>,
                TestNamespace.OtherType2<T>
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    //This should produce a parse tree but not pass semantic analysis as it doesn't make sense
    @Test
    void testBasicTypeTwoImplementsNoFieldsEmptyBracesGenericParameter() {
        var testProgram = """
            type TestNamespace.TestType :
                TestNamespace.OtherType1<T>,
                TestNamespace.OtherType2<T> {}
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    //This should produce a parse tree but not pass semantic analysis as it doesn't make sense
    @Test
    void testBasicTypeTwoImplementsNoFieldsSplitEmptyBracesGenericParameter() {
        var testProgram = """
            type TestNamespace.TestType :
                TestNamespace.OtherType1<T>,
                TestNamespace.OtherType2<T> {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicVersionedTypeEmptyVersion() {
        var testProgram = """
            type TestNamespace.TestVersionedType {
                version 1 {}
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicVersionedTypeOneVersion() {
        var testProgram = """
            type TestNamespace.TestVersionedType {
                version 1 {
                    testField : int32
                }
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testBasicVersionedTypeTwoVersions() {
        var testProgram = """
            type TestNamespace.TestVersionedType {
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
    void testGenericType() {
        var testProgram = """
            type TestNamespace.TestGenericType<T> {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericTypeWithImplements() {
        var testProgram = """
            type TestNamespace.TestGenericType<T> : TestNamespace.TestImplements {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericTypeWithImplementsGeneric() {
        var testProgram = """
            type TestNamespace.TestGenericType<T> : TestNamespace.TestImplements<T> {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericTypeWithTwoImplements() {
        var testProgram = """
            type TestNamespace.TestGenericType<T> : TestNamespace.TestImplements1, TestNamespace.TestImplements2 {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericTypeWithTwoImplementsGeneric() {
        var testProgram = """
            type TestNamespace.TestGenericType<T> : TestNamespace.TestImplements1<T>, TestNamespace.TestImplements2<T> {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericTypeWithBounds() {
        var testProgram = """
            type TestNamespace.TestGenericType<T : TestNamespace.TestType> {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericTypeWithNestedBounds() {
        var testProgram = """
            type TestNamespace.TestGenericType<T : set<TestNamespace.TestType>> {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericTypeWithTwoBounds() {
        var testProgram = """
            type TestNamespace.TestGenericType<T : TestNamespace.TestType1 & TestNamespace.TestType2 > {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericVersionedTypeWithSingleGenericParameters() {
        var testProgram = """
            type TestNamespace.TestVersionedGenericType<T> {
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
    void testGenericVersionedTypeWithSingleGenericParametersWithBounds() {
        var testProgram = """
            type TestNamespace.TestVersionedGenericType<T : TestNamespace.TestType> {
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
    void testGenericVersionedTypeWithSingleGenericParametersWithTwoBounds() {
        var testProgram = """
            type TestNamespace.TestVersionedGenericType<T : TestNamespace.TestType1 & TestNamespace.TestType2> {
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
    void testGenericVersionedTypeWithSingleGenericParametersWithNestedBoundsGeneric() {
        var testProgram = """
            type TestNamespace.TestVersionedGenericType<T : set<T>> {
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
    void testGenericVersionedTypeWithSingleGenericParametersWithNestedBoundsType() {
        var testProgram = """
            type TestNamespace.TestVersionedGenericType<T : set<TestNamespace.TestType>> {
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
    void testGenericVersionedTypeWithSingleGenericParametersWithDoubleNestedBoundsType() {
        var testProgram = """
            type TestNamespace.TestVersionedGenericType<T : set<set<TestNamespace.TestType>>> {
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
    void testGenericVersionedTypeWithSingleGenericParametersAndOneEmptyVersion() {
        var testProgram = """
            type TestNamespace.TestVersionedGenericType<T> {
                version 1 {}
                version 2 {
                    testField : int32
                }
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericVersionedTypeWithIndividualGenericParameters() {
        var testProgram = """
            type TestNamespace.TestVersionedGenericType {
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
    void testGenericVersionedTypeWithIndividualGenericParametersAndDifferentOneImplements() {
        var testProgram = """
            type TestNamespace.TestVersionedGenericType {
                version 1 <T> : TestNamespace.OtherType1 {
                    testField : int32
                }
                version 2 <T> : TestNamespace.OtherType2 {
                    testField : int32
                }
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericVersionedTypeWithIndividualGenericParametersAndDifferentOneImplementsWithOneBounds() {
        var testProgram = """
            type TestNamespace.TestVersionedGenericType {
                version 1 <T : TestNamespace.TestType> : TestNamespace.OtherType1 {
                    testField : int32
                }
                version 2 <T : TestNamespace.TestType> : TestNamespace.TestType, TestNamespace.OtherType2 {
                    testField : int32
                }
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericVersionedTypeWithIndividualGenericParametersAndDifferentOneImplementsWithTwoBounds() {
        var testProgram = """
            type TestNamespace.TestVersionedGenericType {
                version 1 <T : TestNamespace.TestType1 & TestNamespace.TestType2> : TestNamespace.OtherType1 {
                    testField : int32
                }
                version 2 <T : TestNamespace.TestType1 & TestNamespace.TestType2> : TestNamespace.TestType, TestNamespace.OtherType2 {
                    testField : int32
                }
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testGenericVersionedTypeWithIndividualGenericParametersAndDifferentTwoImplements() {
        var testProgram = """
            type TestNamespace.TestVersionedGenericType {
                version 1 <T> : TestNamespace.OtherType1, TestNamespace.OtherType2 {
                    testField : int32
                }
                version 2 <T> : TestNamespace.OtherType3<T>, TestNamespace.OtherType4<T> {
                    testField : int32
                }
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testAllFieldTypes() {
        var testProgram = """
            type TestNamespace.TestAllFieldTypes<T> {
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
                testTypeField : TestNamespace.TestType
                testGenericField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testAllFieldTypesOptional() {
        var testProgram = """
            type TestNamespace.TestAllFieldTypes<T> {
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
                testTypeField : optional TestNamespace.TestType
                testGenericField : optional T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testAllFieldTypesOptionalOneLine() {
        var testProgram = """
            type TestNamespace.TestAllFieldTypes<T> { testDoubleField : optional double testFloatField : optional float testInt32Field : optional int32 testInt64Field : optional int64 testBoolField : optional bool testStringField : optional string testByteField : optional bytes testDecimalField : optional decimal testDateField : optional date testDatetimeField : optional datetime testMapField : optional map<int32, int32> testSetField : optional set<int32> testArrayField : optional int32[] testTypeField : optional TestNamespace.TestType testGenericField : optional T }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testRecursiveMaps() {
        var testProgram = """
            type TestNamespace.TestRecursiveMaps{
                testRecursiveMap : map<map<int32, int32>, map<int32, int32>>
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testRecursiveSets() {
        var testProgram = """
            type TestNamespace.TestRecursiveSet {
                testRecursiveSet : set<set<int32>>
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testRecursiveMultiDimensionalArray() {
        var testProgram = """
            type TestNamespace.TestMultiDimensionalArray {
                testMultiDimensionalArray : int32[][][]
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testRecursiveNestedMapSetArray() {
        var testProgram = """
            type TestNamespace.TestMultiDimensionalArray {
                testNestedMapSetArray : map<set<int[][]>, set<int32[][]>>
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testSingleLineCommentAtTop() {
        var testProgram = """
            //Comment at top
            type TestNamespace.TestComment {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testSingleLineCommentInMiddle() {
        var testProgram = """
            type TestNamespace.TestComment {
                //Comment in middle
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testSingleLineCommentAtBottom() {
        var testProgram = """
            type TestNamespace.TestComment {
            }
            //Comment at bottom
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testSingleLineCommentWithToken() {
        var testProgram = """
            //type int32 string bool
            type TestNamespace.TestComment {
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
            type TestNamespace.TestComment {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void testMultiLineCommentInMiddle() {
        var testProgram = """
            type TestNamespace.TestComment {
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
            type TestNamespace.TestComment {
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
            type TestNamespace.TestComment {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

}