package com.kdsc.protogen.parsetreepostprocessing;

import com.kdsc.protogen.BaseCompilerTest;
import com.kdsc.protogen.parsetreenodes.ParseTreeFormattedStringOptions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TestUndetectableNodeReplacerWithTypeVersions extends BaseCompilerTest {

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
            //TypeNode
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
                                //Super -> //NonArrayFieldTypeNode
            //TypeNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestType
                //VersionsNode
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 1
                        //FieldsNode
                            //FieldNode
                                //FieldNameNode
                                    FieldName : testField
                                //FieldTypeNode
                                    Optional : false
                                    //TypeFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //NamespaceNameGenericParametersNode
                                            //NamespaceNameNode
                                                //NamespaceNode
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    Name : TypeToReplace
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 2
                        //FieldsNode
                            //FieldNode
                                //FieldNameNode
                                    FieldName : testField
                                //FieldTypeNode
                                    Optional : false
                                    //TypeFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //NamespaceNameGenericParametersNode
                                            //NamespaceNameNode
                                                //NamespaceNode
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
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
            //TypeNode
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
                                //Super -> //NonArrayFieldTypeNode
            //TypeNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : Type
                //VersionsNode
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 1
                        //FieldsNode
                            //FieldNode
                                //FieldNameNode
                                    FieldName : testField
                                //FieldTypeNode
                                    Optional : false
                                    //MapFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        Key
                                            //FieldTypeNode
                                                Optional : false
                                                //TypeFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
                                                        //NamespaceNameNode
                                                            //NamespaceNode
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                Name : TypeToReplace
                                        Value
                                            //FieldTypeNode
                                                Optional : false
                                                //TypeFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
                                                        //NamespaceNameNode
                                                            //NamespaceNode
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                Name : TypeToReplace
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 2
                        //FieldsNode
                            //FieldNode
                                //FieldNameNode
                                    FieldName : testField
                                //FieldTypeNode
                                    Optional : false
                                    //MapFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        Key
                                            //FieldTypeNode
                                                Optional : false
                                                //TypeFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
                                                        //NamespaceNameNode
                                                            //NamespaceNode
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                Name : TypeToReplace
                                        Value
                                            //FieldTypeNode
                                                Optional : false
                                                //TypeFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
                                                        //NamespaceNameNode
                                                            //NamespaceNode
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
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
            //TypeNode
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
                                //Super -> //NonArrayFieldTypeNode
            //TypeNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestType
                //VersionsNode
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 1
                        //FieldsNode
                            //FieldNode
                                //FieldNameNode
                                    FieldName : testField
                                //FieldTypeNode
                                    Optional : false
                                    //SetFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //FieldTypeNode
                                            Optional : false
                                            //TypeFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : TypeToReplace
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 2
                        //FieldsNode
                            //FieldNode
                                //FieldNameNode
                                    FieldName : testField
                                //FieldTypeNode
                                    Optional : false
                                    //SetFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //FieldTypeNode
                                            Optional : false
                                            //TypeFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedListTypeInVersions() {

        var testProgram = """
            type TestNamespace.TypeToReplace {
                testField : int32
            }
            
            type TestNamespace.TestType {
                version 1 {
                    testField : list<TestNamespace.TypeToReplace>
                }
                version 2 {
                    testField : list<TestNamespace.TypeToReplace>
                }
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //TypeNode
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
                                //Super -> //NonArrayFieldTypeNode
            //TypeNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestType
                //VersionsNode
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 1
                        //FieldsNode
                            //FieldNode
                                //FieldNameNode
                                    FieldName : testField
                                //FieldTypeNode
                                    Optional : false
                                    //ListFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //FieldTypeNode
                                            Optional : false
                                            //TypeFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : TypeToReplace
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 2
                        //FieldsNode
                            //FieldNode
                                //FieldNameNode
                                    FieldName : testField
                                //FieldTypeNode
                                    Optional : false
                                    //ListFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //FieldTypeNode
                                            Optional : false
                                            //TypeFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
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
            //TypeNode
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
                                //Super -> //NonArrayFieldTypeNode
            //TypeNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestType
                //VersionsNode
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 1
                        //FieldsNode
                            //FieldNode
                                //FieldNameNode
                                    FieldName : testField
                                //FieldTypeNode
                                    Optional : false
                                    //ValueOrErrorFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //FieldTypeNode
                                            Optional : false
                                            //TypeFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : TypeToReplace
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 2
                        //FieldsNode
                            //FieldNode
                                //FieldNameNode
                                    FieldName : testField
                                //FieldTypeNode
                                    Optional : false
                                    //ValueOrErrorFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //FieldTypeNode
                                            Optional : false
                                            //TypeFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
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
            //TypeNode
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
                                //Super -> //NonArrayFieldTypeNode
            //TypeNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestType
                //VersionsNode
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 1
                        //FieldsNode
                            //FieldNode
                                //FieldNameNode
                                    FieldName : testField
                                //FieldTypeNode
                                    Optional : false
                                    //ArrayFieldTypeNode
                                        //TypeFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //NamespaceNameGenericParametersNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : TypeToReplace
                                        Dimensions : 2
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 2
                        //FieldsNode
                            //FieldNode
                                //FieldNameNode
                                    FieldName : testField
                                //FieldTypeNode
                                    Optional : false
                                    //ArrayFieldTypeNode
                                        //TypeFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //NamespaceNameGenericParametersNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : TypeToReplace
                                        Dimensions : 2
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
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
            //TypeNode
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
                                //Super -> //NonArrayFieldTypeNode
            //TypeNode
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestTypeInterface
                    //GenericParametersWithBoundsNode
                        //GenericParameterWithBoundsNode
                            Identifier : T
                //FieldsNode
                    //FieldNode
                        //FieldNameNode
                            FieldName : testInterfaceField
                        //FieldTypeNode
                            Optional : false
                            //GenericObjectFieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                //GenericParameterNode
                                    Identifier : T
            //TypeNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestType
                //VersionsNode
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 1
                        //ImplementsListNode
                            //NamespaceNameGenericParametersNode
                                //NamespaceNameNode
                                    //NamespaceNode
                                        Namespace : TestNamespace
                                    //NameNode
                                        Name : TestTypeInterface
                                //GenericParametersNode
                                    //FieldTypeNode
                                        Optional : false
                                        //TypeFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //NamespaceNameGenericParametersNode
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
                                    //TypeFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //NamespaceNameGenericParametersNode
                                            //NamespaceNameNode
                                                //NamespaceNode
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    Name : TypeToReplace
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 2
                        //ImplementsListNode
                            //NamespaceNameGenericParametersNode
                                //NamespaceNameNode
                                    //NamespaceNode
                                        Namespace : TestNamespace
                                    //NameNode
                                        Name : TestTypeInterface
                                //GenericParametersNode
                                    //FieldTypeNode
                                        Optional : false
                                        //TypeFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //NamespaceNameGenericParametersNode
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
                                    //TypeFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //NamespaceNameGenericParametersNode
                                            //NamespaceNameNode
                                                //NamespaceNode
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
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
            //TypeNode
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
                                //Super -> //NonArrayFieldTypeNode
            //TypeNode
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestTypeInterface
                    //GenericParametersWithBoundsNode
                        //GenericParameterWithBoundsNode
                            Identifier : T
                //FieldsNode
                    //FieldNode
                        //FieldNameNode
                            FieldName : testInterfaceField
                        //FieldTypeNode
                            Optional : false
                            //GenericObjectFieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                //GenericParameterNode
                                    Identifier : T
            //TypeNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : Type
                //VersionsNode
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 1
                        //ImplementsListNode
                            //NamespaceNameGenericParametersNode
                                //NamespaceNameNode
                                    //NamespaceNode
                                        Namespace : TestNamespace
                                    //NameNode
                                        Name : TestTypeInterface
                                //GenericParametersNode
                                    //FieldTypeNode
                                        Optional : false
                                        //MapFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            Key
                                                //FieldTypeNode
                                                    Optional : false
                                                    //TypeFieldTypeNode
                                                        //Super -> //NonArrayFieldTypeNode
                                                        //NamespaceNameGenericParametersNode
                                                            //NamespaceNameNode
                                                                //NamespaceNode
                                                                    Namespace : TestNamespace
                                                                //NameNode
                                                                    Name : TypeToReplace
                                            Value
                                                //FieldTypeNode
                                                    Optional : false
                                                    //TypeFieldTypeNode
                                                        //Super -> //NonArrayFieldTypeNode
                                                        //NamespaceNameGenericParametersNode
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
                                    //MapFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        Key
                                            //FieldTypeNode
                                                Optional : false
                                                //TypeFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
                                                        //NamespaceNameNode
                                                            //NamespaceNode
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                Name : TypeToReplace
                                        Value
                                            //FieldTypeNode
                                                Optional : false
                                                //TypeFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
                                                        //NamespaceNameNode
                                                            //NamespaceNode
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                Name : TypeToReplace
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 2
                        //ImplementsListNode
                            //NamespaceNameGenericParametersNode
                                //NamespaceNameNode
                                    //NamespaceNode
                                        Namespace : TestNamespace
                                    //NameNode
                                        Name : TestTypeInterface
                                //GenericParametersNode
                                    //FieldTypeNode
                                        Optional : false
                                        //MapFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            Key
                                                //FieldTypeNode
                                                    Optional : false
                                                    //TypeFieldTypeNode
                                                        //Super -> //NonArrayFieldTypeNode
                                                        //NamespaceNameGenericParametersNode
                                                            //NamespaceNameNode
                                                                //NamespaceNode
                                                                    Namespace : TestNamespace
                                                                //NameNode
                                                                    Name : TypeToReplace
                                            Value
                                                //FieldTypeNode
                                                    Optional : false
                                                    //TypeFieldTypeNode
                                                        //Super -> //NonArrayFieldTypeNode
                                                        //NamespaceNameGenericParametersNode
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
                                    //MapFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        Key
                                            //FieldTypeNode
                                                Optional : false
                                                //TypeFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
                                                        //NamespaceNameNode
                                                            //NamespaceNode
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                Name : TypeToReplace
                                        Value
                                            //FieldTypeNode
                                                Optional : false
                                                //TypeFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
                                                        //NamespaceNameNode
                                                            //NamespaceNode
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
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
            //TypeNode
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
                                //Super -> //NonArrayFieldTypeNode
            //TypeNode
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestTypeInterface
                    //GenericParametersWithBoundsNode
                        //GenericParameterWithBoundsNode
                            Identifier : T
                //FieldsNode
                    //FieldNode
                        //FieldNameNode
                            FieldName : testInterfaceField
                        //FieldTypeNode
                            Optional : false
                            //GenericObjectFieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                //GenericParameterNode
                                    Identifier : T
            //TypeNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestType
                //VersionsNode
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 1
                        //ImplementsListNode
                            //NamespaceNameGenericParametersNode
                                //NamespaceNameNode
                                    //NamespaceNode
                                        Namespace : TestNamespace
                                    //NameNode
                                        Name : TestTypeInterface
                                //GenericParametersNode
                                    //FieldTypeNode
                                        Optional : false
                                        //SetFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //FieldTypeNode
                                                Optional : false
                                                //TypeFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
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
                                    //SetFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //FieldTypeNode
                                            Optional : false
                                            //TypeFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : TypeToReplace
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 2
                        //ImplementsListNode
                            //NamespaceNameGenericParametersNode
                                //NamespaceNameNode
                                    //NamespaceNode
                                        Namespace : TestNamespace
                                    //NameNode
                                        Name : TestTypeInterface
                                //GenericParametersNode
                                    //FieldTypeNode
                                        Optional : false
                                        //SetFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //FieldTypeNode
                                                Optional : false
                                                //TypeFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
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
                                    //SetFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //FieldTypeNode
                                            Optional : false
                                            //TypeFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedListTypeInImplementsListInVersions() {

        var testProgram = """
            type TestNamespace.TypeToReplace {
                testField : int32
            }
            
            type interface TestNamespace.TestTypeInterface<T> {
                testInterfaceField : T
            }
            
            type TestNamespace.TestType {
                version 1 : TestNamespace.TestTypeInterface<list<TestNamespace.TypeToReplace>> {
                    testField : list<TestNamespace.TypeToReplace>
                }
                version 2 : TestNamespace.TestTypeInterface<list<TestNamespace.TypeToReplace>> {
                    testField : list<TestNamespace.TypeToReplace>
                }
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //TypeNode
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
                                //Super -> //NonArrayFieldTypeNode
            //TypeNode
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestTypeInterface
                    //GenericParametersWithBoundsNode
                        //GenericParameterWithBoundsNode
                            Identifier : T
                //FieldsNode
                    //FieldNode
                        //FieldNameNode
                            FieldName : testInterfaceField
                        //FieldTypeNode
                            Optional : false
                            //GenericObjectFieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                //GenericParameterNode
                                    Identifier : T
            //TypeNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestType
                //VersionsNode
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 1
                        //ImplementsListNode
                            //NamespaceNameGenericParametersNode
                                //NamespaceNameNode
                                    //NamespaceNode
                                        Namespace : TestNamespace
                                    //NameNode
                                        Name : TestTypeInterface
                                //GenericParametersNode
                                    //FieldTypeNode
                                        Optional : false
                                        //ListFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //FieldTypeNode
                                                Optional : false
                                                //TypeFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
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
                                    //ListFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //FieldTypeNode
                                            Optional : false
                                            //TypeFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : TypeToReplace
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 2
                        //ImplementsListNode
                            //NamespaceNameGenericParametersNode
                                //NamespaceNameNode
                                    //NamespaceNode
                                        Namespace : TestNamespace
                                    //NameNode
                                        Name : TestTypeInterface
                                //GenericParametersNode
                                    //FieldTypeNode
                                        Optional : false
                                        //ListFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //FieldTypeNode
                                                Optional : false
                                                //TypeFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
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
                                    //ListFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //FieldTypeNode
                                            Optional : false
                                            //TypeFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
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
            //TypeNode
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
                                //Super -> //NonArrayFieldTypeNode
            //TypeNode
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestTypeInterface
                    //GenericParametersWithBoundsNode
                        //GenericParameterWithBoundsNode
                            Identifier : T
                //FieldsNode
                    //FieldNode
                        //FieldNameNode
                            FieldName : testInterfaceField
                        //FieldTypeNode
                            Optional : false
                            //GenericObjectFieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                //GenericParameterNode
                                    Identifier : T
            //TypeNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestType
                //VersionsNode
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 1
                        //ImplementsListNode
                            //NamespaceNameGenericParametersNode
                                //NamespaceNameNode
                                    //NamespaceNode
                                        Namespace : TestNamespace
                                    //NameNode
                                        Name : TestTypeInterface
                                //GenericParametersNode
                                    //FieldTypeNode
                                        Optional : false
                                        //ValueOrErrorFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //FieldTypeNode
                                                Optional : false
                                                //TypeFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
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
                                    //ValueOrErrorFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //FieldTypeNode
                                            Optional : false
                                            //TypeFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : TypeToReplace
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 2
                        //ImplementsListNode
                            //NamespaceNameGenericParametersNode
                                //NamespaceNameNode
                                    //NamespaceNode
                                        Namespace : TestNamespace
                                    //NameNode
                                        Name : TestTypeInterface
                                //GenericParametersNode
                                    //FieldTypeNode
                                        Optional : false
                                        //ValueOrErrorFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //FieldTypeNode
                                                Optional : false
                                                //TypeFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
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
                                    //ValueOrErrorFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //FieldTypeNode
                                            Optional : false
                                            //TypeFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
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
            //TypeNode
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
                                //Super -> //NonArrayFieldTypeNode
            //TypeNode
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestTypeInterface
                    //GenericParametersWithBoundsNode
                        //GenericParameterWithBoundsNode
                            Identifier : T
                //FieldsNode
                    //FieldNode
                        //FieldNameNode
                            FieldName : testInterfaceField
                        //FieldTypeNode
                            Optional : false
                            //GenericObjectFieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                //GenericParameterNode
                                    Identifier : T
            //TypeNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestType
                //VersionsNode
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 1
                        //ImplementsListNode
                            //NamespaceNameGenericParametersNode
                                //NamespaceNameNode
                                    //NamespaceNode
                                        Namespace : TestNamespace
                                    //NameNode
                                        Name : TestTypeInterface
                                //GenericParametersNode
                                    //FieldTypeNode
                                        Optional : false
                                        //ArrayFieldTypeNode
                                            //TypeFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : TypeToReplace
                                            Dimensions : 2
                        //FieldsNode
                            //FieldNode
                                //FieldNameNode
                                    FieldName : testField
                                //FieldTypeNode
                                    Optional : false
                                    //ArrayFieldTypeNode
                                        //TypeFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //NamespaceNameGenericParametersNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : TypeToReplace
                                        Dimensions : 2
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 2
                        //ImplementsListNode
                            //NamespaceNameGenericParametersNode
                                //NamespaceNameNode
                                    //NamespaceNode
                                        Namespace : TestNamespace
                                    //NameNode
                                        Name : TestTypeInterface
                                //GenericParametersNode
                                    //FieldTypeNode
                                        Optional : false
                                        //ArrayFieldTypeNode
                                            //TypeFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : TypeToReplace
                                            Dimensions : 2
                        //FieldsNode
                            //FieldNode
                                //FieldNameNode
                                    FieldName : testField
                                //FieldTypeNode
                                    Optional : false
                                    //ArrayFieldTypeNode
                                        //TypeFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //NamespaceNameGenericParametersNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : TypeToReplace
                                        Dimensions : 2
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNonNestedTypeInGenericParameterBounds() {

        var testProgram = """
            type TestNamespace.TypeToReplace {
                testField : int32
            }
            
            type TestNamespace.TestType {
                version 1 <T : TestNamespace.TypeToReplace>
                version 2 <T : TestNamespace.TypeToReplace>
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //TypeNode
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
                                //Super -> //NonArrayFieldTypeNode
            //TypeNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestType
                //VersionsNode
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 1
                        //GenericParametersWithBoundsNode
                            //GenericParameterWithBoundsNode
                                Identifier : T
                                //FieldTypeNode
                                    Optional : false
                                    //TypeFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //NamespaceNameGenericParametersNode
                                            //NamespaceNameNode
                                                //NamespaceNode
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    Name : TypeToReplace
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 2
                        //GenericParametersWithBoundsNode
                            //GenericParameterWithBoundsNode
                                Identifier : T
                                //FieldTypeNode
                                    Optional : false
                                    //TypeFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //NamespaceNameGenericParametersNode
                                            //NamespaceNameNode
                                                //NamespaceNode
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedMapTypeInGenericParameterBounds() {

        var testProgram = """
            type TestNamespace.TypeToReplace {
                testField : int32
            }
            
            type TestNamespace.Type {
                version 1 <T : map<TestNamespace.TypeToReplace, TestNamespace.TypeToReplace>>
                version 2 <T : map<TestNamespace.TypeToReplace, TestNamespace.TypeToReplace>>
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //TypeNode
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
                                //Super -> //NonArrayFieldTypeNode
            //TypeNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : Type
                //VersionsNode
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 1
                        //GenericParametersWithBoundsNode
                            //GenericParameterWithBoundsNode
                                Identifier : T
                                //FieldTypeNode
                                    Optional : false
                                    //MapFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        Key
                                            //FieldTypeNode
                                                Optional : false
                                                //TypeFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
                                                        //NamespaceNameNode
                                                            //NamespaceNode
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                Name : TypeToReplace
                                        Value
                                            //FieldTypeNode
                                                Optional : false
                                                //TypeFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
                                                        //NamespaceNameNode
                                                            //NamespaceNode
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                Name : TypeToReplace
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 2
                        //GenericParametersWithBoundsNode
                            //GenericParameterWithBoundsNode
                                Identifier : T
                                //FieldTypeNode
                                    Optional : false
                                    //MapFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        Key
                                            //FieldTypeNode
                                                Optional : false
                                                //TypeFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
                                                        //NamespaceNameNode
                                                            //NamespaceNode
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                Name : TypeToReplace
                                        Value
                                            //FieldTypeNode
                                                Optional : false
                                                //TypeFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
                                                        //NamespaceNameNode
                                                            //NamespaceNode
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedSetTypeInGenericParameterBounds() {

        var testProgram = """
            type TestNamespace.TypeToReplace {
                testField : int32
            }
            
            type TestNamespace.TestType {
                version 1 <T : set<TestNamespace.TypeToReplace>>
                version 2 <T : set<TestNamespace.TypeToReplace>>
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //TypeNode
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
                                //Super -> //NonArrayFieldTypeNode
            //TypeNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestType
                //VersionsNode
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 1
                        //GenericParametersWithBoundsNode
                            //GenericParameterWithBoundsNode
                                Identifier : T
                                //FieldTypeNode
                                    Optional : false
                                    //SetFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //FieldTypeNode
                                            Optional : false
                                            //TypeFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : TypeToReplace
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 2
                        //GenericParametersWithBoundsNode
                            //GenericParameterWithBoundsNode
                                Identifier : T
                                //FieldTypeNode
                                    Optional : false
                                    //SetFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //FieldTypeNode
                                            Optional : false
                                            //TypeFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedListTypeInGenericParameterBounds() {

        var testProgram = """
            type TestNamespace.TypeToReplace {
                testField : int32
            }
            
            type TestNamespace.TestType {
                version 1 <T : list<TestNamespace.TypeToReplace>>
                version 2 <T : list<TestNamespace.TypeToReplace>>
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //TypeNode
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
                                //Super -> //NonArrayFieldTypeNode
            //TypeNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestType
                //VersionsNode
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 1
                        //GenericParametersWithBoundsNode
                            //GenericParameterWithBoundsNode
                                Identifier : T
                                //FieldTypeNode
                                    Optional : false
                                    //ListFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //FieldTypeNode
                                            Optional : false
                                            //TypeFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : TypeToReplace
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 2
                        //GenericParametersWithBoundsNode
                            //GenericParameterWithBoundsNode
                                Identifier : T
                                //FieldTypeNode
                                    Optional : false
                                    //ListFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //FieldTypeNode
                                            Optional : false
                                            //TypeFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedValueOrErrorTypeInGenericParameterBounds() {

        var testProgram = """
            type TestNamespace.TypeToReplace {
                testField : int32
            }
            
            type TestNamespace.TestType {
                version 1 <T : valueorerror<TestNamespace.TypeToReplace>>
                version 2 <T : valueorerror<TestNamespace.TypeToReplace>>
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //TypeNode
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
                                //Super -> //NonArrayFieldTypeNode
            //TypeNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestType
                //VersionsNode
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 1
                        //GenericParametersWithBoundsNode
                            //GenericParameterWithBoundsNode
                                Identifier : T
                                //FieldTypeNode
                                    Optional : false
                                    //ValueOrErrorFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //FieldTypeNode
                                            Optional : false
                                            //TypeFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : TypeToReplace
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 2
                        //GenericParametersWithBoundsNode
                            //GenericParameterWithBoundsNode
                                Identifier : T
                                //FieldTypeNode
                                    Optional : false
                                    //ValueOrErrorFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //FieldTypeNode
                                            Optional : false
                                            //TypeFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedArrayTypeInGenericParameterBounds() {

        var testProgram = """
            type TestNamespace.TypeToReplace {
                testField : int32
            }
            
            type TestNamespace.TestType {
                version 1 <T : TestNamespace.TypeToReplace[][]>
                version 2 <T : TestNamespace.TypeToReplace[][]>
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //TypeNode
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
                                //Super -> //NonArrayFieldTypeNode
            //TypeNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestType
                //VersionsNode
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 1
                        //GenericParametersWithBoundsNode
                            //GenericParameterWithBoundsNode
                                Identifier : T
                                //FieldTypeNode
                                    Optional : false
                                    //ArrayFieldTypeNode
                                        //TypeFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //NamespaceNameGenericParametersNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : TypeToReplace
                                        Dimensions : 2
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 2
                        //GenericParametersWithBoundsNode
                            //GenericParameterWithBoundsNode
                                Identifier : T
                                //FieldTypeNode
                                    Optional : false
                                    //ArrayFieldTypeNode
                                        //TypeFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //NamespaceNameGenericParametersNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : TypeToReplace
                                        Dimensions : 2
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
    }

}