package com.kdsc.protogen.parsetree;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestVersionsNode extends BaseTestNode {

    @Test
    public void testCreate() {
        new VersionsNode(
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
            new VersionsNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                null
            )
        );
    }

    @Test
    public void testGetters() {
        List<VersionNode> versionNodes = Collections.emptyList();
        var node = new VersionsNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            versionNodes
        );
        assertEquals(versionNodes, node.getVersionNodes(), "Created and retrieved objects don't match");
    }

    @Test
    public void testToString() {
        var node = new VersionsNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            Collections.emptyList()
        );
        var expectedToStringOutput = """
        //VersionsNode
            //Super -> //BaseParseTreeNode
                SourceFileName : TestFileName.pg
                Line : 1
                CharPosition : 0
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }

}