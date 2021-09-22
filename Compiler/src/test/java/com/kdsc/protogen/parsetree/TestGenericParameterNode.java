package com.kdsc.protogen.parsetree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestGenericParameterNode extends BaseTestNode {

    @Test
    public void testCreate() {
        new GenericParameterNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            "T"
        );
    }

    @Test
    public void testNullsOrEmptiesInConstructor() {

        assertThrows(NullPointerException.class,
            () ->
                new GenericParameterNode(
                    BaseTestNode.fileName,
                    BaseTestNode.line,
                    BaseTestNode.charPosition,
                    null
                )
        );

        assertThrows(IllegalArgumentException.class,
            () ->
            new GenericParameterNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                ""
            )
        );

    }

    @Test
    public void testGetters() {
        var identifier = "T";
        var node = new GenericParameterNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            identifier
        );
        assertEquals(identifier, node.getIdentifier(), "Created and retrieved objects don't match");
    }

    @Test
    public void testToString() {
        var node = new GenericParameterNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            "T"
        );
        var expectedToStringOutput = """
        //GenericParameterNode
            Identifier : T
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }

    public static GenericParameterNode createTestNode() {
        return new GenericParameterNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            "T"
        );
    }

}