package com.kdsc.protogen.antlr.parser;

import org.junit.jupiter.api.Test;

public class GeneralTests  extends BaseParserTest {

    @Test
    void emptyFile() {
        var testProgram = """
        """;
        compileProgramAndCheckNoParserErrors(testProgram);
    }

    @Test
    void typeKeyAndEnum() {
        var testProgram = """
            
            //TYPE
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
                testArrayField : optional int32[]
                testTypeField : optional TestNamespace.TestType
                testGenericField : optional T
            }
            
            //KEY
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
                testArrayField : optional int32[]
                testKeyField : optional TestNamespace.TestKey
                testGenericField : optional T
            }
            
            //ENUM
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
}
