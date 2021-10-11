package com.kdsc.protogen.nodes;

import com.kdsc.protogen.parsetreenodes.ParseTreeFormattedStringOptions;
import com.kdsc.protogen.parsetreenodes.TestNamespaceNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public final class TestBaseNode {

    @Spy
    BaseNode baseNodeMock;

    @Test
    public void testOneIndent() {
        assertEquals("    ", baseNodeMock.oneIndent(), "Expected one indent to be four spaces");
    }

    @Test
    public void testClassToFormattedStringTitle() {
        var expectedOutput = """
        //BaseNode
        """;
        var stringBuilder = new StringBuilder();
        baseNodeMock.classToFormattedStringTitle(stringBuilder, ParseTreeFormattedStringOptions.defaultParseTreeFormattedStringOptions, BaseNode.class);
        assertEquals(expectedOutput, stringBuilder.toString(), "Unexpected class to formatted string output");
    }

    @Test
    public void testSuperToFormattedStringSuper() {
        var expectedOutput = """
            //Super -> Test super formatted string
        """;
        var stringBuilder = new StringBuilder();
        baseNodeMock.superToFormattedStringSuper(stringBuilder, ParseTreeFormattedStringOptions.defaultParseTreeFormattedStringOptions, "Test super formatted string");
        assertEquals(expectedOutput, stringBuilder.toString(), "Unexpected super to formatted string super");
    }

    @Test
    public void testFieldToFormattedStringFieldString() {
        var expectedOutput = """
            TestField : TestString
        """;
        var stringBuilder = new StringBuilder();
        baseNodeMock.fieldToFormattedStringField(stringBuilder, ParseTreeFormattedStringOptions.defaultParseTreeFormattedStringOptions, "TestField", "TestString");
        assertEquals(expectedOutput, stringBuilder.toString(), "Unexpected super to formatted string super");
    }

    @Test
    public void testFieldToFormattedStringFieldInt() {
        var expectedOutput = """
            TestField : 2147483647
        """;
        var stringBuilder = new StringBuilder();
        baseNodeMock.fieldToFormattedStringField(stringBuilder, ParseTreeFormattedStringOptions.defaultParseTreeFormattedStringOptions, "TestField", Integer.MAX_VALUE);
        assertEquals(expectedOutput, stringBuilder.toString(), "Unexpected super to formatted string super");
    }

    @Test
    public void testFieldToFormattedStringFieldLong() {
        var expectedOutput = """
            TestField : 9223372036854775807
        """;
        var stringBuilder = new StringBuilder();
        baseNodeMock.fieldToFormattedStringField(stringBuilder, ParseTreeFormattedStringOptions.defaultParseTreeFormattedStringOptions, "TestField", Long.MAX_VALUE);
        assertEquals(expectedOutput, stringBuilder.toString(), "Unexpected super to formatted string super");
    }

    @Test
    public void testFieldToFormattedStringFieldBoolean() {
        var expectedOutput = """
            TestField : true
        """;
        var stringBuilder = new StringBuilder();
        baseNodeMock.fieldToFormattedStringField(stringBuilder, ParseTreeFormattedStringOptions.defaultParseTreeFormattedStringOptions, "TestField", true);
        assertEquals(expectedOutput, stringBuilder.toString(), "Unexpected super to formatted string super");
    }

    @Test
    public void testFieldToFormattedStringFieldNode() {
        var expectedOutput = """
            TestField
                //NamespaceNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : TestFileName.pg
                        Line : 1
                        CharPosition : 0
                    Namespace : Namespace
        """;
        var stringBuilder = new StringBuilder();
        baseNodeMock.fieldToFormattedStringField(stringBuilder, ParseTreeFormattedStringOptions.defaultParseTreeFormattedStringOptions, "TestField", TestNamespaceNode.createPopulatedTestNode());
        assertEquals(expectedOutput, stringBuilder.toString(), "Unexpected super to formatted string super");
    }

    @Test
    public void testFieldToFormattedStringFieldNodeTwoIndents() {
        var expectedOutput = """
                TestField
                    //NamespaceNode
                        //Super -> //BaseParseTreeNode
                            SourceFileName : TestFileName.pg
                            Line : 1
                            CharPosition : 0
                        Namespace : Namespace
        """;
        var stringBuilder = new StringBuilder();
        baseNodeMock.fieldToFormattedStringField(stringBuilder, ParseTreeFormattedStringOptions.defaultParseTreeFormattedStringOptions, "TestField", TestNamespaceNode.createPopulatedTestNode(), 2);
        assertEquals(expectedOutput, stringBuilder.toString(), "Unexpected super to formatted string super");
    }

    @Test
    public void testFieldToFormattedStringOptionalFieldNode() {
        var expectedOutput = """
            //NamespaceNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : TestFileName.pg
                    Line : 1
                    CharPosition : 0
                Namespace : Namespace
        """;
        var stringBuilder = new StringBuilder();
        baseNodeMock.fieldToFormattedStringField(stringBuilder, ParseTreeFormattedStringOptions.defaultParseTreeFormattedStringOptions, Optional.of(TestNamespaceNode.createPopulatedTestNode()));
        assertEquals(expectedOutput, stringBuilder.toString(), "Unexpected super to formatted string super");
    }

    @Test
    public void testFieldToFormattedStringOptionalFieldNodeTwoIndents() {
        var expectedOutput = """
                //NamespaceNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : TestFileName.pg
                        Line : 1
                        CharPosition : 0
                    Namespace : Namespace
        """;
        var stringBuilder = new StringBuilder();
        baseNodeMock.fieldToFormattedStringField(stringBuilder, ParseTreeFormattedStringOptions.defaultParseTreeFormattedStringOptions, Optional.of(TestNamespaceNode.createPopulatedTestNode()), 2);
        assertEquals(expectedOutput, stringBuilder.toString(), "Unexpected super to formatted string super");
    }


    @Test
    public void testFieldToFormattedStringListFieldNode() {
        var expectedOutput = """
            //NamespaceNode
                //Super -> //BaseParseTreeNode
                    SourceFileName : TestFileName.pg
                    Line : 1
                    CharPosition : 0
                Namespace : Namespace
        """;
        var stringBuilder = new StringBuilder();
        baseNodeMock.fieldToFormattedStringField(stringBuilder, ParseTreeFormattedStringOptions.defaultParseTreeFormattedStringOptions, List.of(TestNamespaceNode.createPopulatedTestNode()));
        assertEquals(expectedOutput, stringBuilder.toString(), "Unexpected super to formatted string super");
    }

    @Test
    public void testFieldToFormattedStringListFieldNodeTwoIndents() {
        var expectedOutput = """
                //NamespaceNode
                    //Super -> //BaseParseTreeNode
                        SourceFileName : TestFileName.pg
                        Line : 1
                        CharPosition : 0
                    Namespace : Namespace
        """;
        var stringBuilder = new StringBuilder();
        baseNodeMock.fieldToFormattedStringField(stringBuilder, ParseTreeFormattedStringOptions.defaultParseTreeFormattedStringOptions, List.of(TestNamespaceNode.createPopulatedTestNode()), 2);
        assertEquals(expectedOutput, stringBuilder.toString(), "Unexpected super to formatted string super");
    }

    @Test
    public void testIndentString() {
        var expectedOutput = """
                        ExpectedOutput
        """;
        var stringBuilder = new StringBuilder();
        stringBuilder.append("ExpectedOutput");
        var outputString = baseNodeMock.indentAndReturnString(stringBuilder, ParseTreeFormattedStringOptions.defaultParseTreeFormattedStringOptions, 4);
        assertEquals(expectedOutput, outputString, "Unexpected super to formatted string super");
    }

}