package com.kdsc.protogen.parsetree;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestFileNode extends BaseTestNode {

    @Test
    public void testCreateMinimal() {
        new FileNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            Collections.emptyList(),
            Collections.emptyList(),
            Collections.emptyList()
        );
    }

    @Test
    public void testCreatePopulated() {
        createPopulatedTestNode();
    }

    @Test
    public void testInvalidConstructorCall() {

        assertThrows(NullPointerException.class,
            () ->
            new FileNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                null,
                Collections.emptyList(),
                Collections.emptyList()
            )
        );

        assertThrows(NullPointerException.class,
            () ->
            new FileNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                Collections.emptyList(),
                null,
                Collections.emptyList()
            )
        );

        assertThrows(NullPointerException.class,
            () ->
            new FileNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                Collections.emptyList(),
                Collections.emptyList(),
                null
            )
        );
    }

    @Test
    public void testGetters() {
        List<ProtoGenTypeNode> typeNodes = Collections.emptyList();
        List<ProtoGenKeyNode> keyNodes = Collections.emptyList();
        List<ProtoGenEnumNode> enumNodes = Collections.emptyList();
        var node = new FileNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            typeNodes,
            keyNodes,
            enumNodes
        );
        assertEquals(typeNodes, node.getProtoGenTypeNodes(), "Created and retrieved objects don't match");
        assertEquals(keyNodes, node.getProtoGenKeyNodes(), "Created and retrieved objects don't match");
        assertEquals(enumNodes, node.getProtoGenEnumNodes(), "Created and retrieved objects don't match");
    }

    @Test
    public void testToString() {
        var node = createPopulatedTestNode();
        var expectedToStringOutput = """
        //FileNode
            //Super -> //BaseParseTreeNode
                SourceFileName : TestFileName.pg
                Line : 1
                CharPosition : 0
            //ProtoGenTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : TestFileName.pg
                    Line : 1
                    CharPosition : 0
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : TestFileName.pg
                        Line : 1
                        CharPosition : 0
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : TestFileName.pg
                            Line : 1
                            CharPosition : 0
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : TestFileName.pg
                                Line : 1
                                CharPosition : 0
                            Namespace : Namespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : TestFileName.pg
                                Line : 1
                                CharPosition : 0
                            Name : Name
                    //GenericParametersWithBoundsNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : TestFileName.pg
                            Line : 1
                            CharPosition : 0
                        //GenericParameterWithBoundsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : TestFileName.pg
                                Line : 1
                                CharPosition : 0
                            Identifier : T
                            //NamespaceNameGenericParametersNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : TestFileName.pg
                                    Line : 1
                                    CharPosition : 0
                                //NamespaceNameNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : TestFileName.pg
                                        Line : 1
                                        CharPosition : 0
                                    //NamespaceNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : TestFileName.pg
                                            Line : 1
                                            CharPosition : 0
                                        Namespace : Namespace
                                    //NameNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : TestFileName.pg
                                            Line : 1
                                            CharPosition : 0
                                        Name : Name
                                //GenericParametersNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : TestFileName.pg
                                        Line : 1
                                        CharPosition : 0
                                    //FieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : TestFileName.pg
                                            Line : 1
                                            CharPosition : 0
                                        Optional : false
                                        //BoolFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : TestFileName.pg
                                                    Line : 1
                                                    CharPosition : 0
                //ImplementsListNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : TestFileName.pg
                        Line : 1
                        CharPosition : 0
                    //NamespaceNameGenericParametersNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : TestFileName.pg
                            Line : 1
                            CharPosition : 0
                        //NamespaceNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : TestFileName.pg
                                Line : 1
                                CharPosition : 0
                            //NamespaceNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : TestFileName.pg
                                    Line : 1
                                    CharPosition : 0
                                Namespace : Namespace
                            //NameNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : TestFileName.pg
                                    Line : 1
                                    CharPosition : 0
                                Name : Name
                        //GenericParametersNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : TestFileName.pg
                                Line : 1
                                CharPosition : 0
                            //FieldTypeNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : TestFileName.pg
                                    Line : 1
                                    CharPosition : 0
                                Optional : false
                                //BoolFieldTypeNode
                                    //Super -> //NonArrayFieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : TestFileName.pg
                                            Line : 1
                                            CharPosition : 0
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : TestFileName.pg
                        Line : 1
                        CharPosition : 0
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : TestFileName.pg
                            Line : 1
                            CharPosition : 0
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : TestFileName.pg
                                Line : 1
                                CharPosition : 0
                            FieldName : FieldName
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : TestFileName.pg
                                Line : 1
                                CharPosition : 0
                            Optional : false
                            //BoolFieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : TestFileName.pg
                                        Line : 1
                                        CharPosition : 0
            //ProtoGenKeyNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : TestFileName.pg
                    Line : 1
                    CharPosition : 0
                IsInterface : false
                //NamespaceNameGenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : TestFileName.pg
                        Line : 1
                        CharPosition : 0
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : TestFileName.pg
                            Line : 1
                            CharPosition : 0
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : TestFileName.pg
                                Line : 1
                                CharPosition : 0
                            Namespace : Namespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : TestFileName.pg
                                Line : 1
                                CharPosition : 0
                            Name : Name
                    //GenericParametersWithBoundsNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : TestFileName.pg
                            Line : 1
                            CharPosition : 0
                        //GenericParameterWithBoundsNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : TestFileName.pg
                                Line : 1
                                CharPosition : 0
                            Identifier : T
                            //NamespaceNameGenericParametersNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : TestFileName.pg
                                    Line : 1
                                    CharPosition : 0
                                //NamespaceNameNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : TestFileName.pg
                                        Line : 1
                                        CharPosition : 0
                                    //NamespaceNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : TestFileName.pg
                                            Line : 1
                                            CharPosition : 0
                                        Namespace : Namespace
                                    //NameNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : TestFileName.pg
                                            Line : 1
                                            CharPosition : 0
                                        Name : Name
                                //GenericParametersNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : TestFileName.pg
                                        Line : 1
                                        CharPosition : 0
                                    //FieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : TestFileName.pg
                                            Line : 1
                                            CharPosition : 0
                                        Optional : false
                                        //BoolFieldTypeNode
                                            //Super -> //NonArrayFieldTypeNode
                                                //Super -> //BaseParseTreeNode
                                                    SourceFileName : TestFileName.pg
                                                    Line : 1
                                                    CharPosition : 0
                //ImplementsListNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : TestFileName.pg
                        Line : 1
                        CharPosition : 0
                    //NamespaceNameGenericParametersNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : TestFileName.pg
                            Line : 1
                            CharPosition : 0
                        //NamespaceNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : TestFileName.pg
                                Line : 1
                                CharPosition : 0
                            //NamespaceNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : TestFileName.pg
                                    Line : 1
                                    CharPosition : 0
                                Namespace : Namespace
                            //NameNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : TestFileName.pg
                                    Line : 1
                                    CharPosition : 0
                                Name : Name
                        //GenericParametersNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : TestFileName.pg
                                Line : 1
                                CharPosition : 0
                            //FieldTypeNode
                                //Super -> //BaseParseTreeNode
                                    SourceFileName : TestFileName.pg
                                    Line : 1
                                    CharPosition : 0
                                Optional : false
                                //BoolFieldTypeNode
                                    //Super -> //NonArrayFieldTypeNode
                                        //Super -> //BaseParseTreeNode
                                            SourceFileName : TestFileName.pg
                                            Line : 1
                                            CharPosition : 0
                //FieldsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : TestFileName.pg
                        Line : 1
                        CharPosition : 0
                    //FieldNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : TestFileName.pg
                            Line : 1
                            CharPosition : 0
                        //FieldNameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : TestFileName.pg
                                Line : 1
                                CharPosition : 0
                            FieldName : FieldName
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : TestFileName.pg
                                Line : 1
                                CharPosition : 0
                            Optional : false
                            //BoolFieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : TestFileName.pg
                                        Line : 1
                                        CharPosition : 0
            //ProtoGenEnumNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : TestFileName.pg
                    Line : 1
                    CharPosition : 0
                //NamespaceNameNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : TestFileName.pg
                        Line : 1
                        CharPosition : 0
                    //NamespaceNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : TestFileName.pg
                            Line : 1
                            CharPosition : 0
                        Namespace : Namespace
                    //NameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : TestFileName.pg
                            Line : 1
                            CharPosition : 0
                        Name : Name
                //EnumCasesNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : TestFileName.pg
                        Line : 1
                        CharPosition : 0
                    //EnumNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : TestFileName.pg
                            Line : 1
                            CharPosition : 0
                        EnumName : EnumName
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }

    @Test
    public void testEquals() {
        var node1 = createPopulatedTestNode();
        var node2 = createPopulatedTestNode();
        assertEquals(node1, node2, "Expected objects to be equal");
        assertEquals(node1.toString(), node2.toString(), "Expected objects toString to be equal");
    }

    @Test
    public void testHashcode() {
        var node1Hashcode = createPopulatedTestNode().hashCode();
        var node2Hashcode = createPopulatedTestNode().hashCode();
        assertEquals(node1Hashcode, node2Hashcode, "Expected objects to be equal");
    }

    @Test
    public void testClone() {
        var node1 = createPopulatedTestNode();
        var node2 = node1.clone();
        assertEquals(node1, node2, "Expected cloned objects to be equal");
        assertEquals(node1.hashCode(), node2.hashCode(), "Expected cloned objects hashcode to be equal");
        assertEquals(node1.toString(), node2.toString(), "Expected cloned objects toString to be equal");
    }

    public static FileNode createPopulatedTestNode() {
        return new FileNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            List.of(TestProtoGenTypeNode.createPopulatedTestNode()),
            List.of(TestProtoGenKeyNode.createPopulatedTestNode()),
            List.of(TestProtoGenEnumNode.createPopulatedTestNode())
        );
    }

}