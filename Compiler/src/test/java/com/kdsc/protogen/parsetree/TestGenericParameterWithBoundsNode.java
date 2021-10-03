package com.kdsc.protogen.parsetree;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestGenericParameterWithBoundsNode extends BaseTestNode {

    @Test
    public void testCreate() {
        new GenericParameterWithBoundsNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            "T",
            Collections.emptyList()
        );
    }

    @Test
    public void testInvalidConstructorCall() {

        assertThrows(NullPointerException.class,
            () ->
                new GenericParameterWithBoundsNode(
                    BaseTestNode.fileName,
                    BaseTestNode.line,
                    BaseTestNode.charPosition,
                    null,
                    Collections.emptyList()
                )
        );

        assertThrows(IllegalArgumentException.class,
            () ->
            new GenericParameterWithBoundsNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                "",
                Collections.emptyList()
            )
        );

        assertThrows(NullPointerException.class,
            () ->
            new GenericParameterWithBoundsNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                "T",
                null
            )
        );
    }

    @Test
    public void testGetters() {
        var identifier = "T";
        List<NamespaceNameGenericParametersNode> namespaceNameGenericParametersNodes = Collections.emptyList();
        var node = new GenericParameterWithBoundsNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            identifier,
                namespaceNameGenericParametersNodes
        );
        assertEquals(identifier, node.getIdentifier(), "Created and retrieved objects don't match");
        assertEquals(namespaceNameGenericParametersNodes, node.getNamespaceNameGenericParametersNodes(), "Created and retrieved objects don't match");
    }

    @Test
    public void testToString() {
        var node = new GenericParameterWithBoundsNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            "T",
            Collections.emptyList()
        );
        var expectedToStringOutput = """
        //GenericParameterWithBoundsNode
            //Super -> //BaseParseTreeNode
                SourceFileName : TestFileName.pg
                Line : 1
                CharPosition : 0
            Identifier : T
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }

}