package com.kdsc.protogen.parsetree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestFieldNameNode extends BaseTestNode {

    @Test
    public void testCreate() {
        new FieldNameNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            "FieldName"
        );
    }

    @Test
    public void testInvalidConstructorCall() {

        assertThrows(NullPointerException.class,
            () ->
            new FieldNameNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                null
            )
        );

        assertThrows(IllegalArgumentException.class,
            () ->
            new FieldNameNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                ""
            )
        );
    }

    @Test
    public void testGetters() {
        var fieldName = "FieldName";
        var node = new FieldNameNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            "FieldName"
        );
        assertEquals(fieldName, node.getFieldName(), "Created and retrieved objects don't match");
    }

    @Test
    public void testToString() {
        var node = new FieldNameNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            "FieldName"
        );
        var expectedToStringOutput = """
        //FieldNameNode
            //Super -> //BaseParseTreeNode
                SourceFileName : TestFileName.pg
                Line : 1
                CharPosition : 0
            FieldName : FieldName
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }

    public static FieldNameNode createTestNode() {
        return new FieldNameNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            "FieldName"
        );
    }

}