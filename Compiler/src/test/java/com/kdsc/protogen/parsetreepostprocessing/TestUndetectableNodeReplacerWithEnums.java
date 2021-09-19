package com.kdsc.protogen.parsetreepostprocessing;

import com.kdsc.protogen.antlr.parser.BaseParserTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUndetectableNodeReplacerWithEnums extends BaseParserTest {

    @Test
    public void testReplaceOneNonNestedType() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type TestNamespace.TestType {
                testField : TestNamespace.EnumToReplace
            }
        """;
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var expectedToStringOutput = """
        //FileNode
            //ProtoGenTypeNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestType
                //FieldsNode
                    //FieldNode
                        //FieldNameNode
                            FieldName : testField
                        //FieldTypeNode
                            Optional : false
                            //EnumFieldTypeNode
                                //NamespaceNameGenericParametersWithoutBoundsNode
                                    //NamespaceNameNode
                                        //NamespaceNode
                                            Namespace : TestNamespace
                                        //NameNode
                                            Name : EnumToReplace
            //ProtoGenEnumNode
                //NamespaceNameNode
                    //NamespaceNode
                        Namespace : TestNamespace
                    //NameNode
                        Name : EnumToReplace
                //EnumCasesNode
                    //EnumNameNode
                        EnumName : testCase1
        """;
        assertEquals(expectedToStringOutput, newFileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedMapType() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type TestNamespace.Type {
                testField : map<TestNamespace.EnumToReplace, TestNamespace.EnumToReplace>
            }
        """;
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var expectedToStringOutput = """
        //FileNode
            //ProtoGenTypeNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : Type
                //FieldsNode
                    //FieldNode
                        //FieldNameNode
                            FieldName : testField
                        //FieldTypeNode
                            Optional : false
                            //MapFieldTypeNode
                                //Key
                                    //FieldTypeNode
                                        Optional : false
                                        //EnumFieldTypeNode
                                            //NamespaceNameGenericParametersWithoutBoundsNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : EnumToReplace
                                //Value
                                    //FieldTypeNode
                                        Optional : false
                                        //EnumFieldTypeNode
                                            //NamespaceNameGenericParametersWithoutBoundsNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : EnumToReplace
            //ProtoGenEnumNode
                //NamespaceNameNode
                    //NamespaceNode
                        Namespace : TestNamespace
                    //NameNode
                        Name : EnumToReplace
                //EnumCasesNode
                    //EnumNameNode
                        EnumName : testCase1
        """;
        assertEquals(expectedToStringOutput, newFileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedSetType() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type TestNamespace.TestType {
                testField : set<TestNamespace.EnumToReplace>
            }
        """;
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var expectedToStringOutput = """
        //FileNode
            //ProtoGenTypeNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestType
                //FieldsNode
                    //FieldNode
                        //FieldNameNode
                            FieldName : testField
                        //FieldTypeNode
                            Optional : false
                            //SetFieldTypeNode
                                //Entry
                                    //FieldTypeNode
                                        Optional : false
                                        //EnumFieldTypeNode
                                            //NamespaceNameGenericParametersWithoutBoundsNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : EnumToReplace
            //ProtoGenEnumNode
                //NamespaceNameNode
                    //NamespaceNode
                        Namespace : TestNamespace
                    //NameNode
                        Name : EnumToReplace
                //EnumCasesNode
                    //EnumNameNode
                        EnumName : testCase1
        """;
        assertEquals(expectedToStringOutput, newFileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedValueOrErrorType() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type TestNamespace.TestType {
                testField : valueorerror<TestNamespace.EnumToReplace>
            }
        """;
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var expectedToStringOutput = """
        //FileNode
            //ProtoGenTypeNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestType
                //FieldsNode
                    //FieldNode
                        //FieldNameNode
                            FieldName : testField
                        //FieldTypeNode
                            Optional : false
                            //ValueOrErrorFieldTypeNode
                                //Entry
                                    //FieldTypeNode
                                        Optional : false
                                        //EnumFieldTypeNode
                                            //NamespaceNameGenericParametersWithoutBoundsNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : EnumToReplace
            //ProtoGenEnumNode
                //NamespaceNameNode
                    //NamespaceNode
                        Namespace : TestNamespace
                    //NameNode
                        Name : EnumToReplace
                //EnumCasesNode
                    //EnumNameNode
                        EnumName : testCase1
        """;
        assertEquals(expectedToStringOutput, newFileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedArrayType() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type TestNamespace.TestType {
                testField : TestNamespace.EnumToReplace[][]
            }
        """;
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var expectedToStringOutput = """
        //FileNode
            //ProtoGenTypeNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestType
                //FieldsNode
                    //FieldNode
                        //FieldNameNode
                            FieldName : testField
                        //FieldTypeNode
                            Optional : false
                            //ArrayFieldTypeNode
                                //EnumFieldTypeNode
                                    //NamespaceNameGenericParametersWithoutBoundsNode
                                        //NamespaceNameNode
                                            //NamespaceNode
                                                Namespace : TestNamespace
                                            //NameNode
                                                Name : EnumToReplace
                                Dimensions : 2
            //ProtoGenEnumNode
                //NamespaceNameNode
                    //NamespaceNode
                        Namespace : TestNamespace
                    //NameNode
                        Name : EnumToReplace
                //EnumCasesNode
                    //EnumNameNode
                        EnumName : testCase1
        """;
        assertEquals(expectedToStringOutput, newFileNode.toString(), "Unexpected toString output");
    }

}