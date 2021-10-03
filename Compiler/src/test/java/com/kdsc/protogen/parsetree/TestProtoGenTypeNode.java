package com.kdsc.protogen.parsetree;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestProtoGenTypeNode extends BaseTestNode {

    @Test
    public void testCreate() {
        new ProtoGenTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            false,
            TestNamespaceNameGenericParametersWithBoundsNode.createTestNode(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty()
        );
    }

    @Test
    public void testInvalidConstructorCall() {

        assertThrows(NullPointerException.class,
            () ->
            new ProtoGenTypeNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                false,
                null,
                Optional.empty(),
                Optional.empty(),
                Optional.empty()
            )
        );

        assertThrows(NullPointerException.class,
            () ->
            new ProtoGenTypeNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                false,
                TestNamespaceNameGenericParametersWithBoundsNode.createTestNode(),
                null,
                Optional.empty(),
                Optional.empty()
            )
        );

        assertThrows(NullPointerException.class,
            () ->
            new ProtoGenTypeNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                false,
                TestNamespaceNameGenericParametersWithBoundsNode.createTestNode(),
                Optional.empty(),
                null,
                Optional.empty()
            )
        );

        assertThrows(NullPointerException.class,
            () ->
            new ProtoGenTypeNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                false,
                TestNamespaceNameGenericParametersWithBoundsNode.createTestNode(),
                Optional.empty(),
                Optional.empty(),
                null
            )
        );

    }

    @Test
    public void testGetters() {

        var isInterface = false;
        var namespaceNameGenericParametersWithBounds = TestNamespaceNameGenericParametersWithBoundsNode.createTestNode();
        Optional<ImplementsListNode> implementsListNode = Optional.empty();
        Optional<VersionsNode> versionsNode = Optional.empty();
        Optional<FieldsNode> fieldsNode = Optional.empty();
        @SuppressWarnings("ConstantConditions") var node = new ProtoGenTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            isInterface,
            namespaceNameGenericParametersWithBounds,
            implementsListNode,
            versionsNode,
            fieldsNode
        );
        //noinspection ConstantConditions
        assertEquals(isInterface, node.isInterface(), "Created and retrieved objects don't match");
        assertEquals(namespaceNameGenericParametersWithBounds, node.getNamespaceNameGenericParametersWithBoundsNode(), "Created and retrieved objects don't match");
        assertEquals(implementsListNode, node.getImplementsListNode(), "Created and retrieved objects don't match");
        assertEquals(versionsNode, node.getVersionsNode(), "Created and retrieved objects don't match");
        assertEquals(fieldsNode, node.getFieldsNode(), "Created and retrieved objects don't match");
    }

    @Test
    public void testToString() {
        var node = new ProtoGenTypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            false,
            TestNamespaceNameGenericParametersWithBoundsNode.createTestNode(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty()
        );
        var expectedToStringOutput = """
        //ProtoGenTypeNode
            //Super -> //BaseParseTreeNode
                SourceFileName : TestFileName.pg
                Line : 1
                CharPosition : 0
            IsInterface : false
            //NamespaceNameGenericParametersWithBoundsNode
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