package com.kdsc.protogen.parsetree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestVersionNumberNode extends BaseTestNode {

    @Test
    public void testCreateMinimal() {
        new VersionNumberNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            1
        );
    }

    @Test
    public void testCreatePopulated() {
        createPopulatedTestNode();
    }

    @Test
    public void testGetters() {
        var versionNumber = 1;
        var node = new VersionNumberNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            1
        );
        assertEquals(versionNumber, node.getVersionNumber(), "Created and retrieved objects don't match");
    }

    @Test
    public void testToString() {
        var node = createPopulatedTestNode();
        var expectedToStringOutput = """
        //VersionNumberNode
            //Super -> //BaseParseTreeNode
                SourceFileName : TestFileName.pg
                Line : 1
                CharPosition : 0
            VersionNumber : 1
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }

    @Test
    public void testEquals() {
        var node1 = createPopulatedTestNode();
        var node2 = createPopulatedTestNode();
        assertEquals(node1, node2, "Expected objects to be equal");
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
    }

    public static VersionNumberNode createPopulatedTestNode() {
        return new VersionNumberNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            1
        );
    }

}