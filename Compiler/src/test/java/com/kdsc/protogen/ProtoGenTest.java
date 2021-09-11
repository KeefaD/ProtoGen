package com.kdsc.protogen;

import com.kdsc.protogen.antlr.ProtoGenLexer;
import com.kdsc.protogen.antlr.ProtoGenParser;
import com.kdsc.protogen.antlr.ProtoGenVisitorTest;
import com.kdsc.protogen.antlr.errors.ProtoGenErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class ProtoGenTest {

    @Test
    void emptyFile() {
        var testProgram = """
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

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
                version 1 <T> : TestNamespace.KeithsOtherType1 {
                    testField : int32
                }
                version 2 <T> : TestNamespace.KeithsOtherType2 {
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

    @Test
    void basicEmptyEnum() {
        var testProgram = """
            enum TestNamespace.TestEnum
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicEmptyEnumWithBracesOnOneLine() {
        var testProgram = """
            enum TestNamespace.TestEnum {}
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicEmptyEnumWithSplitBraces() {
        var testProgram = """
            enum TestNamespace.TestEnum {
            }
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicEnumWithOneCase() {
        var testProgram = """
            enum TestNamespace.TestEnum {
                testEnumCase
            }
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void basicEnumWithTwoCases() {
        var testProgram = """
            enum TestNamespace.TestEnum {
                testEnumCase1
                testEnumCase2
            }
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void versionedEnumWithOneCase() {
        var testProgram = """
            enum TestNamespace.TestEnum {
                version 1 {
                    testEnumCase
                }
            }
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void versionedEnumWithTwoCases() {
        var testProgram = """
            enum TestNamespace.TestEnum {
                version 1 {
                    testEnumCase1
                    testEnumCase2
                }
                version 2 {
                    testEnumCase1
                    testEnumCase2
                }
            }
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void versionedEnumWithTwoCasesOneEmpty() {
        var testProgram = """
            enum TestNamespace.TestEnum {
                version 1 {
                    test EnumCase1
                    testEnumCase2
                }
                version 2 {
                }
            }
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    void compileProgramAndCheckNoParserErrors(String testProgram) {

        var inputStream = new ByteArrayInputStream(testProgram.getBytes(StandardCharsets.UTF_8));

        try {
            var antlrInputStream = new ANTLRInputStream(inputStream);
            var lexer = new ProtoGenLexer(antlrInputStream);
            var tokens = new CommonTokenStream(lexer);
            var parser = new ProtoGenParser(tokens);
            parser.removeErrorListeners();
            var errorListener = new ProtoGenErrorListener();
            parser.addErrorListener(errorListener);
            var visitor = new ProtoGenVisitorTest();
            visitor.visit(parser.file());

            if(errorListener.errorOccurred()) {
                for(var message : errorListener.getErrors()) {
                    System.out.println(message);
                }
                assert(false);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
