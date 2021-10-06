package com.kdsc.protogen.parsetree;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGenericParametersWithBoundsNode extends BaseTestNode {

    @Test
    public void testCreateMinimal() {
        new GenericParametersWithBoundsNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            Collections.emptyList()
        );
    }

    @Test
    public void testCreatePopulated() {
        createPopulatedTestNode();
    }

    @Test
    public void testInvalidConstructorCall() {

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
        var node = createPopulatedTestNode();
        var expectedToStringOutput = """
        //GenericParametersWithBoundsNode
            //Super -> //BaseParseTreeNode
                SourceFileName : TestFileName.pg
                Line : 1
                CharPosition : 0
            //GenericParameterWithBoundsNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : TestFileName.pg
                    Line : 1
                    CharPosition : 0
                Identifier : T
                //NamespaceNameGenericParametersNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : TestFileName.pg
                        Line : 1
                        CharPosition : 0
                    //NamespaceNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : TestFileName.pg
                            Line : 1
                            CharPosition : 0
                        //NamespaceNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : TestFileName.pg
                                Line : 1
                                CharPosition : 0
                            Namespace : Namespace
                        //NameNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : TestFileName.pg
                                Line : 1
                                CharPosition : 0
                            Name : Name
                    //GenericParametersNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : TestFileName.pg
                            Line : 1
                            CharPosition : 0
                        //FieldTypeNode
                            //Super -> //BaseParseTreeNode
                                SourceFileName : TestFileName.pg
                                Line : 1
                                CharPosition : 0
                            Optional : false
                            //BoolFieldTypeNode
                                //Super -> //NonArrayFieldTypeNode
                                    //Super -> //BaseParseTreeNode
                                        SourceFileName : TestFileName.pg
                                        Line : 1
                                        CharPosition : 0
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }

    @Test
    public void testEquals() {
        var node1 = createPopulatedTestNode();
        var node2 = createPopulatedTestNode();
        assertEquals(node1, node2, "Expected objects to be equal");
    }

    @Test
    public void testHashcode() {
        var node1Hashcode = createPopulatedTestNode().hashCode();
        var node2Hashcode = createPopulatedTestNode().hashCode();
        assertEquals(node1Hashcode, node2Hashcode, "Expected objects to be equal");
    }

    @Test
    public void testClone() {
        var node1 = createPopulatedTestNode();
        var node2 = node1.clone();
        assertEquals(node1, node2, "Expected cloned objects to be equal");
        assertEquals(node1.hashCode(), node2.hashCode(), "Expected cloned objects hashcode to be equal");
    }

    public static GenericParametersWithBoundsNode createPopulatedTestNode() {
        return new GenericParametersWithBoundsNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            List.of(TestGenericParameterWithBoundsNode.createPopulatedTestNode())
        );
    }

}