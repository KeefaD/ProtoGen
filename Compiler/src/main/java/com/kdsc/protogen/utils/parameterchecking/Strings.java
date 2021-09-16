package com.kdsc.protogen.utils.parameterchecking;

public class Strings {

    public static void requireNonBlank(String inputString) {
        if(inputString == null || inputString.isBlank()) {
            throw new IllegalArgumentException("Require string to be non blank");
        }
    }
}
