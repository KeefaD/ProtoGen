package com.kdsc.protogen.utils.parameterchecking;

public class Numbers {

    public static void requireZeroOrGreater(long inputNumber) {
        if(inputNumber < 0) {
            throw new IllegalArgumentException("Require number to be zero or greater");
        }
    }
}
