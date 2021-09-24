package com.kdsc.protogen.semanticanalysis;

import com.kdsc.protogen.utils.parameterchecking.Numbers;
import com.kdsc.protogen.utils.parameterchecking.Strings;

public enum SemanticErrorType {

    REDEFINITION_OF_OBJECT(1, "Redefinition of object %s"),
    REDEFINITION_OF_ENUM_VERSION(2, "Redefinition of enum version %d"),
    REDEFINITION_OF_ENUM_CASE(3, "Redefinition of enum case %s"),
    REDEFINITION_OF_TYPE_VERSION(4, "Redefinition of type version %d"),
    TYPE_REFERS_TO_NON_EXISTENT_TYPE_IN_IMPLEMENTS_LIST(5, "Type %s refers to non existent type %s in implements list"),
    REDEFINITION_OF_GENERIC_PARAMETER(6, "Type %s redefines generic parameter %s"),
    GENERIC_PARAMETER_BOUNDS_REFERS_TO_NON_EXISTENT_TYPE(7, "Generic parameter bounds for type parameter %s refers to non existent type %s"),
    GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES(8, "Generic parameter bounds for type parameter %s refers to non type %s more than once"),
    GENERIC_PARAMETER_HAS_NOT_BEEN_DEFINED_IN_TYPE(9, "Generic parameter %s has not been defined in type %s"),
    MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE(10, "More than one non interface type specified in implements list for type %s non interface type %s"),
    NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION(11, "Number of type parameters in implements list for item %s - %d does not match type definition %d"),
    UNKNOWN_OBJECT(12, "Unknown object %s"),
    CANNOT_HAVE_IMPLEMENTS_LIST_ON_OUTER_TYPE_AND_VERSION_AT_THE_SAME_TIME(13, "Cannot have implements list on outer type and version at the same time %s");

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

