package com.kdsc.protogen.parsetree.fieldtypenodes;

import com.kdsc.protogen.parsetree.BaseTestNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public final class TestArrayFieldTypeNode extends BaseTestNode {

    @Test
    public void testCreateMinimal() {
        new ArrayFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestBoolFieldTypeNode.createPopulatedTestNode(),
            1
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
            new ArrayFieldTypeNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                null,
                1
            )
        );
    }

    @Test
    public void testZeroOrNegativeDimensionsInConstructor() {

        assertThrows(IllegalArgumentException.class,
            () ->
            new ArrayFieldTypeNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                TestBoolFieldTypeNode.createPopulatedTestNode(),
                0
            )
        );

        assertThrows(IllegalArgumentException.class,
            () ->
            new ArrayFieldTypeNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                TestBoolFieldTypeNode.createPopulatedTestNode(),
                -1
            )
        );
    }

    @Test
    public void testGetters() {
        var nonArrayFieldTypeNode = TestBoolFieldTypeNode.createPopulatedTestNode();
        var dimensions = 1;
        var node = new ArrayFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            nonArrayFieldTypeNode,
            1
        );
        assertEquals(nonArrayFieldTypeNode, node.getNonArrayFieldTypeNode(), "Created and retrieved objects don't match");
        assertEquals(dimensions, node.getDimensions(), "Created and retrieved objects don't match");
    }

    @Test
    public void testToString() {
        var node = createPopulatedTestNode();
        var expectedToStringOutput = """
        //ArrayFieldTypeNode
            //Super -> //BaseParseTreeNode
                SourceFileName : TestFileName.pg
                Line : 1
                CharPosition : 0
            //BoolFieldTypeNode
                //Super -> //NonArrayFieldTypeNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : TestFileName.pg
                        Line : 1
                        CharPosition : 0
            Dimensions : 1
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

    public static ArrayFieldTypeNode createPopulatedTestNode() {
        return new ArrayFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestBoolFieldTypeNode.createPopulatedTestNode(),
            1
        );
    }

}