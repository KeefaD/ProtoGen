package com.kdsc.protogen.parsetree.fieldtypenodes;

import com.kdsc.protogen.parsetree.BaseTestNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDateTimeFieldTypeNode extends BaseTestNode {

    @Test
    public void testCreate() {
        new DateTimeFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition
        );
    }

    @Test
    public void testToString() {
        var node = new DateTimeFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition
        );
        var expectedToStringOutput = """
        //DateTimeFieldTypeNode
            //Super -> //NonArrayFieldTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : TestFileName.pg
                    Line : 1
                    CharPosition : 0
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }

}