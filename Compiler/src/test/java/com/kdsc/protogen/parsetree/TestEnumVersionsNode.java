package com.kdsc.protogen.parsetree;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    public void testNullsOrEmptiesInConstructor() {

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
        assertEquals(enumVersionNode, node.getEnumVersionNode(), "Created and retrieved objects don't match");
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
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }
}
