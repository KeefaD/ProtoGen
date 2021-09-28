package com.kdsc.protogen.parsetree;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestProtoGenEnumNode extends BaseTestNode {

    @Test
    public void testCreate() {
        new ProtoGenEnumNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestNamespaceNameNode.createTestNode(),
            Optional.empty(),
            Optional.empty()
        );
    }

    @Test
    public void testNullsOrEmptiesInConstructor() {

        assertThrows(NullPointerException.class,
            () ->
            new ProtoGenEnumNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                null,
                Optional.empty(),
                Optional.empty()
            )
        );

        assertThrows(NullPointerException.class,
            () ->
            new ProtoGenEnumNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                TestNamespaceNameNode.createTestNode(),
                null,
                Optional.empty()
            )
        );

        assertThrows(NullPointerException.class,
            () ->
            new ProtoGenEnumNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                TestNamespaceNameNode.createTestNode(),
                Optional.empty(),
                null
            )
        );

        assertThrows(IllegalArgumentException.class,
            () ->
            new ProtoGenEnumNode(
                BaseTestNode.fileName,
                BaseTestNode.line,
                BaseTestNode.charPosition,
                TestNamespaceNameNode.createTestNode(),
                Optional.of(TestEnumVersionsNode.createTestNode()),
                Optional.of(TestEnumCasesNode.createTestNode())
            )
        );
    }

    @Test
    public void testGetters() {
        var namespaceName = TestNamespaceNameNode.createTestNode();
        Optional<EnumVersionsNode> enumVersions = Optional.empty();
        Optional<EnumCasesNode> enumCases = Optional.empty();
        var node = new ProtoGenEnumNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            namespaceName,
            enumVersions,
            enumCases
        );
        assertEquals(namespaceName, node.getNamespaceNameNode(), "Created and retrieved objects don't match");
        assertEquals(enumVersions, node.getEnumVersionsNode(), "Created and retrieved objects don't match");
        assertEquals(enumCases, node.getEnumCasesNode(), "Created and retrieved objects don't match");
    }

    @Test
    public void testToString() {
        var node = new ProtoGenEnumNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestNamespaceNameNode.createTestNode(),
            Optional.empty(),
            Optional.empty()
        );
        var expectedToStringOutput = """
        //ProtoGenEnumNode
            //NamespaceNameNode
                //NamespaceNode
                    Namespace : Namespace
                //NameNode
                    Name : Name
        """;
        assertEquals(expectedToStringOutput, node.toString(), "Unexpected toString output");
    }

    public static ProtoGenEnumNode createTestNode() {
        return new ProtoGenEnumNode(
            BaseTestNode.fileName,
            BaseTestNode.line,
            BaseTestNode.charPosition,
            TestNamespaceNameNode.createTestNode(),
            Optional.empty(),
            Optional.empty()
        );
    }
}