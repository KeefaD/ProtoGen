package com.kdsc.protogen.parsetreepostprocessing;

import com.kdsc.protogen.BaseCompilerTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUndetectableNodeReplacerWithTypes extends BaseCompilerTest {

    @Test
    public void testReplaceOneNonNestedType() {

        var testProgram = """
            type TestNamespace.TypeToReplace {
                testField : int32
            }
            
            type TestNamespace.TestType {
                testField : TestNamespace.TypeToReplace
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //Super -> //BaseParseTreeNode
                SourceFileName : FakeSourceFileName.pg
                Line : 1
                CharPosition : 4
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 23
                            Name : TypeToReplace
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 23
                            Name : TestType
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
                            //TypeFieldTypeNode
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
                                            Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedMapType() {

        var testProgram = """
            type TestNamespace.TypeToReplace {
                testField : int32
            }
            
            type TestNamespace.Type {
                testField : map<TestNamespace.TypeToReplace, TestNamespace.TypeToReplace>
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //Super -> //BaseParseTreeNode
                SourceFileName : FakeSourceFileName.pg
                Line : 1
                CharPosition : 4
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 23
                            Name : TypeToReplace
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 23
                            Name : Type
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
                                        //TypeFieldTypeNode
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
                                                        Name : TypeToReplace
                                Value
                                    //FieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 6
                                            CharPosition : 53
                                        Optional : false
                                        //TypeFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 6
                                                    CharPosition : 53
                                            //NamespaceNameGenericParametersNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 6
                                                    CharPosition : 53
                                                //NamespaceNameNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 6
                                                        CharPosition : 53
                                                    //NamespaceNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 6
                                                            CharPosition : 53
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 6
                                                            CharPosition : 67
                                                        Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedSetType() {

        var testProgram = """
            type TestNamespace.TypeToReplace {
                testField : int32
            }
            
            type TestNamespace.TestType {
                testField : set<TestNamespace.TypeToReplace>
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //Super -> //BaseParseTreeNode
                SourceFileName : FakeSourceFileName.pg
                Line : 1
                CharPosition : 4
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 23
                            Name : TypeToReplace
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 23
                            Name : TestType
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
                                    //TypeFieldTypeNode
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
                                                    Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedValueOrErrorType() {

        var testProgram = """
            type TestNamespace.TypeToReplace {
                testField : int32
            }
            
            type TestNamespace.TestType {
                testField : valueorerror<TestNamespace.TypeToReplace>
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //Super -> //BaseParseTreeNode
                SourceFileName : FakeSourceFileName.pg
                Line : 1
                CharPosition : 4
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 23
                            Name : TypeToReplace
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 23
                            Name : TestType
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
                                    //TypeFieldTypeNode
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
                                                    Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedArrayType() {

        var testProgram = """
            type TestNamespace.TypeToReplace {
                testField : int32
            }
            
            type TestNamespace.TestType {
                testField : TestNamespace.TypeToReplace[][]
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //Super -> //BaseParseTreeNode
                SourceFileName : FakeSourceFileName.pg
                Line : 1
                CharPosition : 4
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 23
                            Name : TypeToReplace
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 23
                            Name : TestType
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
                                //TypeFieldTypeNode
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
                                                Name : TypeToReplace
                                Dimensions : 2
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNonNestedTypeInImplementsList() {

        var testProgram = """
            type TestNamespace.TypeToReplace {
                testField : int32
            }
            
            type interface TestNamespace.TestTypeInterface<T> {
                testInterfaceField : T
            }
            
            type TestNamespace.TestType : TestNamespace.TestTypeInterface<TestNamespace.TypeToReplace>{
                testField : TestNamespace.TypeToReplace
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //Super -> //BaseParseTreeNode
                SourceFileName : FakeSourceFileName.pg
                Line : 1
                CharPosition : 4
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 23
                            Name : TypeToReplace
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 19
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 19
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 19
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 33
                            Name : TestTypeInterface
                    //GenericParametersWithBoundsNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 50
                        //GenericParameterWithBoundsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 51
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 9
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 9
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 9
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 23
                            Name : TestType
                //ImplementsListNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 9
                        CharPosition : 32
                    //NamespaceNameGenericParametersNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 9
                            CharPosition : 34
                        //NamespaceNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 34
                            //NamespaceNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 9
                                    CharPosition : 34
                                Namespace : TestNamespace
                            //NameNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 9
                                    CharPosition : 48
                                Name : TestTypeInterface
                        //GenericParametersNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 65
                            //FieldTypeNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 9
                                    CharPosition : 66
                                Optional : false
                                //TypeFieldTypeNode
                                    //Super -> //NonArrayFieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 9
                                            CharPosition : 66
                                    //NamespaceNameGenericParametersNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 9
                                            CharPosition : 66
                                        //NamespaceNameNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 9
                                                CharPosition : 66
                                            //NamespaceNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 9
                                                    CharPosition : 66
                                                Namespace : TestNamespace
                                            //NameNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 9
                                                    CharPosition : 80
                                                Name : TypeToReplace
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
                            //TypeFieldTypeNode
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
                                            Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedMapTypeInImplementsList() {

        var testProgram = """
            type TestNamespace.TypeToReplace {
                testField : int32
            }
            
            type interface TestNamespace.TestTypeInterface<T> {
                testInterfaceField : T
            }
            
            type TestNamespace.Type : TestNamespace.TestTypeInterface<map<TestNamespace.TypeToReplace, TestNamespace.TypeToReplace>> {
                testField : map<TestNamespace.TypeToReplace, TestNamespace.TypeToReplace>
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //Super -> //BaseParseTreeNode
                SourceFileName : FakeSourceFileName.pg
                Line : 1
                CharPosition : 4
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 23
                            Name : TypeToReplace
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 19
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 19
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 19
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 33
                            Name : TestTypeInterface
                    //GenericParametersWithBoundsNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 50
                        //GenericParameterWithBoundsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 51
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 9
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 9
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 9
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 23
                            Name : Type
                //ImplementsListNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 9
                        CharPosition : 28
                    //NamespaceNameGenericParametersNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 9
                            CharPosition : 30
                        //NamespaceNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 30
                            //NamespaceNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 9
                                    CharPosition : 30
                                Namespace : TestNamespace
                            //NameNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 9
                                    CharPosition : 44
                                Name : TestTypeInterface
                        //GenericParametersNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 61
                            //FieldTypeNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 9
                                    CharPosition : 62
                                Optional : false
                                //MapFieldTypeNode
                                    //Super -> //NonArrayFieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 9
                                            CharPosition : 62
                                    Key
                                        //FieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 9
                                                CharPosition : 66
                                            Optional : false
                                            //TypeFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 9
                                                        CharPosition : 66
                                                //NamespaceNameGenericParametersNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 9
                                                        CharPosition : 66
                                                    //NamespaceNameNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 9
                                                            CharPosition : 66
                                                        //NamespaceNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 9
                                                                CharPosition : 66
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 9
                                                                CharPosition : 80
                                                            Name : TypeToReplace
                                    Value
                                        //FieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 9
                                                CharPosition : 95
                                            Optional : false
                                            //TypeFieldTypeNode
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
                                                            Name : TypeToReplace
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
                                        //TypeFieldTypeNode
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
                                                        Name : TypeToReplace
                                Value
                                    //FieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 10
                                            CharPosition : 53
                                        Optional : false
                                        //TypeFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 10
                                                    CharPosition : 53
                                            //NamespaceNameGenericParametersNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 10
                                                    CharPosition : 53
                                                //NamespaceNameNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 10
                                                        CharPosition : 53
                                                    //NamespaceNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 10
                                                            CharPosition : 53
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 10
                                                            CharPosition : 67
                                                        Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedSetTypeInImplementsList() {

        var testProgram = """
            type TestNamespace.TypeToReplace {
                testField : int32
            }
            
            type interface TestNamespace.TestTypeInterface<T> {
                testInterfaceField : T
            }
            
            type TestNamespace.TestType : TestNamespace.TestTypeInterface<set<TestNamespace.TypeToReplace>> {
                testField : set<TestNamespace.TypeToReplace>
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //Super -> //BaseParseTreeNode
                SourceFileName : FakeSourceFileName.pg
                Line : 1
                CharPosition : 4
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 23
                            Name : TypeToReplace
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 19
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 19
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 19
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 33
                            Name : TestTypeInterface
                    //GenericParametersWithBoundsNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 50
                        //GenericParameterWithBoundsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 51
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 9
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 9
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 9
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 23
                            Name : TestType
                //ImplementsListNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 9
                        CharPosition : 32
                    //NamespaceNameGenericParametersNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 9
                            CharPosition : 34
                        //NamespaceNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 34
                            //NamespaceNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 9
                                    CharPosition : 34
                                Namespace : TestNamespace
                            //NameNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 9
                                    CharPosition : 48
                                Name : TestTypeInterface
                        //GenericParametersNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 65
                            //FieldTypeNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 9
                                    CharPosition : 66
                                Optional : false
                                //SetFieldTypeNode
                                    //Super -> //NonArrayFieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 9
                                            CharPosition : 66
                                    //FieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 9
                                            CharPosition : 70
                                        Optional : false
                                        //TypeFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 9
                                                    CharPosition : 70
                                            //NamespaceNameGenericParametersNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 9
                                                    CharPosition : 70
                                                //NamespaceNameNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 9
                                                        CharPosition : 70
                                                    //NamespaceNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 9
                                                            CharPosition : 70
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 9
                                                            CharPosition : 84
                                                        Name : TypeToReplace
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
                                    //TypeFieldTypeNode
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
                                                    Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedValueOrErrorTypeInImplementsList() {

        var testProgram = """
            type TestNamespace.TypeToReplace {
                testField : int32
            }
            
            type interface TestNamespace.TestTypeInterface<T> {
                testInterfaceField : T
            }
            
            type TestNamespace.TestType : TestNamespace.TestTypeInterface<valueorerror<TestNamespace.TypeToReplace>> {
                testField : valueorerror<TestNamespace.TypeToReplace>
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //Super -> //BaseParseTreeNode
                SourceFileName : FakeSourceFileName.pg
                Line : 1
                CharPosition : 4
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 23
                            Name : TypeToReplace
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 19
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 19
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 19
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 33
                            Name : TestTypeInterface
                    //GenericParametersWithBoundsNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 50
                        //GenericParameterWithBoundsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 51
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 9
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 9
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 9
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 23
                            Name : TestType
                //ImplementsListNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 9
                        CharPosition : 32
                    //NamespaceNameGenericParametersNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 9
                            CharPosition : 34
                        //NamespaceNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 34
                            //NamespaceNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 9
                                    CharPosition : 34
                                Namespace : TestNamespace
                            //NameNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 9
                                    CharPosition : 48
                                Name : TestTypeInterface
                        //GenericParametersNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 65
                            //FieldTypeNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 9
                                    CharPosition : 66
                                Optional : false
                                //ValueOrErrorFieldTypeNode
                                    //Super -> //NonArrayFieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 9
                                            CharPosition : 66
                                    //FieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 9
                                            CharPosition : 79
                                        Optional : false
                                        //TypeFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 9
                                                    CharPosition : 79
                                            //NamespaceNameGenericParametersNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 9
                                                    CharPosition : 79
                                                //NamespaceNameNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 9
                                                        CharPosition : 79
                                                    //NamespaceNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 9
                                                            CharPosition : 79
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 9
                                                            CharPosition : 93
                                                        Name : TypeToReplace
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
                                    //TypeFieldTypeNode
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
                                                    Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedArrayTypeInImplementsList() {

        var testProgram = """
            type TestNamespace.TypeToReplace {
                testField : int32
            }
            
            type interface TestNamespace.TestTypeInterface<T> {
                testInterfaceField : T
            }
            
            type TestNamespace.TestType : TestNamespace.TestTypeInterface<TestNamespace.TypeToReplace[][]> {
                testField : TestNamespace.TypeToReplace[][]
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //Super -> //BaseParseTreeNode
                SourceFileName : FakeSourceFileName.pg
                Line : 1
                CharPosition : 4
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 23
                            Name : TypeToReplace
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 19
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 19
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 19
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 33
                            Name : TestTypeInterface
                    //GenericParametersWithBoundsNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 50
                        //GenericParameterWithBoundsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 51
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 9
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 9
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 9
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 23
                            Name : TestType
                //ImplementsListNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 9
                        CharPosition : 32
                    //NamespaceNameGenericParametersNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 9
                            CharPosition : 34
                        //NamespaceNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 34
                            //NamespaceNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 9
                                    CharPosition : 34
                                Namespace : TestNamespace
                            //NameNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 9
                                    CharPosition : 48
                                Name : TestTypeInterface
                        //GenericParametersNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 65
                            //FieldTypeNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : FakeSourceFileName.pg
                                    Line : 9
                                    CharPosition : 66
                                Optional : false
                                //ArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 9
                                        CharPosition : 66
                                    //TypeFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 9
                                                CharPosition : 66
                                        //NamespaceNameGenericParametersNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 9
                                                CharPosition : 66
                                            //NamespaceNameNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 9
                                                    CharPosition : 66
                                                //NamespaceNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 9
                                                        CharPosition : 66
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 9
                                                        CharPosition : 80
                                                    Name : TypeToReplace
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
                                //TypeFieldTypeNode
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
                                                Name : TypeToReplace
                                Dimensions : 2
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNonNestedTypeInVersions() {

        var testProgram = """
            type TestNamespace.TypeToReplace {
                testField : int32
            }
            
            type TestNamespace.TestType {
                version 1 {
                    testField : TestNamespace.TypeToReplace
                }
                version 2 {
                    testField : TestNamespace.TypeToReplace
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 23
                            Name : TypeToReplace
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 23
                            Name : TestType
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
                                    //TypeFieldTypeNode
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
                                                    Name : TypeToReplace
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
                                    //TypeFieldTypeNode
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
                                                    Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedMapTypeInVersions() {

        var testProgram = """
            type TestNamespace.TypeToReplace {
                testField : int32
            }
            
            type TestNamespace.Type {
                version 1 {
                    testField : map<TestNamespace.TypeToReplace, TestNamespace.TypeToReplace>
                }
                version 2 {
                    testField : map<TestNamespace.TypeToReplace, TestNamespace.TypeToReplace>
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 23
                            Name : TypeToReplace
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 23
                            Name : Type
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
                                                //TypeFieldTypeNode
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
                                                                Name : TypeToReplace
                                        Value
                                            //FieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 7
                                                    CharPosition : 57
                                                Optional : false
                                                //TypeFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 7
                                                            CharPosition : 57
                                                    //NamespaceNameGenericParametersNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 7
                                                            CharPosition : 57
                                                        //NamespaceNameNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 7
                                                                CharPosition : 57
                                                            //NamespaceNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 7
                                                                    CharPosition : 57
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 7
                                                                    CharPosition : 71
                                                                Name : TypeToReplace
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
                                                //TypeFieldTypeNode
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
                                                                Name : TypeToReplace
                                        Value
                                            //FieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 10
                                                    CharPosition : 57
                                                Optional : false
                                                //TypeFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 10
                                                            CharPosition : 57
                                                    //NamespaceNameGenericParametersNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 10
                                                            CharPosition : 57
                                                        //NamespaceNameNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 10
                                                                CharPosition : 57
                                                            //NamespaceNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 10
                                                                    CharPosition : 57
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 10
                                                                    CharPosition : 71
                                                                Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedSetTypeInVersions() {

        var testProgram = """
            type TestNamespace.TypeToReplace {
                testField : int32
            }
            
            type TestNamespace.TestType {
                version 1 {
                    testField : set<TestNamespace.TypeToReplace>
                }
                version 2 {
                    testField : set<TestNamespace.TypeToReplace>
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 23
                            Name : TypeToReplace
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 23
                            Name : TestType
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
                                            //TypeFieldTypeNode
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
                                                            Name : TypeToReplace
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
                                            //TypeFieldTypeNode
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
                                                            Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedValueOrErrorTypeInVersions() {

        var testProgram = """
            type TestNamespace.TypeToReplace {
                testField : int32
            }
            
            type TestNamespace.TestType {
                version 1 {
                    testField : valueorerror<TestNamespace.TypeToReplace>
                }
                version 2 {
                    testField : valueorerror<TestNamespace.TypeToReplace>
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 23
                            Name : TypeToReplace
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 23
                            Name : TestType
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
                                            //TypeFieldTypeNode
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
                                                            Name : TypeToReplace
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
                                            //TypeFieldTypeNode
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
                                                            Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedArrayTypeInVersions() {

        var testProgram = """
            type TestNamespace.TypeToReplace {
                testField : int32
            }
            
            type TestNamespace.TestType {
                version 1 {
                    testField : TestNamespace.TypeToReplace[][]
                }
                version 2 {
                    testField : TestNamespace.TypeToReplace[][]
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 23
                            Name : TypeToReplace
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 23
                            Name : TestType
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
                                        //TypeFieldTypeNode
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
                                                        Name : TypeToReplace
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
                                        //TypeFieldTypeNode
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
                                                        Name : TypeToReplace
                                        Dimensions : 2
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNonNestedTypeInImplementsListInVersions() {

        var testProgram = """
            type TestNamespace.TypeToReplace {
                testField : int32
            }
            
            type interface TestNamespace.TestTypeInterface<T> {
                testInterfaceField : T
            }
            
            type TestNamespace.TestType {
                version 1 : TestNamespace.TestTypeInterface<TestNamespace.TypeToReplace>{
                    testField : TestNamespace.TypeToReplace
                }
                version 2 : TestNamespace.TestTypeInterface<TestNamespace.TypeToReplace>{
                    testField : TestNamespace.TypeToReplace
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 23
                            Name : TypeToReplace
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 19
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 19
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 19
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 33
                            Name : TestTypeInterface
                    //GenericParametersWithBoundsNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 50
                        //GenericParameterWithBoundsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 51
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 9
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 9
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 9
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 23
                            Name : TestType
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
                                        Name : TestTypeInterface
                                //GenericParametersNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 10
                                        CharPosition : 51
                                    //FieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 10
                                            CharPosition : 52
                                        Optional : false
                                        //TypeFieldTypeNode
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
                                                        Name : TypeToReplace
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
                                    //TypeFieldTypeNode
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
                                                    Name : TypeToReplace
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
                                        Name : TestTypeInterface
                                //GenericParametersNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 13
                                        CharPosition : 51
                                    //FieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 13
                                            CharPosition : 52
                                        Optional : false
                                        //TypeFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 13
                                                    CharPosition : 52
                                            //NamespaceNameGenericParametersNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 13
                                                    CharPosition : 52
                                                //NamespaceNameNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 13
                                                        CharPosition : 52
                                                    //NamespaceNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 13
                                                            CharPosition : 52
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 13
                                                            CharPosition : 66
                                                        Name : TypeToReplace
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
                                    //TypeFieldTypeNode
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
                                                    Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedMapTypeInImplementsListInVersions() {

        var testProgram = """
            type TestNamespace.TypeToReplace {
                testField : int32
            }
            
            type interface TestNamespace.TestTypeInterface<T> {
                testInterfaceField : T
            }
            
            type TestNamespace.Type {
                version 1 : TestNamespace.TestTypeInterface<map<TestNamespace.TypeToReplace, TestNamespace.TypeToReplace>> {
                    testField : map<TestNamespace.TypeToReplace, TestNamespace.TypeToReplace>
                }
                version 2 : TestNamespace.TestTypeInterface<map<TestNamespace.TypeToReplace, TestNamespace.TypeToReplace>> {
                    testField : map<TestNamespace.TypeToReplace, TestNamespace.TypeToReplace>
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 23
                            Name : TypeToReplace
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 19
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 19
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 19
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 33
                            Name : TestTypeInterface
                    //GenericParametersWithBoundsNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 50
                        //GenericParameterWithBoundsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 51
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 9
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 9
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 9
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 23
                            Name : Type
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
                                        Name : TestTypeInterface
                                //GenericParametersNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 10
                                        CharPosition : 51
                                    //FieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 10
                                            CharPosition : 52
                                        Optional : false
                                        //MapFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 10
                                                    CharPosition : 52
                                            Key
                                                //FieldTypeNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 10
                                                        CharPosition : 56
                                                    Optional : false
                                                    //TypeFieldTypeNode
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
                                                                    Name : TypeToReplace
                                            Value
                                                //FieldTypeNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 10
                                                        CharPosition : 85
                                                    Optional : false
                                                    //TypeFieldTypeNode
                                                        //Super -> //NonArrayFieldTypeNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 10
                                                                CharPosition : 85
                                                        //NamespaceNameGenericParametersNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 10
                                                                CharPosition : 85
                                                            //NamespaceNameNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 10
                                                                    CharPosition : 85
                                                                //NamespaceNode
                                                                    //Super -> //BaseParseTreeNode
                                                                        SourceFileName : FakeSourceFileName.pg
                                                                        Line : 10
                                                                        CharPosition : 85
                                                                    Namespace : TestNamespace
                                                                //NameNode
                                                                    //Super -> //BaseParseTreeNode
                                                                        SourceFileName : FakeSourceFileName.pg
                                                                        Line : 10
                                                                        CharPosition : 99
                                                                    Name : TypeToReplace
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
                                                //TypeFieldTypeNode
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
                                                                Name : TypeToReplace
                                        Value
                                            //FieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 11
                                                    CharPosition : 57
                                                Optional : false
                                                //TypeFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 11
                                                            CharPosition : 57
                                                    //NamespaceNameGenericParametersNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 11
                                                            CharPosition : 57
                                                        //NamespaceNameNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 11
                                                                CharPosition : 57
                                                            //NamespaceNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 11
                                                                    CharPosition : 57
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 11
                                                                    CharPosition : 71
                                                                Name : TypeToReplace
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
                                        Name : TestTypeInterface
                                //GenericParametersNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 13
                                        CharPosition : 51
                                    //FieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 13
                                            CharPosition : 52
                                        Optional : false
                                        //MapFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 13
                                                    CharPosition : 52
                                            Key
                                                //FieldTypeNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 13
                                                        CharPosition : 56
                                                    Optional : false
                                                    //TypeFieldTypeNode
                                                        //Super -> //NonArrayFieldTypeNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 13
                                                                CharPosition : 56
                                                        //NamespaceNameGenericParametersNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 13
                                                                CharPosition : 56
                                                            //NamespaceNameNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 13
                                                                    CharPosition : 56
                                                                //NamespaceNode
                                                                    //Super -> //BaseParseTreeNode
                                                                        SourceFileName : FakeSourceFileName.pg
                                                                        Line : 13
                                                                        CharPosition : 56
                                                                    Namespace : TestNamespace
                                                                //NameNode
                                                                    //Super -> //BaseParseTreeNode
                                                                        SourceFileName : FakeSourceFileName.pg
                                                                        Line : 13
                                                                        CharPosition : 70
                                                                    Name : TypeToReplace
                                            Value
                                                //FieldTypeNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 13
                                                        CharPosition : 85
                                                    Optional : false
                                                    //TypeFieldTypeNode
                                                        //Super -> //NonArrayFieldTypeNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 13
                                                                CharPosition : 85
                                                        //NamespaceNameGenericParametersNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 13
                                                                CharPosition : 85
                                                            //NamespaceNameNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 13
                                                                    CharPosition : 85
                                                                //NamespaceNode
                                                                    //Super -> //BaseParseTreeNode
                                                                        SourceFileName : FakeSourceFileName.pg
                                                                        Line : 13
                                                                        CharPosition : 85
                                                                    Namespace : TestNamespace
                                                                //NameNode
                                                                    //Super -> //BaseParseTreeNode
                                                                        SourceFileName : FakeSourceFileName.pg
                                                                        Line : 13
                                                                        CharPosition : 99
                                                                    Name : TypeToReplace
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
                                    //MapFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 14
                                                CharPosition : 24
                                        Key
                                            //FieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 14
                                                    CharPosition : 28
                                                Optional : false
                                                //TypeFieldTypeNode
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
                                                                Name : TypeToReplace
                                        Value
                                            //FieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 14
                                                    CharPosition : 57
                                                Optional : false
                                                //TypeFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 14
                                                            CharPosition : 57
                                                    //NamespaceNameGenericParametersNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 14
                                                            CharPosition : 57
                                                        //NamespaceNameNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 14
                                                                CharPosition : 57
                                                            //NamespaceNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 14
                                                                    CharPosition : 57
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 14
                                                                    CharPosition : 71
                                                                Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedSetTypeInImplementsListInVersions() {

        var testProgram = """
            type TestNamespace.TypeToReplace {
                testField : int32
            }
            
            type interface TestNamespace.TestTypeInterface<T> {
                testInterfaceField : T
            }
            
            type TestNamespace.TestType {
                version 1 : TestNamespace.TestTypeInterface<set<TestNamespace.TypeToReplace>> {
                    testField : set<TestNamespace.TypeToReplace>
                }
                version 2 : TestNamespace.TestTypeInterface<set<TestNamespace.TypeToReplace>> {
                    testField : set<TestNamespace.TypeToReplace>
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 23
                            Name : TypeToReplace
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 19
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 19
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 19
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 33
                            Name : TestTypeInterface
                    //GenericParametersWithBoundsNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 50
                        //GenericParameterWithBoundsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 51
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 9
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 9
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 9
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 23
                            Name : TestType
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
                                        Name : TestTypeInterface
                                //GenericParametersNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 10
                                        CharPosition : 51
                                    //FieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 10
                                            CharPosition : 52
                                        Optional : false
                                        //SetFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 10
                                                    CharPosition : 52
                                            //FieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 10
                                                    CharPosition : 56
                                                Optional : false
                                                //TypeFieldTypeNode
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
                                                                Name : TypeToReplace
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
                                            //TypeFieldTypeNode
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
                                                            Name : TypeToReplace
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
                                        Name : TestTypeInterface
                                //GenericParametersNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 13
                                        CharPosition : 51
                                    //FieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 13
                                            CharPosition : 52
                                        Optional : false
                                        //SetFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 13
                                                    CharPosition : 52
                                            //FieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 13
                                                    CharPosition : 56
                                                Optional : false
                                                //TypeFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 13
                                                            CharPosition : 56
                                                    //NamespaceNameGenericParametersNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 13
                                                            CharPosition : 56
                                                        //NamespaceNameNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 13
                                                                CharPosition : 56
                                                            //NamespaceNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 13
                                                                    CharPosition : 56
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 13
                                                                    CharPosition : 70
                                                                Name : TypeToReplace
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
                                            //TypeFieldTypeNode
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
                                                            Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedValueOrErrorTypeInImplementsListInVersions() {

        var testProgram = """
            type TestNamespace.TypeToReplace {
                testField : int32
            }
            
            type interface TestNamespace.TestTypeInterface<T> {
                testInterfaceField : T
            }
            
            type TestNamespace.TestType {
                version 1 : TestNamespace.TestTypeInterface<valueorerror<TestNamespace.TypeToReplace>> {
                    testField : valueorerror<TestNamespace.TypeToReplace>
                }
                version 2 : TestNamespace.TestTypeInterface<valueorerror<TestNamespace.TypeToReplace>> {
                    testField : valueorerror<TestNamespace.TypeToReplace>
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 23
                            Name : TypeToReplace
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 19
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 19
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 19
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 33
                            Name : TestTypeInterface
                    //GenericParametersWithBoundsNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 50
                        //GenericParameterWithBoundsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 51
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 9
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 9
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 9
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 23
                            Name : TestType
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
                                        Name : TestTypeInterface
                                //GenericParametersNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 10
                                        CharPosition : 51
                                    //FieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 10
                                            CharPosition : 52
                                        Optional : false
                                        //ValueOrErrorFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 10
                                                    CharPosition : 52
                                            //FieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 10
                                                    CharPosition : 65
                                                Optional : false
                                                //TypeFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 10
                                                            CharPosition : 65
                                                    //NamespaceNameGenericParametersNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 10
                                                            CharPosition : 65
                                                        //NamespaceNameNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 10
                                                                CharPosition : 65
                                                            //NamespaceNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 10
                                                                    CharPosition : 65
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 10
                                                                    CharPosition : 79
                                                                Name : TypeToReplace
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
                                            //TypeFieldTypeNode
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
                                                            Name : TypeToReplace
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
                                        Name : TestTypeInterface
                                //GenericParametersNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 13
                                        CharPosition : 51
                                    //FieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 13
                                            CharPosition : 52
                                        Optional : false
                                        //ValueOrErrorFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 13
                                                    CharPosition : 52
                                            //FieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : FakeSourceFileName.pg
                                                    Line : 13
                                                    CharPosition : 65
                                                Optional : false
                                                //TypeFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 13
                                                            CharPosition : 65
                                                    //NamespaceNameGenericParametersNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 13
                                                            CharPosition : 65
                                                        //NamespaceNameNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 13
                                                                CharPosition : 65
                                                            //NamespaceNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 13
                                                                    CharPosition : 65
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                //Super -> //BaseParseTreeNode
                                                                    SourceFileName : FakeSourceFileName.pg
                                                                    Line : 13
                                                                    CharPosition : 79
                                                                Name : TypeToReplace
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
                                            //TypeFieldTypeNode
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
                                                            Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedArrayTypeInImplementsListInVersions() {

        var testProgram = """
            type TestNamespace.TypeToReplace {
                testField : int32
            }
            
            type interface TestNamespace.TestTypeInterface<T> {
                testInterfaceField : T
            }
            
            type TestNamespace.TestType {
                version 1 : TestNamespace.TestTypeInterface<TestNamespace.TypeToReplace[][]> {
                    testField : TestNamespace.TypeToReplace[][]
                }
                version 2 : TestNamespace.TestTypeInterface<TestNamespace.TypeToReplace[][]> {
                    testField : TestNamespace.TypeToReplace[][]
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 1
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 1
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 1
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 1
                                CharPosition : 23
                            Name : TypeToReplace
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 5
                    CharPosition : 4
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 5
                        CharPosition : 19
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 19
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 19
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 33
                            Name : TestTypeInterface
                    //GenericParametersWithBoundsNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 5
                            CharPosition : 50
                        //GenericParameterWithBoundsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 5
                                CharPosition : 51
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
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : FakeSourceFileName.pg
                    Line : 9
                    CharPosition : 4
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : FakeSourceFileName.pg
                        Line : 9
                        CharPosition : 9
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : FakeSourceFileName.pg
                            Line : 9
                            CharPosition : 9
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 9
                            Namespace : TestNamespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : FakeSourceFileName.pg
                                Line : 9
                                CharPosition : 23
                            Name : TestType
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
                                        Name : TestTypeInterface
                                //GenericParametersNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 10
                                        CharPosition : 51
                                    //FieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 10
                                            CharPosition : 52
                                        Optional : false
                                        //ArrayFieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 10
                                                CharPosition : 52
                                            //TypeFieldTypeNode
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
                                                            Name : TypeToReplace
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
                                        //TypeFieldTypeNode
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
                                                        Name : TypeToReplace
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
                                        Name : TestTypeInterface
                                //GenericParametersNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : FakeSourceFileName.pg
                                        Line : 13
                                        CharPosition : 51
                                    //FieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : FakeSourceFileName.pg
                                            Line : 13
                                            CharPosition : 52
                                        Optional : false
                                        //ArrayFieldTypeNode
                                            //Super -> //BaseParseTreeNode
                                                SourceFileName : FakeSourceFileName.pg
                                                Line : 13
                                                CharPosition : 52
                                            //TypeFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 13
                                                        CharPosition : 52
                                                //NamespaceNameGenericParametersNode
                                                    //Super -> //BaseParseTreeNode
                                                        SourceFileName : FakeSourceFileName.pg
                                                        Line : 13
                                                        CharPosition : 52
                                                    //NamespaceNameNode
                                                        //Super -> //BaseParseTreeNode
                                                            SourceFileName : FakeSourceFileName.pg
                                                            Line : 13
                                                            CharPosition : 52
                                                        //NamespaceNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 13
                                                                CharPosition : 52
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            //Super -> //BaseParseTreeNode
                                                                SourceFileName : FakeSourceFileName.pg
                                                                Line : 13
                                                                CharPosition : 66
                                                            Name : TypeToReplace
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
                                        //TypeFieldTypeNode
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
                                                        Name : TypeToReplace
                                        Dimensions : 2
        """;
        assertEquals(expectedToStringOutput, fileNode.toString(), "Unexpected toString output");
    }

}