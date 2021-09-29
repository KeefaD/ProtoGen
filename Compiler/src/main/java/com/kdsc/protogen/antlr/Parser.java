package com.kdsc.protogen.antlr;

import com.kdsc.protogen.antlr.generated.ProtoGenLexer;
import com.kdsc.protogen.antlr.generated.ProtoGenParser;
import com.kdsc.protogen.antlr.visitor.ProtoGenVisitor;
import com.kdsc.protogen.parsetree.FileNode;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {

    public static ParserResults parse(final List<String> pathsToParse) {
        var parserResults = new ParserResults();
        var fileNodes = pathsToParse
            .stream()
                .map(
                    p -> {
                        var errorListener = new ProtoGenErrorListener(p);

                        ANTLRInputStream antlrInputStream;
                        ProtoGenLexer protoGenLexer;

                        //TODO:KMD look at this
                        try (var inputStream = new FileInputStream(p)) {
                            try {
                                antlrInputStream = new ANTLRInputStream(inputStream);
                            } catch (IOException exception) {
                                throw new RuntimeException(exception);
                            }
                        } catch (IOException ioException) {
                            throw new RuntimeException(ioException);
                        }

                        protoGenLexer = new ProtoGenLexer(antlrInputStream);
                        var tokens = new CommonTokenStream(protoGenLexer);
                        var parser = new ProtoGenParser(tokens);

                        parser.removeErrorListeners();
                        parser.addErrorListener(errorListener);
                        var parseTree = parser.file();

                        if(errorListener.errorOccurred()) {
                            parserResults.getParserErrors().addAll(errorListener.getErrors());
                        }
                        var visitor = new ProtoGenVisitor(p);
                        return (FileNode) visitor.visit(parseTree);
                    }
                )
                .collect(Collectors.toList());

        parserResults.getFileNodes().addAll(fileNodes);
        return parserResults;
    }
}