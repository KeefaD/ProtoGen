package com.kdsc.protogen.semanticanalysis;

import com.kdsc.protogen.utils.parameterchecking.Numbers;
import com.kdsc.protogen.utils.parameterchecking.Strings;

public enum SemanticErrorType {

    REDEFINITION_OF_OBJECT(1, "Redefinition of object %s"),
    REDEFINITION_OF_ENUM_VERSION(2, "Redefinition of enum version %d"),
    REDEFINITION_OF_ENUM_CASE(3, "Redefinition of enum case %s"),
    REDEFINITION_OF_TYPE_VERSION(4, "Redefinition of type version %d"),
    TYPE_REFERS_TO_NON_EXISTENT_TYPE_IN_IMPLEMENTS_LIST(5, "Type %s refers to non existent type %s in implements list");

    private final long number;
    private final String message;

    SemanticErrorType(final long number, final String message) {
        Numbers.requireOneOrGreater(number);
        Strings.requireNonBlank(message);
        this.number = number;
        this.message = message;
    }

    public long getNumber() {
        return number;
    }

    public String getMessage(final Object... arguments) {
        return message.formatted(arguments);
    }

}
