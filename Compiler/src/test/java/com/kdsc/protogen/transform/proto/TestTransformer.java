package com.kdsc.protogen.transform.proto;

import com.kdsc.protogen.BaseCompilerTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestTransformer  extends BaseCompilerTest {

    //TODO:KMD I think we need to add verbose to the compiler and logging
    //TODO:KMD Need to think about capitalisation for namespaces, should we allow uppercase packages, investigate
    @Test
    public void testBasicEnum() {
        var testProgram = """            
            enum TestNamespace.Enum
        """;
        var fileGenerationTree = runCompilerToTransformReturnProtoFileNodes(testProgram);
        //TODO:KMD Ok these multiline strings seem to strip out trailing spaces hence the \s, this could be a problem, have a think
        var expectedToStringOutput = """
        //EnumFileNode
            //ProtoFileNode
                //FileNode
                    FileName : TestNamespace.Enum.proto
                    Path :\s
                    FileNameAndPath : TestNamespace.Enum.proto
            Name : Enum
        """;
        assertNotNull(fileGenerationTree, "FileGenerationTree list is null");
        assertEquals(1, fileGenerationTree.size(), "Unexpected file generation tree size");
        assertEquals(expectedToStringOutput, fileGenerationTree.get(0).toString(), "Unexpected toString output");
    }

}