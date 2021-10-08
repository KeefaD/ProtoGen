package com.kdsc.protogen.utils.parameterchecking;

import java.util.Arrays;
import java.util.Optional;

public final class Optionals {

    public static void requireOne(final Optional<?>... optionals) {
        if(optionals == null) {
            throw new NullPointerException("Optionals var args is null");
        }
        var isPresentCount =  Arrays
            .stream(optionals)
            .filter(Optional::isPresent)
            .count();
        if(isPresentCount != 1) {
            throw new IllegalArgumentException("Expected one present optional found " + isPresentCount);
        }
    }

}