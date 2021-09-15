package com.kdsc.protogen.parsetree;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestEnumCasesNode extends BaseTestNode {

    @Test
    public void testCreate() {
        new EnumCasesNode(
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
            new EnumCasesNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                null
            )
        );
    }

    @Test
    public void testGetters() {
        List<EnumNameNode> enumNamesNodes = Collections.emptyList();
        var node = new EnumCasesNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            enumNamesNodes
        );
        assertEquals(enumNamesNodes, node.getEnumNameNodes(), "Created and retrieved objects don't match");
    }

    @Test
    public void testToString() {
        var node = new EnumCasesNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            Collections.emptyList()
        );
        var expectedToStringOutput = """
        //EnumCasesNode
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }
}
