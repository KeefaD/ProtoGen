package com.kdsc.protogen;

import com.kdsc.protogen.antlr.ProtoGenLexer;
import com.kdsc.protogen.antlr.ProtoGenParser;
import com.kdsc.protogen.antlr.ProtoGenVisitorTest;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class ProtoGenTest {

    @Test
    void basicTypeNoFieldsOrBraces() {
        var testProgram = """
            type KeithsNamespace.KeithsType
        """;
        compileProgram(testProgram);
    }

    @Test
    void basicTypeNoFieldsWithBraces() {
        var testProgram = """
            type KeithsNamespace.KeithsType {}
        """;
        compileProgram(testProgram);
    }

    @Test
    void basicTypeNoFieldsWithSplitBraces() {
        var testProgram = """
            type KeithsNamespace.KeithsType {
            }
        """;
        compileProgram(testProgram);
    }

    @Test
    void basicTypeOneField() {
        var testProgram = """
            type KeithsNamespace.KeithsType {
                testField : int32
            }
        """;
        compileProgram(testProgram);
    }

    @Test
    void basicTypeTwoFields() {
        var testProgram = """
            type KeithsNamespace.KeithsType {
                testField1 : int32
                testField2 : int32
            }
        """;
        compileProgram(testProgram);
    }

    @Test
    void basicTypeOneImplementsNoFieldsNoBraces() {
        var testProgram = """
            type KeithsNamespace.KeithsType : KeithsNamespace.KeithsOtherType
        """;
        compileProgram(testProgram);
    }

    @Test
    void basicTypeOneImplementsNoFieldsEmptyBraces() {
        var testProgram = """
            type KeithsNamespace.KeithsType : KeithsNamespace.KeithsOtherType {}
        """;
        compileProgram(testProgram);
    }

    @Test
    void basicTypeOneImplementsNoFieldsSplitEmptyBraces() {
        var testProgram = """
            type KeithsNamespace.KeithsType : KeithsNamespace.KeithsOtherType {
            }
        """;
        compileProgram(testProgram);
    }


    @Test
    void basicTypeOneImplementsNoFieldsNoBracesGenericParameter() {
        var testProgram = """
            type KeithsNamespace.KeithsType : KeithsNamespace.KeithsOtherType<T>
        """;
        compileProgram(testProgram);
    }

    @Test
    void basicTypeOneImplementsNoFieldsEmptyBracesGenericParameter() {
        var testProgram = """
            type KeithsNamespace.KeithsType : KeithsNamespace.KeithsOtherType<T> {}
        """;
        compileProgram(testProgram);
    }

    @Test
    void basicTypeOneImplementsNoFieldsSplitEmptyBracesGenericParameter() {
        var testProgram = """
            type KeithsNamespace.KeithsType : KeithsNamespace.KeithsOtherType<T> {
            }
        """;
        compileProgram(testProgram);
    }

    @Test
    void basicTypeTwoImplementsNoFieldsNoBraces() {
        var testProgram = """
            type KeithsNamespace.KeithsType :
                KeithsNamespace.KeithsOtherType1,
                KeithsNamespace.KeithsOtherType2
        """;
        compileProgram(testProgram);
    }

    @Test
    void basicTypeTwoImplementsNoFieldsEmptyBraces() {
        var testProgram = """
            type KeithsNamespace.KeithsType :
                KeithsNamespace.KeithsOtherType1,
                KeithsNamespace.KeithsOtherType2 {}
        """;
        compileProgram(testProgram);
    }

    @Test
    void basicTypeTwoImplementsNoFieldsSplitEmptyBraces() {
        var testProgram = """
            type KeithsNamespace.KeithsType :
                KeithsNamespace.KeithsOtherType1,
                KeithsNamespace.KeithsOtherType2 {
            }
        """;
        compileProgram(testProgram);
    }

    @Test
    void basicTypeTwoImplementsNoFieldsNoBracesGenericParameter() {
        var testProgram = """
            type KeithsNamespace.KeithsType :
                KeithsNamespace.KeithsOtherType1<T>,
                KeithsNamespace.KeithsOtherType2<T>
        """;
        compileProgram(testProgram);
    }

    @Test
    void basicTypeTwoImplementsNoFieldsEmptyBracesGenericParameter() {
        var testProgram = """
            type KeithsNamespace.KeithsType :
                KeithsNamespace.KeithsOtherType1<T>,
                KeithsNamespace.KeithsOtherType2<T> {}
        """;
        compileProgram(testProgram);
    }

    @Test
    void basicTypeTwoImplementsNoFieldsSplitEmptyBracesGenericParameter() {
        var testProgram = """
            type KeithsNamespace.KeithsType :
                KeithsNamespace.KeithsOtherType1<T>,
                KeithsNamespace.KeithsOtherType2<T> {
            }
        """;
        compileProgram(testProgram);
    }

    @Test
    void basicVersionedTypeOneVersion() {
        var testProgram = """
            type KeithsNamespace.KeithsVersionedType {
                version 1 {
                    testField : int32
                }
            }
        """;
        compileProgram(testProgram);
    }

    @Test
    void basicVersionedTypeTwoVersions() {
        var testProgram = """
            type KeithsNamespace.KeithsVersionedType {
                version 1 {
                    testField : int32
                }
                version 2 {
                    testField : int32
                }
            }
        """;
        compileProgram(testProgram);
    }

    @Test
    void genericType() {
        var testProgram = """
            type KeithsNamespace.KeithsGenericType<T> {
                testField : T
            }
        """;
        compileProgram(testProgram);
    }

    @Test
    void genericVersionedTypeWithSingleGenericParameters() {
        var testProgram = """
            type KeithsNamespace.KeithsVersionedGenericType<T> {
                version 1 {
                    testField : int32
                }
                version 2 {
                    testField : int32
                }
            }
        """;
        compileProgram(testProgram);
    }

    @Test
    void genericVersionedTypeWithIndividualGenericParameters() {
        var testProgram = """
            type KeithsNamespace.KeithsVersionedGenericType {
                version 1 <T> {
                    testField : int32
                }
                version 2 <T> {
                    testField : int32
                }
            }
        """;
        compileProgram(testProgram);
    }

    @Test
    void genericVersionedTypeWithIndividualGenericParametersAndDifferentOneImplements() {
        var testProgram = """
            type KeithsNamespace.KeithsVersionedGenericType {
                version 1 <T> : KeithsNamespace.KeithsOtherType1 {
                    testField : int32
                }
                version 2 <T> : KeithsNamespace.KeithsOtherType2 {
                    testField : int32
                }
            }
        """;
        compileProgram(testProgram);
    }

    @Test
    void genericVersionedTypeWithIndividualGenericParametersAndDifferentTwoImplements() {
        var testProgram = """
            type KeithsNamespace.KeithsVersionedGenericType {
                version 1 <T> : KeithsNamespace.KeithsOtherType1, KeithsNamespace.KeithsOtherType2 {
                    testField : int32
                }
                version 2 <T> : KeithsNamespace.KeithsOtherType3<T>, KeithsNamespace.KeithsOtherType4<T> {
                    testField : int32
                }
            }
        """;
        compileProgram(testProgram);
    }

    void compileProgram(String testProgram) {

        var inputStream = new ByteArrayInputStream(testProgram.getBytes(StandardCharsets.UTF_8));

        try {
            var antlrInputStream = new ANTLRInputStream(inputStream);
            var lexer = new ProtoGenLexer(antlrInputStream);
            var tokens = new CommonTokenStream(lexer);
            var parser = new ProtoGenParser(tokens);
            var visitor = new ProtoGenVisitorTest();
            visitor.visit(parser.file());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
