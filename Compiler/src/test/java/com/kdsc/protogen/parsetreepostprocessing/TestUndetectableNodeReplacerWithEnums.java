package com.kdsc.protogen.parsetreepostprocessing;

import com.kdsc.protogen.BaseCompilerTest;
import com.kdsc.protogen.parsetreenodes.ParseTreeFormattedStringOptions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TestUndetectableNodeReplacerWithEnums extends BaseCompilerTest {

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
                            Name : TestType
                //FieldsNode
                    //FieldNode
                        //FieldNameNode
                            FieldName : testField
                        //FieldTypeNode
                            Optional : false
                            //EnumFieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                //NamespaceNameGenericParametersNode
                                    //NamespaceNameNode
                                        //NamespaceNode
                                            Namespace : TestNamespace
                                        //NameNode
                                            Name : EnumToReplace
            //EnumNode
                //NamespaceNameNode
                    //NamespaceNode
                        Namespace : TestNamespace
                    //NameNode
                        Name : EnumToReplace
                //EnumCasesNode
                    //EnumNameNode
                        EnumName : testCase1
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
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
                                        //EnumFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //NamespaceNameGenericParametersNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : EnumToReplace
                                Value
                                    //FieldTypeNode
                                        Optional : false
                                        //EnumFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //NamespaceNameGenericParametersNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : EnumToReplace
            //EnumNode
                //NamespaceNameNode
                    //NamespaceNode
                        Namespace : TestNamespace
                    //NameNode
                        Name : EnumToReplace
                //EnumCasesNode
                    //EnumNameNode
                        EnumName : testCase1
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
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
                                    //EnumFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //NamespaceNameGenericParametersNode
                                            //NamespaceNameNode
                                                //NamespaceNode
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    Name : EnumToReplace
            //EnumNode
                //NamespaceNameNode
                    //NamespaceNode
                        Namespace : TestNamespace
                    //NameNode
                        Name : EnumToReplace
                //EnumCasesNode
                    //EnumNameNode
                        EnumName : testCase1
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedListType() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type TestNamespace.TestType {
                testField : list<TestNamespace.EnumToReplace>
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
                                    //EnumFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //NamespaceNameGenericParametersNode
                                            //NamespaceNameNode
                                                //NamespaceNode
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    Name : EnumToReplace
            //EnumNode
                //NamespaceNameNode
                    //NamespaceNode
                        Namespace : TestNamespace
                    //NameNode
                        Name : EnumToReplace
                //EnumCasesNode
                    //EnumNameNode
                        EnumName : testCase1
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
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
                                    //EnumFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //NamespaceNameGenericParametersNode
                                            //NamespaceNameNode
                                                //NamespaceNode
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    Name : EnumToReplace
            //EnumNode
                //NamespaceNameNode
                    //NamespaceNode
                        Namespace : TestNamespace
                    //NameNode
                        Name : EnumToReplace
                //EnumCasesNode
                    //EnumNameNode
                        EnumName : testCase1
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
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
                            Name : TestType
                //FieldsNode
                    //FieldNode
                        //FieldNameNode
                            FieldName : testField
                        //FieldTypeNode
                            Optional : false
                            //ArrayFieldTypeNode
                                //EnumFieldTypeNode
                                    //Super -> //NonArrayFieldTypeNode
                                    //NamespaceNameGenericParametersNode
                                        //NamespaceNameNode
                                            //NamespaceNode
                                                Namespace : TestNamespace
                                            //NameNode
                                                Name : EnumToReplace
                                Dimensions : 2
            //EnumNode
                //NamespaceNameNode
                    //NamespaceNode
                        Namespace : TestNamespace
                    //NameNode
                        Name : EnumToReplace
                //EnumCasesNode
                    //EnumNameNode
                        EnumName : testCase1
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNonNestedTypeInImplementsList() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type interface TestNamespace.TestTypeInterface<T> {
                testInterfaceField : T
            }
            
            type TestNamespace.TestType : TestNamespace.TestTypeInterface<TestNamespace.EnumToReplace> {
                testField : TestNamespace.EnumToReplace
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
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
                                //EnumFieldTypeNode
                                    //Super -> //NonArrayFieldTypeNode
                                    //NamespaceNameGenericParametersNode
                                        //NamespaceNameNode
                                            //NamespaceNode
                                                Namespace : TestNamespace
                                            //NameNode
                                                Name : EnumToReplace
                //FieldsNode
                    //FieldNode
                        //FieldNameNode
                            FieldName : testField
                        //FieldTypeNode
                            Optional : false
                            //EnumFieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                //NamespaceNameGenericParametersNode
                                    //NamespaceNameNode
                                        //NamespaceNode
                                            Namespace : TestNamespace
                                        //NameNode
                                            Name : EnumToReplace
            //EnumNode
                //NamespaceNameNode
                    //NamespaceNode
                        Namespace : TestNamespace
                    //NameNode
                        Name : EnumToReplace
                //EnumCasesNode
                    //EnumNameNode
                        EnumName : testCase1
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedMapTypeInImplementsList() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type interface TestNamespace.TestTypeInterface<T> {
                testInterfaceField : T
            }
            
            type TestNamespace.Type : TestNamespace.TestTypeInterface<map<TestNamespace.EnumToReplace, TestNamespace.EnumToReplace>> {
                testField : map<TestNamespace.EnumToReplace, TestNamespace.EnumToReplace>
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
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
                                            //EnumFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : EnumToReplace
                                    Value
                                        //FieldTypeNode
                                            Optional : false
                                            //EnumFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : EnumToReplace
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
                                        //EnumFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //NamespaceNameGenericParametersNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : EnumToReplace
                                Value
                                    //FieldTypeNode
                                        Optional : false
                                        //EnumFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //NamespaceNameGenericParametersNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : EnumToReplace
            //EnumNode
                //NamespaceNameNode
                    //NamespaceNode
                        Namespace : TestNamespace
                    //NameNode
                        Name : EnumToReplace
                //EnumCasesNode
                    //EnumNameNode
                        EnumName : testCase1
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedSetTypeInImplementsList() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type interface TestNamespace.TestTypeInterface<T> {
                testInterfaceField : T
            }
            
            type TestNamespace.TestType : TestNamespace.TestTypeInterface<set<TestNamespace.EnumToReplace>> {
                testField : set<TestNamespace.EnumToReplace>
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
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
                                        //EnumFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //NamespaceNameGenericParametersNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : EnumToReplace
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
                                    //EnumFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //NamespaceNameGenericParametersNode
                                            //NamespaceNameNode
                                                //NamespaceNode
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    Name : EnumToReplace
            //EnumNode
                //NamespaceNameNode
                    //NamespaceNode
                        Namespace : TestNamespace
                    //NameNode
                        Name : EnumToReplace
                //EnumCasesNode
                    //EnumNameNode
                        EnumName : testCase1
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedListTypeInImplementsList() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type interface TestNamespace.TestTypeInterface<T> {
                testInterfaceField : T
            }
            
            type TestNamespace.TestType : TestNamespace.TestTypeInterface<list<TestNamespace.EnumToReplace>> {
                testField : list<TestNamespace.EnumToReplace>
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
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
                                        //EnumFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //NamespaceNameGenericParametersNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : EnumToReplace
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
                                    //EnumFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //NamespaceNameGenericParametersNode
                                            //NamespaceNameNode
                                                //NamespaceNode
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    Name : EnumToReplace
            //EnumNode
                //NamespaceNameNode
                    //NamespaceNode
                        Namespace : TestNamespace
                    //NameNode
                        Name : EnumToReplace
                //EnumCasesNode
                    //EnumNameNode
                        EnumName : testCase1
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedValueOrErrorTypeInImplementsList() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type interface TestNamespace.TestTypeInterface<T> {
                testInterfaceField : T
            }
            
            type TestNamespace.TestType : TestNamespace.TestTypeInterface<valueorerror<TestNamespace.EnumToReplace>> {
                testField : valueorerror<TestNamespace.EnumToReplace>
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
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
                                        //EnumFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //NamespaceNameGenericParametersNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : EnumToReplace
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
                                    //EnumFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //NamespaceNameGenericParametersNode
                                            //NamespaceNameNode
                                                //NamespaceNode
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    Name : EnumToReplace
            //EnumNode
                //NamespaceNameNode
                    //NamespaceNode
                        Namespace : TestNamespace
                    //NameNode
                        Name : EnumToReplace
                //EnumCasesNode
                    //EnumNameNode
                        EnumName : testCase1
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedArrayTypeInImplementsList() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type interface TestNamespace.TestTypeInterface<T> {
                testInterfaceField : T
            }
            
            type TestNamespace.TestType : TestNamespace.TestTypeInterface<TestNamespace.EnumToReplace[][]> {
                testField : TestNamespace.EnumToReplace[][]
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
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
                                    //EnumFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //NamespaceNameGenericParametersNode
                                            //NamespaceNameNode
                                                //NamespaceNode
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    Name : EnumToReplace
                                    Dimensions : 2
                //FieldsNode
                    //FieldNode
                        //FieldNameNode
                            FieldName : testField
                        //FieldTypeNode
                            Optional : false
                            //ArrayFieldTypeNode
                                //EnumFieldTypeNode
                                    //Super -> //NonArrayFieldTypeNode
                                    //NamespaceNameGenericParametersNode
                                        //NamespaceNameNode
                                            //NamespaceNode
                                                Namespace : TestNamespace
                                            //NameNode
                                                Name : EnumToReplace
                                Dimensions : 2
            //EnumNode
                //NamespaceNameNode
                    //NamespaceNode
                        Namespace : TestNamespace
                    //NameNode
                        Name : EnumToReplace
                //EnumCasesNode
                    //EnumNameNode
                        EnumName : testCase1
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNonNestedTypeInGenericParameterBounds() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type TestNamespace.TestType<T : TestNamespace.EnumToReplace>
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
                            Name : TestType
                    //GenericParametersWithBoundsNode
                        //GenericParameterWithBoundsNode
                            Identifier : T
                            //FieldTypeNode
                                Optional : false
                                //EnumFieldTypeNode
                                    //Super -> //NonArrayFieldTypeNode
                                    //NamespaceNameGenericParametersNode
                                        //NamespaceNameNode
                                            //NamespaceNode
                                                Namespace : TestNamespace
                                            //NameNode
                                                Name : EnumToReplace
            //EnumNode
                //NamespaceNameNode
                    //NamespaceNode
                        Namespace : TestNamespace
                    //NameNode
                        Name : EnumToReplace
                //EnumCasesNode
                    //EnumNameNode
                        EnumName : testCase1
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedMapTypeInGenericParameterBounds() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type TestNamespace.Type<T : map<TestNamespace.EnumToReplace, TestNamespace.EnumToReplace>>
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
                                            //EnumFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : EnumToReplace
                                    Value
                                        //FieldTypeNode
                                            Optional : false
                                            //EnumFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : EnumToReplace
            //EnumNode
                //NamespaceNameNode
                    //NamespaceNode
                        Namespace : TestNamespace
                    //NameNode
                        Name : EnumToReplace
                //EnumCasesNode
                    //EnumNameNode
                        EnumName : testCase1
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedSetTypeInGenericParameterBounds() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type TestNamespace.TestType<T : set<TestNamespace.EnumToReplace>>
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
                                        //EnumFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //NamespaceNameGenericParametersNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : EnumToReplace
            //EnumNode
                //NamespaceNameNode
                    //NamespaceNode
                        Namespace : TestNamespace
                    //NameNode
                        Name : EnumToReplace
                //EnumCasesNode
                    //EnumNameNode
                        EnumName : testCase1
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedListTypeInGenericParameterBounds() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type TestNamespace.TestType<T : list<TestNamespace.EnumToReplace>>
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
                                        //EnumFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //NamespaceNameGenericParametersNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : EnumToReplace
            //EnumNode
                //NamespaceNameNode
                    //NamespaceNode
                        Namespace : TestNamespace
                    //NameNode
                        Name : EnumToReplace
                //EnumCasesNode
                    //EnumNameNode
                        EnumName : testCase1
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedValueOrErrorTypeInGenericParameterBounds() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type TestNamespace.TestType <T : valueorerror<TestNamespace.EnumToReplace>>
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
                                        //EnumFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //NamespaceNameGenericParametersNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : EnumToReplace
            //EnumNode
                //NamespaceNameNode
                    //NamespaceNode
                        Namespace : TestNamespace
                    //NameNode
                        Name : EnumToReplace
                //EnumCasesNode
                    //EnumNameNode
                        EnumName : testCase1
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedArrayTypeInGenericParameterBounds() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type TestNamespace.TestType <T : TestNamespace.EnumToReplace[][]>
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
                            Name : TestType
                    //GenericParametersWithBoundsNode
                        //GenericParameterWithBoundsNode
                            Identifier : T
                            //FieldTypeNode
                                Optional : false
                                //ArrayFieldTypeNode
                                    //EnumFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //NamespaceNameGenericParametersNode
                                            //NamespaceNameNode
                                                //NamespaceNode
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    Name : EnumToReplace
                                    Dimensions : 2
            //EnumNode
                //NamespaceNameNode
                    //NamespaceNode
                        Namespace : TestNamespace
                    //NameNode
                        Name : EnumToReplace
                //EnumCasesNode
                    //EnumNameNode
                        EnumName : testCase1
        """;
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 0), "Unexpected toString output");
    }

}