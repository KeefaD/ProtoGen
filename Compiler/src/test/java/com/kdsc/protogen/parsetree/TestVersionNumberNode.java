package com.kdsc.protogen.parsetree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestVersionNumberNode extends BaseTestNode {

    @Test
    public void testCreate() {
        new VersionNumberNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            1
        );
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
        var node = new VersionNumberNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            1
        );
        var expectedToStringOutput = """
        //VersionNumberNode
            VersionNumber : 1
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }

    public static VersionNumberNode createTestNode() {
        return new VersionNumberNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            1
        );
    }
}
