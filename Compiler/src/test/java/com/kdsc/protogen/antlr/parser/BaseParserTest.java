package com.kdsc.protogen.antlr.parser;

import com.kdsc.protogen.antlr.ProtoGenLexer;
import com.kdsc.protogen.antlr.ProtoGenParser;
import com.kdsc.protogen.antlr.visitor.ProtoGenVisitor;
import com.kdsc.protogen.antlr.errors.ProtoGenErrorListener;
import com.kdsc.protogen.parsetree.FileNode;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public abstract class BaseParserTest {

    public static final String DUMMY_SOURCE_FILE_NAME = "UnitTest";

    protected FileNode compileTestProgramAndCheckNoParserErrors(String testProgram) {

        System.out.println("//Test Program");
        System.out.println(testProgram);

        //TODO:KMD Not keen on having this source file name in two places
        var errorListener = new ProtoGenErrorListener(DUMMY_SOURCE_FILE_NAME);

        var parseTree = compileTestProgramAndReturnParseTree(errorListener, testProgram);

        var visitor = new ProtoGenVisitor(DUMMY_SOURCE_FILE_NAME);
        var fileNode = (FileNode) visitor.visit(parseTree);

        if(errorListener.errorOccurred()) {
            for(var message : errorListener.getErrors()) {
                System.out.println(message);
            }
            fail("Expected test program to compile without error");
        }

        System.out.println("//Parse Tree");
        System.out.println(fileNode.toFormattedString(1));

        return fileNode;
    }

    protected List<String> compileTestProgramReturnParserErrors(String testProgram) {

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
