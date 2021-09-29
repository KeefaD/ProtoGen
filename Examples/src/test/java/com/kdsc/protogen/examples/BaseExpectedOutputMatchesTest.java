package com.kdsc.protogen.examples;

import com.kdsc.protogen.ProtoGen;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.fail;

//TODO:KMD Obviously this needs a lot more work
public class BaseExpectedOutputMatchesTest {

    public static final String TEST_RESOURCES_DIRECTORY = File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "examples" + File.separator;
    public static final String GRADLE_BUILD_DIRECTORY = File.separator + "build" + File.separator + "generated-src" + File.separator + "examples" + File.separator;
    public static final String EXAMPLE_JAVA_DIRECTORY = File.separator + "java" + File.separator;
    public static final String EXAMPLE_PROTO_DIRECTORY = File.separator + "proto" + File.separator;

    private static final boolean writeActualToExpected = false;

    protected void runCompilerWithTestProgram(final String testProgramDirectory, final String baseNamespace, final String outputDirectory) {

        var baseDirectory = System.getProperty("user.dir");

        var fullPathToSourceCodeDirectory = baseDirectory + TEST_RESOURCES_DIRECTORY + testProgramDirectory;
        var fullPathToJavaOutputDirectory = baseDirectory + GRADLE_BUILD_DIRECTORY + outputDirectory + EXAMPLE_JAVA_DIRECTORY;
        var fullPathToProtoOutputDirectory = baseDirectory + GRADLE_BUILD_DIRECTORY + outputDirectory + EXAMPLE_PROTO_DIRECTORY;

        System.out.println("//Example Source Code Directory");
        System.out.println("    " + fullPathToSourceCodeDirectory);
        System.out.println();
        System.out.println("//Java Output Directory");
        System.out.println("    " + fullPathToJavaOutputDirectory);
        System.out.println();
        System.out.println("//Proto Output Directory");
        System.out.println("    " + fullPathToProtoOutputDirectory);
        System.out.println();

        var commandLineArgumentsList = new ArrayList<String>();
        commandLineArgumentsList.add(fullPathToSourceCodeDirectory);
        if(baseNamespace != null && !baseNamespace.isBlank()) {
            commandLineArgumentsList.add("-baseNamespace=" + baseNamespace);
        }
        commandLineArgumentsList.add("-javaOutputDirectory=" + fullPathToJavaOutputDirectory);
        commandLineArgumentsList.add("-protoOutputDirectory=" + fullPathToProtoOutputDirectory);
        commandLineArgumentsList.add("-showParseTree");
        commandLineArgumentsList.add("-showReplacedParseTree");
        commandLineArgumentsList.add("-showFileGenerationTree");
        var commandLineArgumentsArray = commandLineArgumentsList.toArray(new String[0]);

        if(ProtoGen.main(commandLineArgumentsArray) != 0) {
            fail("Unexpected compilation error");
        }
    }
}