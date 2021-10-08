package com.kdsc.protogen.antlr;

import com.kdsc.protogen.utils.parameterchecking.Strings;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.util.ArrayList;
import java.util.List;

public final class ParserErrorListener extends BaseErrorListener {

    private final String sourceFileName;

    private final List<ParserError> errors = new ArrayList<>();

    public ParserErrorListener(final String sourceFileName) {
        Strings.requireNonBlank(sourceFileName);
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