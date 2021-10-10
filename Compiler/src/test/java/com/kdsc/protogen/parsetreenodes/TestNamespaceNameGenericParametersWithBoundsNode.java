package com.kdsc.protogen.parsetreenodes;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public final class TestNamespaceNameGenericParametersWithBoundsNode extends BaseTestNode {

    @Test
    public void testCreateMinimal() {
        new NamespaceNameGenericParametersWithBoundsNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestNamespaceNameNode.createPopulatedTestNode(),
            Optional.empty()
        );
    }

    @Test
    public void testCreatePopulated() {
        createPopulatedTestNode();
    }

    @Test
    public void testInvalidConstructorCall() {

        assertThrows(
            NullPointerException.class,
            () ->
            new NamespaceNameGenericParametersWithBoundsNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                null,
                Optional.empty()
            )
        );

        assertThrows(
            NullPointerException.class,
            () ->
            new NamespaceNameGenericParametersWithBoundsNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                TestNamespaceNameNode.createPopulatedTestNode(),
                null
            )
        );
    }

    @Test
    public void testGetters() {
        var name = TestNamespaceNameNode.createPopulatedTestNode();
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
        var node = createPopulatedTestNode();
        var expectedToStringOutput = """
        //NamespaceNameGenericParametersWithBoundsNode
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
        assertEquals(node1.toString(), node2.toString(), "Expected objects toString to be equal");
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
        assertEquals(node1.toString(), node2.toString(), "Expected cloned objects toString to be equal");
    }

    public static NamespaceNameGenericParametersWithBoundsNode createPopulatedTestNode() {
        return new NamespaceNameGenericParametersWithBoundsNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestNamespaceNameNode.createPopulatedTestNode(),
            Optional.of(TestGenericParametersWithBoundsNode.createPopulatedTestNode())
        );
    }

}