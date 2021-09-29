package com.kdsc.protogen;

import com.kdsc.protogen.antlr.generated.ProtoGenLexer;
import com.kdsc.protogen.antlr.generated.ProtoGenParser;
import com.kdsc.protogen.antlr.visitor.ProtoGenVisitor;
import com.kdsc.protogen.antlr.ProtoGenErrorListener;
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

    public static final String FAKE_SOURCE_FILE_NAME_AND_PATH = "FakeSourceFileName.pg";
    public static final String BASE_NAMESPACE = "";

    protected FileNode runCompilerToParserCheckNoErrors(String testProgram) {

        System.out.println("//Test Program");
        System.out.println(testProgram);

        //TODO:KMD Not keen on having this source file name in two places
        var errorListener = new ProtoGenErrorListener(FAKE_SOURCE_FILE_NAME_AND_PATH);

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
        System.out.println(fileNode.toFormattedString(1));

        return fileNode;
    }

    protected List<String> runCompilerToParserReturnParserErrors(String testProgram) {

        System.out.println("//Test Program");
        System.out.println(testProgram);

        var errorListener = new ProtoGenErrorListener(FAKE_SOURCE_FILE_NAME_AND_PATH);

        compileTestProgramAndReturnParseTree(errorListener, testProgram);

        System.out.println("//Error Messages");
        if(!errorListener.errorOccurred()) {
            System.out.println("None".indent(4));
        }
        errorListener.getErrors()
            .forEach(e -> System.out.println(e.indent(4)));

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
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(fileNode));
        System.out.println("//Semantic Errors");
        if(semanticErrors.size() == 0) {
            System.out.println("None".indent(4));
        }
        //TODO:KMD Why are we getting extra newlines here
        semanticErrors
            .forEach(se -> System.out.println(se.toString().indent(4)));
        return semanticErrors;
    }

    protected List<com.kdsc.protogen.filegenerationtree.FileNode> runCompilerToTransformReturnProtoFileNodes(String testProgram) {
        var fileNode = compileTestProgramCheckNoParserOrSemanticErrors(testProgram);
        var transformer = new Transformer();
        var transformerContext = new TransformerContext(BASE_NAMESPACE);
        var fileGenerationTreeList =  transformer.transform(transformerContext, List.of(fileNode));
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

    //TODO:KMD Sort out all this list of stuff it seems a bit inconsistent
    private FileNode compileTestProgramCheckNoParserOrSemanticErrors(String testProgram) {
        var fileNode = runCompilerToParseTreePostProcessReturnFileNode(testProgram);
        var semanticErrors = SemanticAnalyser.runSemanticAnalysis(List.of(fileNode));
        if(semanticErrors.size() != 0) {
            semanticErrors
                    .forEach(System.out::println);
            fail("Unexpected semantic errors");
        }
        return fileNode;
    }

}