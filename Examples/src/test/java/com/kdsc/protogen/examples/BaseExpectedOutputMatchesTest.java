package com.kdsc.protogen.examples;

import com.kdsc.protogen.ProtoGen;

import java.io.File;

public class BaseExpectedOutputMatchesTest {

    public static final String TEST_RESOURCES_DIRECTORY = File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "examples" + File.separator;

    protected void runCompilerWithTestProgram(String testProgramDirectory) {

        var baseExamplesDirectory = System.getProperty("user.dir") + TEST_RESOURCES_DIRECTORY + testProgramDirectory;
        System.out.println("//Example source code directory");
        System.out.println("    " + baseExamplesDirectory);
        System.out.println();

        ProtoGen.main(baseExamplesDirectory, "-showParseTree", "-showReplacedParseTree", "-showFileGenerationTree");
    }
}