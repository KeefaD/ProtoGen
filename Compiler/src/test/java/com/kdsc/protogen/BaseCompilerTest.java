package com.kdsc.protogen;

import com.kdsc.protogen.antlr.ProtoGenLexer;
import com.kdsc.protogen.antlr.ProtoGenParser;
import com.kdsc.protogen.antlr.visitor.ProtoGenVisitor;
import com.kdsc.protogen.antlr.errors.ProtoGenErrorListener;
import com.kdsc.protogen.parsetree.FileNode;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public abstract class BaseCompilerTest {

    public static final String DUMMY_SOURCE_FILE_NAME = "UnitTest";

    protected FileNode runCompilerToParserCheckNoErrors(String testProgram) {

        System.out.println("//Test Program");
        System.out.println(testProgram);

        //TODO:KMD Not keen on having this source file name in two places
        var errorListener = new ProtoGenErrorListener(DUMMY_SOURCE_FILE_NAME);

        var parseTree = compileTestProgramAndReturnParseTree(errorListener, testProgram);

        if(errorListener.errorOccurred()) {
            for(var message : errorListener.getErrors()) {
                System.out.println(message);
            }
            fail("Expected test program to compile without error");
        }

        var visitor = new ProtoGenVisitor(DUMMY_SOURCE_FILE_NAME);
        var fileNode = (FileNode) visitor.visit(parseTree);

        System.out.println("//Parse Tree");
        System.out.println(fileNode.toFormattedString(1));

        return fileNode;
    }

    protected List<String> runCompilerToParserReturnParserErrors(String testProgram) {

        System.out.println("//Test Program");
        System.out.println(testProgram);

        var errorListener = new ProtoGenErrorListener(DUMMY_SOURCE_FILE_NAME);

        compileTestProgramAndReturnParseTree(errorListener, testProgram);

        System.out.println("//Error Messages");
        if(!errorListener.errorOccurred()) {
            System.out.println("None".indent(4));
        }
        for(var message : errorListener.getErrors()) {
            System.out.println(message.indent(4));
        }

        return errorListener.getErrors();
    }

    protected FileNode runCompilerToParseTreePostProcessReturnFileNode(String testProgram) {
        var fileNode = runCompilerToParserCheckNoErrors(testProgram);
        var returnFileNode = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode)).get(0);
        System.out.println("//Replaced Parse Tree");
        System.out.println(returnFileNode.toFormattedString(1));
        return returnFileNode;
    }

    protected List<SemanticError> runCompilerToSemanticAnalyserReturnSemanticErrors(String testProgram) {
        var fileNode = runCompilerToParserCheckNoErrors(testProgram);
        var fileNodeList = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode));
        return SemanticAnalyser.runSemanticAnalysis(fileNodeList);
    }

    protected List<com.kdsc.protogen.filegenerationtree.FileNode> runCompilerToTransformReturnProtoFileNodes(String testProgram) {
        var fileNode = runCompilerToParserCheckNoErrors(testProgram);
        var fileNodeList = UndetectableNodeReplacer.replaceUndetectableNodes(List.of(fileNode));
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(fileNodeList);
        if(semanticErrors.size() != 0) {
            semanticErrors
                .forEach(System.out::println);
            fail("Unexpected semantic errors");
        }
        var transformer = new Transformer();
        var transformerContext = new TransformerContext();
        return transformer.transform(transformerContext, fileNodeList);
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

}