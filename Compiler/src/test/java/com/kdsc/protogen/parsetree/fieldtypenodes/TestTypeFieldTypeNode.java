package com.kdsc.protogen.parsetree.fieldtypenodes;

import com.kdsc.protogen.parsetree.BaseTestNode;
import com.kdsc.protogen.parsetree.TestNamespaceNameGenericParametersNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestTypeFieldTypeNode extends BaseTestNode {

    @Test
    public void testCreate() {
        new TypeFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestNamespaceNameGenericParametersNode.createTestNode()
        );
    }

    @Test
    public void testInvalidConstructorCall() {

        assertThrows(NullPointerException.class,
            () ->
            new TypeFieldTypeNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                null
            )
        );

    }

    @Test
    public void testGetters() {
        var namespaceNameGenericParametersNode = TestNamespaceNameGenericParametersNode.createTestNode();
        var node = new TypeFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            namespaceNameGenericParametersNode
        );
        assertEquals(namespaceNameGenericParametersNode, node.getNamespaceNameGenericParametersNode(), "Created and retrieved objects don't match");
    }

    @Test
    public void testToString() {
        var node = new TypeFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestNamespaceNameGenericParametersNode.createTestNode()
        );
        var expectedToStringOutput = """
        //TypeFieldTypeNode
            //Super -> //NonArrayFieldTypeNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : TestFileName.pg
                    Line : 1
                    CharPosition : 0
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
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }

}