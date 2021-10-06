package com.kdsc.protogen.parsetree;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestVersionNode extends BaseTestNode {

    @Test
    public void testCreateMinimal() {
        new VersionNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestVersionNumberNode.createPopulatedTestNode(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty()
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
            new VersionNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                null,
                Optional.empty(),
                Optional.empty(),
                Optional.empty()
            )
        );

        assertThrows(NullPointerException.class,
            () ->
            new VersionNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                TestVersionNumberNode.createPopulatedTestNode(),
                null,
                Optional.empty(),
                Optional.empty()
            )
        );

        assertThrows(NullPointerException.class,
            () ->
                new VersionNode(
                    BaseTestNode.fileName,
                    BaseTestNode.line,
                    BaseTestNode.charPosition,
                    TestVersionNumberNode.createPopulatedTestNode(),
                    Optional.empty(),
                    null,
                    Optional.empty()
                )
        );

        assertThrows(NullPointerException.class,
            () ->
            new VersionNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                TestVersionNumberNode.createPopulatedTestNode(),
                Optional.empty(),
                Optional.empty(),
                null
            )
        );
    }

    @Test
    public void testGetters() {
        var versionNumberNode = TestVersionNumberNode.createPopulatedTestNode();
        Optional<FieldsNode> fieldsNode = Optional.empty();
        Optional<GenericParametersWithBoundsNode> genericParametersWithBoundsNode = Optional.empty();
        Optional<ImplementsListNode> implementsListNode = Optional.empty();
        var node = new VersionNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            versionNumberNode,
            genericParametersWithBoundsNode,
            implementsListNode,
            fieldsNode
        );
        assertEquals(versionNumberNode, node.getVersionNumberNode(), "Created and retrieved objects don't match");
        assertEquals(genericParametersWithBoundsNode, node.getGenericParametersWithBoundsNode(), "Created and retrieved objects don't match");
        assertEquals(implementsListNode, node.getImplementsListNode(), "Created and retrieved objects don't match");
        assertEquals(fieldsNode, node.getFieldsNode(), "Created and retrieved objects don't match");
    }

    @Test
    public void testToString() {
        var node = createPopulatedTestNode();
        var expectedToStringOutput = """
        //VersionNode
            //Super -> //BaseParseTreeNode
                SourceFileName : TestFileName.pg
                Line : 1
                CharPosition : 0
            //VersionNumberNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : TestFileName.pg
                    Line : 1
                    CharPosition : 0
                VersionNumber : 1
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
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }

    @Test
    public void testEquals() {
        var node1 = createPopulatedTestNode();
        var node2 = createPopulatedTestNode();
        assertEquals(node1, node2, "Expected objects to be equal");
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
    }

    public static VersionNode createPopulatedTestNode() {
        return new VersionNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestVersionNumberNode.createPopulatedTestNode(),
            Optional.of(TestGenericParametersWithBoundsNode.createPopulatedTestNode()),
            Optional.of(TestImplementsListNode.createPopulatedTestNode()),
            Optional.of(TestFieldsNode.createPopulatedTestNode())
        );
    }

}