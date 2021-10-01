package com.kdsc.protogen.antlr;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.util.ArrayList;
import java.util.List;

//TODO:KMD I think you can end up with optional optional as a field type optional T where T is optional, close this down
//TODO:KMD Missing silly test for this
public class ProtoGenErrorListener extends BaseErrorListener {

    private final String sourceFileName;

    private final List<ParserError> errors = new ArrayList<>();

    public ProtoGenErrorListener(final String sourceFileName) {
        this.sourceFileName = sourceFileName;
    }

    @Override
    public void syntaxError(final Recognizer<?, ?> recognizer, final Object offendingSymbol, final int line, final int charPositionInLine, final String msg, final RecognitionException e) {
        errors.add(new ParserError(sourceFileName, line, charPositionInLine, msg));
    }

    public boolean errorOccurred() {
        return errors.size() != 0;
    }

    public List<ParserError> getErrors() {
        return errors;
    }

}