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
    public void testInvalidConstructorCall() {

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
            //Super -> //NonArrayFieldTypeNode
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
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }

}