package com.kdsc.protogen;

import org.junit.jupiter.api.Test;

public class TypeTests extends BaseParserTest {
    @Test
    void singleType() {
        var testProgram = """
            type TestNamespace.TestType
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void multipleTypes() {
        var testProgram = """
            type TestNamespace.TestType1
            type TestNamespace.TestType2
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicTypeNoFieldsOrBraces() {
        var testProgram = """
            type TestNamespace.TestType
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicTypeNoFieldsWithBraces() {
        var testProgram = """
            type TestNamespace.TestType {}
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicTypeNoFieldsWithSplitBraces() {
        var testProgram = """
            type TestNamespace.TestType {
            }
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicTypeOneField() {
        var testProgram = """
            type TestNamespace.TestType {
                testField : int32
            }
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicTypeTwoFields() {
        var testProgram = """
            type TestNamespace.TestType {
                testField1 : int32
                testField2 : int32
            }
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicTypeOneImplementsNoFieldsNoBraces() {
        var testProgram = """
            type TestNamespace.TestType : TestNamespace.OtherType
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicTypeOneImplementsNoFieldsEmptyBraces() {
        var testProgram = """
            type TestNamespace.TestType : TestNamespace.OtherType {}
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicTypeOneImplementsNoFieldsSplitEmptyBraces() {
        var testProgram = """
            type TestNamespace.TestType : TestNamespace.OtherType {
            }
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicTypeOneImplementsNoFieldsNoBracesGenericParameter() {
        var testProgram = """
            type TestNamespace.TestType : TestNamespace.OtherType<T>
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicTypeOneImplementsNoFieldsEmptyBracesGenericParameter() {
        var testProgram = """
            type TestNamespace.TestType : TestNamespace.OtherType<T> {}
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicTypeOneImplementsNoFieldsSplitEmptyBracesGenericParameter() {
        var testProgram = """
            type TestNamespace.TestType : TestNamespace.OtherType<T> {
            }
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicTypeTwoImplementsNoFieldsNoBraces() {
        var testProgram = """
            type TestNamespace.TestType :
                TestNamespace.OtherType1,
                TestNamespace.OtherType2
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicTypeTwoImplementsNoFieldsEmptyBraces() {
        var testProgram = """
            type TestNamespace.TestType :
                TestNamespace.OtherType1,
                TestNamespace.OtherType2 {}
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicTypeTwoImplementsNoFieldsSplitEmptyBraces() {
        var testProgram = """
            type TestNamespace.TestType :
                TestNamespace.OtherType1,
                TestNamespace.OtherType2 {
            }
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicTypeTwoImplementsNoFieldsNoBracesGenericParameter() {
        var testProgram = """
            type TestNamespace.TestType :
                TestNamespace.OtherType1<T>,
                TestNamespace.OtherType2<T>
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicTypeTwoImplementsNoFieldsEmptyBracesGenericParameter() {
        var testProgram = """
            type TestNamespace.TestType :
                TestNamespace.OtherType1<T>,
                TestNamespace.OtherType2<T> {}
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicTypeTwoImplementsNoFieldsSplitEmptyBracesGenericParameter() {
        var testProgram = """
            type TestNamespace.TestType :
                TestNamespace.OtherType1<T>,
                TestNamespace.OtherType2<T> {
            }
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicVersionedTypeEmptyVersion() {
        var testProgram = """
            type TestNamespace.TestVersionedType {
                version 1 {}
            }
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicVersionedTypeOneVersion() {
        var testProgram = """
            type TestNamespace.TestVersionedType {
                version 1 {
                    testField : int32
                }
            }
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicVersionedTypeTwoVersions() {
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
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void genericType() {
        var testProgram = """
            type TestNamespace.TestGenericType<T> {
                testField : T
            }
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void genericTypeWithBounds() {
        var testProgram = """
            type TestNamespace.TestGenericType<T : TestNamespace.TestType> {
                testField : T
            }
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void genericTypeWithTwoBounds() {
        var testProgram = """
            type TestNamespace.TestGenericType<T : TestNamespace.TestType1 & TestNamespace.TestType2 > {
                testField : T
            }
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void genericVersionedTypeWithSingleGenericParameters() {
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
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void genericVersionedTypeWithSingleGenericParametersWithBounds() {
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
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void genericVersionedTypeWithSingleGenericParametersWithTwoBounds() {
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
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void genericVersionedTypeWithSingleGenericParametersAndOneEmptyVersion() {
        var testProgram = """
            type TestNamespace.TestVersionedGenericType<T> {
                version 1 {}
                version 2 {
                    testField : int32
                }
            }
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void genericVersionedTypeWithIndividualGenericParameters() {
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
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void genericVersionedTypeWithIndividualGenericParametersAndDifferentOneImplements() {
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
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void genericVersionedTypeWithIndividualGenericParametersAndDifferentOneImplementsWithOneBounds() {
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
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void genericVersionedTypeWithIndividualGenericParametersAndDifferentOneImplementsWithTwoBounds() {
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
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void genericVersionedTypeWithIndividualGenericParametersAndDifferentTwoImplements() {
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
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void allFieldTypes() {
        var testProgram = """
            type TestNamespace.TestAllFieldTypes<T> {
                testDoubleField : double
                testFloatField : float
                testInt32Field : int32
                testInt64Field : int64
                testBoolField : bool
                testStringField : string
                testByteField : bytes
                testMapField : map<int32, int32>
                testSetField : set<int32>
                testArrayField : int32[]
                testTypeField : TestNamespace.TestType
                testGenericField : T
            }
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void allFieldTypesOptional() {
        var testProgram = """
            type TestNamespace.TestAllFieldTypes<T> {
                testDoubleField : optional double
                testFloatField : optional float
                testInt32Field : optional int32
                testInt64Field : optional int64
                testBoolField : optional bool
                testStringField : optional string
                testByteField : optional bytes
                testMapField : optional map<int32, int32>
                testSetField : optional set<int32>
                testArrayField : optional int32[]
                testTypeField : optional TestNamespace.TestType
                testGenericField : optional T
            }
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void recursiveMaps() {
        var testProgram = """
            type TestNamespace.TestRecursiveMaps{
                testRecursiveMap : map<map<int32, int32>, map<int32, int32>>
            }
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void recursiveSets() {
        var testProgram = """
            type TestNamespace.TestRecursiveSet {
                testRecursiveSet : set<set<int32>>
            }
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void recursiveMultiDimensionalArray() {
        var testProgram = """
            type TestNamespace.TestMultiDimensionalArray {
                testMultiDimensionalArray : int32[][][]
            }
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void recursiveNestedMapSetArray() {
        var testProgram = """
            type TestNamespace.TestMultiDimensionalArray {
                testNestedMapSetArray : map<set<int[][]>, set<int32[][]>>
            }
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void singleLineCommentAtTop() {
        var testProgram = """
            //Comment at top
            type TestNamespace.TestComment {
            }
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void singleLineCommentInMiddle() {
        var testProgram = """
            type TestNamespace.TestComment {
                //Comment in middle
            }
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void singleLineCommentAtBottom() {
        var testProgram = """
            type TestNamespace.TestComment {
            }
            //Comment at bottom
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void singleLineCommentWithToken() {
        var testProgram = """
            //type int32 string bool
            type TestNamespace.TestComment {
            }
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void multiLineCommentAtTop() {
        var testProgram = """
            /*
            Comment at top
            */
            type TestNamespace.TestComment {
            }
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void multiLineCommentInMiddle() {
        var testProgram = """
            type TestNamespace.TestComment {
                /*
                Comment in middle
                */
            }
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void multiLineCommentAtBottom() {
        var testProgram = """
            type TestNamespace.TestComment {
            }
            /*
            Comment at bottom
            */
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void multiLineCommentWithToken() {
        var testProgram = """
            /*
            type
            int32
            string bool
            */
            type TestNamespace.TestComment {
            }
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }
}
