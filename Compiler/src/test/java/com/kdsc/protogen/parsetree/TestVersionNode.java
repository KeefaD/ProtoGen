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
            Optional.empty()
        );
    }

    @Test
    public void testNullsOrEmptiesInConstructor() {

        assertThrows(NullPointerException.class,
            () ->
            new VersionNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
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
                null
            )
        );
    }

    @Test
    public void testGetters() {
        var versionNumberNode = TestVersionNumberNode.createTestNode();
        Optional<FieldsNode> fieldsNode = Optional.empty();
        var node = new VersionNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            versionNumberNode,
            fieldsNode
        );
        assertEquals(versionNumberNode, node.getVersionNumberNode(), "Created and retrieved objects don't match");
        assertEquals(fieldsNode, node.getFieldsNode(), "Created and retrieved objects don't match");
    }

    @Test
    public void testToString() {
        var node = new VersionNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestVersionNumberNode.createTestNode(),
            Optional.empty()
        );
        var expectedToStringOutput = """
        //VersionNode
            //VersionNumberNode
                VersionNumber : 1
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }

}
