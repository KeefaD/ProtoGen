package com.kdsc.protogen.parsetreepostprocessing;

import com.kdsc.protogen.BaseCompilerTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUndetectableNodeReplacerWithKeys extends BaseCompilerTest {

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
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //Super -> //BaseParseTreeNode
                SourceFileName : FakeSourceFileName.pg
                Line : 1
                CharPosition : 4
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 22
                            Name : KeyToReplace
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 2
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 2
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 8
                            FieldName : testField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 20
                            Optional : false
                            //Int32FieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 2
                                        CharPosition : 20
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 22
                            Name : TestKey
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 6
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 6
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 6
                                CharPosition : 8
                            FieldName : testField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 6
                                CharPosition : 20
                            Optional : false
                            //KeyFieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 6
                                        CharPosition : 20
                                //NamespaceNameGenericParametersNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 6
                                        CharPosition : 20
                                    //NamespaceNameNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 6
                                            CharPosition : 20
                                        //NamespaceNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 6
                                                CharPosition : 20
                                            Namespace : TestNamespace
                                        //NameNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 6
                                                CharPosition : 34
                                            Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
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
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //Super -> //BaseParseTreeNode
                SourceFileName : FakeSourceFileName.pg
                Line : 1
                CharPosition : 4
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 22
                            Name : KeyToReplace
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 2
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 2
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 8
                            FieldName : testField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 20
                            Optional : false
                            //Int32FieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 2
                                        CharPosition : 20
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 22
                            Name : TestKey
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 6
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 6
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 6
                                CharPosition : 8
                            FieldName : testField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 6
                                CharPosition : 20
                            Optional : false
                            //MapFieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 6
                                        CharPosition : 20
                                Key
                                    //FieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 6
                                            CharPosition : 24
                                        Optional : false
                                        //KeyFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 6
                                                    CharPosition : 24
                                            //NamespaceNameGenericParametersNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 6
                                                    CharPosition : 24
                                                //NamespaceNameNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 6
                                                        CharPosition : 24
                                                    //NamespaceNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 6
                                                            CharPosition : 24
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 6
                                                            CharPosition : 38
                                                        Name : KeyToReplace
                                Value
                                    //FieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 6
                                            CharPosition : 52
                                        Optional : false
                                        //KeyFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 6
                                                    CharPosition : 52
                                            //NamespaceNameGenericParametersNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 6
                                                    CharPosition : 52
                                                //NamespaceNameNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 6
                                                        CharPosition : 52
                                                    //NamespaceNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 6
                                                            CharPosition : 52
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 6
                                                            CharPosition : 66
                                                        Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
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
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //Super -> //BaseParseTreeNode
                SourceFileName : FakeSourceFileName.pg
                Line : 1
                CharPosition : 4
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 22
                            Name : KeyToReplace
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 2
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 2
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 8
                            FieldName : testField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 20
                            Optional : false
                            //Int32FieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 2
                                        CharPosition : 20
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 22
                            Name : TestKey
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 6
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 6
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 6
                                CharPosition : 8
                            FieldName : testField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 6
                                CharPosition : 20
                            Optional : false
                            //SetFieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 6
                                        CharPosition : 20
                                //FieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 6
                                        CharPosition : 24
                                    Optional : false
                                    //KeyFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 6
                                                CharPosition : 24
                                        //NamespaceNameGenericParametersNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 6
                                                CharPosition : 24
                                            //NamespaceNameNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 6
                                                    CharPosition : 24
                                                //NamespaceNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 6
                                                        CharPosition : 24
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 6
                                                        CharPosition : 38
                                                    Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
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
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //Super -> //BaseParseTreeNode
                SourceFileName : FakeSourceFileName.pg
                Line : 1
                CharPosition : 4
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 22
                            Name : KeyToReplace
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 2
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 2
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 8
                            FieldName : testField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 20
                            Optional : false
                            //Int32FieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 2
                                        CharPosition : 20
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 22
                            Name : TestKey
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 6
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 6
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 6
                                CharPosition : 8
                            FieldName : testField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 6
                                CharPosition : 20
                            Optional : false
                            //ValueOrErrorFieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 6
                                        CharPosition : 20
                                //FieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 6
                                        CharPosition : 33
                                    Optional : false
                                    //KeyFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 6
                                                CharPosition : 33
                                        //NamespaceNameGenericParametersNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 6
                                                CharPosition : 33
                                            //NamespaceNameNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 6
                                                    CharPosition : 33
                                                //NamespaceNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 6
                                                        CharPosition : 33
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 6
                                                        CharPosition : 47
                                                    Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedArrayType() {

        var testProgram = """
            key TestNamespace.KeyToReplace {
                testField : int32
            }
            
            key TestNamespace.TestReplaceObjectFieldTypeWithKeyFieldType {
                testField : TestNamespace.KeyToReplace[][]
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //Super -> //BaseParseTreeNode
                SourceFileName : FakeSourceFileName.pg
                Line : 1
                CharPosition : 4
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 22
                            Name : KeyToReplace
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 2
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 2
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 8
                            FieldName : testField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 20
                            Optional : false
                            //Int32FieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 2
                                        CharPosition : 20
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 22
                            Name : TestReplaceObjectFieldTypeWithKeyFieldType
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 6
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 6
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 6
                                CharPosition : 8
                            FieldName : testField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 6
                                CharPosition : 20
                            Optional : false
                            //ArrayFieldTypeNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 6
                                    CharPosition : 20
                                //KeyFieldTypeNode
                                    //Super -> //NonArrayFieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 6
                                            CharPosition : 20
                                    //NamespaceNameGenericParametersNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 6
                                            CharPosition : 20
                                        //NamespaceNameNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 6
                                                CharPosition : 20
                                            //NamespaceNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 6
                                                    CharPosition : 20
                                                Namespace : TestNamespace
                                            //NameNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 6
                                                    CharPosition : 34
                                                Name : KeyToReplace
                                Dimensions : 2
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNonNestedKeyInImplementsList() {

        var testProgram = """
            key TestNamespace.KeyToReplace {
                testField : int32
            }
            
            key interface TestNamespace.TestKeyInterface<T> {
                testInterfaceField : T
            }
            
            key TestNamespace.TestKey : TestNamespace.TestKeyInterface<TestNamespace.KeyToReplace> {
                testField : TestNamespace.KeyToReplace
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //Super -> //BaseParseTreeNode
                SourceFileName : FakeSourceFileName.pg
                Line : 1
                CharPosition : 4
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 22
                            Name : KeyToReplace
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 2
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 2
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 8
                            FieldName : testField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 20
                            Optional : false
                            //Int32FieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 2
                                        CharPosition : 20
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 18
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 18
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 18
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 32
                            Name : TestKeyInterface
                    //GenericParametersWithBoundsNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 48
                        //GenericParameterWithBoundsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 49
                            Identifier : T
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 6
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 6
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 6
                                CharPosition : 8
                            FieldName : testInterfaceField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 6
                                CharPosition : 29
                            Optional : false
                            //GenericObjectFieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 6
                                        CharPosition : 29
                                //GenericParameterNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 6
                                        CharPosition : 29
                                    Identifier : T
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 9
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 9
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 9
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 22
                            Name : TestKey
                //ImplementsListNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 9
                        CharPosition : 30
                    //NamespaceNameGenericParametersNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 9
                            CharPosition : 32
                        //NamespaceNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 32
                            //NamespaceNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 9
                                    CharPosition : 32
                                Namespace : TestNamespace
                            //NameNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 9
                                    CharPosition : 46
                                Name : TestKeyInterface
                        //GenericParametersNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 62
                            //FieldTypeNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 9
                                    CharPosition : 63
                                Optional : false
                                //KeyFieldTypeNode
                                    //Super -> //NonArrayFieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 9
                                            CharPosition : 63
                                    //NamespaceNameGenericParametersNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 9
                                            CharPosition : 63
                                        //NamespaceNameNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 9
                                                CharPosition : 63
                                            //NamespaceNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 9
                                                    CharPosition : 63
                                                Namespace : TestNamespace
                                            //NameNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 9
                                                    CharPosition : 77
                                                Name : KeyToReplace
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 10
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 10
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 10
                                CharPosition : 8
                            FieldName : testField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 10
                                CharPosition : 20
                            Optional : false
                            //KeyFieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 10
                                        CharPosition : 20
                                //NamespaceNameGenericParametersNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 10
                                        CharPosition : 20
                                    //NamespaceNameNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 10
                                            CharPosition : 20
                                        //NamespaceNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 10
                                                CharPosition : 20
                                            Namespace : TestNamespace
                                        //NameNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 10
                                                CharPosition : 34
                                            Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedMapTypeInImplementsList() {

        var testProgram = """
            key TestNamespace.KeyToReplace {
                testField : int32
            }
            
            key interface TestNamespace.TestKeyInterface<T> {
                testInterfaceField : T
            }
            
            key TestNamespace.TestKey : TestNamespace.TestKeyInterface<map<TestNamespace.KeyToReplace, TestNamespace.KeyToReplace>> {
                testField : map<TestNamespace.KeyToReplace, TestNamespace.KeyToReplace>
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //Super -> //BaseParseTreeNode
                SourceFileName : FakeSourceFileName.pg
                Line : 1
                CharPosition : 4
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 22
                            Name : KeyToReplace
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 2
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 2
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 8
                            FieldName : testField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 20
                            Optional : false
                            //Int32FieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 2
                                        CharPosition : 20
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 18
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 18
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 18
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 32
                            Name : TestKeyInterface
                    //GenericParametersWithBoundsNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 48
                        //GenericParameterWithBoundsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 49
                            Identifier : T
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 6
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 6
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 6
                                CharPosition : 8
                            FieldName : testInterfaceField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 6
                                CharPosition : 29
                            Optional : false
                            //GenericObjectFieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 6
                                        CharPosition : 29
                                //GenericParameterNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 6
                                        CharPosition : 29
                                    Identifier : T
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 9
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 9
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 9
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 22
                            Name : TestKey
                //ImplementsListNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 9
                        CharPosition : 30
                    //NamespaceNameGenericParametersNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 9
                            CharPosition : 32
                        //NamespaceNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 32
                            //NamespaceNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 9
                                    CharPosition : 32
                                Namespace : TestNamespace
                            //NameNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 9
                                    CharPosition : 46
                                Name : TestKeyInterface
                        //GenericParametersNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 62
                            //FieldTypeNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 9
                                    CharPosition : 63
                                Optional : false
                                //MapFieldTypeNode
                                    //Super -> //NonArrayFieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 9
                                            CharPosition : 63
                                    Key
                                        //FieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 9
                                                CharPosition : 67
                                            Optional : false
                                            //KeyFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 9
                                                        CharPosition : 67
                                                //NamespaceNameGenericParametersNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 9
                                                        CharPosition : 67
                                                    //NamespaceNameNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 9
                                                            CharPosition : 67
                                                        //NamespaceNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 9
                                                                CharPosition : 67
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 9
                                                                CharPosition : 81
                                                            Name : KeyToReplace
                                    Value
                                        //FieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 9
                                                CharPosition : 95
                                            Optional : false
                                            //KeyFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 9
                                                        CharPosition : 95
                                                //NamespaceNameGenericParametersNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 9
                                                        CharPosition : 95
                                                    //NamespaceNameNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 9
                                                            CharPosition : 95
                                                        //NamespaceNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 9
                                                                CharPosition : 95
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 9
                                                                CharPosition : 109
                                                            Name : KeyToReplace
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 10
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 10
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 10
                                CharPosition : 8
                            FieldName : testField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 10
                                CharPosition : 20
                            Optional : false
                            //MapFieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 10
                                        CharPosition : 20
                                Key
                                    //FieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 10
                                            CharPosition : 24
                                        Optional : false
                                        //KeyFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 10
                                                    CharPosition : 24
                                            //NamespaceNameGenericParametersNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 10
                                                    CharPosition : 24
                                                //NamespaceNameNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 10
                                                        CharPosition : 24
                                                    //NamespaceNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 10
                                                            CharPosition : 24
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 10
                                                            CharPosition : 38
                                                        Name : KeyToReplace
                                Value
                                    //FieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 10
                                            CharPosition : 52
                                        Optional : false
                                        //KeyFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 10
                                                    CharPosition : 52
                                            //NamespaceNameGenericParametersNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 10
                                                    CharPosition : 52
                                                //NamespaceNameNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 10
                                                        CharPosition : 52
                                                    //NamespaceNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 10
                                                            CharPosition : 52
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 10
                                                            CharPosition : 66
                                                        Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedSetTypeInImplementsList() {

        var testProgram = """
            key TestNamespace.KeyToReplace {
                testField : int32
            }
            
            key interface TestNamespace.TestKeyInterface<T> {
                testInterfaceField : T
            }
            
            key TestNamespace.TestKey : TestNamespace.TestKeyInterface<set<TestNamespace.KeyToReplace>>{
                testField : set<TestNamespace.KeyToReplace>
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //Super -> //BaseParseTreeNode
                SourceFileName : FakeSourceFileName.pg
                Line : 1
                CharPosition : 4
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 22
                            Name : KeyToReplace
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 2
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 2
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 8
                            FieldName : testField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 20
                            Optional : false
                            //Int32FieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 2
                                        CharPosition : 20
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 18
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 18
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 18
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 32
                            Name : TestKeyInterface
                    //GenericParametersWithBoundsNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 48
                        //GenericParameterWithBoundsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 49
                            Identifier : T
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 6
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 6
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 6
                                CharPosition : 8
                            FieldName : testInterfaceField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 6
                                CharPosition : 29
                            Optional : false
                            //GenericObjectFieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 6
                                        CharPosition : 29
                                //GenericParameterNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 6
                                        CharPosition : 29
                                    Identifier : T
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 9
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 9
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 9
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 22
                            Name : TestKey
                //ImplementsListNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 9
                        CharPosition : 30
                    //NamespaceNameGenericParametersNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 9
                            CharPosition : 32
                        //NamespaceNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 32
                            //NamespaceNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 9
                                    CharPosition : 32
                                Namespace : TestNamespace
                            //NameNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 9
                                    CharPosition : 46
                                Name : TestKeyInterface
                        //GenericParametersNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 62
                            //FieldTypeNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 9
                                    CharPosition : 63
                                Optional : false
                                //SetFieldTypeNode
                                    //Super -> //NonArrayFieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 9
                                            CharPosition : 63
                                    //FieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 9
                                            CharPosition : 67
                                        Optional : false
                                        //KeyFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 9
                                                    CharPosition : 67
                                            //NamespaceNameGenericParametersNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 9
                                                    CharPosition : 67
                                                //NamespaceNameNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 9
                                                        CharPosition : 67
                                                    //NamespaceNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 9
                                                            CharPosition : 67
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 9
                                                            CharPosition : 81
                                                        Name : KeyToReplace
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 10
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 10
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 10
                                CharPosition : 8
                            FieldName : testField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 10
                                CharPosition : 20
                            Optional : false
                            //SetFieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 10
                                        CharPosition : 20
                                //FieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 10
                                        CharPosition : 24
                                    Optional : false
                                    //KeyFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 10
                                                CharPosition : 24
                                        //NamespaceNameGenericParametersNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 10
                                                CharPosition : 24
                                            //NamespaceNameNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 10
                                                    CharPosition : 24
                                                //NamespaceNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 10
                                                        CharPosition : 24
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 10
                                                        CharPosition : 38
                                                    Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedValueOrErrorTypeInImplementsList() {

        var testProgram = """
            key TestNamespace.KeyToReplace {
                testField : int32
            }
            
            key interface TestNamespace.TestKeyInterface<T> {
                testInterfaceField : T
            }
            
            key TestNamespace.TestKey : TestNamespace.TestKeyInterface<valueorerror<TestNamespace.KeyToReplace>> {
                testField : valueorerror<TestNamespace.KeyToReplace>
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //Super -> //BaseParseTreeNode
                SourceFileName : FakeSourceFileName.pg
                Line : 1
                CharPosition : 4
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 22
                            Name : KeyToReplace
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 2
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 2
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 8
                            FieldName : testField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 20
                            Optional : false
                            //Int32FieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 2
                                        CharPosition : 20
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 18
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 18
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 18
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 32
                            Name : TestKeyInterface
                    //GenericParametersWithBoundsNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 48
                        //GenericParameterWithBoundsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 49
                            Identifier : T
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 6
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 6
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 6
                                CharPosition : 8
                            FieldName : testInterfaceField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 6
                                CharPosition : 29
                            Optional : false
                            //GenericObjectFieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 6
                                        CharPosition : 29
                                //GenericParameterNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 6
                                        CharPosition : 29
                                    Identifier : T
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 9
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 9
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 9
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 22
                            Name : TestKey
                //ImplementsListNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 9
                        CharPosition : 30
                    //NamespaceNameGenericParametersNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 9
                            CharPosition : 32
                        //NamespaceNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 32
                            //NamespaceNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 9
                                    CharPosition : 32
                                Namespace : TestNamespace
                            //NameNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 9
                                    CharPosition : 46
                                Name : TestKeyInterface
                        //GenericParametersNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 62
                            //FieldTypeNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 9
                                    CharPosition : 63
                                Optional : false
                                //ValueOrErrorFieldTypeNode
                                    //Super -> //NonArrayFieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 9
                                            CharPosition : 63
                                    //FieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 9
                                            CharPosition : 76
                                        Optional : false
                                        //KeyFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 9
                                                    CharPosition : 76
                                            //NamespaceNameGenericParametersNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 9
                                                    CharPosition : 76
                                                //NamespaceNameNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 9
                                                        CharPosition : 76
                                                    //NamespaceNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 9
                                                            CharPosition : 76
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 9
                                                            CharPosition : 90
                                                        Name : KeyToReplace
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 10
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 10
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 10
                                CharPosition : 8
                            FieldName : testField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 10
                                CharPosition : 20
                            Optional : false
                            //ValueOrErrorFieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 10
                                        CharPosition : 20
                                //FieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 10
                                        CharPosition : 33
                                    Optional : false
                                    //KeyFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 10
                                                CharPosition : 33
                                        //NamespaceNameGenericParametersNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 10
                                                CharPosition : 33
                                            //NamespaceNameNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 10
                                                    CharPosition : 33
                                                //NamespaceNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 10
                                                        CharPosition : 33
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 10
                                                        CharPosition : 47
                                                    Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedArrayTypeInImplementsList() {

        var testProgram = """
            key TestNamespace.KeyToReplace {
                testField : int32
            }
            
            key interface TestNamespace.TestKeyInterface<T> {
                testInterfaceField : T
            }
            
            key TestNamespace.TestReplaceObjectFieldTypeWithKeyFieldType : TestNamespace.TestKeyInterface<TestNamespace.KeyToReplace[][]>{
                testField : TestNamespace.KeyToReplace[][]
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //Super -> //BaseParseTreeNode
                SourceFileName : FakeSourceFileName.pg
                Line : 1
                CharPosition : 4
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 22
                            Name : KeyToReplace
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 2
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 2
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 8
                            FieldName : testField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 20
                            Optional : false
                            //Int32FieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 2
                                        CharPosition : 20
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 18
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 18
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 18
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 32
                            Name : TestKeyInterface
                    //GenericParametersWithBoundsNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 48
                        //GenericParameterWithBoundsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 49
                            Identifier : T
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 6
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 6
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 6
                                CharPosition : 8
                            FieldName : testInterfaceField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 6
                                CharPosition : 29
                            Optional : false
                            //GenericObjectFieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 6
                                        CharPosition : 29
                                //GenericParameterNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 6
                                        CharPosition : 29
                                    Identifier : T
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 9
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 9
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 9
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 22
                            Name : TestReplaceObjectFieldTypeWithKeyFieldType
                //ImplementsListNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 9
                        CharPosition : 65
                    //NamespaceNameGenericParametersNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 9
                            CharPosition : 67
                        //NamespaceNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 67
                            //NamespaceNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 9
                                    CharPosition : 67
                                Namespace : TestNamespace
                            //NameNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 9
                                    CharPosition : 81
                                Name : TestKeyInterface
                        //GenericParametersNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 97
                            //FieldTypeNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 9
                                    CharPosition : 98
                                Optional : false
                                //ArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 9
                                        CharPosition : 98
                                    //KeyFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 9
                                                CharPosition : 98
                                        //NamespaceNameGenericParametersNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 9
                                                CharPosition : 98
                                            //NamespaceNameNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 9
                                                    CharPosition : 98
                                                //NamespaceNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 9
                                                        CharPosition : 98
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 9
                                                        CharPosition : 112
                                                    Name : KeyToReplace
                                    Dimensions : 2
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 10
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 10
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 10
                                CharPosition : 8
                            FieldName : testField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 10
                                CharPosition : 20
                            Optional : false
                            //ArrayFieldTypeNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 10
                                    CharPosition : 20
                                //KeyFieldTypeNode
                                    //Super -> //NonArrayFieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 10
                                            CharPosition : 20
                                    //NamespaceNameGenericParametersNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 10
                                            CharPosition : 20
                                        //NamespaceNameNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 10
                                                CharPosition : 20
                                            //NamespaceNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 10
                                                    CharPosition : 20
                                                Namespace : TestNamespace
                                            //NameNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 10
                                                    CharPosition : 34
                                                Name : KeyToReplace
                                Dimensions : 2
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNonNestedKeyInVersions() {

        var testProgram = """
            key TestNamespace.KeyToReplace {
                testField : int32
            }
            
            key TestNamespace.TestKey {
                version 1 {
                    testField : TestNamespace.KeyToReplace
                }
                version 2 {
                    testField : TestNamespace.KeyToReplace
                }
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //Super -> //BaseParseTreeNode
                SourceFileName : FakeSourceFileName.pg
                Line : 1
                CharPosition : 4
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 22
                            Name : KeyToReplace
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 2
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 2
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 8
                            FieldName : testField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 20
                            Optional : false
                            //Int32FieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 2
                                        CharPosition : 20
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 22
                            Name : TestKey
                //VersionsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 6
                        CharPosition : 8
                    //VersionNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 6
                            CharPosition : 8
                        //VersionNumberNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 6
                                CharPosition : 16
                            VersionNumber : 1
                        //FieldsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 7
                                CharPosition : 12
                            //FieldNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 7
                                    CharPosition : 12
                                //FieldNameNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 7
                                        CharPosition : 12
                                    FieldName : testField
                                //FieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 7
                                        CharPosition : 24
                                    Optional : false
                                    //KeyFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 7
                                                CharPosition : 24
                                        //NamespaceNameGenericParametersNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 7
                                                CharPosition : 24
                                            //NamespaceNameNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 7
                                                    CharPosition : 24
                                                //NamespaceNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 7
                                                        CharPosition : 24
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 7
                                                        CharPosition : 38
                                                    Name : KeyToReplace
                    //VersionNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 9
                            CharPosition : 8
                        //VersionNumberNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 16
                            VersionNumber : 2
                        //FieldsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 10
                                CharPosition : 12
                            //FieldNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 10
                                    CharPosition : 12
                                //FieldNameNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 10
                                        CharPosition : 12
                                    FieldName : testField
                                //FieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 10
                                        CharPosition : 24
                                    Optional : false
                                    //KeyFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 10
                                                CharPosition : 24
                                        //NamespaceNameGenericParametersNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 10
                                                CharPosition : 24
                                            //NamespaceNameNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 10
                                                    CharPosition : 24
                                                //NamespaceNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 10
                                                        CharPosition : 24
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 10
                                                        CharPosition : 38
                                                    Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedMapTypeInVersions() {

        var testProgram = """
            key TestNamespace.KeyToReplace {
                testField : int32
            }
            
            key TestNamespace.TestKey {
                version 1 {
                    testField : map<TestNamespace.KeyToReplace, TestNamespace.KeyToReplace>
                }
                version 2 {
                    testField : map<TestNamespace.KeyToReplace, TestNamespace.KeyToReplace>
                }
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //Super -> //BaseParseTreeNode
                SourceFileName : FakeSourceFileName.pg
                Line : 1
                CharPosition : 4
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 22
                            Name : KeyToReplace
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 2
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 2
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 8
                            FieldName : testField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 20
                            Optional : false
                            //Int32FieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 2
                                        CharPosition : 20
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 22
                            Name : TestKey
                //VersionsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 6
                        CharPosition : 8
                    //VersionNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 6
                            CharPosition : 8
                        //VersionNumberNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 6
                                CharPosition : 16
                            VersionNumber : 1
                        //FieldsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 7
                                CharPosition : 12
                            //FieldNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 7
                                    CharPosition : 12
                                //FieldNameNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 7
                                        CharPosition : 12
                                    FieldName : testField
                                //FieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 7
                                        CharPosition : 24
                                    Optional : false
                                    //MapFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 7
                                                CharPosition : 24
                                        Key
                                            //FieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 7
                                                    CharPosition : 28
                                                Optional : false
                                                //KeyFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 7
                                                            CharPosition : 28
                                                    //NamespaceNameGenericParametersNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 7
                                                            CharPosition : 28
                                                        //NamespaceNameNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 7
                                                                CharPosition : 28
                                                            //NamespaceNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 7
                                                                    CharPosition : 28
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 7
                                                                    CharPosition : 42
                                                                Name : KeyToReplace
                                        Value
                                            //FieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 7
                                                    CharPosition : 56
                                                Optional : false
                                                //KeyFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 7
                                                            CharPosition : 56
                                                    //NamespaceNameGenericParametersNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 7
                                                            CharPosition : 56
                                                        //NamespaceNameNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 7
                                                                CharPosition : 56
                                                            //NamespaceNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 7
                                                                    CharPosition : 56
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 7
                                                                    CharPosition : 70
                                                                Name : KeyToReplace
                    //VersionNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 9
                            CharPosition : 8
                        //VersionNumberNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 16
                            VersionNumber : 2
                        //FieldsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 10
                                CharPosition : 12
                            //FieldNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 10
                                    CharPosition : 12
                                //FieldNameNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 10
                                        CharPosition : 12
                                    FieldName : testField
                                //FieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 10
                                        CharPosition : 24
                                    Optional : false
                                    //MapFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 10
                                                CharPosition : 24
                                        Key
                                            //FieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 10
                                                    CharPosition : 28
                                                Optional : false
                                                //KeyFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 10
                                                            CharPosition : 28
                                                    //NamespaceNameGenericParametersNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 10
                                                            CharPosition : 28
                                                        //NamespaceNameNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 10
                                                                CharPosition : 28
                                                            //NamespaceNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 10
                                                                    CharPosition : 28
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 10
                                                                    CharPosition : 42
                                                                Name : KeyToReplace
                                        Value
                                            //FieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 10
                                                    CharPosition : 56
                                                Optional : false
                                                //KeyFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 10
                                                            CharPosition : 56
                                                    //NamespaceNameGenericParametersNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 10
                                                            CharPosition : 56
                                                        //NamespaceNameNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 10
                                                                CharPosition : 56
                                                            //NamespaceNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 10
                                                                    CharPosition : 56
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 10
                                                                    CharPosition : 70
                                                                Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedSetTypeInVersions() {

        var testProgram = """
            key TestNamespace.KeyToReplace {
                testField : int32
            }
            
            key TestNamespace.TestKey {
                version 1 {
                    testField : set<TestNamespace.KeyToReplace>
                }
                version 2 {
                    testField : set<TestNamespace.KeyToReplace>
                }
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //Super -> //BaseParseTreeNode
                SourceFileName : FakeSourceFileName.pg
                Line : 1
                CharPosition : 4
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 22
                            Name : KeyToReplace
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 2
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 2
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 8
                            FieldName : testField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 20
                            Optional : false
                            //Int32FieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 2
                                        CharPosition : 20
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 22
                            Name : TestKey
                //VersionsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 6
                        CharPosition : 8
                    //VersionNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 6
                            CharPosition : 8
                        //VersionNumberNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 6
                                CharPosition : 16
                            VersionNumber : 1
                        //FieldsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 7
                                CharPosition : 12
                            //FieldNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 7
                                    CharPosition : 12
                                //FieldNameNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 7
                                        CharPosition : 12
                                    FieldName : testField
                                //FieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 7
                                        CharPosition : 24
                                    Optional : false
                                    //SetFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 7
                                                CharPosition : 24
                                        //FieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 7
                                                CharPosition : 28
                                            Optional : false
                                            //KeyFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 7
                                                        CharPosition : 28
                                                //NamespaceNameGenericParametersNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 7
                                                        CharPosition : 28
                                                    //NamespaceNameNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 7
                                                            CharPosition : 28
                                                        //NamespaceNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 7
                                                                CharPosition : 28
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 7
                                                                CharPosition : 42
                                                            Name : KeyToReplace
                    //VersionNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 9
                            CharPosition : 8
                        //VersionNumberNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 16
                            VersionNumber : 2
                        //FieldsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 10
                                CharPosition : 12
                            //FieldNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 10
                                    CharPosition : 12
                                //FieldNameNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 10
                                        CharPosition : 12
                                    FieldName : testField
                                //FieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 10
                                        CharPosition : 24
                                    Optional : false
                                    //SetFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 10
                                                CharPosition : 24
                                        //FieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 10
                                                CharPosition : 28
                                            Optional : false
                                            //KeyFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 10
                                                        CharPosition : 28
                                                //NamespaceNameGenericParametersNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 10
                                                        CharPosition : 28
                                                    //NamespaceNameNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 10
                                                            CharPosition : 28
                                                        //NamespaceNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 10
                                                                CharPosition : 28
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 10
                                                                CharPosition : 42
                                                            Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedValueOrErrorTypeInVersions() {

        var testProgram = """
            key TestNamespace.KeyToReplace {
                testField : int32
            }
            
            key TestNamespace.TestKey {
                version 1 {
                    testField : valueorerror<TestNamespace.KeyToReplace>
                }
                version 2 {
                    testField : valueorerror<TestNamespace.KeyToReplace>
                }
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //Super -> //BaseParseTreeNode
                SourceFileName : FakeSourceFileName.pg
                Line : 1
                CharPosition : 4
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 22
                            Name : KeyToReplace
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 2
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 2
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 8
                            FieldName : testField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 20
                            Optional : false
                            //Int32FieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 2
                                        CharPosition : 20
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 22
                            Name : TestKey
                //VersionsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 6
                        CharPosition : 8
                    //VersionNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 6
                            CharPosition : 8
                        //VersionNumberNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 6
                                CharPosition : 16
                            VersionNumber : 1
                        //FieldsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 7
                                CharPosition : 12
                            //FieldNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 7
                                    CharPosition : 12
                                //FieldNameNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 7
                                        CharPosition : 12
                                    FieldName : testField
                                //FieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 7
                                        CharPosition : 24
                                    Optional : false
                                    //ValueOrErrorFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 7
                                                CharPosition : 24
                                        //FieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 7
                                                CharPosition : 37
                                            Optional : false
                                            //KeyFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 7
                                                        CharPosition : 37
                                                //NamespaceNameGenericParametersNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 7
                                                        CharPosition : 37
                                                    //NamespaceNameNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 7
                                                            CharPosition : 37
                                                        //NamespaceNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 7
                                                                CharPosition : 37
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 7
                                                                CharPosition : 51
                                                            Name : KeyToReplace
                    //VersionNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 9
                            CharPosition : 8
                        //VersionNumberNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 16
                            VersionNumber : 2
                        //FieldsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 10
                                CharPosition : 12
                            //FieldNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 10
                                    CharPosition : 12
                                //FieldNameNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 10
                                        CharPosition : 12
                                    FieldName : testField
                                //FieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 10
                                        CharPosition : 24
                                    Optional : false
                                    //ValueOrErrorFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 10
                                                CharPosition : 24
                                        //FieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 10
                                                CharPosition : 37
                                            Optional : false
                                            //KeyFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 10
                                                        CharPosition : 37
                                                //NamespaceNameGenericParametersNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 10
                                                        CharPosition : 37
                                                    //NamespaceNameNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 10
                                                            CharPosition : 37
                                                        //NamespaceNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 10
                                                                CharPosition : 37
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 10
                                                                CharPosition : 51
                                                            Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedArrayTypeInVersions() {

        var testProgram = """
            key TestNamespace.KeyToReplace {
                testField : int32
            }
            
            key TestNamespace.TestReplaceObjectFieldTypeWithKeyFieldType {
                version 1 {
                    testField : TestNamespace.KeyToReplace[][]
                }
                version 2 {
                    testField : TestNamespace.KeyToReplace[][]
                }
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //Super -> //BaseParseTreeNode
                SourceFileName : FakeSourceFileName.pg
                Line : 1
                CharPosition : 4
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 22
                            Name : KeyToReplace
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 2
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 2
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 8
                            FieldName : testField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 20
                            Optional : false
                            //Int32FieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 2
                                        CharPosition : 20
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 22
                            Name : TestReplaceObjectFieldTypeWithKeyFieldType
                //VersionsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 6
                        CharPosition : 8
                    //VersionNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 6
                            CharPosition : 8
                        //VersionNumberNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 6
                                CharPosition : 16
                            VersionNumber : 1
                        //FieldsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 7
                                CharPosition : 12
                            //FieldNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 7
                                    CharPosition : 12
                                //FieldNameNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 7
                                        CharPosition : 12
                                    FieldName : testField
                                //FieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 7
                                        CharPosition : 24
                                    Optional : false
                                    //ArrayFieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 7
                                            CharPosition : 24
                                        //KeyFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 7
                                                    CharPosition : 24
                                            //NamespaceNameGenericParametersNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 7
                                                    CharPosition : 24
                                                //NamespaceNameNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 7
                                                        CharPosition : 24
                                                    //NamespaceNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 7
                                                            CharPosition : 24
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 7
                                                            CharPosition : 38
                                                        Name : KeyToReplace
                                        Dimensions : 2
                    //VersionNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 9
                            CharPosition : 8
                        //VersionNumberNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 16
                            VersionNumber : 2
                        //FieldsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 10
                                CharPosition : 12
                            //FieldNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 10
                                    CharPosition : 12
                                //FieldNameNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 10
                                        CharPosition : 12
                                    FieldName : testField
                                //FieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 10
                                        CharPosition : 24
                                    Optional : false
                                    //ArrayFieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 10
                                            CharPosition : 24
                                        //KeyFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 10
                                                    CharPosition : 24
                                            //NamespaceNameGenericParametersNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 10
                                                    CharPosition : 24
                                                //NamespaceNameNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 10
                                                        CharPosition : 24
                                                    //NamespaceNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 10
                                                            CharPosition : 24
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 10
                                                            CharPosition : 38
                                                        Name : KeyToReplace
                                        Dimensions : 2
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNonNestedKeyInImplementsListInVersions() {

        var testProgram = """
            key TestNamespace.KeyToReplace {
                testField : int32
            }
            
            key interface TestNamespace.TestKeyInterface<T> {
                testInterfaceField : T
            }
            
            key TestNamespace.TestKey {
                version 1 : TestNamespace.TestKeyInterface<TestNamespace.KeyToReplace> {
                    testField : TestNamespace.KeyToReplace
                }
                version 2 : TestNamespace.TestKeyInterface<TestNamespace.KeyToReplace> {
                    testField : TestNamespace.KeyToReplace
                }
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //Super -> //BaseParseTreeNode
                SourceFileName : FakeSourceFileName.pg
                Line : 1
                CharPosition : 4
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 22
                            Name : KeyToReplace
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 2
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 2
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 8
                            FieldName : testField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 20
                            Optional : false
                            //Int32FieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 2
                                        CharPosition : 20
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 18
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 18
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 18
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 32
                            Name : TestKeyInterface
                    //GenericParametersWithBoundsNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 48
                        //GenericParameterWithBoundsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 49
                            Identifier : T
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 6
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 6
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 6
                                CharPosition : 8
                            FieldName : testInterfaceField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 6
                                CharPosition : 29
                            Optional : false
                            //GenericObjectFieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 6
                                        CharPosition : 29
                                //GenericParameterNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 6
                                        CharPosition : 29
                                    Identifier : T
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 9
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 9
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 9
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 22
                            Name : TestKey
                //VersionsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 10
                        CharPosition : 8
                    //VersionNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 10
                            CharPosition : 8
                        //VersionNumberNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 10
                                CharPosition : 16
                            VersionNumber : 1
                        //ImplementsListNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 10
                                CharPosition : 18
                            //NamespaceNameGenericParametersNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 10
                                    CharPosition : 20
                                //NamespaceNameNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 10
                                        CharPosition : 20
                                    //NamespaceNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 10
                                            CharPosition : 20
                                        Namespace : TestNamespace
                                    //NameNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 10
                                            CharPosition : 34
                                        Name : TestKeyInterface
                                //GenericParametersNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 10
                                        CharPosition : 50
                                    //FieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 10
                                            CharPosition : 51
                                        Optional : false
                                        //KeyFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 10
                                                    CharPosition : 51
                                            //NamespaceNameGenericParametersNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 10
                                                    CharPosition : 51
                                                //NamespaceNameNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 10
                                                        CharPosition : 51
                                                    //NamespaceNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 10
                                                            CharPosition : 51
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 10
                                                            CharPosition : 65
                                                        Name : KeyToReplace
                        //FieldsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 11
                                CharPosition : 12
                            //FieldNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 11
                                    CharPosition : 12
                                //FieldNameNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 11
                                        CharPosition : 12
                                    FieldName : testField
                                //FieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 11
                                        CharPosition : 24
                                    Optional : false
                                    //KeyFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 11
                                                CharPosition : 24
                                        //NamespaceNameGenericParametersNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 11
                                                CharPosition : 24
                                            //NamespaceNameNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 11
                                                    CharPosition : 24
                                                //NamespaceNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 11
                                                        CharPosition : 24
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 11
                                                        CharPosition : 38
                                                    Name : KeyToReplace
                    //VersionNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 13
                            CharPosition : 8
                        //VersionNumberNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 13
                                CharPosition : 16
                            VersionNumber : 2
                        //ImplementsListNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 13
                                CharPosition : 18
                            //NamespaceNameGenericParametersNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 13
                                    CharPosition : 20
                                //NamespaceNameNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 13
                                        CharPosition : 20
                                    //NamespaceNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 13
                                            CharPosition : 20
                                        Namespace : TestNamespace
                                    //NameNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 13
                                            CharPosition : 34
                                        Name : TestKeyInterface
                                //GenericParametersNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 13
                                        CharPosition : 50
                                    //FieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 13
                                            CharPosition : 51
                                        Optional : false
                                        //KeyFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 13
                                                    CharPosition : 51
                                            //NamespaceNameGenericParametersNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 13
                                                    CharPosition : 51
                                                //NamespaceNameNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 13
                                                        CharPosition : 51
                                                    //NamespaceNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 13
                                                            CharPosition : 51
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 13
                                                            CharPosition : 65
                                                        Name : KeyToReplace
                        //FieldsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 14
                                CharPosition : 12
                            //FieldNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 14
                                    CharPosition : 12
                                //FieldNameNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 14
                                        CharPosition : 12
                                    FieldName : testField
                                //FieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 14
                                        CharPosition : 24
                                    Optional : false
                                    //KeyFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 14
                                                CharPosition : 24
                                        //NamespaceNameGenericParametersNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 14
                                                CharPosition : 24
                                            //NamespaceNameNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 14
                                                    CharPosition : 24
                                                //NamespaceNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 14
                                                        CharPosition : 24
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 14
                                                        CharPosition : 38
                                                    Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedMapTypeInImplementsListInVersions() {

        var testProgram = """
            key TestNamespace.KeyToReplace {
                testField : int32
            }
            
            key interface TestNamespace.TestKeyInterface<T> {
                testInterfaceField : T
            }
            
            key TestNamespace.TestKey {
                version 1 : TestNamespace.TestKeyInterface<map<TestNamespace.KeyToReplace, TestNamespace.KeyToReplace>> {
                    testField : map<TestNamespace.KeyToReplace, TestNamespace.KeyToReplace>
                }
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //Super -> //BaseParseTreeNode
                SourceFileName : FakeSourceFileName.pg
                Line : 1
                CharPosition : 4
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 22
                            Name : KeyToReplace
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 2
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 2
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 8
                            FieldName : testField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 20
                            Optional : false
                            //Int32FieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 2
                                        CharPosition : 20
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 18
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 18
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 18
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 32
                            Name : TestKeyInterface
                    //GenericParametersWithBoundsNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 48
                        //GenericParameterWithBoundsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 49
                            Identifier : T
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 6
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 6
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 6
                                CharPosition : 8
                            FieldName : testInterfaceField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 6
                                CharPosition : 29
                            Optional : false
                            //GenericObjectFieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 6
                                        CharPosition : 29
                                //GenericParameterNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 6
                                        CharPosition : 29
                                    Identifier : T
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 9
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 9
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 9
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 22
                            Name : TestKey
                //VersionsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 10
                        CharPosition : 8
                    //VersionNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 10
                            CharPosition : 8
                        //VersionNumberNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 10
                                CharPosition : 16
                            VersionNumber : 1
                        //ImplementsListNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 10
                                CharPosition : 18
                            //NamespaceNameGenericParametersNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 10
                                    CharPosition : 20
                                //NamespaceNameNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 10
                                        CharPosition : 20
                                    //NamespaceNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 10
                                            CharPosition : 20
                                        Namespace : TestNamespace
                                    //NameNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 10
                                            CharPosition : 34
                                        Name : TestKeyInterface
                                //GenericParametersNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 10
                                        CharPosition : 50
                                    //FieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 10
                                            CharPosition : 51
                                        Optional : false
                                        //MapFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 10
                                                    CharPosition : 51
                                            Key
                                                //FieldTypeNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 10
                                                        CharPosition : 55
                                                    Optional : false
                                                    //KeyFieldTypeNode
                                                        //Super -> //NonArrayFieldTypeNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 10
                                                                CharPosition : 55
                                                        //NamespaceNameGenericParametersNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 10
                                                                CharPosition : 55
                                                            //NamespaceNameNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 10
                                                                    CharPosition : 55
                                                                //NamespaceNode
                                                                    //Super -> //BaseParseTreeNode
                                                                        SourceFileName : FakeSourceFileName.pg
                                                                        Line : 10
                                                                        CharPosition : 55
                                                                    Namespace : TestNamespace
                                                                //NameNode
                                                                    //Super -> //BaseParseTreeNode
                                                                        SourceFileName : FakeSourceFileName.pg
                                                                        Line : 10
                                                                        CharPosition : 69
                                                                    Name : KeyToReplace
                                            Value
                                                //FieldTypeNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 10
                                                        CharPosition : 83
                                                    Optional : false
                                                    //KeyFieldTypeNode
                                                        //Super -> //NonArrayFieldTypeNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 10
                                                                CharPosition : 83
                                                        //NamespaceNameGenericParametersNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 10
                                                                CharPosition : 83
                                                            //NamespaceNameNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 10
                                                                    CharPosition : 83
                                                                //NamespaceNode
                                                                    //Super -> //BaseParseTreeNode
                                                                        SourceFileName : FakeSourceFileName.pg
                                                                        Line : 10
                                                                        CharPosition : 83
                                                                    Namespace : TestNamespace
                                                                //NameNode
                                                                    //Super -> //BaseParseTreeNode
                                                                        SourceFileName : FakeSourceFileName.pg
                                                                        Line : 10
                                                                        CharPosition : 97
                                                                    Name : KeyToReplace
                        //FieldsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 11
                                CharPosition : 12
                            //FieldNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 11
                                    CharPosition : 12
                                //FieldNameNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 11
                                        CharPosition : 12
                                    FieldName : testField
                                //FieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 11
                                        CharPosition : 24
                                    Optional : false
                                    //MapFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 11
                                                CharPosition : 24
                                        Key
                                            //FieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 11
                                                    CharPosition : 28
                                                Optional : false
                                                //KeyFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 11
                                                            CharPosition : 28
                                                    //NamespaceNameGenericParametersNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 11
                                                            CharPosition : 28
                                                        //NamespaceNameNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 11
                                                                CharPosition : 28
                                                            //NamespaceNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 11
                                                                    CharPosition : 28
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 11
                                                                    CharPosition : 42
                                                                Name : KeyToReplace
                                        Value
                                            //FieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 11
                                                    CharPosition : 56
                                                Optional : false
                                                //KeyFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 11
                                                            CharPosition : 56
                                                    //NamespaceNameGenericParametersNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 11
                                                            CharPosition : 56
                                                        //NamespaceNameNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 11
                                                                CharPosition : 56
                                                            //NamespaceNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 11
                                                                    CharPosition : 56
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 11
                                                                    CharPosition : 70
                                                                Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedSetTypeInImplementsListInVersions() {

        var testProgram = """
            key TestNamespace.KeyToReplace {
                testField : int32
            }
            
            key interface TestNamespace.TestKeyInterface<T> {
                testInterfaceField : T
            }
            
            key TestNamespace.TestKey {
                version 1 : TestNamespace.TestKeyInterface<set<TestNamespace.KeyToReplace>> {
                    testField : set<TestNamespace.KeyToReplace>
                }
                version 2 : TestNamespace.TestKeyInterface<set<TestNamespace.KeyToReplace>> {
                    testField : set<TestNamespace.KeyToReplace>
                }
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //Super -> //BaseParseTreeNode
                SourceFileName : FakeSourceFileName.pg
                Line : 1
                CharPosition : 4
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 22
                            Name : KeyToReplace
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 2
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 2
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 8
                            FieldName : testField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 20
                            Optional : false
                            //Int32FieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 2
                                        CharPosition : 20
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 18
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 18
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 18
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 32
                            Name : TestKeyInterface
                    //GenericParametersWithBoundsNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 48
                        //GenericParameterWithBoundsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 49
                            Identifier : T
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 6
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 6
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 6
                                CharPosition : 8
                            FieldName : testInterfaceField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 6
                                CharPosition : 29
                            Optional : false
                            //GenericObjectFieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 6
                                        CharPosition : 29
                                //GenericParameterNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 6
                                        CharPosition : 29
                                    Identifier : T
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 9
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 9
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 9
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 22
                            Name : TestKey
                //VersionsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 10
                        CharPosition : 8
                    //VersionNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 10
                            CharPosition : 8
                        //VersionNumberNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 10
                                CharPosition : 16
                            VersionNumber : 1
                        //ImplementsListNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 10
                                CharPosition : 18
                            //NamespaceNameGenericParametersNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 10
                                    CharPosition : 20
                                //NamespaceNameNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 10
                                        CharPosition : 20
                                    //NamespaceNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 10
                                            CharPosition : 20
                                        Namespace : TestNamespace
                                    //NameNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 10
                                            CharPosition : 34
                                        Name : TestKeyInterface
                                //GenericParametersNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 10
                                        CharPosition : 50
                                    //FieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 10
                                            CharPosition : 51
                                        Optional : false
                                        //SetFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 10
                                                    CharPosition : 51
                                            //FieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 10
                                                    CharPosition : 55
                                                Optional : false
                                                //KeyFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 10
                                                            CharPosition : 55
                                                    //NamespaceNameGenericParametersNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 10
                                                            CharPosition : 55
                                                        //NamespaceNameNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 10
                                                                CharPosition : 55
                                                            //NamespaceNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 10
                                                                    CharPosition : 55
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 10
                                                                    CharPosition : 69
                                                                Name : KeyToReplace
                        //FieldsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 11
                                CharPosition : 12
                            //FieldNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 11
                                    CharPosition : 12
                                //FieldNameNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 11
                                        CharPosition : 12
                                    FieldName : testField
                                //FieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 11
                                        CharPosition : 24
                                    Optional : false
                                    //SetFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 11
                                                CharPosition : 24
                                        //FieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 11
                                                CharPosition : 28
                                            Optional : false
                                            //KeyFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 11
                                                        CharPosition : 28
                                                //NamespaceNameGenericParametersNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 11
                                                        CharPosition : 28
                                                    //NamespaceNameNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 11
                                                            CharPosition : 28
                                                        //NamespaceNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 11
                                                                CharPosition : 28
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 11
                                                                CharPosition : 42
                                                            Name : KeyToReplace
                    //VersionNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 13
                            CharPosition : 8
                        //VersionNumberNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 13
                                CharPosition : 16
                            VersionNumber : 2
                        //ImplementsListNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 13
                                CharPosition : 18
                            //NamespaceNameGenericParametersNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 13
                                    CharPosition : 20
                                //NamespaceNameNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 13
                                        CharPosition : 20
                                    //NamespaceNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 13
                                            CharPosition : 20
                                        Namespace : TestNamespace
                                    //NameNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 13
                                            CharPosition : 34
                                        Name : TestKeyInterface
                                //GenericParametersNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 13
                                        CharPosition : 50
                                    //FieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 13
                                            CharPosition : 51
                                        Optional : false
                                        //SetFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 13
                                                    CharPosition : 51
                                            //FieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 13
                                                    CharPosition : 55
                                                Optional : false
                                                //KeyFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 13
                                                            CharPosition : 55
                                                    //NamespaceNameGenericParametersNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 13
                                                            CharPosition : 55
                                                        //NamespaceNameNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 13
                                                                CharPosition : 55
                                                            //NamespaceNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 13
                                                                    CharPosition : 55
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 13
                                                                    CharPosition : 69
                                                                Name : KeyToReplace
                        //FieldsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 14
                                CharPosition : 12
                            //FieldNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 14
                                    CharPosition : 12
                                //FieldNameNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 14
                                        CharPosition : 12
                                    FieldName : testField
                                //FieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 14
                                        CharPosition : 24
                                    Optional : false
                                    //SetFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 14
                                                CharPosition : 24
                                        //FieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 14
                                                CharPosition : 28
                                            Optional : false
                                            //KeyFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 14
                                                        CharPosition : 28
                                                //NamespaceNameGenericParametersNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 14
                                                        CharPosition : 28
                                                    //NamespaceNameNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 14
                                                            CharPosition : 28
                                                        //NamespaceNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 14
                                                                CharPosition : 28
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 14
                                                                CharPosition : 42
                                                            Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedValueOrErrorTypeInImplementsListInVersions() {

        var testProgram = """
            key TestNamespace.KeyToReplace {
                testField : int32
            }
            
            key interface TestNamespace.TestKeyInterface<T> {
                testInterfaceField : T
            }
            
            key TestNamespace.TestKey {
                version 1 : TestNamespace.TestKeyInterface<valueorerror<TestNamespace.KeyToReplace>> {
                    testField : valueorerror<TestNamespace.KeyToReplace>
                }
                version 2 : TestNamespace.TestKeyInterface<valueorerror<TestNamespace.KeyToReplace>> {
                    testField : valueorerror<TestNamespace.KeyToReplace>
                }
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //Super -> //BaseParseTreeNode
                SourceFileName : FakeSourceFileName.pg
                Line : 1
                CharPosition : 4
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 22
                            Name : KeyToReplace
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 2
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 2
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 8
                            FieldName : testField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 20
                            Optional : false
                            //Int32FieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 2
                                        CharPosition : 20
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 18
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 18
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 18
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 32
                            Name : TestKeyInterface
                    //GenericParametersWithBoundsNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 48
                        //GenericParameterWithBoundsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 49
                            Identifier : T
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 6
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 6
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 6
                                CharPosition : 8
                            FieldName : testInterfaceField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 6
                                CharPosition : 29
                            Optional : false
                            //GenericObjectFieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 6
                                        CharPosition : 29
                                //GenericParameterNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 6
                                        CharPosition : 29
                                    Identifier : T
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 9
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 9
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 9
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 22
                            Name : TestKey
                //VersionsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 10
                        CharPosition : 8
                    //VersionNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 10
                            CharPosition : 8
                        //VersionNumberNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 10
                                CharPosition : 16
                            VersionNumber : 1
                        //ImplementsListNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 10
                                CharPosition : 18
                            //NamespaceNameGenericParametersNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 10
                                    CharPosition : 20
                                //NamespaceNameNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 10
                                        CharPosition : 20
                                    //NamespaceNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 10
                                            CharPosition : 20
                                        Namespace : TestNamespace
                                    //NameNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 10
                                            CharPosition : 34
                                        Name : TestKeyInterface
                                //GenericParametersNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 10
                                        CharPosition : 50
                                    //FieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 10
                                            CharPosition : 51
                                        Optional : false
                                        //ValueOrErrorFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 10
                                                    CharPosition : 51
                                            //FieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 10
                                                    CharPosition : 64
                                                Optional : false
                                                //KeyFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 10
                                                            CharPosition : 64
                                                    //NamespaceNameGenericParametersNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 10
                                                            CharPosition : 64
                                                        //NamespaceNameNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 10
                                                                CharPosition : 64
                                                            //NamespaceNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 10
                                                                    CharPosition : 64
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 10
                                                                    CharPosition : 78
                                                                Name : KeyToReplace
                        //FieldsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 11
                                CharPosition : 12
                            //FieldNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 11
                                    CharPosition : 12
                                //FieldNameNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 11
                                        CharPosition : 12
                                    FieldName : testField
                                //FieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 11
                                        CharPosition : 24
                                    Optional : false
                                    //ValueOrErrorFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 11
                                                CharPosition : 24
                                        //FieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 11
                                                CharPosition : 37
                                            Optional : false
                                            //KeyFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 11
                                                        CharPosition : 37
                                                //NamespaceNameGenericParametersNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 11
                                                        CharPosition : 37
                                                    //NamespaceNameNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 11
                                                            CharPosition : 37
                                                        //NamespaceNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 11
                                                                CharPosition : 37
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 11
                                                                CharPosition : 51
                                                            Name : KeyToReplace
                    //VersionNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 13
                            CharPosition : 8
                        //VersionNumberNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 13
                                CharPosition : 16
                            VersionNumber : 2
                        //ImplementsListNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 13
                                CharPosition : 18
                            //NamespaceNameGenericParametersNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 13
                                    CharPosition : 20
                                //NamespaceNameNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 13
                                        CharPosition : 20
                                    //NamespaceNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 13
                                            CharPosition : 20
                                        Namespace : TestNamespace
                                    //NameNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 13
                                            CharPosition : 34
                                        Name : TestKeyInterface
                                //GenericParametersNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 13
                                        CharPosition : 50
                                    //FieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 13
                                            CharPosition : 51
                                        Optional : false
                                        //ValueOrErrorFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 13
                                                    CharPosition : 51
                                            //FieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 13
                                                    CharPosition : 64
                                                Optional : false
                                                //KeyFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 13
                                                            CharPosition : 64
                                                    //NamespaceNameGenericParametersNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 13
                                                            CharPosition : 64
                                                        //NamespaceNameNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 13
                                                                CharPosition : 64
                                                            //NamespaceNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 13
                                                                    CharPosition : 64
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 13
                                                                    CharPosition : 78
                                                                Name : KeyToReplace
                        //FieldsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 14
                                CharPosition : 12
                            //FieldNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 14
                                    CharPosition : 12
                                //FieldNameNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 14
                                        CharPosition : 12
                                    FieldName : testField
                                //FieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 14
                                        CharPosition : 24
                                    Optional : false
                                    //ValueOrErrorFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 14
                                                CharPosition : 24
                                        //FieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 14
                                                CharPosition : 37
                                            Optional : false
                                            //KeyFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 14
                                                        CharPosition : 37
                                                //NamespaceNameGenericParametersNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 14
                                                        CharPosition : 37
                                                    //NamespaceNameNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 14
                                                            CharPosition : 37
                                                        //NamespaceNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 14
                                                                CharPosition : 37
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 14
                                                                CharPosition : 51
                                                            Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedArrayTypeInImplementsListInVersions() {

        var testProgram = """
            key TestNamespace.KeyToReplace {
                testField : int32
            }
            
            key interface TestNamespace.TestKeyInterface<T> {
                testInterfaceField : T
            }
            
            key TestNamespace.TestReplaceObjectFieldTypeWithKeyFieldType {
                version 1 : TestNamespace.TestKeyInterface<TestNamespace.KeyToReplace[][]> {
                    testField : TestNamespace.KeyToReplace[][]
                }
                version 2 : TestNamespace.TestKeyInterface<TestNamespace.KeyToReplace[][]> {
                    testField : TestNamespace.KeyToReplace[][]
                }
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //Super -> //BaseParseTreeNode
                SourceFileName : FakeSourceFileName.pg
                Line : 1
                CharPosition : 4
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 22
                            Name : KeyToReplace
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 2
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 2
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 8
                            FieldName : testField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 2
                                CharPosition : 20
                            Optional : false
                            //Int32FieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 2
                                        CharPosition : 20
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 18
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 18
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 18
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 32
                            Name : TestKeyInterface
                    //GenericParametersWithBoundsNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 48
                        //GenericParameterWithBoundsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 49
                            Identifier : T
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 6
                        CharPosition : 8
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 6
                            CharPosition : 8
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 6
                                CharPosition : 8
                            FieldName : testInterfaceField
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 6
                                CharPosition : 29
                            Optional : false
                            //GenericObjectFieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 6
                                        CharPosition : 29
                                //GenericParameterNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 6
                                        CharPosition : 29
                                    Identifier : T
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 9
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 9
                        CharPosition : 8
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 9
                            CharPosition : 8
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 8
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 22
                            Name : TestReplaceObjectFieldTypeWithKeyFieldType
                //VersionsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 10
                        CharPosition : 8
                    //VersionNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 10
                            CharPosition : 8
                        //VersionNumberNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 10
                                CharPosition : 16
                            VersionNumber : 1
                        //ImplementsListNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 10
                                CharPosition : 18
                            //NamespaceNameGenericParametersNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 10
                                    CharPosition : 20
                                //NamespaceNameNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 10
                                        CharPosition : 20
                                    //NamespaceNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 10
                                            CharPosition : 20
                                        Namespace : TestNamespace
                                    //NameNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 10
                                            CharPosition : 34
                                        Name : TestKeyInterface
                                //GenericParametersNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 10
                                        CharPosition : 50
                                    //FieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 10
                                            CharPosition : 51
                                        Optional : false
                                        //ArrayFieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 10
                                                CharPosition : 51
                                            //KeyFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 10
                                                        CharPosition : 51
                                                //NamespaceNameGenericParametersNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 10
                                                        CharPosition : 51
                                                    //NamespaceNameNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 10
                                                            CharPosition : 51
                                                        //NamespaceNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 10
                                                                CharPosition : 51
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 10
                                                                CharPosition : 65
                                                            Name : KeyToReplace
                                            Dimensions : 2
                        //FieldsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 11
                                CharPosition : 12
                            //FieldNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 11
                                    CharPosition : 12
                                //FieldNameNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 11
                                        CharPosition : 12
                                    FieldName : testField
                                //FieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 11
                                        CharPosition : 24
                                    Optional : false
                                    //ArrayFieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 11
                                            CharPosition : 24
                                        //KeyFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 11
                                                    CharPosition : 24
                                            //NamespaceNameGenericParametersNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 11
                                                    CharPosition : 24
                                                //NamespaceNameNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 11
                                                        CharPosition : 24
                                                    //NamespaceNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 11
                                                            CharPosition : 24
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 11
                                                            CharPosition : 38
                                                        Name : KeyToReplace
                                        Dimensions : 2
                    //VersionNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 13
                            CharPosition : 8
                        //VersionNumberNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 13
                                CharPosition : 16
                            VersionNumber : 2
                        //ImplementsListNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 13
                                CharPosition : 18
                            //NamespaceNameGenericParametersNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 13
                                    CharPosition : 20
                                //NamespaceNameNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 13
                                        CharPosition : 20
                                    //NamespaceNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 13
                                            CharPosition : 20
                                        Namespace : TestNamespace
                                    //NameNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 13
                                            CharPosition : 34
                                        Name : TestKeyInterface
                                //GenericParametersNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 13
                                        CharPosition : 50
                                    //FieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 13
                                            CharPosition : 51
                                        Optional : false
                                        //ArrayFieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 13
                                                CharPosition : 51
                                            //KeyFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 13
                                                        CharPosition : 51
                                                //NamespaceNameGenericParametersNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 13
                                                        CharPosition : 51
                                                    //NamespaceNameNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 13
                                                            CharPosition : 51
                                                        //NamespaceNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 13
                                                                CharPosition : 51
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 13
                                                                CharPosition : 65
                                                            Name : KeyToReplace
                                            Dimensions : 2
                        //FieldsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 14
                                CharPosition : 12
                            //FieldNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 14
                                    CharPosition : 12
                                //FieldNameNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 14
                                        CharPosition : 12
                                    FieldName : testField
                                //FieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 14
                                        CharPosition : 24
                                    Optional : false
                                    //ArrayFieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 14
                                            CharPosition : 24
                                        //KeyFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 14
                                                    CharPosition : 24
                                            //NamespaceNameGenericParametersNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 14
                                                    CharPosition : 24
                                                //NamespaceNameNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 14
                                                        CharPosition : 24
                                                    //NamespaceNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 14
                                                            CharPosition : 24
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 14
                                                            CharPosition : 38
                                                        Name : KeyToReplace
                                        Dimensions : 2
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

}