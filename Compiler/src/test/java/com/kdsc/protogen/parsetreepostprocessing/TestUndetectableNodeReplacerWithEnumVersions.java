package com.kdsc.protogen.parsetreepostprocessing;

import com.kdsc.protogen.BaseCompilerTest;
import com.kdsc.protogen.parsetreenodes.ParseTreeFormattedStringOptions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TestUndetectableNodeReplacerWithEnumVersions extends BaseCompilerTest {

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
    public void testReplaceOneNestedListTypeInVersions() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type TestNamespace.TestType {
                version 1 {
                    testField : list<TestNamespace.EnumToReplace>
                }
                version 2 {
                    testField : list<TestNamespace.EnumToReplace>
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
    public void testReplaceOneNestedListTypeInImplementsListInVersions() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type interface TestNamespace.TestTypeInterface<T> {
                testInterfaceField : T
            }
            
            type TestNamespace.TestType {
                version 1 : TestNamespace.TestTypeInterface<list<TestNamespace.EnumToReplace>> {
                    testField : list<TestNamespace.EnumToReplace>
                }
                version 2 : TestNamespace.TestTypeInterface<list<TestNamespace.EnumToReplace>> {
                    testField : list<TestNamespace.EnumToReplace>
                }
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
    public void testReplaceOneNonNestedTypeInGenericParameterBoundsInVersions() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type TestNamespace.TestType {
                version 1 <T : TestNamespace.EnumToReplace>
                version 2 <T : TestNamespace.EnumToReplace>
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
                //VersionsNode
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 1
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
                    //VersionNode
                        //VersionNumberNode
                            VersionNumber : 2
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
    public void testReplaceOneNestedMapTypeInGenericParameterBoundsInVersions() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type TestNamespace.Type {
                version 1 <T : map<TestNamespace.EnumToReplace, TestNamespace.EnumToReplace>>
                version 2 <T : map<TestNamespace.EnumToReplace, TestNamespace.EnumToReplace>>
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
    public void testReplaceOneNestedSetTypeInGenericParameterBoundsInVersions() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type TestNamespace.TestType {
                version 1 <T : set<TestNamespace.EnumToReplace>>
                version 2 <T : set<TestNamespace.EnumToReplace>>
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
    public void testReplaceOneNestedListTypeInGenericParameterBoundsInVersions() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type TestNamespace.TestType {
                version 1 <T : list<TestNamespace.EnumToReplace>>
                version 2 <T : list<TestNamespace.EnumToReplace>>
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
    public void testReplaceOneNestedValueOrErrorTypeInGenericParameterBoundsInVersions() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type TestNamespace.TestType {
                version 1 <T : valueorerror<TestNamespace.EnumToReplace>>
                version 2 <T : valueorerror<TestNamespace.EnumToReplace>>
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
    public void testReplaceOneNestedArrayTypeInGenericParameterBoundsInVersions() {

        var testProgram = """
            enum TestNamespace.EnumToReplace {
                testCase1
            }
            
            type TestNamespace.TestType {
                version 1 <T : TestNamespace.EnumToReplace[][]>
                version 2 <T : TestNamespace.EnumToReplace[][]>
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