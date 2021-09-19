package com.kdsc.protogen.utils.parameterchecking;

import java.util.List;

public class Lists {

    public static void requireAtLeastOne(final List<?> inputList) {
        if(inputList == null) {
            throw new NullPointerException("inputList args is null");
        }
        if(inputList.size() < 1) {
            throw new IllegalArgumentException("Expected inputList to have at least one item in it");
        }
    }
}
