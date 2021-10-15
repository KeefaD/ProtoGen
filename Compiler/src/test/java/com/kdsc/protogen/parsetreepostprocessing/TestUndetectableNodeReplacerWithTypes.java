package com.kdsc.protogen.parsetreepostprocessing;

import com.kdsc.protogen.BaseCompilerTest;
import com.kdsc.protogen.parsetreenodes.ParseTreeFormattedStringOptions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TestUndetectableNodeReplacerWithTypes extends BaseCompilerTest {

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
    public void testReplaceOneNestedListType() {

        var testProgram = """
            type TestNamespace.TypeToReplace {
                testField : int32
            }
            
            type TestNamespace.TestType {
                testField : list<TestNamespace.TypeToReplace>
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
    public void testReplaceOneNestedListTypeInImplementsList() {

        var testProgram = """
            type TestNamespace.TypeToReplace {
                testField : int32
            }
            
            type interface TestNamespace.TestTypeInterface<T> {
                testInterfaceField : T
            }
            
            type TestNamespace.TestType : TestNamespace.TestTypeInterface<list<TestNamespace.TypeToReplace>> {
                testField : list<TestNamespace.TypeToReplace>
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
            
            type TestNamespace.TestType<T : TestNamespace.TypeToReplace>
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
            
            type TestNamespace.Type<T : map<TestNamespace.TypeToReplace, TestNamespace.TypeToReplace>>
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
            
            type TestNamespace.TestType<T : set<TestNamespace.TypeToReplace>>
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
            
            type TestNamespace.TestType<T : list<TestNamespace.TypeToReplace>>
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
            
            type TestNamespace.TestType<T : valueorerror<TestNamespace.TypeToReplace>>
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
            
            type TestNamespace.TestType<T : TestNamespace.TypeToReplace[][]>
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