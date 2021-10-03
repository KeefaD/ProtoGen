package com.kdsc.protogen.parsetree.fieldtypenodes;

import com.kdsc.protogen.parsetree.BaseTestNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBoolFieldTypeNode extends BaseTestNode {

    @Test
    public void testCreate() {
        new BoolFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition
        );
    }

    @Test
    public void testToString() {
        var node = new BoolFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition
        );
        var expectedToStringOutput = """
        //BoolFieldTypeNode
            //Super -> //NonArrayFieldTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : TestFileName.pg
                    Line : 1
                    CharPosition : 0
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }

    public static BoolFieldTypeNode createTestNode() {
        return new BoolFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition
        );
    }

}