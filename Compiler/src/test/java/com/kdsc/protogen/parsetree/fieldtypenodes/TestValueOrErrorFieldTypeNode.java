package com.kdsc.protogen.parsetree.fieldtypenodes;

import com.kdsc.protogen.parsetree.BaseTestNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestValueOrErrorFieldTypeNode extends BaseTestNode {

    @Test
    public void testCreate() {
        new ValueOrErrorFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestFieldTypeNode.createTestNode()
        );
    }

    @Test
    public void testNullsOrEmptiesInConstructor() {

        assertThrows(NullPointerException.class,
            () ->
            new ValueOrErrorFieldTypeNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                null
            )
        );

    }

    @Test
    public void testGetters() {
        var entryFieldTypeNode = TestFieldTypeNode.createTestNode();
        var node = new ValueOrErrorFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            entryFieldTypeNode
        );
        assertEquals(entryFieldTypeNode, node.getFieldTypeNode(), "Created and retrieved objects don't match");
    }

    //TODO:KMD Maybe I should get rid of the entry level
    @Test
    public void testToString() {
        var node = new ValueOrErrorFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestFieldTypeNode.createTestNode()
        );
        var expectedToStringOutput = """
        //ValueOrErrorFieldTypeNode
            //Entry
                //FieldTypeNode
                    Optional : false
                    //BoolFieldTypeNode
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }
}
