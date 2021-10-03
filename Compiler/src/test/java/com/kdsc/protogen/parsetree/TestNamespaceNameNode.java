package com.kdsc.protogen.parsetree;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestNamespaceNameNode extends BaseTestNode {

    @Test
    public void testCreate() {
        new NamespaceNameNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            List.of(TestNamespaceNode.createTestNode()),
            TestNameNode.createTestNode()
        );
    }

    @Test
    public void testInvalidConstructorCall() {

        assertThrows(NullPointerException.class,
            () ->
            new NamespaceNameNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                null,
                TestNameNode.createTestNode()
            )
        );

        assertThrows(IllegalArgumentException.class,
            () ->
            new NamespaceNameNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                Collections.emptyList(),
                TestNameNode.createTestNode()
            )
        );

        assertThrows(NullPointerException.class,
            () ->
            new NamespaceNameNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                List.of(TestNamespaceNode.createTestNode()),
                null
            )
        );
    }

    @Test
    public void testGetters() {
        List<NamespaceNode> namespaceNodes = List.of(TestNamespaceNode.createTestNode());
        var nameNode = TestNameNode.createTestNode();
        var node = new NamespaceNameNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            namespaceNodes,
            nameNode
        );
        assertEquals(namespaceNodes, node.getNamespaceNodes(), "Created and retrieved objects don't match");
        assertEquals(nameNode, node.getNameNode(), "Created and retrieved objects don't match");
    }

    @Test
    public void testToString() {
        var node = new NamespaceNameNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            List.of(TestNamespaceNode.createTestNode()),
            TestNameNode.createTestNode()
        );
        var expectedToStringOutput = """
        //NamespaceNameNode
            //Super -> //BaseParseTreeNode
                SourceFileName : TestFileName.pg
                Line : 1
                CharPosition : 0
            //NamespaceNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : TestFileName.pg
                    Line : 1
                    CharPosition : 0
                Namespace : Namespace
            //NameNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : TestFileName.pg
                    Line : 1
                    CharPosition : 0
                Name : Name
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }

    public static NamespaceNameNode createTestNode() {
        return new NamespaceNameNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            List.of(TestNamespaceNode.createTestNode()),
            TestNameNode.createTestNode()
        );
    }

}