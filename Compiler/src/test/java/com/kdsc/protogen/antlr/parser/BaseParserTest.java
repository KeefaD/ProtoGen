package com.kdsc.protogen.antlr.parser;

import com.kdsc.protogen.antlr.ProtoGenLexer;
import com.kdsc.protogen.antlr.ProtoGenParser;
import com.kdsc.protogen.antlr.ProtoGenVisitorImplementation;
import com.kdsc.protogen.antlr.errors.ProtoGenErrorListener;
import com.kdsc.protogen.parsetree.FileNode;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public abstract class BaseParserTest {

    protected void compileTestProgramAndCheckNoParserErrors(String testProgram) {

        System.out.println("//Test Program");
        System.out.println(testProgram);

        var inputStream = new ByteArrayInputStream(testProgram.getBytes(StandardCharsets.UTF_8));

        try {
            var antlrInputStream = new ANTLRInputStream(inputStream);
            var lexer = new ProtoGenLexer(antlrInputStream);
            var tokens = new CommonTokenStream(lexer);
            var parser = new ProtoGenParser(tokens);
            parser.removeErrorListeners();
            var errorListener = new ProtoGenErrorListener();
            parser.addErrorListener(errorListener);
            var visitor = new ProtoGenVisitorImplementation();
            var node = (FileNode) visitor.visit(parser.file());

            if(errorListener.errorOccurred()) {
                for(var message : errorListener.getErrors()) {
                    System.out.println(message);
                }
                fail("Expected test program to compile without error");
            }

            System.out.println("//Parse Tree");
            System.out.println(node.toFormattedString(1));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected List<String> compileTestProgramReturnParserErrors(String testProgram) {
        System.out.println("//Test Program");
        System.out.println(testProgram);

        var inputStream = new ByteArrayInputStream(testProgram.getBytes(StandardCharsets.UTF_8));

        var errorListener = new ProtoGenErrorListener();

        try {
            var antlrInputStream = new ANTLRInputStream(inputStream);
            var lexer = new ProtoGenLexer(antlrInputStream);
            var tokens = new CommonTokenStream(lexer);
            var parser = new ProtoGenParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(errorListener);
            var visitor = new ProtoGenVisitorImplementation();
            visitor.visit(parser.file());

            System.out.println("//Error Messages");
            if(!errorListener.errorOccurred()) {
                System.out.println("None".indent(4));
            }

            for(var message : errorListener.getErrors()) {
                System.out.println(message.indent(4));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return errorListener.getErrors();
    }
}
