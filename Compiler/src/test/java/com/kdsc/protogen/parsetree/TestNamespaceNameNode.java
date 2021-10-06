package com.kdsc.protogen.parsetree;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestNamespaceNameNode extends BaseTestNode {

    @Test
    public void testCreateMinimal() {
        new NamespaceNameNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            List.of(TestNamespaceNode.createPopulatedTestNode()),
            TestNameNode.createPopulatedTestNode()
        );
    }

    @Test
    public void testCreatePopulated() {
        createPopulatedTestNode();
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
                TestNameNode.createPopulatedTestNode()
            )
        );

        assertThrows(IllegalArgumentException.class,
            () ->
            new NamespaceNameNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                Collections.emptyList(),
                TestNameNode.createPopulatedTestNode()
            )
        );

        assertThrows(NullPointerException.class,
            () ->
            new NamespaceNameNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                List.of(TestNamespaceNode.createPopulatedTestNode()),
                null
            )
        );
    }

    @Test
    public void testGetters() {
        List<NamespaceNode> namespaceNodes = List.of(TestNamespaceNode.createPopulatedTestNode());
        var nameNode = TestNameNode.createPopulatedTestNode();
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
        var node = createPopulatedTestNode();
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

    @Test
    public void testEquals() {
        var node1 = createPopulatedTestNode();
        var node2 = createPopulatedTestNode();
        assertEquals(node1, node2, "Expected objects to be equal");
        assertEquals(node1.toString(), node2.toString(), "Expected objects toString to be equal");
    }

    @Test
    public void testHashcode() {
        var node1Hashcode = createPopulatedTestNode().hashCode();
        var node2Hashcode = createPopulatedTestNode().hashCode();
        assertEquals(node1Hashcode, node2Hashcode, "Expected objects to be equal");
    }

    @Test
    public void testClone() {
        var node1 = createPopulatedTestNode();
        var node2 = node1.clone();
        assertEquals(node1, node2, "Expected cloned objects to be equal");
        assertEquals(node1.hashCode(), node2.hashCode(), "Expected cloned objects hashcode to be equal");
        assertEquals(node1.toString(), node2.toString(), "Expected cloned objects toString to be equal");
    }

    public static NamespaceNameNode createPopulatedTestNode() {
        return new NamespaceNameNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            List.of(TestNamespaceNode.createPopulatedTestNode()),
            TestNameNode.createPopulatedTestNode()
        );
    }

}