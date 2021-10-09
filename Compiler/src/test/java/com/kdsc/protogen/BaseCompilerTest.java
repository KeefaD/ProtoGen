package com.kdsc.protogen;

import com.kdsc.protogen.antlr.ParserError;
import com.kdsc.protogen.antlr.ParserResults;
import com.kdsc.protogen.antlr.generated.ProtoGenLexer;
import com.kdsc.protogen.antlr.generated.ProtoGenParser;
import com.kdsc.protogen.antlr.visitor.ProtoGenVisitor;
import com.kdsc.protogen.antlr.ParserErrorListener;
import com.kdsc.protogen.compilerresults.CompilerResults;
import com.kdsc.protogen.parsetreenodes.FileNode;
import com.kdsc.protogen.parsetreenodes.ParseTreeFormattedStringOptions;
import com.kdsc.protogen.parsetreepostprocessing.UndetectableNodeReplacer;
import com.kdsc.protogen.semanticanalysis.SemanticAnalyser;
import com.kdsc.protogen.semanticanalysis.SemanticError;
import com.kdsc.protogen.transform.TransformerContext;
import com.kdsc.protogen.transform.proto.Transformer;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public abstract class BaseCompilerTest {

    public static final String FAKE_SOURCE_FILE_NAME_AND_PATH = "FakeSourceFileName.pg";
    public static final String BASE_NAMESPACE = "";

    protected FileNode runCompilerToParserCheckNoErrors(String testProgram) {

        System.out.println("//Test Program");
        System.out.println(testProgram);

        var errorListener = new ParserErrorListener(FAKE_SOURCE_FILE_NAME_AND_PATH);

        var parseTree = compileTestProgramAndReturnParseTree(errorListener, testProgram);

        if(errorListener.errorOccurred()) {
            for(var message : errorListener.getErrors()) {
                System.out.println(message);
            }
            fail("Expected test program to compile without error");
        }

        var visitor = new ProtoGenVisitor(FAKE_SOURCE_FILE_NAME_AND_PATH);
        var fileNode = (FileNode) visitor.visit(parseTree);

        System.out.println("//Parse Tree");
        System.out.println(fileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 1));

        return fileNode;
    }

    protected List<ParserError> runCompilerToParserReturnParserErrors(String testProgram) {

        System.out.println("//Test Program");
        System.out.println(testProgram);

        var errorListener = new ParserErrorListener(FAKE_SOURCE_FILE_NAME_AND_PATH);

        compileTestProgramAndReturnParseTree(errorListener, testProgram);

        System.out.println("//Error Messages");
        if(!errorListener.errorOccurred()) {
            System.out.println("None".indent(4));
        }
        errorListener.getErrors()
            .forEach(e -> System.out.println(e.toString().indent(4)));

        return errorListener.getErrors();
    }

    protected FileNode runCompilerToParseTreePostProcessReturnFileNode(String testProgram) {

        var fileNode = runCompilerToParserCheckNoErrors(testProgram);

        var parserResult = new ParserResults(Collections.emptyList(), List.of(fileNode));

        var undetectableNodeReplacer = new UndetectableNodeReplacer();
        var returnFileNode = undetectableNodeReplacer.replaceUndetectableNodes(parserResult).get(0);

        System.out.println("//Replaced Parse Tree");
        System.out.println(returnFileNode.toFormattedString(ParseTreeFormattedStringOptions.hideBaseParseTreeNode, 1));

        return returnFileNode;
    }

    protected List<SemanticError> runCompilerToSemanticAnalyserReturnSemanticErrors(String testProgram) {

        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);

        var compilerResults = new CompilerResults(List.of(fileNode));
        var semanticAnalyser = new SemanticAnalyser();
        var semanticErrors = semanticAnalyser.runSemanticAnalysis(compilerResults);

        System.out.println("//Semantic Errors");
        if(semanticErrors.size() == 0) {
            System.out.println("None".indent(4));
        }

        semanticErrors
            .forEach(se -> System.out.print(se.toString().indent(4)));

        return semanticErrors;
    }

    protected List<com.kdsc.protogen.filegenerationtreenodes.FileNode> runCompilerToTransformReturnProtoFileNodes(String testProgram) {

        var fileNode = compileTestProgramCheckNoParserOrSemanticErrors(testProgram);

        var compilerResults = new CompilerResults(List.of(fileNode));
        var transformer = new Transformer();

        var transformerContext = new TransformerContext(
            BASE_NAMESPACE
        );
        var fileGenerationTreeList =  transformer.transform(compilerResults, transformerContext);

        System.out.println("//File Generation Tree");
        fileGenerationTreeList
            .forEach(
                fgt -> System.out.println(fgt.toFormattedString(1))
            );

        return fileGenerationTreeList;
    }

    private ParseTree compileTestProgramAndReturnParseTree(BaseErrorListener errorListener, String testProgram) {

        var inputStream = new ByteArrayInputStream(testProgram.getBytes(StandardCharsets.UTF_8));

        ANTLRInputStream antlrInputStream;
        try {
            antlrInputStream = new ANTLRInputStream(inputStream);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        var lexer = new ProtoGenLexer(antlrInputStream);
        var tokens = new CommonTokenStream(lexer);
        var parser = new ProtoGenParser(tokens);

        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);

        return parser.file();
    }

    private FileNode compileTestProgramCheckNoParserOrSemanticErrors(String testProgram) {

        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);

        var compilerResults = new CompilerResults(List.of(fileNode));
        var semanticAnalyser = new SemanticAnalyser();
        var semanticErrors = semanticAnalyser.runSemanticAnalysis(compilerResults);

        if(semanticErrors.size() != 0) {
            semanticErrors
                .forEach(System.out::println);
            fail("Unexpected semantic errors");
        }

        return fileNode;
    }

}