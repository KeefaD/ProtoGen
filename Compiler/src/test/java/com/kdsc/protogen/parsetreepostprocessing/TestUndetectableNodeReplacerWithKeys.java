package com.kdsc.protogen.parsetreepostprocessing;

import com.kdsc.protogen.BaseCompilerTest;
import com.kdsc.protogen.parsetreenodes.ParseTreeFormattedStringOptions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TestUndetectableNodeReplacerWithKeys extends BaseCompilerTest {

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
            //KeyNode
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
                                //Super -> //NonArrayFieldTypeNode
            //KeyNode
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
                                //Super -> //NonArrayFieldTypeNode
                                //NamespaceNameGenericParametersNode
                                    //NamespaceNameNode
                                        //NamespaceNode
                                            Namespace : TestNamespace
                                        //NameNode
                                            Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
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
            //KeyNode
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
                                //Super -> //NonArrayFieldTypeNode
            //KeyNode
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
                                //Super -> //NonArrayFieldTypeNode
                                Key
                                    //FieldTypeNode
                                        Optional : false
                                        //KeyFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //NamespaceNameGenericParametersNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : KeyToReplace
                                Value
                                    //FieldTypeNode
                                        Optional : false
                                        //KeyFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //NamespaceNameGenericParametersNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
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
            //KeyNode
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
                                //Super -> //NonArrayFieldTypeNode
            //KeyNode
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
                                //Super -> //NonArrayFieldTypeNode
                                //FieldTypeNode
                                    Optional : false
                                    //KeyFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //NamespaceNameGenericParametersNode
                                            //NamespaceNameNode
                                                //NamespaceNode
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
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
            //KeyNode
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
                                //Super -> //NonArrayFieldTypeNode
            //KeyNode
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
                                //Super -> //NonArrayFieldTypeNode
                                //FieldTypeNode
                                    Optional : false
                                    //KeyFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //NamespaceNameGenericParametersNode
                                            //NamespaceNameNode
                                                //NamespaceNode
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
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
            //KeyNode
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
                                //Super -> //NonArrayFieldTypeNode
            //KeyNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestReplaceObjectFieldTypeWithKeyFieldType
                //FieldsNode
                    //FieldNode
                        //FieldNameNode
                            FieldName : testField
                        //FieldTypeNode
                            Optional : false
                            //ArrayFieldTypeNode
                                //KeyFieldTypeNode
                                    //Super -> //NonArrayFieldTypeNode
                                    //NamespaceNameGenericParametersNode
                                        //NamespaceNameNode
                                            //NamespaceNode
                                                Namespace : TestNamespace
                                            //NameNode
                                                Name : KeyToReplace
                                Dimensions : 2
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
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
            //KeyNode
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
                                //Super -> //NonArrayFieldTypeNode
            //KeyNode
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestKeyInterface
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
            //KeyNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestKey
                //ImplementsListNode
                    //NamespaceNameGenericParametersNode
                        //NamespaceNameNode
                            //NamespaceNode
                                Namespace : TestNamespace
                            //NameNode
                                Name : TestKeyInterface
                        //GenericParametersNode
                            //FieldTypeNode
                                Optional : false
                                //KeyFieldTypeNode
                                    //Super -> //NonArrayFieldTypeNode
                                    //NamespaceNameGenericParametersNode
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
                            //KeyFieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                //NamespaceNameGenericParametersNode
                                    //NamespaceNameNode
                                        //NamespaceNode
                                            Namespace : TestNamespace
                                        //NameNode
                                            Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
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
            //KeyNode
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
                                //Super -> //NonArrayFieldTypeNode
            //KeyNode
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestKeyInterface
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
            //KeyNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestKey
                //ImplementsListNode
                    //NamespaceNameGenericParametersNode
                        //NamespaceNameNode
                            //NamespaceNode
                                Namespace : TestNamespace
                            //NameNode
                                Name : TestKeyInterface
                        //GenericParametersNode
                            //FieldTypeNode
                                Optional : false
                                //MapFieldTypeNode
                                    //Super -> //NonArrayFieldTypeNode
                                    Key
                                        //FieldTypeNode
                                            Optional : false
                                            //KeyFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : KeyToReplace
                                    Value
                                        //FieldTypeNode
                                            Optional : false
                                            //KeyFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
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
                            //MapFieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                Key
                                    //FieldTypeNode
                                        Optional : false
                                        //KeyFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //NamespaceNameGenericParametersNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : KeyToReplace
                                Value
                                    //FieldTypeNode
                                        Optional : false
                                        //KeyFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //NamespaceNameGenericParametersNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
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
            //KeyNode
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
                                //Super -> //NonArrayFieldTypeNode
            //KeyNode
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestKeyInterface
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
            //KeyNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestKey
                //ImplementsListNode
                    //NamespaceNameGenericParametersNode
                        //NamespaceNameNode
                            //NamespaceNode
                                Namespace : TestNamespace
                            //NameNode
                                Name : TestKeyInterface
                        //GenericParametersNode
                            //FieldTypeNode
                                Optional : false
                                //SetFieldTypeNode
                                    //Super -> //NonArrayFieldTypeNode
                                    //FieldTypeNode
                                        Optional : false
                                        //KeyFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //NamespaceNameGenericParametersNode
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
                            //SetFieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                //FieldTypeNode
                                    Optional : false
                                    //KeyFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //NamespaceNameGenericParametersNode
                                            //NamespaceNameNode
                                                //NamespaceNode
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
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
            //KeyNode
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
                                //Super -> //NonArrayFieldTypeNode
            //KeyNode
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestKeyInterface
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
            //KeyNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestKey
                //ImplementsListNode
                    //NamespaceNameGenericParametersNode
                        //NamespaceNameNode
                            //NamespaceNode
                                Namespace : TestNamespace
                            //NameNode
                                Name : TestKeyInterface
                        //GenericParametersNode
                            //FieldTypeNode
                                Optional : false
                                //ValueOrErrorFieldTypeNode
                                    //Super -> //NonArrayFieldTypeNode
                                    //FieldTypeNode
                                        Optional : false
                                        //KeyFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //NamespaceNameGenericParametersNode
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
                            //ValueOrErrorFieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                //FieldTypeNode
                                    Optional : false
                                    //KeyFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //NamespaceNameGenericParametersNode
                                            //NamespaceNameNode
                                                //NamespaceNode
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
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
            //KeyNode
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
                                //Super -> //NonArrayFieldTypeNode
            //KeyNode
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestKeyInterface
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
            //KeyNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestReplaceObjectFieldTypeWithKeyFieldType
                //ImplementsListNode
                    //NamespaceNameGenericParametersNode
                        //NamespaceNameNode
                            //NamespaceNode
                                Namespace : TestNamespace
                            //NameNode
                                Name : TestKeyInterface
                        //GenericParametersNode
                            //FieldTypeNode
                                Optional : false
                                //ArrayFieldTypeNode
                                    //KeyFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //NamespaceNameGenericParametersNode
                                            //NamespaceNameNode
                                                //NamespaceNode
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    Name : KeyToReplace
                                    Dimensions : 2
                //FieldsNode
                    //FieldNode
                        //FieldNameNode
                            FieldName : testField
                        //FieldTypeNode
                            Optional : false
                            //ArrayFieldTypeNode
                                //KeyFieldTypeNode
                                    //Super -> //NonArrayFieldTypeNode
                                    //NamespaceNameGenericParametersNode
                                        //NamespaceNameNode
                                            //NamespaceNode
                                                Namespace : TestNamespace
                                            //NameNode
                                                Name : KeyToReplace
                                Dimensions : 2
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
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
            //KeyNode
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
                                //Super -> //NonArrayFieldTypeNode
            //KeyNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestKey
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
                                    //KeyFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //NamespaceNameGenericParametersNode
                                            //NamespaceNameNode
                                                //NamespaceNode
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    Name : KeyToReplace
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 2
                        //FieldsNode
                            //FieldNode
                                //FieldNameNode
                                    FieldName : testField
                                //FieldTypeNode
                                    Optional : false
                                    //KeyFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //NamespaceNameGenericParametersNode
                                            //NamespaceNameNode
                                                //NamespaceNode
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
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
            //KeyNode
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
                                //Super -> //NonArrayFieldTypeNode
            //KeyNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestKey
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
                                                //KeyFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
                                                        //NamespaceNameNode
                                                            //NamespaceNode
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                Name : KeyToReplace
                                        Value
                                            //FieldTypeNode
                                                Optional : false
                                                //KeyFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
                                                        //NamespaceNameNode
                                                            //NamespaceNode
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                Name : KeyToReplace
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
                                                //KeyFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
                                                        //NamespaceNameNode
                                                            //NamespaceNode
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                Name : KeyToReplace
                                        Value
                                            //FieldTypeNode
                                                Optional : false
                                                //KeyFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
                                                        //NamespaceNameNode
                                                            //NamespaceNode
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
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
            //KeyNode
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
                                //Super -> //NonArrayFieldTypeNode
            //KeyNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestKey
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
                                            //KeyFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : KeyToReplace
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
                                            //KeyFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
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
            //KeyNode
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
                                //Super -> //NonArrayFieldTypeNode
            //KeyNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestKey
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
                                            //KeyFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : KeyToReplace
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
                                            //KeyFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
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
            //KeyNode
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
                                //Super -> //NonArrayFieldTypeNode
            //KeyNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestReplaceObjectFieldTypeWithKeyFieldType
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
                                        //KeyFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //NamespaceNameGenericParametersNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : KeyToReplace
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
                                        //KeyFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //NamespaceNameGenericParametersNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : KeyToReplace
                                        Dimensions : 2
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
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
            //KeyNode
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
                                //Super -> //NonArrayFieldTypeNode
            //KeyNode
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestKeyInterface
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
            //KeyNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestKey
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
                                        Name : TestKeyInterface
                                //GenericParametersNode
                                    //FieldTypeNode
                                        Optional : false
                                        //KeyFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //NamespaceNameGenericParametersNode
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
                                    //KeyFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //NamespaceNameGenericParametersNode
                                            //NamespaceNameNode
                                                //NamespaceNode
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    Name : KeyToReplace
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 2
                        //ImplementsListNode
                            //NamespaceNameGenericParametersNode
                                //NamespaceNameNode
                                    //NamespaceNode
                                        Namespace : TestNamespace
                                    //NameNode
                                        Name : TestKeyInterface
                                //GenericParametersNode
                                    //FieldTypeNode
                                        Optional : false
                                        //KeyFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //NamespaceNameGenericParametersNode
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
                                    //KeyFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //NamespaceNameGenericParametersNode
                                            //NamespaceNameNode
                                                //NamespaceNode
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
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
            //KeyNode
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
                                //Super -> //NonArrayFieldTypeNode
            //KeyNode
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestKeyInterface
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
            //KeyNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestKey
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
                                        Name : TestKeyInterface
                                //GenericParametersNode
                                    //FieldTypeNode
                                        Optional : false
                                        //MapFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            Key
                                                //FieldTypeNode
                                                    Optional : false
                                                    //KeyFieldTypeNode
                                                        //Super -> //NonArrayFieldTypeNode
                                                        //NamespaceNameGenericParametersNode
                                                            //NamespaceNameNode
                                                                //NamespaceNode
                                                                    Namespace : TestNamespace
                                                                //NameNode
                                                                    Name : KeyToReplace
                                            Value
                                                //FieldTypeNode
                                                    Optional : false
                                                    //KeyFieldTypeNode
                                                        //Super -> //NonArrayFieldTypeNode
                                                        //NamespaceNameGenericParametersNode
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
                                    //MapFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        Key
                                            //FieldTypeNode
                                                Optional : false
                                                //KeyFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
                                                        //NamespaceNameNode
                                                            //NamespaceNode
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                Name : KeyToReplace
                                        Value
                                            //FieldTypeNode
                                                Optional : false
                                                //KeyFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
                                                        //NamespaceNameNode
                                                            //NamespaceNode
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
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
            //KeyNode
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
                                //Super -> //NonArrayFieldTypeNode
            //KeyNode
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestKeyInterface
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
            //KeyNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestKey
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
                                        Name : TestKeyInterface
                                //GenericParametersNode
                                    //FieldTypeNode
                                        Optional : false
                                        //SetFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //FieldTypeNode
                                                Optional : false
                                                //KeyFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
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
                                    //SetFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //FieldTypeNode
                                            Optional : false
                                            //KeyFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : KeyToReplace
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 2
                        //ImplementsListNode
                            //NamespaceNameGenericParametersNode
                                //NamespaceNameNode
                                    //NamespaceNode
                                        Namespace : TestNamespace
                                    //NameNode
                                        Name : TestKeyInterface
                                //GenericParametersNode
                                    //FieldTypeNode
                                        Optional : false
                                        //SetFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //FieldTypeNode
                                                Optional : false
                                                //KeyFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
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
                                    //SetFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //FieldTypeNode
                                            Optional : false
                                            //KeyFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
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
            //KeyNode
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
                                //Super -> //NonArrayFieldTypeNode
            //KeyNode
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestKeyInterface
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
            //KeyNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestKey
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
                                        Name : TestKeyInterface
                                //GenericParametersNode
                                    //FieldTypeNode
                                        Optional : false
                                        //ValueOrErrorFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //FieldTypeNode
                                                Optional : false
                                                //KeyFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
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
                                    //ValueOrErrorFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //FieldTypeNode
                                            Optional : false
                                            //KeyFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : KeyToReplace
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 2
                        //ImplementsListNode
                            //NamespaceNameGenericParametersNode
                                //NamespaceNameNode
                                    //NamespaceNode
                                        Namespace : TestNamespace
                                    //NameNode
                                        Name : TestKeyInterface
                                //GenericParametersNode
                                    //FieldTypeNode
                                        Optional : false
                                        //ValueOrErrorFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //FieldTypeNode
                                                Optional : false
                                                //KeyFieldTypeNode
                                                    //Super -> //NonArrayFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
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
                                    //ValueOrErrorFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //FieldTypeNode
                                            Optional : false
                                            //KeyFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : KeyToReplace
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
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
            //KeyNode
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
                                //Super -> //NonArrayFieldTypeNode
            //KeyNode
                IsInterface : true
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestKeyInterface
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
            //KeyNode
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //NamespaceNameNode
                        //NamespaceNode
                            Namespace : TestNamespace
                        //NameNode
                            Name : TestReplaceObjectFieldTypeWithKeyFieldType
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
                                        Name : TestKeyInterface
                                //GenericParametersNode
                                    //FieldTypeNode
                                        Optional : false
                                        //ArrayFieldTypeNode
                                            //KeyFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : KeyToReplace
                                            Dimensions : 2
                        //FieldsNode
                            //FieldNode
                                //FieldNameNode
                                    FieldName : testField
                                //FieldTypeNode
                                    Optional : false
                                    //ArrayFieldTypeNode
                                        //KeyFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //NamespaceNameGenericParametersNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : KeyToReplace
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
                                        Name : TestKeyInterface
                                //GenericParametersNode
                                    //FieldTypeNode
                                        Optional : false
                                        //ArrayFieldTypeNode
                                            //KeyFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : KeyToReplace
                                            Dimensions : 2
                        //FieldsNode
                            //FieldNode
                                //FieldNameNode
                                    FieldName : testField
                                //FieldTypeNode
                                    Optional : false
                                    //ArrayFieldTypeNode
                                        //KeyFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //NamespaceNameGenericParametersNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : KeyToReplace
                                        Dimensions : 2
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
    }

}