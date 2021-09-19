package com.kdsc.protogen.parsetreepostprocessing;

import com.kdsc.protogen.antlr.parser.BaseParserTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUndetectableNodeReplacerWithKeys extends BaseParserTest {

    @Test
    public void testReplaceOneNonNestedKey() {

        var testProgram = """
            key TestNamespace.KeyToReplace {
                testField : int32
            }
            
            key TestNamespace.TestKey {
                testField : TestNamespace.KeyToReplace
            }
        """;
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var expectedToStringOutput = """
        //FileNode
            //ProtoGenKeyNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : KeyToReplace
                //FieldsNode
                    //FieldNode
                        //FieldNameNode
                            FieldName : testField
                        //FieldTypeNode
                            Optional : false
                            //Int32FieldTypeNode
            //ProtoGenKeyNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestKey
                //FieldsNode
                    //FieldNode
                        //FieldNameNode
                            FieldName : testField
                        //FieldTypeNode
                            Optional : false
                            //KeyFieldTypeNode
                                //NamespaceNameGenericParametersWithoutBoundsNode
                                    //NamespaceNameNode
                                        //NamespaceNode
                                            Namespace : TestNamespace
                                        //NameNode
                                            Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, newFileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedMapType() {

        var testProgram = """
            key TestNamespace.KeyToReplace {
                testField : int32
            }
            
            key TestNamespace.TestKey {
                testField : map<TestNamespace.KeyToReplace, TestNamespace.KeyToReplace>
            }
        """;
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var expectedToStringOutput = """
        //FileNode
            //ProtoGenKeyNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : KeyToReplace
                //FieldsNode
                    //FieldNode
                        //FieldNameNode
                            FieldName : testField
                        //FieldTypeNode
                            Optional : false
                            //Int32FieldTypeNode
            //ProtoGenKeyNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestKey
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
                                        //KeyFieldTypeNode
                                            //NamespaceNameGenericParametersWithoutBoundsNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : KeyToReplace
                                //Value
                                    //FieldTypeNode
                                        Optional : false
                                        //KeyFieldTypeNode
                                            //NamespaceNameGenericParametersWithoutBoundsNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, newFileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedSetType() {

        var testProgram = """
            key TestNamespace.KeyToReplace {
                testField : int32
            }
            
            key TestNamespace.TestKey {
                testField : set<TestNamespace.KeyToReplace>
            }
        """;
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var expectedToStringOutput = """
        //FileNode
            //ProtoGenKeyNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : KeyToReplace
                //FieldsNode
                    //FieldNode
                        //FieldNameNode
                            FieldName : testField
                        //FieldTypeNode
                            Optional : false
                            //Int32FieldTypeNode
            //ProtoGenKeyNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestKey
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
                                        //KeyFieldTypeNode
                                            //NamespaceNameGenericParametersWithoutBoundsNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, newFileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedValueOrErrorType() {

        var testProgram = """
            key TestNamespace.KeyToReplace {
                testField : int32
            }
            
            key TestNamespace.TestKey {
                testField : valueorerror<TestNamespace.KeyToReplace>
            }
        """;
        var fileNode = compileTestProgramAndCheckNoParserErrors(testProgram);
        var newFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        var expectedToStringOutput = """
        //FileNode
            //ProtoGenKeyNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : KeyToReplace
                //FieldsNode
                    //FieldNode
                        //FieldNameNode
                            FieldName : testField
                        //FieldTypeNode
                            Optional : false
                            //Int32FieldTypeNode
            //ProtoGenKeyNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestKey
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
                                        //KeyFieldTypeNode
                                            //NamespaceNameGenericParametersWithoutBoundsNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, newFileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedArrayType() {

        var testProgram = """
            type TestNamespace.TypeToReplace {
                testField : int32
            }
            
            type TestNamespace.TestReplaceObjectFieldTypeWithEnumFieldType {
                testField : TestNamespace.TypeToReplace[][]
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
                            Name : TypeToReplace
                //FieldsNode
                    //FieldNode
                        //FieldNameNode
                            FieldName : testField
                        //FieldTypeNode
                            Optional : false
                            //Int32FieldTypeNode
            //ProtoGenTypeNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestReplaceObjectFieldTypeWithEnumFieldType
                //FieldsNode
                    //FieldNode
                        //FieldNameNode
                            FieldName : testField
                        //FieldTypeNode
                            Optional : false
                            //ArrayFieldTypeNode
                                //TypeFieldTypeNode
                                    //NamespaceNameGenericParametersWithoutBoundsNode
                                        //NamespaceNameNode
                                            //NamespaceNode
                                                Namespace : TestNamespace
                                            //NameNode
                                                Name : TypeToReplace
                                Dimensions : 2
        """;
        assertEquals(expectedToStringOutput, newFileNode.toString(), "Unexpected toString output");
    }

}
