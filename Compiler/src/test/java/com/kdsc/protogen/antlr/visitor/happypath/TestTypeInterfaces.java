package com.kdsc.protogen.antlr.visitor.happypath;

import com.kdsc.protogen.BaseCompilerTest;
import org.junit.jupiter.api.Test;

public class TestTypeInterfaces extends BaseCompilerTest {

    @Test
    void singleTypeInterface() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void singleTypeInterfaceNestedNamespace() {
        var testProgram = """
            type interface TestNamespace.TestNestedNamespace.TestTypeInterface
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void multipleTypeInterfaces() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface1
            type interface TestNamespace.TestTypeInterface2
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicTypeInterfaceNoFieldsOrBraces() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicTypeInterfaceNoFieldsWithBraces() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface {}
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicTypeInterfaceNoFieldsWithSplitBraces() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicTypeInterfaceOneField() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface {
                testField : int32
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicTypeInterfaceTwoFields() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface {
                testField1 : int32
                testField2 : int32
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicTypeInterfaceOneImplementsNoFieldsNoBraces() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface : TestNamespace.OtherTypeInterface
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicTypeInterfaceOneImplementsNoFieldsEmptyBraces() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface : TestNamespace.OtherTypeInterface {}
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicTypeInterfaceOneImplementsNoFieldsSplitEmptyBraces() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface : TestNamespace.OtherTypeInterface {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicTypeInterfaceOneImplementsNoFieldsNoBracesGenericParameter() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface : TestNamespace.OtherTypeInterface<T>
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicTypeInterfaceOneImplementsNoFieldsEmptyBracesGenericParameter() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface : TestNamespace.OtherTypeInterface<T> {}
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicTypeInterfaceOneImplementsNoFieldsSplitEmptyBracesGenericParameter() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface : TestNamespace.OtherTypeInterface<T> {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicTypeInterfaceTwoImplementsNoFieldsNoBraces() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface :
                TestNamespace.OtherTypeInterface1,
                TestNamespace.OtherTypeInterface2
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicTypeInterfaceTwoImplementsNoFieldsEmptyBraces() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface :
                TestNamespace.OtherTypeInterface1,
                TestNamespace.OtherTypeInterface2 {}
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicTypeInterfaceTwoImplementsNoFieldsSplitEmptyBraces() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface :
                TestNamespace.OtherTypeInterface1,
                TestNamespace.OtherTypeInterface2 {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicTypeInterfaceTwoImplementsNoFieldsNoBracesGenericParameter() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface :
                TestNamespace.OtherTypeInterface1<T>,
                TestNamespace.OtherTypeInterface2<T>
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicTypeInterfaceTwoImplementsNoFieldsEmptyBracesGenericParameter() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface :
                TestNamespace.OtherTypeInterface1<T>,
                TestNamespace.OtherTypeInterface2<T> {}
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicTypeInterfaceTwoImplementsNoFieldsSplitEmptyBracesGenericParameter() {
        var testProgram = """
            type interface TestNamespace.TestTypeInterface :
                TestNamespace.OtherTypeInterface1<T>,
                TestNamespace.OtherTypeInterface2<T> {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicVersionedTypeInterfaceEmptyVersion() {
        var testProgram = """
            type interface TestNamespace.TestVersionedTypeInterface {
                version 1 {}
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void basicVersionedTypeInterfaceOneVersion() {
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
    void basicVersionedTypeInterfaceTwoVersions() {
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
    void genericTypeInterface() {
        var testProgram = """
            type interface TestNamespace.TestGenericTypeInterface<T> {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void genericTypeInterfaceWithImplements() {
        var testProgram = """
            type interface TestNamespace.TestGenericTypeInterface<T> : TestNamespace.TestImplementsInterface {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void genericTypeInterfaceWithImplementsGeneric() {
        var testProgram = """
            type interface TestNamespace.TestGenericTypeInterface<T> : TestNamespace.TestImplementsInterface<T> {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void genericTypeInterfaceWithTwoImplements() {
        var testProgram = """
            type interface TestNamespace.TestGenericTypeInterface<T> : TestNamespace.TestImplementsInterface1, TestNamespace.TestImplementsInterface2 {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void genericTypeInterfaceWithTwoImplementsGeneric() {
        var testProgram = """
            type interface TestNamespace.TestGenericTypeInterface<T> : TestNamespace.TestImplementsInterface1<T>, TestNamespace.TestImplementsInterface2<T> {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void genericTypeInterfaceWithBounds() {
        var testProgram = """
            type interface TestNamespace.TestGenericTypeInterface<T : TestNamespace.TestTypeInterface> {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void genericTypeInterfaceWithTwoBounds() {
        var testProgram = """
            type interface TestNamespace.TestGenericTypeInterface<T : TestNamespace.TestTypeInterface1 & TestNamespace.TestTypeInterface2 > {
                testField : T
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void genericVersionedTypeInterfaceWithSingleGenericParameters() {
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
    void genericVersionedTypeInterfaceWithSingleGenericParametersWithBounds() {
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
    void genericVersionedTypeInterfaceWithSingleGenericParametersWithTwoBounds() {
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
    void genericVersionedTypeInterfaceWithSingleGenericParametersAndOneEmptyVersion() {
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
    void genericVersionedTypeInterfaceWithIndividualGenericParameters() {
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
    void genericVersionedTypeInterfaceWithIndividualGenericParametersAndDifferentOneImplements() {
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
    void genericVersionedTypeInterfaceWithIndividualGenericParametersAndDifferentOneImplementsWithOneBounds() {
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
    void genericVersionedTypeInterfaceWithIndividualGenericParametersAndDifferentOneImplementsWithTwoBounds() {
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
    void genericVersionedTypeInterfaceWithIndividualGenericParametersAndDifferentTwoImplements() {
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
    void allFieldTypeInterfaces() {
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
    void allFieldTypeInterfacesOptional() {
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
    void allFieldTypeInterfacesOptionalOneLine() {
        var testProgram = """
            type interface TestNamespace.TestAllFieldTypeInterfaces<T> { testDoubleField : optional double testFloatField : optional float testInt32Field : optional int32 testInt64Field : optional int64 testBoolField : optional bool testStringField : optional string testByteField : optional bytes testDecimalField : optional decimal testDateField : optional date testDatetimeField : optional datetime testMapField : optional map<int32, int32> testSetField : optional set<int32> testArrayField : optional int32[] testTypeInterfaceField : optional TestNamespace.TestTypeInterface testGenericField : optional T }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void recursiveMaps() {
        var testProgram = """
            type interface TestNamespace.TestRecursiveMaps {
                testRecursiveMap : map<map<int32, int32>, map<int32, int32>>
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void recursiveSets() {
        var testProgram = """
            type interface TestNamespace.TestRecursiveSet {
                testRecursiveSet : set<set<int32>>
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void recursiveMultiDimensionalArray() {
        var testProgram = """
            type interface TestNamespace.TestMultiDimensionalArray {
                testMultiDimensionalArray : int32[][][]
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void recursiveNestedMapSetArray() {
        var testProgram = """
            type interface TestNamespace.TestMultiDimensionalArray {
                testNestedMapSetArray : map<set<int32[][]>, set<int32[][]>>
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void singleLineCommentAtTop() {
        var testProgram = """
            //Comment at top
            type interface TestNamespace.TestComment {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void singleLineCommentInMiddle() {
        var testProgram = """
            type interface TestNamespace.TestComment {
                //Comment in middle
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void singleLineCommentAtBottom() {
        var testProgram = """
            type interface TestNamespace.TestComment {
            }
            //Comment at bottom
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void singleLineCommentWithToken() {
        var testProgram = """
            //type interface int32 string bool
            type interface TestNamespace.TestComment {
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
            type interface TestNamespace.TestComment {
            }
        """;
        runCompilerToParserCheckNoErrors(testProgram);
    }

    @Test
    void multiLineCommentInMiddle() {
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
    void multiLineCommentAtBottom() {
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
    void multiLineCommentWithToken() {
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
