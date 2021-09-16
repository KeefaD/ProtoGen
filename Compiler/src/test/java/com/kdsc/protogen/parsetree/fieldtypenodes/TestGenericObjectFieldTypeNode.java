package com.kdsc.protogen.parsetree.fieldtypenodes;

import com.kdsc.protogen.parsetree.BaseTestNode;
import com.kdsc.protogen.parsetree.GenericParametersWithoutBoundsNode;
import com.kdsc.protogen.parsetree.TestGenericParameterWithoutBoundsNode;
import com.kdsc.protogen.parsetree.TestNamespaceNameGenericParametersWithoutBoundsNode;
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
            TestGenericParameterWithoutBoundsNode.createTestNode()
        );
    }

    @Test
    public void testNullsOrEmptiesInConstructor() {

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
        var namespaceNameGenericParametersWithoutBoundsNode = TestGenericParameterWithoutBoundsNode.createTestNode();
        var node = new GenericObjectFieldTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            namespaceNameGenericParametersWithoutBoundsNode
        );
        assertEquals(namespaceNameGenericParametersWithoutBoundsNode, node.getGenericParameterWithoutBoundsNode(), "Created and retrieved objects don't match");
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
