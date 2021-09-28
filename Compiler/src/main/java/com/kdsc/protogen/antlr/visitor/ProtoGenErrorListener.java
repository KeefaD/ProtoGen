package com.kdsc.protogen.antlr.visitor;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.util.ArrayList;
import java.util.List;

//TODO:KMD Missing silly test for this
public class ProtoGenErrorListener extends BaseErrorListener {

    public static final String PARSER_ERROR_MESSAGE = "PROTOGEN_PARSER_ERROR in file:%s at line:%d char:%d with msg:%s";

    private final String sourceFileName;
    private final List<String> errors = new ArrayList<>();

    public ProtoGenErrorListener(final String sourceFileName) {
        this.sourceFileName = sourceFileName;
    }

    @Override
    public void syntaxError(final Recognizer<?, ?> recognizer, final Object offendingSymbol, final int line, final int charPositionInLine, final String msg, final RecognitionException e) {
        errors.add(PARSER_ERROR_MESSAGE.formatted(sourceFileName, line, charPositionInLine, msg));
    }

    public boolean errorOccurred() {
        return errors.size() != 0;
    }

    public List<String> getErrors() {
        return errors;
    }

}
