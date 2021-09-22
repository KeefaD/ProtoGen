package com.kdsc.protogen.parsetree;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestNamespaceNameGenericParametersNode extends BaseTestNode {

    @Test
    public void testCreate() {
        new NamespaceNameGenericParametersNode(
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
            new NamespaceNameGenericParametersNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                null,
                Optional.empty()
            )
        );

        assertThrows(NullPointerException.class,
            () ->
            new NamespaceNameGenericParametersNode(
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
        Optional<GenericParametersNode> genericParameters = Optional.empty();
        var node = new NamespaceNameGenericParametersNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            name,
            genericParameters
        );
        assertEquals(name, node.getNamespaceNameNode(), "Created and retrieved objects don't match");
        assertEquals(genericParameters, node.getGenericParametersNode(), "Created and retrieved objects don't match");
    }

    @Test
    public void testToString() {
        var node = new NamespaceNameGenericParametersNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestNamespaceNameNode.createTestNode(),
            Optional.empty()
        );
        var expectedToStringOutput = """
        //NamespaceNameGenericParametersNode
            //NamespaceNameNode
                //NamespaceNode
                    Namespace : Namespace
                //NameNode
                    Name : Name
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }

    public static NamespaceNameGenericParametersNode createTestNode() {
        return new NamespaceNameGenericParametersNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestNamespaceNameNode.createTestNode(),
            Optional.empty()
        );
    }

}
