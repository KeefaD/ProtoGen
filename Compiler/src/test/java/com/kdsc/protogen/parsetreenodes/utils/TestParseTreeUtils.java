package com.kdsc.protogen.parsetreenodes.utils;

import com.kdsc.protogen.parsetreenodes.NameNode;
import com.kdsc.protogen.parsetreenodes.NamespaceNameNode;
import com.kdsc.protogen.parsetreenodes.NamespaceNode;
import com.kdsc.protogen.parsetreenodes.fieldtypenodes.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class TestParseTreeUtils {

    @Test
    public void testGetNamespaceStringNull() {
        assertThrows(
            NullPointerException.class,
            () -> ParseTreeUtils.getNamespaceString(null)
        );
    }

    @Test
    public void testGetNamespaceStringSingleNamespace() {
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
        var expectedOutput = "Namespace";
        var output = ParseTreeUtils.getNamespaceString(testNamespaceName);
        assertEquals(expectedOutput, output, "Actual output doesn't match expected output");
    }

    @Test
    public void testGetNamespaceStringTwoNamespaces() {
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
        var expectedOutput = "Namespace1.Namespace2";
        var output = ParseTreeUtils.getNamespaceString(testNamespaceName);
        assertEquals(expectedOutput, output, "Actual output doesn't match expected output");
    }

    @Test
    public void testGetNamespaceNameStringNull() {
        assertThrows(
            NullPointerException.class,
            () -> ParseTreeUtils.getNamespaceNameString(null)
        );
    }

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

    @Test
    public void testConvertTypeToStringFieldTypeNodeNull() {
        assertThrows(
            NullPointerException.class,
            () -> ParseTreeUtils.convertFieldTypeToString((FieldTypeNode) null)
        );
    }

    @Test
    public void testConvertTypeToStringArrayFieldTypeNodeNull() {
        assertThrows(
            NullPointerException.class,
            () -> ParseTreeUtils.convertFieldTypeToString((ArrayFieldTypeNode) null)
        );
    }

    @Test
    public void testConvertTypeToStringNonArrayFieldTypeNodeNull() {
        assertThrows(
            NullPointerException.class,
            () -> ParseTreeUtils.convertFieldTypeToString((NonArrayFieldTypeNode) null)
        );
    }

    @Test
    public void testConvertTypeToStringFieldTypeNodeArray() {
        var expectedOutput = "bool[]";
        var output = ParseTreeUtils.convertFieldTypeToString(TestArrayFieldTypeNode.createPopulatedTestNode());
        assertEquals(expectedOutput, output, "Actual output doesn't match expected output");
    }

    @Test
    public void testConvertTypeToStringFieldTypeNodeBool() {
        var expectedOutput = "bool";
        var output = ParseTreeUtils.convertFieldTypeToString(TestBoolFieldTypeNode.createPopulatedTestNode());
        assertEquals(expectedOutput, output, "Actual output doesn't match expected output");
    }

    @Test
    public void testConvertTypeToStringFieldTypeNodeBytes() {
        var expectedOutput = "bytes";
        var output = ParseTreeUtils.convertFieldTypeToString(TestBytesFieldTypeNode.createPopulatedTestNode());
        assertEquals(expectedOutput, output, "Actual output doesn't match expected output");
    }

    @Test
    public void testConvertTypeToStringFieldTypeNodeDate() {
        var expectedOutput = "date";
        var output = ParseTreeUtils.convertFieldTypeToString(TestDateFieldTypeNode.createPopulatedTestNode());
        assertEquals(expectedOutput, output, "Actual output doesn't match expected output");
    }

    @Test
    public void testConvertTypeToStringFieldTypeNodeDateTime() {
        var expectedOutput = "datetime";
        var output = ParseTreeUtils.convertFieldTypeToString(TestDateTimeFieldTypeNode.createPopulatedTestNode());
        assertEquals(expectedOutput, output, "Actual output doesn't match expected output");
    }

    @Test
    public void testConvertTypeToStringFieldTypeNodeDecimal() {
        var expectedOutput = "decimal";
        var output = ParseTreeUtils.convertFieldTypeToString(TestDecimalFieldTypeNode.createPopulatedTestNode());
        assertEquals(expectedOutput, output, "Actual output doesn't match expected output");
    }

    @Test
    public void testConvertTypeToStringFieldTypeNodeDouble() {
        var expectedOutput = "double";
        var output = ParseTreeUtils.convertFieldTypeToString(TestDoubleFieldTypeNode.createPopulatedTestNode());
        assertEquals(expectedOutput, output, "Actual output doesn't match expected output");
    }

    @Test
    public void testConvertTypeToStringFieldTypeNodeEnum() {
        var expectedOutput = "Namespace.Name";
        var output = ParseTreeUtils.convertFieldTypeToString(TestEnumFieldTypeNode.createPopulatedTestNode());
        assertEquals(expectedOutput, output, "Actual output doesn't match expected output");
    }

    @Test
    public void testConvertTypeToStringFieldTypeNodeFloat() {
        var expectedOutput = "float";
        var output = ParseTreeUtils.convertFieldTypeToString(TestFloatFieldTypeNode.createPopulatedTestNode());
        assertEquals(expectedOutput, output, "Actual output doesn't match expected output");
    }

    @Test
    public void testConvertTypeToStringFieldTypeNodeGenericObject() {
        var expectedOutput = "T";
        var output = ParseTreeUtils.convertFieldTypeToString(TestGenericObjectFieldTypeNode.createPopulatedTestNode());
        assertEquals(expectedOutput, output, "Actual output doesn't match expected output");
    }

    @Test
    public void testConvertTypeToStringFieldTypeNodeInt32() {
        var expectedOutput = "int32";
        var output = ParseTreeUtils.convertFieldTypeToString(TestInt32FieldTypeNode.createPopulatedTestNode());
        assertEquals(expectedOutput, output, "Actual output doesn't match expected output");
    }

    @Test
    public void testConvertTypeToStringFieldTypeNodeInt64() {
        var expectedOutput = "int64";
        var output = ParseTreeUtils.convertFieldTypeToString(TestInt64FieldTypeNode.createPopulatedTestNode());
        assertEquals(expectedOutput, output, "Actual output doesn't match expected output");
    }

    @Test
    public void testConvertTypeToStringFieldTypeNodeKey() {
        var expectedOutput = "Namespace.Name";
        var output = ParseTreeUtils.convertFieldTypeToString(TestKeyFieldTypeNode.createPopulatedTestNode());
        assertEquals(expectedOutput, output, "Actual output doesn't match expected output");
    }

    @Test
    public void testConvertTypeToStringFieldTypeNodeList() {
        var expectedOutput = "list<bool>";
        var output = ParseTreeUtils.convertFieldTypeToString(TestListFieldTypeNode.createPopulatedTestNode());
        assertEquals(expectedOutput, output, "Actual output doesn't match expected output");
    }

    @Test
    public void testConvertTypeToStringFieldTypeNodeLocalDate() {
        var expectedOutput = "localdate";
        var output = ParseTreeUtils.convertFieldTypeToString(TestLocalDateFieldTypeNode.createPopulatedTestNode());
        assertEquals(expectedOutput, output, "Actual output doesn't match expected output");
    }

    @Test
    public void testConvertTypeToStringFieldTypeNodeLocalDateTime() {
        var expectedOutput = "localdatetime";
        var output = ParseTreeUtils.convertFieldTypeToString(TestLocalDateTimeFieldTypeNode.createPopulatedTestNode());
        assertEquals(expectedOutput, output, "Actual output doesn't match expected output");
    }

    @Test
    public void testConvertTypeToMapFieldTypeNodeMap() {
        var expectedOutput = "map<bool, bool>";
        var output = ParseTreeUtils.convertFieldTypeToString(TestMapFieldTypeNode.createPopulatedTestNode());
        assertEquals(expectedOutput, output, "Actual output doesn't match expected output");
    }

    @Test
    public void testConvertTypeToMapFieldTypeNodeObject() {
        var expectedOutput = "UnknownObject(Namespace.Name)";
        var output = ParseTreeUtils.convertFieldTypeToString(TestObjectFieldTypeNode.createPopulatedTestNode());
        assertEquals(expectedOutput, output, "Actual output doesn't match expected output");
    }

    @Test
    public void testConvertTypeToMapFieldTypeNodeSet() {
        var expectedOutput = "set<bool>";
        var output = ParseTreeUtils.convertFieldTypeToString(TestSetFieldTypeNode.createPopulatedTestNode());
        assertEquals(expectedOutput, output, "Actual output doesn't match expected output");
    }

    @Test
    public void testConvertTypeToStringFieldTypeNodeString() {
        var expectedOutput = "string";
        var output = ParseTreeUtils.convertFieldTypeToString(TestStringFieldTypeNode.createPopulatedTestNode());
        assertEquals(expectedOutput, output, "Actual output doesn't match expected output");
    }

    @Test
    public void testConvertTypeToStringFieldTypeNodeType() {
        var expectedOutput = "Namespace.Name";
        var output = ParseTreeUtils.convertFieldTypeToString(TestTypeFieldTypeNode.createPopulatedTestNode());
        assertEquals(expectedOutput, output, "Actual output doesn't match expected output");
    }

    @Test
    public void testConvertTypeToMapFieldTypeNodeValueOrError() {
        var expectedOutput = "valueorerror<bool>";
        var output = ParseTreeUtils.convertFieldTypeToString(TestValueOrErrorFieldTypeNode.createPopulatedTestNode());
        assertEquals(expectedOutput, output, "Actual output doesn't match expected output");
    }

}