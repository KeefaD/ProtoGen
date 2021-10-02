package com.kdsc.protogen.examples;

import com.kdsc.protogen.ProtoGen;
import com.kdsc.protogen.examples.utils.FileUtils;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//TODO:KMD Obviously this needs a lot more work
public class BaseExpectedOutputMatchesTest {

    public static final String TEST_RESOURCES_DIRECTORY = File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "examples" + File.separator;
    public static final String GRADLE_GENERATED_SRC_OUTPUT_DIRECTORY = File.separator + "build" + File.separator + "generated-src" + File.separator + "examples" + File.separator;
    public static final String EXPECTED_RESULTS_OUTPUT_DIRECTORY = File.separator + "src" + File.separator + "main" + File.separator + "examples" + File.separator;
    public static final String EXAMPLE_JAVA_DIRECTORY = File.separator + "java" + File.separator;
    public static final String EXAMPLE_PROTO_DIRECTORY = File.separator + "proto" + File.separator;

    private static final boolean writeActualToExpected = false;

    protected void runCompilerWithTestProgram(final String testProgramDirectory, final String baseNamespace, final String outputDirectory) {

        var baseDirectory = System.getProperty("user.dir");

        var fullPathToSourceCodeDirectory = baseDirectory + TEST_RESOURCES_DIRECTORY + testProgramDirectory;

        var fullPathToExpectedOutputDirectory = baseDirectory + EXPECTED_RESULTS_OUTPUT_DIRECTORY + outputDirectory;

        String fullPathToOutputDirectory;
        if(writeActualToExpected) {
            fullPathToOutputDirectory = fullPathToExpectedOutputDirectory;
        } else {
            fullPathToOutputDirectory = baseDirectory + GRADLE_GENERATED_SRC_OUTPUT_DIRECTORY + outputDirectory;
        }

        var fullPathToJavaOutputDirectory = fullPathToOutputDirectory + EXAMPLE_JAVA_DIRECTORY;
        var fullPathToProtoOutputDirectory = fullPathToOutputDirectory + EXAMPLE_PROTO_DIRECTORY;

        System.out.println("//Example Source Code Directory");
        System.out.println("    " + fullPathToSourceCodeDirectory);
        System.out.println();
        System.out.println("//Base Output Directory");
        System.out.println("    " + fullPathToOutputDirectory);
        System.out.println();
        System.out.println("//Java Output Directory");
        System.out.println("    " + fullPathToJavaOutputDirectory);
        System.out.println();
        System.out.println("//Proto Output Directory");
        System.out.println("    " + fullPathToProtoOutputDirectory);
        System.out.println();

        FileUtils.deleteDirectoryRecursively(fullPathToOutputDirectory);

        //TODO:KMD Not sure about this
        FileUtils.makeDirectory(fullPathToOutputDirectory);

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

        if(writeActualToExpected) {
            fail("Running tests in write actual to expected mode");
        }
        assertExpectedFilesMatchActual(fullPathToExpectedOutputDirectory, fullPathToOutputDirectory);
    }

    protected void assertExpectedFilesMatchActual(final String expectedResultsDirectory, final String actualResultsDirectory) {
        var expectedResultsDirectoryOutputPath = Paths.get(expectedResultsDirectory);
        var expectedResultsDirectoryOutputFile = expectedResultsDirectoryOutputPath.toFile();
        var actualResultsDirectoryOutputPath = Paths.get(actualResultsDirectory);
        var actualResultsDirectoryOutputFile = actualResultsDirectoryOutputPath.toFile();
        assertExpectedDirectoryMatchesActual(expectedResultsDirectoryOutputFile, actualResultsDirectoryOutputFile);
    }

    protected void assertExpectedDirectoryMatchesActual(final File expectedResultsDirectory, final File actualResultsDirectory) {
        if(!expectedResultsDirectory.exists()) {
            fail("Expected results directory does not exist " + expectedResultsDirectory.toPath());
        }
        if(!actualResultsDirectory.exists()) {
            fail("Actual results directory does not exist " + actualResultsDirectory.toPath());
        }

        var expectedFileNames = Arrays
            //TODO:KMD listFiles can be null
            .stream(expectedResultsDirectory.listFiles())
            .filter(f -> !f.isDirectory())
            .map(File::getName)
            .collect(Collectors.toSet());
        var actualFileNames = Arrays
            //TODO:KMD listFiles can be null
            .stream(actualResultsDirectory.listFiles())
            .filter(f -> !f.isDirectory())
            .map(File::getName)
            .collect(Collectors.toSet());
        if(!expectedFileNames.containsAll(actualFileNames)) {
            var actualFileNamesSet = new HashSet<>(actualFileNames);
            actualFileNamesSet.removeAll(expectedFileNames);
            var missingFilesMessage = String.join(", ", actualFileNamesSet);
            fail("Missing expected files " + expectedResultsDirectory + " does not contain files " + missingFilesMessage);
        }
        if(!actualFileNames.containsAll(expectedFileNames)) {
            var expectedFileNamesSet = new HashSet<>(expectedFileNames);
            expectedFileNamesSet.removeAll(actualFileNames);
            var missingDirectoriesMessage = String.join(", ", expectedFileNamesSet);
            fail("Missing actual files " + actualResultsDirectory + " does not contain files " + missingDirectoriesMessage);
        }

        var expectedDirectoryNames = Arrays
            //TODO:KMD listFiles can be null
            .stream(expectedResultsDirectory.listFiles())
            .filter(File::isDirectory)
            .map(File::getName)
            .collect(Collectors.toSet());
        var actualDirectoryNames = Arrays
            //TODO:KMD listFiles can be null
            .stream(actualResultsDirectory.listFiles())
            .filter(File::isDirectory)
            .map(File::getName)
            .collect(Collectors.toSet());
        if(!expectedDirectoryNames.containsAll(actualDirectoryNames)) {
            var actualDirectoryNamesSet = new HashSet<>(actualDirectoryNames);
            actualDirectoryNamesSet.removeAll(expectedDirectoryNames);
            var missingDirectoriesMessage = String.join(", ", actualDirectoryNamesSet);
            fail("Missing expected directories " + expectedResultsDirectory + " does not contain directories " + missingDirectoriesMessage);
        }
        if(!actualDirectoryNames.containsAll(expectedDirectoryNames)) {
            var expectedDirectoryNamesSet = new HashSet<>(expectedDirectoryNames);
            expectedDirectoryNamesSet.removeAll(actualDirectoryNames);
            var missingDirectoriesMessage = String.join(", ", expectedDirectoryNamesSet);
            fail("Missing actual directories " + actualResultsDirectory + " does not contain directories " + missingDirectoriesMessage);
        }

        expectedFileNames
            .forEach(
                efn -> {
                    var expectedFilePath = new File(expectedResultsDirectory, efn);
                    var expected = FileUtils.readFileContents(expectedFilePath);
                    var actualFilePath = new File(actualResultsDirectory, efn);
                    var actual = FileUtils.readFileContents(actualFilePath);
                    assertEquals(expected, actual, "Actual file contents do not match expected for " + expectedFilePath.toPath());
                }
            );

        expectedDirectoryNames
            .forEach(
                edn -> {
                    var newExpectedResultsDirectory = new File(expectedResultsDirectory, edn);
                    var newActualResultsDirectory = new File(actualResultsDirectory, edn);
                    assertExpectedDirectoryMatchesActual(newExpectedResultsDirectory, newActualResultsDirectory);
                }
            );
    }

}