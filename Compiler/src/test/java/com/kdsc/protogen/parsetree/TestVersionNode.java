package com.kdsc.protogen.parsetree;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestVersionNode extends BaseTestNode {

    @Test
    public void testCreate() {
        new VersionNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestVersionNumberNode.createTestNode(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty()
        );
    }

    @Test
    public void testInvalidConstructorCall() {

        assertThrows(NullPointerException.class,
            () ->
            new VersionNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                null,
                Optional.empty(),
                Optional.empty(),
                Optional.empty()
            )
        );

        assertThrows(NullPointerException.class,
            () ->
            new VersionNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                TestVersionNumberNode.createTestNode(),
                null,
                Optional.empty(),
                Optional.empty()
            )
        );

        assertThrows(NullPointerException.class,
            () ->
                new VersionNode(
                    BaseTestNode.fileName,
                    BaseTestNode.line,
                    BaseTestNode.charPosition,
                    TestVersionNumberNode.createTestNode(),
                    Optional.empty(),
                    null,
                    Optional.empty()
                )
        );

        assertThrows(NullPointerException.class,
            () ->
            new VersionNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                TestVersionNumberNode.createTestNode(),
                Optional.empty(),
                Optional.empty(),
                null
            )
        );
    }

    @Test
    public void testGetters() {
        var versionNumberNode = TestVersionNumberNode.createTestNode();
        Optional<FieldsNode> fieldsNode = Optional.empty();
        Optional<GenericParametersWithBoundsNode> genericParametersWithBoundsNode = Optional.empty();
        Optional<ImplementsListNode> implementsListNode = Optional.empty();
        var node = new VersionNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            versionNumberNode,
            genericParametersWithBoundsNode,
            implementsListNode,
            fieldsNode
        );
        assertEquals(versionNumberNode, node.getVersionNumberNode(), "Created and retrieved objects don't match");
        assertEquals(genericParametersWithBoundsNode, node.getGenericParametersWithBoundsNode(), "Created and retrieved objects don't match");
        assertEquals(implementsListNode, node.getImplementsListNode(), "Created and retrieved objects don't match");
        assertEquals(fieldsNode, node.getFieldsNode(), "Created and retrieved objects don't match");
    }

    @Test
    public void testToString() {
        var node = new VersionNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestVersionNumberNode.createTestNode(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty()
        );
        var expectedToStringOutput = """
        //VersionNode
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
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }

}