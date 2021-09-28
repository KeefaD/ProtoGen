package com.kdsc.protogen.parsetree.fieldtypenodes;

import com.kdsc.protogen.parsetree.BaseTestNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestArrayFieldTypeNode extends BaseTestNode {

    @Test
    public void testCreate() {
        new ArrayFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestBoolFieldTypeNode.createTestNode(),
            1
        );
    }

    @Test
    public void testNullsOrEmptiesInConstructor() {

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
                TestBoolFieldTypeNode.createTestNode(),
                0
            )
        );

        assertThrows(IllegalArgumentException.class,
            () ->
            new ArrayFieldTypeNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                TestBoolFieldTypeNode.createTestNode(),
                -1
            )
        );
    }

    @Test
    public void testGetters() {
        var nonArrayFieldTypeNode = TestBoolFieldTypeNode.createTestNode();
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
        var node = new ArrayFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestBoolFieldTypeNode.createTestNode(),
            1
        );
        var expectedToStringOutput = """
        //ArrayFieldTypeNode
            //BoolFieldTypeNode
            Dimensions : 1
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }

}