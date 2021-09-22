package com.kdsc.protogen.parsetreepostprocessing;

import com.kdsc.protogen.antlr.parser.BaseParserTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUndetectableNodeReplacerWithTypes extends BaseParserTest {

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
                            Name : TestType
                //FieldsNode
                    //FieldNode
                        //FieldNameNode
                            FieldName : testField
                        //FieldTypeNode
                            Optional : false
                            //TypeFieldTypeNode
                                //NamespaceNameGenericParametersNode
                                    //NamespaceNameNode
                                        //NamespaceNode
                                            Namespace : TestNamespace
                                        //NameNode
                                            Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, newFileNode.toString(), "Unexpected toString output");
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
                                        //TypeFieldTypeNode
                                            //NamespaceNameGenericParametersNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : TypeToReplace
                                //Value
                                    //FieldTypeNode
                                        Optional : false
                                        //TypeFieldTypeNode
                                            //NamespaceNameGenericParametersNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, newFileNode.toString(), "Unexpected toString output");
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
                                        //TypeFieldTypeNode
                                            //NamespaceNameGenericParametersNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, newFileNode.toString(), "Unexpected toString output");
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
                                        //TypeFieldTypeNode
                                            //NamespaceNameGenericParametersNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, newFileNode.toString(), "Unexpected toString output");
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
                            Name : TestType
                //FieldsNode
                    //FieldNode
                        //FieldNameNode
                            FieldName : testField
                        //FieldTypeNode
                            Optional : false
                            //ArrayFieldTypeNode
                                //TypeFieldTypeNode
                                    //NamespaceNameGenericParametersNode
                                        //NamespaceNameNode
                                            //NamespaceNode
                                                Namespace : TestNamespace
                                            //NameNode
                                                Name : TypeToReplace
                                Dimensions : 2
        """;
        assertEquals(expectedToStringOutput, newFileNode.toString(), "Unexpected toString output");
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
                                //TypeFieldTypeNode
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
                                //NamespaceNameGenericParametersNode
                                    //NamespaceNameNode
                                        //NamespaceNode
                                            Namespace : TestNamespace
                                        //NameNode
                                            Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, newFileNode.toString(), "Unexpected toString output");
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
                                    //Key
                                        //FieldTypeNode
                                            Optional : false
                                            //TypeFieldTypeNode
                                                //NamespaceNameGenericParametersNode
                                                    //NamespaceNameNode
                                                        //NamespaceNode
                                                            Namespace : TestNamespace
                                                        //NameNode
                                                            Name : TypeToReplace
                                    //Value
                                        //FieldTypeNode
                                            Optional : false
                                            //TypeFieldTypeNode
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
                                //Key
                                    //FieldTypeNode
                                        Optional : false
                                        //TypeFieldTypeNode
                                            //NamespaceNameGenericParametersNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : TypeToReplace
                                //Value
                                    //FieldTypeNode
                                        Optional : false
                                        //TypeFieldTypeNode
                                            //NamespaceNameGenericParametersNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, newFileNode.toString(), "Unexpected toString output");
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
                                    //Entry
                                        //FieldTypeNode
                                            Optional : false
                                            //TypeFieldTypeNode
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
                                //Entry
                                    //FieldTypeNode
                                        Optional : false
                                        //TypeFieldTypeNode
                                            //NamespaceNameGenericParametersNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, newFileNode.toString(), "Unexpected toString output");
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
                                    //Entry
                                        //FieldTypeNode
                                            Optional : false
                                            //TypeFieldTypeNode
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
                                //Entry
                                    //FieldTypeNode
                                        Optional : false
                                        //TypeFieldTypeNode
                                            //NamespaceNameGenericParametersNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, newFileNode.toString(), "Unexpected toString output");
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
                                    //TypeFieldTypeNode
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
                                    //NamespaceNameGenericParametersNode
                                        //NamespaceNameNode
                                            //NamespaceNode
                                                Namespace : TestNamespace
                                            //NameNode
                                                Name : TypeToReplace
                                Dimensions : 2
        """;
        assertEquals(expectedToStringOutput, newFileNode.toString(), "Unexpected toString output");
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
                                        //NamespaceNameGenericParametersNode
                                            //NamespaceNameNode
                                                //NamespaceNode
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, newFileNode.toString(), "Unexpected toString output");
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
                                        //Key
                                            //FieldTypeNode
                                                Optional : false
                                                //TypeFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
                                                        //NamespaceNameNode
                                                            //NamespaceNode
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                Name : TypeToReplace
                                        //Value
                                            //FieldTypeNode
                                                Optional : false
                                                //TypeFieldTypeNode
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
                                        //Key
                                            //FieldTypeNode
                                                Optional : false
                                                //TypeFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
                                                        //NamespaceNameNode
                                                            //NamespaceNode
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                Name : TypeToReplace
                                        //Value
                                            //FieldTypeNode
                                                Optional : false
                                                //TypeFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
                                                        //NamespaceNameNode
                                                            //NamespaceNode
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, newFileNode.toString(), "Unexpected toString output");
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
                                        //Entry
                                            //FieldTypeNode
                                                Optional : false
                                                //TypeFieldTypeNode
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
                                        //Entry
                                            //FieldTypeNode
                                                Optional : false
                                                //TypeFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
                                                        //NamespaceNameNode
                                                            //NamespaceNode
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, newFileNode.toString(), "Unexpected toString output");
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
                                        //Entry
                                            //FieldTypeNode
                                                Optional : false
                                                //TypeFieldTypeNode
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
                                        //Entry
                                            //FieldTypeNode
                                                Optional : false
                                                //TypeFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
                                                        //NamespaceNameNode
                                                            //NamespaceNode
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, newFileNode.toString(), "Unexpected toString output");
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
                                            //NamespaceNameGenericParametersNode
                                                //NamespaceNameNode
                                                    //NamespaceNode
                                                        Namespace : TestNamespace
                                                    //NameNode
                                                        Name : TypeToReplace
                                        Dimensions : 2
        """;
        assertEquals(expectedToStringOutput, newFileNode.toString(), "Unexpected toString output");
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
                                        //TypeFieldTypeNode
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
                                        //NamespaceNameGenericParametersNode
                                            //NamespaceNameNode
                                                //NamespaceNode
                                                    Namespace : TestNamespace
                                                //NameNode
                                                    Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, newFileNode.toString(), "Unexpected toString output");
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
                                            //Key
                                                //FieldTypeNode
                                                    Optional : false
                                                    //TypeFieldTypeNode
                                                        //NamespaceNameGenericParametersNode
                                                            //NamespaceNameNode
                                                                //NamespaceNode
                                                                    Namespace : TestNamespace
                                                                //NameNode
                                                                    Name : TypeToReplace
                                            //Value
                                                //FieldTypeNode
                                                    Optional : false
                                                    //TypeFieldTypeNode
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
                                        //Key
                                            //FieldTypeNode
                                                Optional : false
                                                //TypeFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
                                                        //NamespaceNameNode
                                                            //NamespaceNode
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                Name : TypeToReplace
                                        //Value
                                            //FieldTypeNode
                                                Optional : false
                                                //TypeFieldTypeNode
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
                                            //Key
                                                //FieldTypeNode
                                                    Optional : false
                                                    //TypeFieldTypeNode
                                                        //NamespaceNameGenericParametersNode
                                                            //NamespaceNameNode
                                                                //NamespaceNode
                                                                    Namespace : TestNamespace
                                                                //NameNode
                                                                    Name : TypeToReplace
                                            //Value
                                                //FieldTypeNode
                                                    Optional : false
                                                    //TypeFieldTypeNode
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
                                        //Key
                                            //FieldTypeNode
                                                Optional : false
                                                //TypeFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
                                                        //NamespaceNameNode
                                                            //NamespaceNode
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                Name : TypeToReplace
                                        //Value
                                            //FieldTypeNode
                                                Optional : false
                                                //TypeFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
                                                        //NamespaceNameNode
                                                            //NamespaceNode
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, newFileNode.toString(), "Unexpected toString output");
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
                                            //Entry
                                                //FieldTypeNode
                                                    Optional : false
                                                    //TypeFieldTypeNode
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
                                        //Entry
                                            //FieldTypeNode
                                                Optional : false
                                                //TypeFieldTypeNode
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
                                            //Entry
                                                //FieldTypeNode
                                                    Optional : false
                                                    //TypeFieldTypeNode
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
                                        //Entry
                                            //FieldTypeNode
                                                Optional : false
                                                //TypeFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
                                                        //NamespaceNameNode
                                                            //NamespaceNode
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, newFileNode.toString(), "Unexpected toString output");
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
                                            //Entry
                                                //FieldTypeNode
                                                    Optional : false
                                                    //TypeFieldTypeNode
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
                                        //Entry
                                            //FieldTypeNode
                                                Optional : false
                                                //TypeFieldTypeNode
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
                                            //Entry
                                                //FieldTypeNode
                                                    Optional : false
                                                    //TypeFieldTypeNode
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
                                        //Entry
                                            //FieldTypeNode
                                                Optional : false
                                                //TypeFieldTypeNode
                                                    //NamespaceNameGenericParametersNode
                                                        //NamespaceNameNode
                                                            //NamespaceNode
                                                                Namespace : TestNamespace
                                                            //NameNode
                                                                Name : TypeToReplace
        """;
        assertEquals(expectedToStringOutput, newFileNode.toString(), "Unexpected toString output");
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
                                            //TypeFieldTypeNode
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
                                            //NamespaceNameGenericParametersNode
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