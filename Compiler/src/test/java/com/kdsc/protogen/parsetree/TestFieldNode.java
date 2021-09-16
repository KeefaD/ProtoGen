package com.kdsc.protogen.parsetree;

import com.kdsc.protogen.parsetree.fieldtypenodes.TestFieldTypeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestFieldNode extends BaseTestNode {

    @Test
    public void testCreate() {
        new FieldNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestFieldNameNode.createTestNode(),
            TestFieldTypeNode.createTestNode()
        );
    }

    @Test
    public void testNullsOrEmptiesInConstructor() {

        assertThrows(NullPointerException.class,
            () ->
            new FieldNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                null,
                TestFieldTypeNode.createTestNode()
            )
        );

        assertThrows(NullPointerException.class,
            () ->
            new FieldNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                TestFieldNameNode.createTestNode(),
                null
            )
        );

        assertThrows(NullPointerException.class,
            () ->
                new FieldNode(
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
        var fieldNameNode = TestFieldNameNode.createTestNode();
        var fieldTypeNode = TestFieldTypeNode.createTestNode();
        var node = new FieldNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            fieldNameNode,
            fieldTypeNode
        );
        assertEquals(fieldNameNode, node.getFieldNameNode(), "Created and retrieved objects don't match");
        assertEquals(fieldTypeNode, node.getFieldTypeNode(), "Created and retrieved objects don't match");
    }

    @Test
    public void testToString() {
        var node = new FieldNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestFieldNameNode.createTestNode(),
            TestFieldTypeNode.createTestNode()
        );
        var expectedToStringOutput = """
        //FieldNode
            //FieldNameNode
                FieldName : FieldName
            //FieldTypeNode
                Optional : false
                //BoolFieldTypeNode
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }
}
