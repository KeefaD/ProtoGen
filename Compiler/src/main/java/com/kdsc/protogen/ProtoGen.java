package com.kdsc.protogen;


import com.kdsc.protogen.antlr.ProtoGenLexer;
import com.kdsc.protogen.antlr.ProtoGenParser;
import com.kdsc.protogen.antlr.visitor.ProtoGenVisitor;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

//TODO:KMD This is hideously out of date, but we don't need it yet
public class ProtoGen {
    public static void main(final String... args) {

        var testProgram = "type keithsnamespace.keithstype";

        var inputStream = new ByteArrayInputStream(testProgram.getBytes(StandardCharsets.UTF_8));

        try {
            var antlrInputStream = new ANTLRInputStream(inputStream);
            var lexer = new ProtoGenLexer(antlrInputStream);
            var tokens = new CommonTokenStream(lexer);
            var parser = new ProtoGenParser(tokens);
            var visitor = new ProtoGenVisitor("NA");
            visitor.visit(parser.file());

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("I will rule the world");
    }
}
