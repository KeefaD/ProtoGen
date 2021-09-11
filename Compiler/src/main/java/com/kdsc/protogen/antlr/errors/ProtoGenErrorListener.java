package com.kdsc.protogen.antlr.errors;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.util.ArrayList;
import java.util.List;

public class ProtoGenErrorListener extends BaseErrorListener {

    private List<String> errors = new ArrayList<>();

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        errors.add("line " + line + " char " + charPositionInLine + " " + msg);
    }

    public boolean errorOccurred() {
        return errors.size() != 0;
    }

    public List<String> getErrors() {
        return errors;
    }

}
