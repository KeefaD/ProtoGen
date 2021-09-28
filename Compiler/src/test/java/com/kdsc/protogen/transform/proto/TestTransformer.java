package com.kdsc.protogen.transform.proto;

import com.kdsc.protogen.BaseCompilerTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestTransformer  extends BaseCompilerTest {

    //TODO:KMD Need to think about capitalisation for namespaces, should we allow uppercase packages, investigate
    @Test
    public void testBasicEnum() {
        var testProgram = """            
            enum TestNamespace.Enum
        """;
        var fileGenerationTree = runCompilerToTransformReturnProtoFileNodes(testProgram);
        var expectedToStringOutput = """
        //EnumFileNode
            //ProtoFileNode
                //FileNode
                    FileName : Enum.proto
                    Path : TestNamespace
                    FileNameAndPath : TestNamespace/Enum.proto
        """;
        assertNotNull(fileGenerationTree, "FileGenerationTree list is null");
        assertEquals(1, fileGenerationTree.size(), "Unexpected file generation tree size");
        assertEquals(expectedToStringOutput, fileGenerationTree.get(0).toString(), "Unexpected toString output");
    }

}