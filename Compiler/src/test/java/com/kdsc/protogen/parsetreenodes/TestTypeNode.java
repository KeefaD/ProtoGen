package com.kdsc.protogen.parsetreenodes;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public final class TestTypeNode extends BaseTestNode {

    @Test
    public void testCreateMinimal() {
        new TypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            false,
            TestNamespaceNameGenericParametersWithBoundsNode.createPopulatedTestNode(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty()
        );
    }

    @Test
    public void testCreatePopulated() {
        createPopulatedTestNode();
    }

    @Test
    public void testInvalidConstructorCall() {

        assertThrows(NullPointerException.class,
            () ->
            new TypeNode(
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
            new TypeNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                false,
                TestNamespaceNameGenericParametersWithBoundsNode.createPopulatedTestNode(),
                null,
                Optional.empty(),
                Optional.empty()
            )
        );

        assertThrows(NullPointerException.class,
            () ->
            new TypeNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                false,
                TestNamespaceNameGenericParametersWithBoundsNode.createPopulatedTestNode(),
                Optional.empty(),
                null,
                Optional.empty()
            )
        );

        assertThrows(NullPointerException.class,
            () ->
            new TypeNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                false,
                TestNamespaceNameGenericParametersWithBoundsNode.createPopulatedTestNode(),
                Optional.empty(),
                Optional.empty(),
                null
            )
        );

    }

    @Test
    public void testGetters() {

        var isInterface = false;
        var namespaceNameGenericParametersWithBounds = TestNamespaceNameGenericParametersWithBoundsNode.createPopulatedTestNode();
        Optional<ImplementsListNode> implementsListNode = Optional.empty();
        Optional<VersionsNode> versionsNode = Optional.empty();
        Optional<FieldsNode> fieldsNode = Optional.empty();
        var node = new TypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            isInterface,
            namespaceNameGenericParametersWithBounds,
            implementsListNode,
            versionsNode,
            fieldsNode
        );
        assertEquals(isInterface, node.isInterface(), "Created and retrieved objects don't match");
        assertEquals(namespaceNameGenericParametersWithBounds, node.getNamespaceNameGenericParametersWithBoundsNode(), "Created and retrieved objects don't match");
        assertEquals(implementsListNode, node.getImplementsListNode(), "Created and retrieved objects don't match");
        assertEquals(versionsNode, node.getVersionsNode(), "Created and retrieved objects don't match");
        assertEquals(fieldsNode, node.getFieldsNode(), "Created and retrieved objects don't match");
    }

    @Test
    public void testHasNamespaceNameGetters() {

        var isInterface = false;
        var namespaceNameGenericParametersWithBounds = TestNamespaceNameGenericParametersWithBoundsNode.createPopulatedTestNode();
        Optional<ImplementsListNode> implementsListNode = Optional.empty();
        Optional<VersionsNode> versionsNode = Optional.empty();
        Optional<FieldsNode> fieldsNode = Optional.empty();
        var node = new TypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            isInterface,
            namespaceNameGenericParametersWithBounds,
            implementsListNode,
            versionsNode,
            fieldsNode
        );
        assertEquals(namespaceNameGenericParametersWithBounds.getNamespaceNameNode(), node.getNamespaceNameNode(), "Created and retrieved objects don't match");
        assertEquals(namespaceNameGenericParametersWithBounds.getNamespaceNameNode().getNamespaceNodes(), node.getNamespaceNodes(), "Created and retrieved objects don't match");
        assertEquals(namespaceNameGenericParametersWithBounds.getNamespaceNameNode().getNameNode(), node.getNameNode(), "Created and retrieved objects don't match");
    }

    @Test
    public void testIsLibraryNode() {
        var node = createPopulatedTestNode();
        assertFalse(node.isLibraryNode(), "Expected isLibraryNode to return false for the time being");
    }

    @Test
    public void testToString() {
        var node = createPopulatedTestNode();
        var expectedToStringOutput = """
        //TypeNode
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
                //GenericParametersWithBoundsNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : TestFileName.pg
                        Line : 1
                        CharPosition : 0
                    //GenericParameterWithBoundsNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : TestFileName.pg
                            Line : 1
                            CharPosition : 0
                        Identifier : T
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
            //ImplementsListNode
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
            //FieldsNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : TestFileName.pg
                    Line : 1
                    CharPosition : 0
                //FieldNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : TestFileName.pg
                        Line : 1
                        CharPosition : 0
                    //FieldNameNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : TestFileName.pg
                            Line : 1
                            CharPosition : 0
                        FieldName : FieldName
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

    public static TypeNode createPopulatedTestNode() {
        return new TypeNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            false,
            TestNamespaceNameGenericParametersWithBoundsNode.createPopulatedTestNode(),
            Optional.of(TestImplementsListNode.createPopulatedTestNode()),
            Optional.empty(),
            Optional.of(TestFieldsNode.createPopulatedTestNode())
        );
    }

}