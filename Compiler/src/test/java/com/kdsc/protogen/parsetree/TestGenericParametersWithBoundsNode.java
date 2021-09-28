package com.kdsc.protogen.parsetree;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestGenericParametersWithBoundsNode extends BaseTestNode {

    @Test
    public void testCreate() {
        new GenericParametersWithBoundsNode(
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
            new GenericParametersWithBoundsNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                null
            )
        );
    }

    @Test
    public void testGetters() {
        List<GenericParameterWithBoundsNode> genericParameterWithBoundsNodes = Collections.emptyList();
        var node = new GenericParametersWithBoundsNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            genericParameterWithBoundsNodes
        );
        assertEquals(genericParameterWithBoundsNodes, node.getGenericParameterWithBoundsNodes(), "Created and retrieved objects don't match");
    }

    @Test
    public void testToString() {
        var node = new GenericParametersWithBoundsNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            Collections.emptyList()
        );
        var expectedToStringOutput = """
        //GenericParametersWithBoundsNode
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }
}