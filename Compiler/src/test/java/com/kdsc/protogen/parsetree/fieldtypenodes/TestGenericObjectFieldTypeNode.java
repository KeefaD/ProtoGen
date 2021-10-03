package com.kdsc.protogen.parsetree.fieldtypenodes;

import com.kdsc.protogen.parsetree.BaseTestNode;
import com.kdsc.protogen.parsetree.TestGenericParameterNode;
import com.kdsc.protogen.parsetree.TestNamespaceNameGenericParametersNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestGenericObjectFieldTypeNode extends BaseTestNode {

    @Test
    public void testCreate() {
        new GenericObjectFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestGenericParameterNode.createTestNode()
        );
    }

    @Test
    public void testInvalidConstructorCall() {

        assertThrows(NullPointerException.class,
            () ->
            new GenericObjectFieldTypeNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                null
            )
        );

    }

    @Test
    public void testGetters() {
        var namespaceNameGenericParametersNode = TestGenericParameterNode.createTestNode();
        var node = new GenericObjectFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            namespaceNameGenericParametersNode
        );
        assertEquals(namespaceNameGenericParametersNode, node.getGenericParameterNode(), "Created and retrieved objects don't match");
    }

    @Test
    public void testToString() {
        var node = new GenericObjectFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestGenericParameterNode.createTestNode()
        );
        var expectedToStringOutput = """
        //GenericObjectFieldTypeNode
            //Super -> //NonArrayFieldTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : TestFileName.pg
                    Line : 1
                    CharPosition : 0
            //GenericParameterNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : TestFileName.pg
                    Line : 1
                    CharPosition : 0
                Identifier : T
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }

}