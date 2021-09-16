package com.kdsc.protogen.parsetree;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestGenericParametersWithoutBoundsNode extends BaseTestNode {

    @Test
    public void testCreate() {
        new GenericParametersWithoutBoundsNode(
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
            new GenericParametersWithoutBoundsNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                null
            )
        );
    }

    @Test
    public void testGetters() {
        List<GenericParameterWithoutBoundsNode> genericParameterWithBoundsNodes = Collections.emptyList();
        var node = new GenericParametersWithoutBoundsNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            genericParameterWithBoundsNodes
        );
        assertEquals(genericParameterWithBoundsNodes, node.getGenericParameterWithoutBoundsNodes(), "Created and retrieved objects don't match");
    }

    @Test
    public void testToString() {
        var node = new GenericParametersWithoutBoundsNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            Collections.emptyList()
        );
        var expectedToStringOutput = """
        //GenericParametersWithoutBoundsNode
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }
}
