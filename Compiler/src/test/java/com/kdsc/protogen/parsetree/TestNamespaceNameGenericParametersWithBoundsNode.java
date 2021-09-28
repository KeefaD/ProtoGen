package com.kdsc.protogen.parsetree;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestNamespaceNameGenericParametersWithBoundsNode extends BaseTestNode {

    @Test
    public void testCreate() {
        new NamespaceNameGenericParametersWithBoundsNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestNamespaceNameNode.createTestNode(),
            Optional.empty()
        );
    }

    @Test
    public void testNullsOrEmptiesInConstructor() {

        assertThrows(NullPointerException.class,
            () ->
            new NamespaceNameGenericParametersWithBoundsNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                null,
                Optional.empty()
            )
        );

        assertThrows(NullPointerException.class,
            () ->
            new NamespaceNameGenericParametersWithBoundsNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                TestNamespaceNameNode.createTestNode(),
                null
            )
        );
    }

    @Test
    public void testGetters() {
        var name = TestNamespaceNameNode.createTestNode();
        Optional<GenericParametersWithBoundsNode> genericParametersWithBounds = Optional.empty();
        var node = new NamespaceNameGenericParametersWithBoundsNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            name,
            genericParametersWithBounds
        );
        assertEquals(name, node.getNamespaceNameNode(), "Created and retrieved objects don't match");
        assertEquals(genericParametersWithBounds, node.getGenericParametersWithBoundsNode(), "Created and retrieved objects don't match");
    }

    @Test
    public void testToString() {
        var node = new NamespaceNameGenericParametersWithBoundsNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestNamespaceNameNode.createTestNode(),
            Optional.empty()
        );
        var expectedToStringOutput = """
        //NamespaceNameGenericParametersWithBoundsNode
            //NamespaceNameNode
                //NamespaceNode
                    Namespace : Namespace
                //NameNode
                    Name : Name
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }

    public static NamespaceNameGenericParametersWithBoundsNode createTestNode() {
        return new NamespaceNameGenericParametersWithBoundsNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestNamespaceNameNode.createTestNode(),
            Optional.empty()
        );
    }

}