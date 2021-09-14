package com.kdsc.protogen.antlr.errors;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.util.ArrayList;
import java.util.List;

public class ProtoGenErrorListener extends BaseErrorListener {

    public static final String PARSER_ERROR_MESSAGE = "PROTOGEN PARSER ERROR in:%s at line:%d char:%d with msg:%s";

    private final String sourceFileName;
    private final List<String> errors = new ArrayList<>();

    public ProtoGenErrorListener(String sourceFileName) {
        this.sourceFileName = sourceFileName;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        errors.add(PARSER_ERROR_MESSAGE.formatted(sourceFileName, line, charPositionInLine, msg));
    }

    public boolean errorOccurred() {
        return errors.size() != 0;
    }

    public List<String> getErrors() {
        return errors;
    }

}
