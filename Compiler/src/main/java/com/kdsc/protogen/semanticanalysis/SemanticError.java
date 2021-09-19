package com.kdsc.protogen.semanticanalysis;

import com.kdsc.protogen.utils.parameterchecking.Numbers;

import java.util.Objects;

public record SemanticError(
    SemanticErrorType semanticErrorType,
    String sourceFileName,
    long line,
    long charPosition,
    Object... arguments
) {

    public static final String PARSER_ERROR_MESSAGE = "PROTOGEN SEMANTIC ERROR %d in:%s at line:%d char:%d with msg:%s";

    public SemanticError {
        Objects.requireNonNull(semanticErrorType);
        Objects.requireNonNull(sourceFileName);
        Numbers.requireOneOrGreater(line);
        Numbers.requireZeroOrGreater(charPosition);
    }

    public String getFullErrorMessage() {
        return PARSER_ERROR_MESSAGE.formatted(semanticErrorType.getNumber(), sourceFileName, line, charPosition, semanticErrorType.getMessage(arguments));
    }
}
