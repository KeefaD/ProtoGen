package com.kdsc.protogen.parsetree.fieldtypenodes;

import com.kdsc.protogen.parsetree.BaseTestNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLocalDatetimeFieldTypeNode extends BaseTestNode {

    @Test
    public void testCreate() {
        new LocalDatetimeFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition
        );
    }

    @Test
    public void testToString() {
        var node = new LocalDatetimeFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition
        );
        var expectedToStringOutput = """
        //LocalDatetimeFieldTypeNode
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }

}