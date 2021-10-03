package com.kdsc.protogen.parsetree;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestFileNode extends BaseTestNode {

    @Test
    public void testCreate() {
        new FileNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            Collections.emptyList(),
            Collections.emptyList(),
            Collections.emptyList()
        );
    }

    @Test
    public void testInvalidConstructorCall() {

        assertThrows(NullPointerException.class,
            () ->
            new FileNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                null,
                Collections.emptyList(),
                Collections.emptyList()
            )
        );

        assertThrows(NullPointerException.class,
            () ->
            new FileNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                Collections.emptyList(),
                null,
                Collections.emptyList()
            )
        );

        assertThrows(NullPointerException.class,
            () ->
            new FileNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                Collections.emptyList(),
                Collections.emptyList(),
                null
            )
        );
    }

    @Test
    public void testGetters() {
        List<ProtoGenTypeNode> typeNodes = Collections.emptyList();
        List<ProtoGenKeyNode> keyNodes = Collections.emptyList();
        List<ProtoGenEnumNode> enumNodes = Collections.emptyList();
        var node = new FileNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            typeNodes,
            keyNodes,
            enumNodes
        );
        assertEquals(typeNodes, node.getProtoGenTypeNodes(), "Created and retrieved objects don't match");
        assertEquals(keyNodes, node.getProtoGenKeyNodes(), "Created and retrieved objects don't match");
        assertEquals(enumNodes, node.getProtoGenEnumNodes(), "Created and retrieved objects don't match");
    }

    @Test
    public void testToString() {
        var node = new FileNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            Collections.emptyList(),
            Collections.emptyList(),
            Collections.emptyList()
        );
        var expectedToStringOutput = """
        //FileNode
            //Super -> //BaseParseTreeNode
                SourceFileName : TestFileName.pg
                Line : 1
                CharPosition : 0
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }

}