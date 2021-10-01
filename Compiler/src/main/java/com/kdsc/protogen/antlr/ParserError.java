package com.kdsc.protogen.antlr;

import com.kdsc.protogen.utils.parameterchecking.Numbers;
import com.kdsc.protogen.utils.parameterchecking.Strings;

import java.util.Objects;

public record ParserError(
    String sourceFileName,
    long line,
    long charPosition,
    String message
) {

    public static final String PARSER_ERROR_MESSAGE = "PROTOGEN_PARSER_ERROR in file:%s at line:%d char:%d with message:%s";

    public ParserError {
        Strings.requireNonBlank(sourceFileName);
        Numbers.requireOneOrGreater(line);
        Numbers.requireZeroOrGreater(charPosition);
        Strings.requireNonBlank(message);
    }

    public String getFullErrorMessage() {
        return PARSER_ERROR_MESSAGE.formatted(sourceFileName, line, charPosition, message);
    }

    @Override
    public String toString() {
        return getFullErrorMessage();
    }

}