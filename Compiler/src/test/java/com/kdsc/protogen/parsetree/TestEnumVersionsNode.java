package com.kdsc.protogen.parsetree;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestEnumVersionsNode extends BaseTestNode {

    @Test
    public void testCreate() {
        new EnumVersionsNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            Collections.emptyList()
        );
    }

    @Test
    public void testInvalidConstructorCall() {

        assertThrows(NullPointerException.class,
            () ->
            new EnumVersionsNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                null
            )
        );
    }

    @Test
    public void testGetters() {
        List<EnumVersionNode> enumVersionNode = Collections.emptyList();
        var node = new EnumVersionsNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            enumVersionNode
        );
        assertEquals(enumVersionNode, node.getEnumVersionNodes(), "Created and retrieved objects don't match");
    }

    @Test
    public void testToString() {
        var node = new EnumVersionsNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            Collections.emptyList()
        );
        var expectedToStringOutput = """
        //EnumVersionsNode
            //Super -> //BaseParseTreeNode
                SourceFileName : TestFileName.pg
                Line : 1
                CharPosition : 0
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }

    public static EnumVersionsNode createTestNode() {
        return new EnumVersionsNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            Collections.emptyList()
        );
    }

}