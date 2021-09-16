package com.kdsc.protogen.parsetree;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestNamespaceNameGenericParametersWithoutBoundsNode extends BaseTestNode {

    @Test
    public void testCreate() {
        new NamespaceNameGenericParametersWithoutBoundsNode(
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
            new NamespaceNameGenericParametersWithoutBoundsNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                null,
                Optional.empty()
            )
        );

        assertThrows(NullPointerException.class,
            () ->
            new NamespaceNameGenericParametersWithoutBoundsNode(
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
        Optional<GenericParametersWithoutBoundsNode> genericParametersWithoutBounds = Optional.empty();
        var node = new NamespaceNameGenericParametersWithoutBoundsNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            name,
            genericParametersWithoutBounds
        );
        assertEquals(name, node.getNamespaceNameNode(), "Created and retrieved objects don't match");
        assertEquals(genericParametersWithoutBounds, node.getGenericParametersWithoutBoundsNode(), "Created and retrieved objects don't match");
    }

    @Test
    public void testToString() {
        var node = new NamespaceNameGenericParametersWithoutBoundsNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestNamespaceNameNode.createTestNode(),
            Optional.empty()
        );
        var expectedToStringOutput = """
        //NamespaceNameGenericParametersWithoutBoundsNode
            //NamespaceNameNode
                //NamespaceNode
                    Namespace : Namespace
                //NameNode
                    Name : Name
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }

    public static NamespaceNameGenericParametersWithoutBoundsNode createTestNode() {
        return new NamespaceNameGenericParametersWithoutBoundsNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestNamespaceNameNode.createTestNode(),
            Optional.empty()
        );
    }

}
