package com.kdsc.protogen.parsetreenodes.utils;

import com.kdsc.protogen.parsetreenodes.NameNode;
import com.kdsc.protogen.parsetreenodes.NamespaceNameNode;
import com.kdsc.protogen.parsetreenodes.NamespaceNode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TestParseTreeUtils {

    @Test
    public void testGetNamespaceNameStringSingleNamespace() {
        var testNamespaceName = new NamespaceNameNode(
            "UnitTest",
            1,
            0,
            List.of(
                new NamespaceNode(
                    "UnitTest",
                    1,
                    0,
                    "Namespace"
                )
            ),
            new NameNode("UnitTest", 1, 0, "Name")
        );
        var expectedOutput = "Namespace.Name";
        var output = ParseTreeUtils.getNamespaceNameString(testNamespaceName);
        assertEquals(expectedOutput, output, "Actual output doesn't match expected output");
    }

    @Test
    public void testGetNamespaceNameStringTwoNamespaces() {
        var testNamespaceName = new NamespaceNameNode(
            "UnitTest",
            1,
            0,
            List.of(
                new NamespaceNode(
                    "UnitTest",
                    1,
                    0,
                    "Namespace1"
                ),
                new NamespaceNode(
                    "UnitTest",
                    1,
                    0,
                    "Namespace2"
                )
            ),
            new NameNode("UnitTest", 1, 0, "Name")
        );
        var expectedOutput = "Namespace1.Namespace2.Name";
        var output = ParseTreeUtils.getNamespaceNameString(testNamespaceName);
        assertEquals(expectedOutput, output, "Actual output doesn't match expected output");
    }

}