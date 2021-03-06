package com.kdsc.protogen.parsetreenodes;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public final class TestFieldsNode extends BaseTestNode {

    @Test
    public void testCreateMinimal() {
        new FieldsNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            List.of(TestFieldNode.createPopulatedTestNode())
        );
    }

    @Test
    public void testCreatePopulated() {
        createPopulatedTestNode();
    }

    @Test
    public void testInvalidConstructorCall() {

        assertThrows(
            NullPointerException.class,
            () ->
            new FieldsNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                null
            )
        );

        assertThrows(
            IllegalArgumentException.class,
            () ->
            new FieldsNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                Collections.emptyList()
            )
        );

    }

    @Test
    public void testGetters() {
        var fieldNodes = List.of(TestFieldNode.createPopulatedTestNode());
        var node = new FieldsNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            fieldNodes
        );
        assertEquals(fieldNodes, node.getFieldNodes(), "Created and retrieved objects don't match");
    }

    @Test
    public void testToString() {
        var node = createPopulatedTestNode();
        var expectedToStringOutput = """
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

    public static FieldsNode createPopulatedTestNode() {
        return new FieldsNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            List.of(TestFieldNode.createPopulatedTestNode())
        );
    }

}