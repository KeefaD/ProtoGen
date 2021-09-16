package com.kdsc.protogen.antlr.parser.happypath;

import com.kdsc.protogen.antlr.parser.BaseParserTest;
import org.junit.jupiter.api.Test;

public class TestKeyInterfaces extends BaseParserTest {

    @Test
    void singleKeyInterface() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void singleKeyInterfaceNestedNamespace() {
        var testProgram = """
            key interface TestNamespace.TestNestedNamespace.TestKeyInterface
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void multipleKeyInterfaces() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface1
            key interface TestNamespace.TestKeyInterface2
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicKeyInterfaceNoFieldsOrBraces() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicKeyInterfaceNoFieldsWithBraces() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface {}
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicKeyInterfaceNoFieldsWithSplitBraces() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface {
            }
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicKeyInterfaceOneField() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface {
                testField : int32
            }
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicKeyInterfaceTwoFields() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface {
                testField1 : int32
                testField2 : int32
            }
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicKeyInterfaceOneImplementsNoFieldsNoBraces() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface : TestNamespace.OtherKeyInterface
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicKeyInterfaceOneImplementsNoFieldsEmptyBraces() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface : TestNamespace.OtherKeyInterface {}
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicKeyInterfaceOneImplementsNoFieldsSplitEmptyBraces() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface : TestNamespace.OtherKeyInterface {
            }
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicKeyInterfaceOneImplementsNoFieldsNoBracesGenericParameter() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface : TestNamespace.OtherKeyInterface<T>
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicKeyInterfaceOneImplementsNoFieldsEmptyBracesGenericParameter() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface : TestNamespace.OtherKeyInterface<T> {}
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicKeyInterfaceOneImplementsNoFieldsSplitEmptyBracesGenericParameter() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface : TestNamespace.OtherKeyInterface<T> {
            }
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicKeyInterfaceTwoImplementsNoFieldsNoBraces() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface :
                TestNamespace.OtherKeyInterface1,
                TestNamespace.OtherKeyInterface2
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicKeyInterfaceTwoImplementsNoFieldsEmptyBraces() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface :
                TestNamespace.OtherKeyInterface1,
                TestNamespace.OtherKeyInterface2 {}
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicKeyInterfaceTwoImplementsNoFieldsSplitEmptyBraces() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface :
                TestNamespace.OtherKeyInterface1,
                TestNamespace.OtherKeyInterface2 {
            }
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicKeyInterfaceTwoImplementsNoFieldsNoBracesGenericParameter() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface :
                TestNamespace.OtherKeyInterface1<T>,
                TestNamespace.OtherKeyInterface2<T>
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicKeyInterfaceTwoImplementsNoFieldsEmptyBracesGenericParameter() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface :
                TestNamespace.OtherKeyInterface1<T>,
                TestNamespace.OtherKeyInterface2<T> {}
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicKeyInterfaceTwoImplementsNoFieldsSplitEmptyBracesGenericParameter() {
        var testProgram = """
            key interface TestNamespace.TestKeyInterface :
                TestNamespace.OtherKeyInterface1<T>,
                TestNamespace.OtherKeyInterface2<T> {
            }
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicVersionedKeyInterfaceEmptyVersion() {
        var testProgram = """
            key interface TestNamespace.TestVersionedKeyInterface {
                version 1 {}
            }
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicVersionedKeyInterfaceOneVersion() {
        var testProgram = """
            key interface TestNamespace.TestVersionedKeyInterface {
                version 1 {
                    testField : int32
                }
            }
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicVersionedKeyInterfaceTwoVersions() {
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
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void genericKeyInterface() {
        var testProgram = """
            key interface TestNamespace.TestGenericKeyInterface<T> {
                testField : T
            }
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void genericKeyInterfaceWithImplements() {
        var testProgram = """
            key interface TestNamespace.TestGenericKeyInterface<T> : TestNamespace.TestImplementsInterface {
                testField : T
            }
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void genericKeyInterfaceWithImplementsGeneric() {
        var testProgram = """
            key interface TestNamespace.TestGenericKeyInterface<T> : TestNamespace.TestImplementsInterface<T> {
                testField : T
            }
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void genericKeyInterfaceWithTwoImplements() {
        var testProgram = """
            key interface TestNamespace.TestGenericKeyInterface<T> : TestNamespace.TestImplementsInterface1, TestNamespace.TestImplementsInterface2 {
                testField : T
            }
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void genericKeyInterfaceWithTwoImplementsGeneric() {
        var testProgram = """
            key interface TestNamespace.TestGenericKeyInterface<T> : TestNamespace.TestImplementsInterface1<T>, TestNamespace.TestImplementsInterface2<T> {
                testField : T
            }
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void genericKeyInterfaceWithBounds() {
        var testProgram = """
            key interface TestNamespace.TestGenericKeyInterface<T : TestNamespace.TestKeyInterface> {
                testField : T
            }
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void genericKeyInterfaceWithTwoBounds() {
        var testProgram = """
            key interface TestNamespace.TestGenericKeyInterface<T : TestNamespace.TestKeyInterface1 & TestNamespace.TestKeyInterface2 > {
                testField : T
            }
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void genericVersionedKeyInterfaceWithSingleGenericParameters() {
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
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void genericVersionedKeyInterfaceWithSingleGenericParametersWithBounds() {
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
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void genericVersionedKeyInterfaceWithSingleGenericParametersWithTwoBounds() {
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
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void genericVersionedKeyInterfaceWithSingleGenericParametersAndOneEmptyVersion() {
        var testProgram = """
            key interface TestNamespace.TestVersionedGenericKeyInterface<T> {
                version 1 {}
                version 2 {
                    testField : int32
                }
            }
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void genericVersionedKeyInterfaceWithIndividualGenericParameters() {
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
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void genericVersionedKeyInterfaceWithIndividualGenericParametersAndDifferentOneImplements() {
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
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void genericVersionedKeyInterfaceWithIndividualGenericParametersAndDifferentOneImplementsWithOneBounds() {
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
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void genericVersionedKeyInterfaceWithIndividualGenericParametersAndDifferentOneImplementsWithTwoBounds() {
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
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void genericVersionedKeyInterfaceWithIndividualGenericParametersAndDifferentTwoImplements() {
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
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void allFieldKeyInterfaces() {
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
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void allFieldKeyInterfacesOptional() {
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
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void allFieldKeyInterfacesOptionalOneLine() {
        var testProgram = """
            key interface TestNamespace.TestAllFieldTypes<T> { testDoubleField : optional double testFloatField : optional float testInt32Field : optional int32 testInt64Field : optional int64 testBoolField : optional bool testStringField : optional string testByteField : optional bytes testDecimalField : optional decimal testDateField : optional date testDatetimeField : optional datetime testMapField : optional map<int32, int32> testSetField : optional set<int32> testArrayField : optional int32[] testTypeField : optional TestNamespace.TestType testGenericField : optional T }
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void recursiveMaps() {
        var testProgram = """
            key interface TestNamespace.TestRecursiveMaps{
                testRecursiveMap : map<map<int32, int32>, map<int32, int32>>
            }
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void recursiveSets() {
        var testProgram = """
            key interface TestNamespace.TestRecursiveSet {
                testRecursiveSet : set<set<int32>>
            }
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void recursiveMultiDimensionalArray() {
        var testProgram = """
            key interface TestNamespace.TestMultiDimensionalArray {
                testMultiDimensionalArray : int32[][][]
            }
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void recursiveNestedMapSetArray() {
        var testProgram = """
            key interface TestNamespace.TestMultiDimensionalArray {
                testNestedMapSetArray : map<set<int[][]>, set<int32[][]>>
            }
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void singleLineCommentAtTop() {
        var testProgram = """
            //Comment at top
            key interface TestNamespace.TestComment {
            }
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void singleLineCommentInMiddle() {
        var testProgram = """
            key interface TestNamespace.TestComment {
                //Comment in middle
            }
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void singleLineCommentAtBottom() {
        var testProgram = """
            key interface TestNamespace.TestComment {
            }
            //Comment at bottom
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void singleLineCommentWithToken() {
        var testProgram = """
            //type int32 string bool
            key interface TestNamespace.TestComment {
            }
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void multiLineCommentAtTop() {
        var testProgram = """
            /*
            Comment at top
            */
            key interface TestNamespace.TestComment {
            }
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void multiLineCommentInMiddle() {
        var testProgram = """
            key interface TestNamespace.TestComment {
                /*
                Comment in middle
                */
            }
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void multiLineCommentAtBottom() {
        var testProgram = """
            key interface TestNamespace.TestComment {
            }
            /*
            Comment at bottom
            */
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void multiLineCommentWithToken() {
        var testProgram = """
            /*
            type
            int32
            string bool
            */
            key interface TestNamespace.TestComment {
            }
        """;
        compileTestProgramAndCheckNoParserErrors(testProgram);
    }
}