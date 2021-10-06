package com.kdsc.protogen.parsetreepostprocessing;

import com.kdsc.protogen.BaseCompilerTest;
import com.kdsc.protogen.parsetree.ParseTreeFormattedStringOptions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUndetectableNodeReplacerWithEnums extends BaseCompilerTest {

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
                                //Super -> //NonArrayFieldTypeNode
                                //NamespaceNameGenericParametersNode
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
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
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
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
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
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
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
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
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
                                    //Super -> //NonArrayFieldTypeNode
                                    //NamespaceNameGenericParametersNode
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
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
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
            //ProtoGenTypeNode
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
            //ProtoGenTypeNode
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
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
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
            //ProtoGenTypeNode
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
            //ProtoGenTypeNode
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
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
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
            //ProtoGenTypeNode
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
            //ProtoGenTypeNode
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
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
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
            //ProtoGenTypeNode
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
            //ProtoGenTypeNode
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
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
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
            //ProtoGenTypeNode
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
            //ProtoGenTypeNode
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
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNonNestedTypeInVersions() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type TestNamespace.TestType {
                version 1 {
                    testField : TestNamespace.EnumToReplace
                }
                version 2 {
                    testField : TestNamespace.EnumToReplace
                }
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
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
                                    //EnumFieldTypeNode
                                        //Super -> //NonArrayFieldTypeNode
                                        //NamespaceNameGenericParametersNode
                                            //NamespaceNameNode
                                                //NamespaceNode
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    Name : EnumToReplace
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 2
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
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedMapTypeInVersions() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type TestNamespace.Type {
                version 1 {
                    testField : map<TestNamespace.EnumToReplace, TestNamespace.EnumToReplace>
                }
                version 2 {
                    testField : map<TestNamespace.EnumToReplace, TestNamespace.EnumToReplace>
                }
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
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
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedSetTypeInVersions() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type TestNamespace.TestType {
                version 1 {
                    testField : set<TestNamespace.EnumToReplace>
                }
                version 2 {
                    testField : set<TestNamespace.EnumToReplace>
                }
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
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
                                            //EnumFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : EnumToReplace
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
                                            //EnumFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
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
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedValueOrErrorTypeInVersions() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type TestNamespace.TestType {
                version 1 {
                    testField : valueorerror<TestNamespace.EnumToReplace>
                }
                version 2 {
                    testField : valueorerror<TestNamespace.EnumToReplace>
                }
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
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
                                            //EnumFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : EnumToReplace
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
                                            //EnumFieldTypeNode
                                                //Super -> //NonArrayFieldTypeNode
                                                //NamespaceNameGenericParametersNode
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
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedArrayTypeInVersions() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type TestNamespace.TestType {
                version 1 {
                    testField : TestNamespace.EnumToReplace[][]
                }
                version 2 {
                    testField : TestNamespace.EnumToReplace[][]
                }
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
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
                                        //EnumFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //NamespaceNameGenericParametersNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : EnumToReplace
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
                                        //EnumFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                            //NamespaceNameGenericParametersNode
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
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
    }

    //TODO:KMD Perhaps you should use T in the test type versions, it would be less confusing
    @Test
    public void testReplaceOneNonNestedTypeInImplementsListInVersions() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type interface TestNamespace.TestTypeInterface<T> {
                testInterfaceField : T
            }
            
            type TestNamespace.TestType {
                version 1 : TestNamespace.TestTypeInterface<TestNamespace.EnumToReplace> {
                    testField : TestNamespace.EnumToReplace
                }
                version 2 : TestNamespace.TestTypeInterface<TestNamespace.EnumToReplace> {
                    testField : TestNamespace.EnumToReplace
                }
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //ProtoGenTypeNode
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
            //ProtoGenTypeNode
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
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedMapTypeInImplementsListInVersions() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type interface TestNamespace.TestTypeInterface<T> {
                testInterfaceField : T
            }
            
            type TestNamespace.Type {
                version 1 : TestNamespace.TestTypeInterface<map<TestNamespace.EnumToReplace, TestNamespace.EnumToReplace>> {
                    testField : map<TestNamespace.EnumToReplace, TestNamespace.EnumToReplace>
                }
                version 2 : TestNamespace.TestTypeInterface<map<TestNamespace.EnumToReplace, TestNamespace.EnumToReplace>> {
                    testField : map<TestNamespace.EnumToReplace, TestNamespace.EnumToReplace>
                }
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //ProtoGenTypeNode
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
            //ProtoGenTypeNode
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
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedSetTypeInImplementsListInVersions() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type interface TestNamespace.TestTypeInterface<T> {
                testInterfaceField : T
            }
            
            type TestNamespace.TestType {
                version 1 : TestNamespace.TestTypeInterface<set<TestNamespace.EnumToReplace>> {
                    testField : set<TestNamespace.EnumToReplace>
                }
                version 2 : TestNamespace.TestTypeInterface<set<TestNamespace.EnumToReplace>> {
                    testField : set<TestNamespace.EnumToReplace>
                }
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //ProtoGenTypeNode
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
            //ProtoGenTypeNode
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
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedValueOrErrorTypeInImplementsListInVersions() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type interface TestNamespace.TestTypeInterface<T> {
                testInterfaceField : T
            }
            
            type TestNamespace.TestType {
                version 1 : TestNamespace.TestTypeInterface<valueorerror<TestNamespace.EnumToReplace>> {
                    testField : valueorerror<TestNamespace.EnumToReplace>
                }
                version 2 : TestNamespace.TestTypeInterface<valueorerror<TestNamespace.EnumToReplace>> {
                    testField : valueorerror<TestNamespace.EnumToReplace>
                }
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //ProtoGenTypeNode
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
            //ProtoGenTypeNode
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
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
    }

    @Test
    public void testReplaceOneNestedArrayTypeInImplementsListInVersions() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type interface TestNamespace.TestTypeInterface<T> {
                testInterfaceField : T
            }
            
            type TestNamespace.TestType {
                version 1 : TestNamespace.TestTypeInterface<TestNamespace.EnumToReplace[][]> {
                    testField : TestNamespace.EnumToReplace[][]
                }
                version 2 : TestNamespace.TestTypeInterface<TestNamespace.EnumToReplace[][]> {
                    testField : TestNamespace.EnumToReplace[][]
                }
            }
        """;
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var expectedToStringOutput = """
        //FileNode
            //ProtoGenTypeNode
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
            //ProtoGenTypeNode
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
        assertEquals(expectedToStringOutput, fileNode.toFormattedString(0, ParseTreeFormattedStringOptions.hideBaseParseTreeNode), "Unexpected toString output");
    }

}