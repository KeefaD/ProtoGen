package com.kdsc.protogen.parsetree.fieldtypenodes;

import com.kdsc.protogen.parsetree.BaseTestNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestMapFieldTypeNode extends BaseTestNode {

    @Test
    public void testCreate() {
        new MapFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestFieldTypeNode.createTestNode(),
            TestFieldTypeNode.createTestNode()
        );
    }

    @Test
    public void testNullsOrEmptiesInConstructor() {

        assertThrows(NullPointerException.class,
            () ->
            new MapFieldTypeNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                null,
                TestFieldTypeNode.createTestNode()
            )
        );

        assertThrows(NullPointerException.class,
            () ->
            new MapFieldTypeNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                TestFieldTypeNode.createTestNode(),
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
        var keyFieldTypeNode = TestFieldTypeNode.createTestNode();
        var valueFieldTypeNode = TestFieldTypeNode.createTestNode();
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
        var node = new MapFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestFieldTypeNode.createTestNode(),
            TestFieldTypeNode.createTestNode()
        );
        var expectedToStringOutput = """
        //MapFieldTypeNode
            //Key
                //FieldTypeNode
                    Optional : false
                    //BoolFieldTypeNode
            //Value
                //FieldTypeNode
                    Optional : false
                    //BoolFieldTypeNode
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }
}