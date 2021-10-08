package com.kdsc.protogen.parsetreenodes;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public final class TestEnumNode extends BaseTestNode {

    @Test
    public void testCreateMinimal() {
        new EnumNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestNamespaceNameNode.createPopulatedTestNode(),
            Optional.empty(),
            Optional.of(TestEnumCasesNode.createPopulatedTestNode())
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
            new EnumNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                null,
                Optional.empty(),
                Optional.empty()
            )
        );

        assertThrows(NullPointerException.class,
            () ->
            new EnumNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                TestNamespaceNameNode.createPopulatedTestNode(),
                null,
                Optional.empty()
            )
        );

        assertThrows(NullPointerException.class,
            () ->
            new EnumNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                TestNamespaceNameNode.createPopulatedTestNode(),
                Optional.empty(),
                null
            )
        );

        assertThrows(IllegalArgumentException.class,
            () ->
            new EnumNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                TestNamespaceNameNode.createPopulatedTestNode(),
                Optional.of(TestEnumVersionsNode.createPopulatedTestNode()),
                Optional.of(TestEnumCasesNode.createPopulatedTestNode())
            )
        );
    }

    @Test
    public void testGetters() {
        var namespaceName = TestNamespaceNameNode.createPopulatedTestNode();
        Optional<EnumVersionsNode> enumVersions = Optional.empty();
        Optional<EnumCasesNode> enumCases = Optional.of(TestEnumCasesNode.createPopulatedTestNode());
        var node = new EnumNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            namespaceName,
            enumVersions,
            enumCases
        );
        assertEquals(namespaceName, node.getNamespaceNameNode(), "Created and retrieved objects don't match");
        assertEquals(enumVersions, node.getEnumVersionsNode(), "Created and retrieved objects don't match");
        assertEquals(enumCases, node.getEnumCasesNode(), "Created and retrieved objects don't match");
    }

    @Test
    public void testHasNamespaceNameGetters() {
        var namespaceName = TestNamespaceNameNode.createPopulatedTestNode();
        Optional<EnumVersionsNode> enumVersions = Optional.empty();
        Optional<EnumCasesNode> enumCases = Optional.of(TestEnumCasesNode.createPopulatedTestNode());
        var node = new EnumNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            namespaceName,
            enumVersions,
            enumCases
        );
        assertEquals(namespaceName, node.getNamespaceNameNode(), "Created and retrieved objects don't match");
        assertEquals(namespaceName.getNamespaceNodes(), node.getNamespaceNodes(), "Created and retrieved objects don't match");
        assertEquals(namespaceName.getNameNode(), node.getNameNode(), "Created and retrieved objects don't match");
    }

    @Test
    public void testIsLibraryNode() {
        var node = createPopulatedTestNode();
        assertFalse(node.isLibraryNode(), "Expected isLibraryNode to return false for the time being");
    }

    @Test
    public void testToString() {
        var node = createPopulatedTestNode();
        var expectedToStringOutput = """
        //EnumNode
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
            //EnumCasesNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : TestFileName.pg
                    Line : 1
                    CharPosition : 0
                //EnumNameNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : TestFileName.pg
                        Line : 1
                        CharPosition : 0
                    EnumName : EnumName
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

    public static EnumNode createPopulatedTestNode() {
        return new EnumNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestNamespaceNameNode.createPopulatedTestNode(),
            Optional.empty(),
            Optional.of(TestEnumCasesNode.createPopulatedTestNode())
        );
    }

}