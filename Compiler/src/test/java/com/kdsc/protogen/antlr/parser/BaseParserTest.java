package com.kdsc.protogen.antlr.parser;

import com.kdsc.protogen.antlr.ProtoGenLexer;
import com.kdsc.protogen.antlr.ProtoGenParser;
import com.kdsc.protogen.antlr.ProtoGenVisitorImplementation;
import com.kdsc.protogen.antlr.errors.ProtoGenErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public abstract class BaseParserTest {

    protected void compileProgramAndCheckNoParserErrors(String testProgram) {

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
            var node = visitor.visit(parser.file());

            if(errorListener.errorOccurred()) {
                for(var message : errorListener.getErrors()) {
                    System.out.println(message);
                }
                assert(false);
            }

            System.out.println(node);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
