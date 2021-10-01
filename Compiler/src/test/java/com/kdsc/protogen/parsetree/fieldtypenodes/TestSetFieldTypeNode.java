package com.kdsc.protogen.parsetree.fieldtypenodes;

import com.kdsc.protogen.parsetree.BaseTestNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//TODO:KMD Should probably test objects where everything that can be empty is NOT empty
public class TestSetFieldTypeNode extends BaseTestNode {

    @Test
    public void testCreate() {
        new SetFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestFieldTypeNode.createTestNode()
        );
    }

    @Test
    public void testInvalidConstructorCall() {

        assertThrows(NullPointerException.class,
            () ->
            new SetFieldTypeNode(
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
        var node = new SetFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            entryFieldTypeNode
        );
        assertEquals(entryFieldTypeNode, node.getFieldTypeNode(), "Created and retrieved objects don't match");
    }

    @Test
    public void testToString() {
        var node = new SetFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestFieldTypeNode.createTestNode()
        );
        var expectedToStringOutput = """
        //SetFieldTypeNode
            //FieldTypeNode
                Optional : false
                //BoolFieldTypeNode
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }
}