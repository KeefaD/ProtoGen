package com.kdsc.protogen.utils;

import java.util.stream.Stream;

public class Streams {

    //TODO:KMD What is this possible heap pollution from parameterised vararg types
    public static <T> Stream<T> concat(Stream<T>... streams) {
        if(streams == null) {
            throw new NullPointerException("Expected at least one stream");
        }
        return Stream.of(streams)
            .flatMap(s -> s);
    }

}