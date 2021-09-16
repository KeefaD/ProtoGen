package com.kdsc.protogen.parsetree.fieldtypenodes;

import com.kdsc.protogen.parsetree.BaseTestNode;
import com.kdsc.protogen.parsetree.TestNamespaceNameGenericParametersWithoutBoundsNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestObjectFieldTypeNode extends BaseTestNode {

    @Test
    public void testCreate() {
        new ObjectFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestNamespaceNameGenericParametersWithoutBoundsNode.createTestNode()
        );
    }

    @Test
    public void testNullsOrEmptiesInConstructor() {

        assertThrows(NullPointerException.class,
            () ->
            new ObjectFieldTypeNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                null
            )
        );

    }

    @Test
    public void testGetters() {
        var namespaceNameGenericParametersWithoutBoundsNode = TestNamespaceNameGenericParametersWithoutBoundsNode.createTestNode();
        var node = new ObjectFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            namespaceNameGenericParametersWithoutBoundsNode
        );
        assertEquals(namespaceNameGenericParametersWithoutBoundsNode, node.getNamespaceNameGenericParametersWithoutBoundsNode(), "Created and retrieved objects don't match");
    }

    @Test
    public void testToString() {
        var node = new ObjectFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestNamespaceNameGenericParametersWithoutBoundsNode.createTestNode()
        );
        var expectedToStringOutput = """
        //ObjectFieldTypeNode
            //NamespaceNameGenericParametersWithoutBoundsNode
                //NamespaceNameNode
                    //NamespaceNode
                        Namespace : Namespace
                    //NameNode
                        Name : Name
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }
}