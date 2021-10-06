package com.kdsc.protogen.parsetree.fieldtypenodes;

import com.kdsc.protogen.parsetree.*;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TestFieldTypeNode extends BaseTestNode {

    @Test
    public void testCreateMinimal() {
        new FieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            false,
            Optional.empty(),
            Optional.of(TestBoolFieldTypeNode.createPopulatedTestNode())
        );
    }

    @Test
    public void testCreatePopulatedWithArrayFieldTypeNode() {
        new FieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            false,
            Optional.of(TestArrayFieldTypeNode.createPopulatedTestNode()),
            Optional.empty()
        );
    }

    @Test
    public void testCreatePopulatedWithNonArrayFieldTypeNode() {
        createPopulatedTestNode();
    }

    @Test
    public void testInvalidConstructorCall() {

        assertThrows(NullPointerException.class,
            () ->
            new FieldTypeNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                false,
                null,
                Optional.empty()
            )
        );

        assertThrows(NullPointerException.class,
            () ->
            new FieldTypeNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                false,
                Optional.empty(),
                null
            )
        );

        assertThrows(NullPointerException.class,
            () ->
            new FieldTypeNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                false,
                null,
                null
            )
        );

        assertThrows(IllegalArgumentException.class,
            () ->
            new FieldTypeNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                false,
                Optional.of(TestArrayFieldTypeNode.createPopulatedTestNode()),
                Optional.of(TestBoolFieldTypeNode.createPopulatedTestNode())
            )
        );
    }

    @Test
    public void testGetters() {
        var optional = true;
        Optional<ArrayFieldTypeNode> arrayFieldTypeNodes = Optional.empty();
        Optional<NonArrayFieldTypeNode> nonArrayFieldTypeNodes = Optional.of(TestBoolFieldTypeNode.createPopulatedTestNode());
        var node = new FieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            optional,
            arrayFieldTypeNodes,
            nonArrayFieldTypeNodes
        );
        assertEquals(optional, node.isOptional(), "Created and retrieved objects don't match");
        assertEquals(arrayFieldTypeNodes, node.getArrayFieldTypeNode(), "Created and retrieved objects don't match");
        assertEquals(nonArrayFieldTypeNodes, node.getNonArrayFieldTypeNode(), "Created and retrieved objects don't match");
    }

    @Test
    public void testToString() {
        var node = createPopulatedTestNode();
        var expectedToStringOutput = """
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

    public static FieldTypeNode createPopulatedTestNode() {
        return new FieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            false,
            Optional.empty(),
            Optional.of(TestBoolFieldTypeNode.createPopulatedTestNode())
        );
    }

}