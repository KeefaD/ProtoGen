package com.kdsc.protogen.compilerresults;

import com.kdsc.protogen.antlr.Parser;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public final class TestCompilerResults {

    private String fullPathToSourceCodeDirectory = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "compilerresultstest" + File.separator;

    @Test
    public void testCreate() {
        new CompilerResults(
            Collections.emptyList()
        );
    }

    @Test
    public void testInvalidConstructorCall() {
        assertThrows(
            NullPointerException.class,
            () ->
            new CompilerResults(
                null
            )
        );
    }

    @Test
    public void testGetters() {

        //TODO:KMD Need to do something about the reporting of filenames as the message looks really bad and it is inconsistent
        final var filename = fullPathToSourceCodeDirectory + "OneOfEachType.pg";
        var fileList = List.of(filename);
        var parser = new Parser();
        var parserResults = parser.parse(fileList);
        assertFalse(parserResults.hasParserErrorOccurred(), "Expected no parser errors to have occurred");
        var fileNodes = parserResults.fileNodes();
        var compilerResults = new CompilerResults(fileNodes);

        assertEquals(fileNodes, compilerResults.getFileNodes(), "Expected value to match");

        assertEquals(1, compilerResults.getTypeNodeMap().size(), "Expected compilerResults.getTypeNodeMap().size() to be 1");
        assertEquals(1, compilerResults.getTypeInterfaceNodeMap().size(), "Expected compilerResults.getTypeInterfaceNodeMap().size() to be 1");
        assertEquals(1, compilerResults.getKeyNodeMap().size(), "Expected compilerResults.getKeyNodeMap().size() to be 1");
        assertEquals(1, compilerResults.getKeyInterfaceNodeMap().size(), "Expected compilerResults.getKeyInterfaceNodeMap().size() to be 1");
        assertEquals(1, compilerResults.getEnumNodeMap().size(), "Expected compilerResults.getEnumNodeMap().size() to be 1");

        assertEquals(2, compilerResults.getAllTypeNodeMap().size(), "Expected compilerResults.getAllTypeNodeMap().size() to be 2");
        assertEquals(2, compilerResults.getAllKeyNodeMap().size(), "Expected compilerResults.getAllKeyNodeMap().size() to be 2");

        assertNotNull(compilerResults.getTypeNodeMap().get("compilerresultstest.Type"), "Expected to be able to find type compilerresultstest.Type");
        assertNotNull(compilerResults.getTypeInterfaceNodeMap().get("compilerresultstest.TypeInterface"), "Expected to be able to find type compilerresultstest.TypeInterface");

        assertNotNull(compilerResults.getKeyNodeMap().get("compilerresultstest.Key"), "Expected to be able to find key compilerresultstest.Key");
        assertNotNull(compilerResults.getKeyInterfaceNodeMap().get("compilerresultstest.KeyInterface"), "Expected to be able to find key compilerresultstest.KeyInterface");

        assertNotNull(compilerResults.getEnumNodeMap().get("compilerresultstest.Enum"), "Expected to be able to find enum compilerresultstest.Enum");

        assertNotNull(compilerResults.getAllTypeNodeMap().get("compilerresultstest.Type"), "Expected to be able to find type compilerresultstest.Type");
        assertNotNull(compilerResults.getAllTypeNodeMap().get("compilerresultstest.TypeInterface"), "Expected to be able to find type compilerresultstest.TypeInterface");

        assertNotNull(compilerResults.getAllKeyNodeMap().get("compilerresultstest.Key"), "Expected to be able to find key compilerresultstest.Key");
        assertNotNull(compilerResults.getAllKeyNodeMap().get("compilerresultstest.KeyInterface"), "Expected to be able to find key compilerresultstest.KeyInterface");

    }

}
