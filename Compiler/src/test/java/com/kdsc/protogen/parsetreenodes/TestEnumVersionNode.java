package com.kdsc.protogen.parsetreenodes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public final class TestEnumVersionNode extends BaseTestNode {

    @Test
    public void testCreateMinimal() {
        new EnumVersionNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestVersionNumberNode.createPopulatedTestNode(),
            TestEnumCasesNode.createPopulatedTestNode()
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
            new EnumVersionNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                null,
                TestEnumCasesNode.createPopulatedTestNode()
            )
        );

        assertThrows(
            NullPointerException.class,
            () ->
            new EnumVersionNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                TestVersionNumberNode.createPopulatedTestNode(),
                null
            )
        );
    }

    @Test
    public void testGetters() {
        var versionNumberNode = TestVersionNumberNode.createPopulatedTestNode();
        EnumCasesNode enumCases = TestEnumCasesNode.createPopulatedTestNode();
        var node = new EnumVersionNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            versionNumberNode,
            enumCases
        );
        assertEquals(versionNumberNode, node.getVersionNumberNode(), "Created and retrieved objects don't match");
        assertEquals(enumCases, node.getEnumCasesNode(), "Created and retrieved objects don't match");
    }

    @Test
    public void testToString() {
        var node = createPopulatedTestNode();
        var expectedToStringOutput = """
        //EnumVersionNode
            //Super -> //BaseParseTreeNode
                SourceFileName : TestFileName.pg
                Line : 1
                CharPosition : 0
            //VersionNumberNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : TestFileName.pg
                    Line : 1
                    CharPosition : 0
                VersionNumber : 1
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

    public static EnumVersionNode createPopulatedTestNode() {
        return new EnumVersionNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestVersionNumberNode.createPopulatedTestNode(),
            TestEnumCasesNode.createPopulatedTestNode()
        );
    }

}