package com.kdsc.protogen.parsetreenodes.fieldtypenodes;

import com.kdsc.protogen.parsetreenodes.BaseTestNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public final class TestMapFieldTypeNode extends BaseTestNode {

    @Test
    public void testCreateMinimal() {
        new MapFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestFieldTypeNode.createPopulatedTestNode(),
            TestFieldTypeNode.createPopulatedTestNode()
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
            new MapFieldTypeNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                null,
                TestFieldTypeNode.createPopulatedTestNode()
            )
        );

        assertThrows(NullPointerException.class,
            () ->
            new MapFieldTypeNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                TestFieldTypeNode.createPopulatedTestNode(),
                null
            )
        );

        assertThrows(NullPointerException.class,
            () ->
            new MapFieldTypeNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                null,
                null
            )
        );

    }

    @Test
    public void testGetters() {
        var keyFieldTypeNode = TestFieldTypeNode.createPopulatedTestNode();
        var valueFieldTypeNode = TestFieldTypeNode.createPopulatedTestNode();
        var node = new MapFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            keyFieldTypeNode,
            valueFieldTypeNode
        );
        assertEquals(keyFieldTypeNode, node.getKeyFieldTypeNode(), "Created and retrieved objects don't match");
        assertEquals(valueFieldTypeNode, node.getValueFieldTypeNode(), "Created and retrieved objects don't match");
    }

    @Test
    public void testToString() {
        var node = createPopulatedTestNode();
        var expectedToStringOutput = """
        //MapFieldTypeNode
            //Super -> //NonArrayFieldTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : TestFileName.pg
                    Line : 1
                    CharPosition : 0
            Key
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
            Value
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

    public static MapFieldTypeNode createPopulatedTestNode() {
        return new MapFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestFieldTypeNode.createPopulatedTestNode(),
            TestFieldTypeNode.createPopulatedTestNode()
        );
    }

}