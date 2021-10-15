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
    GENERIC_PARAMETER_BOUNDS_REFERS_TO_TYPE_MULTIPLE_TIMES(8, "Generic parameter bounds for type parameter %s refers to type %s more than once"),
    MORE_THAN_ONE_NON_INTERFACE_SPECIFIED_IN_IMPLEMENTS_LIST_FOR_TYPE(9, "More than one non interface type specified in implements list for type %s non interface type %s"),
    NUMBER_OF_TYPE_PARAMETERS_IN_IMPLEMENTS_ITEM_DOES_NOT_MATCH_TYPE_DEFINITION(10, "Number of type parameters in implements list for item %s - %d does not match type definition %d"),
    UNKNOWN_OBJECT(11, "Unknown object %s"),
    CANNOT_HAVE_IMPLEMENTS_LIST_ON_OUTER_TYPE_AND_VERSION_AT_THE_SAME_TIME(12, "Cannot have implements list on outer type and version at the same time %s for type %s"),
    CANNOT_HAVE_GENERIC_PARAMETERS_ON_OUTER_TYPE_AND_VERSION_AT_THE_SAME_TIME(13, "Cannot have generic parameters on outer type and version at the same time %s for type %s"),
    INHERITANCE_LOOP_DETECTED(14, "Inheritance loop detected %s for type %s"),
    EXTENDING_INTERFACE_WITH_NON_INTERFACE(15, "Attempt to extend interface type %s with non interface %s"),
    SPECIFIED_GENERIC_PARAMETER_DOES_NOT_SATISFY_TYPE_BOUNDS(16, "Specified generic parameter %s does not satisfy type bounds, it does not implement or extend type or interface %s"),
    UNKNOWN_GENERIC_PARAMETER(17, "Unknown generic parameter %s");

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