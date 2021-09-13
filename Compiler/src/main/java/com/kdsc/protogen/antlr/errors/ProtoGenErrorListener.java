package com.kdsc.protogen.antlr.errors;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.util.ArrayList;
import java.util.List;

public class ProtoGenErrorListener extends BaseErrorListener {

    public static final String PARSER_ERROR_MESSAGE = "PROTOGEN PARSER ERROR at line:%d char:%d with msg:%s";

    private final List<String> errors = new ArrayList<>();


    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        errors.add(PARSER_ERROR_MESSAGE.formatted(line, charPositionInLine, msg));
    }

    public boolean errorOccurred() {
        return errors.size() != 0;
    }

    public List<String> getErrors() {
        return errors;
    }

}
