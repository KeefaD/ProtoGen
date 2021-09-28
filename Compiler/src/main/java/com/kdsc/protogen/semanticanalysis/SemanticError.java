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

    public static final String SEMANTIC_ERROR_MESSAGE = "PROTOGEN_SEMANTIC_ERROR_%d in file:%s at line:%d char:%d with message:%s";

    public SemanticError {
        Objects.requireNonNull(semanticErrorType);
        Objects.requireNonNull(sourceFileName);
        Numbers.requireOneOrGreater(line);
        Numbers.requireZeroOrGreater(charPosition);
    }

    public String getFullErrorMessage() {
        return SEMANTIC_ERROR_MESSAGE.formatted(semanticErrorType.getNumber(), sourceFileName, line, charPosition, semanticErrorType.getMessage(arguments));
    }

    @Override
    public String toString() {
        return getFullErrorMessage();
    }

}
