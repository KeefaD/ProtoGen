package com.kdsc.protogen.utils.parameterchecking;

public class Numbers {

    public static void requireZeroOrGreater(final long inputNumber) {
        if(inputNumber < 0) {
            throw new IllegalArgumentException("Require number to be zero or greater");
        }
    }

    public static void requireOneOrGreater(final long inputNumber) {
        if(inputNumber < 1) {
            throw new IllegalArgumentException("Require number to be one or greater");
        }
    }
}
