package com.kdsc.protogen.parsetreenodes.fieldtypenodes;

import com.kdsc.protogen.parsetreenodes.BaseTestNode;
import com.kdsc.protogen.parsetreenodes.TestNamespaceNameGenericParametersNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public final class TestEnumFieldTypeNode extends BaseTestNode {

    @Test
    public void testCreateMinimal() {
        new EnumFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestNamespaceNameGenericParametersNode.createPopulatedTestNode()
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
            new EnumFieldTypeNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                null
            )
        );

    }

    @Test
    public void testGetters() {
        var namespaceNameGenericParametersNode = TestNamespaceNameGenericParametersNode.createPopulatedTestNode();
        var node = new EnumFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            namespaceNameGenericParametersNode
        );
        assertEquals(namespaceNameGenericParametersNode, node.getNamespaceNameGenericParametersNode(), "Created and retrieved objects don't match");
    }

    @Test
    public void testHasNamespaceNameGetters() {
        var namespaceNameGenericParametersNode = TestNamespaceNameGenericParametersNode.createPopulatedTestNode();
        var node = new EnumFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            namespaceNameGenericParametersNode
        );
        assertEquals(namespaceNameGenericParametersNode.getNamespaceNameNode(), node.getNamespaceNameNode(), "Created and retrieved objects don't match");
        assertEquals(namespaceNameGenericParametersNode.getNamespaceNameNode().getNamespaceNodes(), node.getNamespaceNodes(), "Created and retrieved objects don't match");
        assertEquals(namespaceNameGenericParametersNode.getNamespaceNameNode().getNameNode(), node.getNameNode(), "Created and retrieved objects don't match");
    }

    @Test
    public void testToString() {
        var node = createPopulatedTestNode();
        var expectedToStringOutput = """
        //EnumFieldTypeNode
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

    public static EnumFieldTypeNode createPopulatedTestNode() {
        return new EnumFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestNamespaceNameGenericParametersNode.createPopulatedTestNode()
        );
    }

}