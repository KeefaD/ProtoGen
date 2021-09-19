package com.kdsc.protogen.semanticanalysis;

import com.kdsc.protogen.utils.parameterchecking.Numbers;
import com.kdsc.protogen.utils.parameterchecking.Strings;

public enum SemanticErrorType {

    REDEFINITION_OF_OBJECT(1, "Redefinition of object %s"),
    REDEFINITION_OF_ENUM_VERSION(2, "Redefinition of enum version %d"),
    REDEFINITION_OF_ENUM_CASE(3, "Redefinition of enum case %s");

    private final long number;
    private final String message;

    SemanticErrorType(long number, String message) {
        Numbers.requireOneOrGreater(number);
        Strings.requireNonBlank(message);
        this.number = number;
        this.message = message;
    }

    public long getNumber() {
        return number;
    }

    public String getMessage(Object... arguments) {
        return String.format(message, arguments);
    }

}
