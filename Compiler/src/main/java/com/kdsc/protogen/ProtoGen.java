package com.kdsc.protogen;

import com.kdsc.protogen.antlr.Parser;
import com.kdsc.protogen.codegeneration.CodeGenerate;
import com.kdsc.protogen.codegeneration.CodeGeneratorContext;
import com.kdsc.protogen.compilerresults.CompilerResults;
import com.kdsc.protogen.parsetreenodes.ParseTreeFormattedStringOptions;
import com.kdsc.protogen.parsetreepostprocessing.UndetectableNodeReplacer;
import com.kdsc.protogen.semanticanalysis.SemanticAnalyser;
import com.kdsc.protogen.transform.Transform;
import com.kdsc.protogen.transform.TransformerContext;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//TODO:KMD Make ProtoGenType an abstract type and create ProtoGenInterface as well
//TODO:KMD Test running Gradle on the command line to build everything
//TODO:KMD Test building on Windows
//TODO:KMD I think you can end up with optional optional as a field type optional T where T is optional, close this down
//TODO:KMD We need to see if we can categorise the unit tests and make them show up in those categories in the unit test results
//TODO:KMD We need to run protobuf on the examples
//TODO:KMD How are we going to represent versions in Proto
//TODO:KMD We need to do proto name escaping
//TODO:KMD We need to to do name escaping in general or prevent keywords, need to make up your mind soon, keywords is going to be annoying once you add more languages, as long as the types come out with the right name it is ok
//TODO:KMD Think about capitalisation of fields
//TODO:KMD Check isProtoGen type for types that have generic parameters, it's all going to go to shit if they are not
//TODO:KMD Need to test moving all the directories around
//TODO:KMD Need to think about capitalisation for namespaces, should we allow uppercase packages, investigate
//TODO:KMD Maybe don't do versioning or type libraries yet, but leave it open to it
//TODO:KMD Continuous calculations
//TODO:KDM Clone in generated objects
//TODO:KMD Builders in generated objects?
//TODO:KMD Imports syntactic sugar?
//TODO:KMD Maybe don't do keys yet, not that interesting until ProtoBoxes
//TODO:KMD Think about implementing the same interface multiple times, this is really IMPORTANT
//TODO:KMD Protogen RPC service, investigate
//TODO:KMD Perhaps use Guava for immutable stuff
//TODO:KMD Follow google style guide
//TODO:KMD Deal with Double/Float NAN and equals
//TODO:KMD Make a reflection test for equals and hashcode in nodes, ParseTree equals and hash code, how are we going to know if a field gets added, perhaps we should add a reflection test, or make equals hashcode reflection
//TODO:KMD Need to do a day just working on TODO:KMD's to keep the numbers down otherwise it is going ot get out of control
//TODO:KMD Generate standard example tests
//TODO:KMD Have a think about file contexts and proto and java, we have to share them or come up with something clever because of the shared FieldTypeNodes
//TODO:KMD Make file generation tree nodes consistent with parse tree nodes
//TODO:KMD Need to think about charsets, perhaps write a program in a funny charset, it has to be totally reliable
//TODO:KMD I think we need to add verbose to the compiler and logging
//TODO:KMD When you use an incorrectly spelled primitive type it thinks it is a type parameter even through that type parameter is not specified, improve that error message
//TODO:KMD Put line numbers when printing ProtoGen input files in tests
public final class ProtoGen {

    public static final char OPTION_MARKER = '-';
    public static final String BASE_NAMESPACE = OPTION_MARKER + "basenamespace";
    public static final String JAVA_OUTPUT_DIRECTORY = OPTION_MARKER + "javaoutputdirectory";
    public static final String PROTO_OUTPUT_DIRECTORY = OPTION_MARKER + "protooutputdirectory";
    public static final String SHOW_PARSE_TREE = OPTION_MARKER + "showparsetree";
    public static final String SHOW_REPLACED_PARSE_TREE = OPTION_MARKER + "showreplacedparsetree";
    public static final String SHOW_FILE_GENERATION_TREE = OPTION_MARKER + "showfilegenerationtree";
    public static final String SHOW_PARSE_TREE_NODE_DETAILS = OPTION_MARKER + "showparsetreenodedetails";
    public static final String ARGUMENT_VALUE_SEPARATOR = "=";

    //TODO:KMD This class is a mess at the moment, obviously clean it all up
    public static int main(final String... args) {

        //TODO:KMD Don't need this, just need to check we have source files, that is the minimum
        if(args == null || args.length < 1) {
            printUsage();
            return -1;
        }

        var sourceFileList = new ArrayList<String>();
        var useBaseNamespace = false;
        var baseNamespace = "";
        var useJavaOutputDirectory = false;
        var javaOutputDirectory = "";
        var useProtoOutputDirectory = false;
        var protoOutputDirectory = "";
        var showParseTree = false;
        var showReplacedParseTree = false;
        var showFileGenerationTree = false;
        var showParseTreeNodeDetails = false;
        for(var arg : args) {
            if(arg.length() > 0 && arg.charAt(0) == OPTION_MARKER) {
                var lowerCaseArg = arg.toLowerCase();
                if(lowerCaseArg.split("=").length == 2) {
                    lowerCaseArg = lowerCaseArg.split("=")[0];
                }
                if(lowerCaseArg.equals(BASE_NAMESPACE)) {
                    useBaseNamespace = true;
                    var splitArg = arg.split(ARGUMENT_VALUE_SEPARATOR);
                    if(splitArg.length != 2) {
                        printUsage();
                        return -1;
                    }
                    baseNamespace = splitArg[1];
                } else if(lowerCaseArg.equals(JAVA_OUTPUT_DIRECTORY)) {
                    useJavaOutputDirectory = true;
                    var splitArg = arg.split(ARGUMENT_VALUE_SEPARATOR);
                    if(splitArg.length != 2) {
                        printUsage();
                        return -1;
                    }
                    javaOutputDirectory = splitArg[1];
                } else if(lowerCaseArg.equals(PROTO_OUTPUT_DIRECTORY)) {
                    useProtoOutputDirectory = true;
                    var splitArg = arg.split(ARGUMENT_VALUE_SEPARATOR);
                    if(splitArg.length != 2) {
                        printUsage();
                        return -1;
                    }
                    protoOutputDirectory = splitArg[1];
                } else if(lowerCaseArg.equals(SHOW_PARSE_TREE)) {
                    showParseTree = true;
                } else if(lowerCaseArg.equals(SHOW_REPLACED_PARSE_TREE)) {
                    showReplacedParseTree = true;
                } else if(lowerCaseArg.equals(SHOW_FILE_GENERATION_TREE)) {
                    showFileGenerationTree = true;
                } else if(lowerCaseArg.equals(SHOW_PARSE_TREE_NODE_DETAILS)) {
                    showParseTreeNodeDetails = true;
                } else if(arg.contains("=")) {
                    printUsage();
                    return -1;
                }
                continue;
            }
            sourceFileList.add(arg);
        }

        return compileProgram(sourceFileList, useBaseNamespace, baseNamespace, useJavaOutputDirectory, javaOutputDirectory, useProtoOutputDirectory, protoOutputDirectory, showParseTree, showReplacedParseTree, showFileGenerationTree, showParseTreeNodeDetails);
    }

    private static int compileProgram(final List<String> sourceFileList, final boolean useBaseNamespace, final String baseNamespace, final boolean useJavaOutputDirectory, final String javaOutputDirectory, final boolean useProtoOutputDirectory, final String protoOutputDirectory, final boolean showParseTree, final boolean showReplacedParseTree, final boolean showFileGenerationTree, final boolean showParseTreeNodeDetails) {

        //TODO:KMD Assume one directory argument for now, can't be bothered to do files
//        sourceFileList
//            .stream()
//            .forEach(
//                sf -> {
//                    var file = new File(sf);
//                    if(!file.exists()) {
//                        System.out.println("Source file or directory doesn't exist " + sf);
//                        //TODO:KMD Return false
//                        return;
//                    }
//                    if(file.isDirectory()) {
//                        System.out.println("Is directory " + sf);
//                    }
//                }
//            );

        //TODO:KMD This is a bit odd
        var parseTreeFormattedStringOptions = showParseTreeNodeDetails
            ? ParseTreeFormattedStringOptions.defaultParseTreeFormattedStringOptions
            : ParseTreeFormattedStringOptions.hideBaseParseTreeNode;

        var directory = sourceFileList.get(0);
        List<String> filePaths;
        try (var walk = Files.walk(Paths.get(directory))) {
            filePaths = walk
                .map(Path::toFile)
                .filter(f -> !f.isDirectory())
                .map(File::toString)
                .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //TODO:KMD This is temporary
        System.out.println("//File paths");
        filePaths
            .forEach(fp -> System.out.println("    " + fp));

        var parser = new Parser();
        var parserResults = parser.parse(filePaths);

        if(parserResults.hasParserErrorOccurred()) {
            System.out.println("//Parser Errors");
            parserResults
                .parserErrors()
                .forEach(pe -> System.out.println("    " + pe));
            //TODO:KMD Don't like the fact this doesn't start at -1
            return -2;
        }

        if(showParseTree) {
            System.out.println();
            System.out.println("//Parse Tree");
            parserResults.fileNodes()
                .forEach(pf -> System.out.println(pf.toFormattedString(parseTreeFormattedStringOptions, 1)));
        }

        var undetectableNodeReplacer = new UndetectableNodeReplacer();
        var compilerResults = new CompilerResults(undetectableNodeReplacer.replaceUndetectableNodes(parserResults));

        if(showReplacedParseTree) {
            System.out.println();
            System.out.println("//Replaced Parse Tree");
            compilerResults
                .getFileNodes()
                .forEach(pf -> System.out.println(pf.toFormattedString(parseTreeFormattedStringOptions, 1)));
        }

        var semanticAnalyser = new SemanticAnalyser();
        var semanticErrors = semanticAnalyser.runSemanticAnalysis(compilerResults);

        if(semanticErrors.size() > 0) {
            System.out.println();
            System.out.println("//Semantic Errors");
            semanticErrors
                .forEach(se -> System.out.println("    " + se));
            return -3;
        }

        //TODO:KMD Perhaps this should be static
        var transform = new Transform();
        var transformerContext = new TransformerContext(
            baseNamespace
        );
        var fileGenerationTree =  transform.transform(compilerResults, transformerContext);

        if(showFileGenerationTree) {
            System.out.println();
            System.out.println("//File Generation Tree");
            fileGenerationTree
                .forEach(fgt -> System.out.println(fgt.toFormattedString(1)));
        }

        var codeGenerate = new CodeGenerate();
        var codeGeneratorContext = new CodeGeneratorContext(javaOutputDirectory, protoOutputDirectory);
        codeGenerate.generate(codeGeneratorContext, fileGenerationTree);

        return 0;
    }

    private static void printUsage() {
        var usageMessage = """
        
        ProtoGen sourceFiles -baseNamespace=com.test -javaOutputDirectory=/directory -protoOutputDirectory=/directory -showParseTree -showReplacedParseTree -showFileGenerationTree -showParseTreeNodeDetails
        """;
        System.out.println(usageMessage);
    }

}