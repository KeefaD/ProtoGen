package com.kdsc.protogen.parsetree.fieldtypenodes;

import com.kdsc.protogen.parsetree.*;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestFieldTypeNode extends BaseTestNode {

    @Test
    public void testCreate() {
        new FieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            false,
            Optional.empty(),
            Optional.of(TestBoolFieldTypeNode.createTestNode())
        );
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
    }

    @Test
    public void testGetters() {
        var optional = true;
        Optional<ArrayFieldTypeNode> arrayFieldTypeNodes = Optional.empty();
        Optional<NonArrayFieldTypeNode> nonArrayFieldTypeNodes = Optional.of(TestBoolFieldTypeNode.createTestNode());
        @SuppressWarnings("ConstantConditions") var node = new FieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            optional,
            arrayFieldTypeNodes,
            nonArrayFieldTypeNodes
        );
        //noinspection ConstantConditions
        assertEquals(optional, node.isOptional(), "Created and retrieved objects don't match");
        assertEquals(arrayFieldTypeNodes, node.getArrayFieldTypeNode(), "Created and retrieved objects don't match");
        assertEquals(nonArrayFieldTypeNodes, node.getNonArrayFieldTypeNode(), "Created and retrieved objects don't match");
    }

    @Test
    public void testToString() {
        var node = new FieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            false,
            Optional.empty(),
            Optional.of(TestBoolFieldTypeNode.createTestNode())
        );
        var expectedToStringOutput = """
        //FieldTypeNode
            Optional : false
            //BoolFieldTypeNode
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }

    public static FieldTypeNode createTestNode() {
        return new FieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            false,
            Optional.empty(),
            Optional.of(TestBoolFieldTypeNode.createTestNode())
        );
    }

}