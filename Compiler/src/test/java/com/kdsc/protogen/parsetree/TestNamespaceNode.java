package com.kdsc.protogen.parsetree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestNamespaceNode extends BaseTestNode {

    @Test
    public void testCreate() {
        new NamespaceNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            "Namespace"
        );
    }

    @Test
    public void testInvalidConstructorCall() {

        assertThrows(NullPointerException.class,
            () ->
            new NamespaceNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                null
            )
        );

        assertThrows(IllegalArgumentException.class,
            () ->
            new NamespaceNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                ""
            )
        );
    }

    @Test
    public void testGetters() {
        var namespace = "Namespace";
        var node = new NamespaceNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            namespace
        );
        assertEquals(namespace, node.getNamespace(), "Created and retrieved objects don't match");
    }

    @Test
    public void testToString() {
        var node = new NamespaceNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            "Namespace"
        );
        var expectedToStringOutput = """
        //NamespaceNode
            //Super -> //BaseParseTreeNode
                SourceFileName : TestFileName.pg
                Line : 1
                CharPosition : 0
            Namespace : Namespace
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }

    public static NamespaceNode createTestNode() {
        return new NamespaceNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            "Namespace"
        );
    }

}