package com.kdsc.protogen;

import com.kdsc.protogen.antlr.Parser;
import com.kdsc.protogen.parsetreepostprocessing.UndetectableNodeReplacer;
import com.kdsc.protogen.semanticanalysis.SemanticAnalyser;
import com.kdsc.protogen.transform.TransformerContext;
import com.kdsc.protogen.transform.proto.Transformer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProtoGen {

    public static final char OPTION_MARKER = '-';
    public static final String BASE_NAMESPACE = OPTION_MARKER + "basenamespace";
    public static final String SHOW_PARSE_TREE = OPTION_MARKER + "showparsetree";
    public static final String SHOW_REPLACED_PARSE_TREE = OPTION_MARKER + "showreplacedparsetree";
    public static final String SHOW_FILE_GENERATION_TREE = OPTION_MARKER + "showfilegenerationtree";
    public static final String ARGUMENT_VALUE_SEPARATOR = "=";

    //TODO:KMD This class is a mess at the moment, obviously clean it all up
    public static void main(final String... args) {

        if(args == null || args.length < 1) {
            printUsage();
            return;
        }

        var sourceFileList = new ArrayList<String>();
        var useBaseNamespace = false;
        var baseNamespace = "";
        var showParseTree = false;
        var showReplacedParseTree = false;
        var showFileGenerationTree = false;
        for(var arg : args) {
            if(arg.length() > 0 && arg.charAt(0) == OPTION_MARKER) {
                var lowerCaseArg = arg.toLowerCase();
                if(lowerCaseArg.startsWith(BASE_NAMESPACE)) {
                    useBaseNamespace = true;
                    var splitArg = arg.split(ARGUMENT_VALUE_SEPARATOR);
                    if(splitArg.length != 2) {
                        printUsage();
                        return;
                    }
                    baseNamespace = splitArg[1];
                } else if(lowerCaseArg.startsWith(SHOW_PARSE_TREE)) {
                    showParseTree = true;
                    continue;
                } else if(lowerCaseArg.startsWith(SHOW_REPLACED_PARSE_TREE)) {
                    showReplacedParseTree = true;
                    continue;
                } else if(lowerCaseArg.startsWith(SHOW_FILE_GENERATION_TREE)) {
                    showFileGenerationTree = true;
                    continue;
                }
                if(arg.contains("=")) {
                    printUsage();
                    return;
                }
            }

            sourceFileList.add(arg);
        }

        compileProgram(sourceFileList, useBaseNamespace, baseNamespace, showParseTree, showReplacedParseTree, showFileGenerationTree);
    }

    private static boolean compileProgram(final List<String> sourceFileList, final boolean useBaseNamespace, final String baseNamespace, final boolean showParseTree, final boolean showReplacedParseTree, final boolean showFileGenerationTree) {

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

        var parserResults = Parser.parse(filePaths);

        if(parserResults.hasParserErrorOccurred()) {
            System.out.println("//Parser Errors");
            parserResults
                .getParserErrors()
                .forEach(pe -> System.out.println("    " + pe));
            return false;
        }

        if(showParseTree) {
            System.out.println();
            System.out.println("//Parse Tree");
            parserResults.getFileNodes()
                .forEach(pf -> System.out.println(pf.toFormattedString(1)));
        }

        var replacedFileNodes = UndetectableNodeReplacer.replaceUndetectableNodes(parserResults.getFileNodes());

        if(showReplacedParseTree) {
            System.out.println();
            System.out.println("//Replaced Parse Tree");
            replacedFileNodes
                .forEach(pf -> System.out.println(pf.toFormattedString(1)));
        }

        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(replacedFileNodes);

        if(semanticErrors.size() > 0) {
            System.out.println();
            System.out.println("//Semantic Errors");
            semanticErrors
                .forEach(se -> System.out.println("    " + se));
            return false;
        }

        var transformer = new Transformer();
        var transformerContext = new TransformerContext(baseNamespace);
        var fileGenerationTree =  transformer.transform(transformerContext, replacedFileNodes);

        if(showFileGenerationTree) {
            System.out.println();
            System.out.println("//File Generation Tree");
            fileGenerationTree
                .forEach(fgt -> System.out.println(fgt.toFormattedString(1)));
            return false;
        }

        return true;
    }

    private static void printUsage() {
        var usageMessage = """
        
        ProtoGen sourceFiles -baseNamespace=com.test -showParseTree -showReplacedParseTree -showFileGenerationTree
        """;
        System.out.println(usageMessage);
    }

}