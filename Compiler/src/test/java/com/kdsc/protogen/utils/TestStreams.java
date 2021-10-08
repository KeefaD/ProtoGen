package com.kdsc.protogen.utils;

import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class TestStreams {

    @Test
    public void testConcatTwoStreams() {

        var stream1 = Stream.of(1, 2, 3, 4);
        var stream2 = Stream.of(5, 6, 7, 8);

        var expectedStream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8);
        var mergedStreams = Streams.concat(stream1, stream2);

        assertEquals(expectedStream.map(Object::toString).collect(Collectors.joining("-")), mergedStreams.map(Object::toString).collect(Collectors.joining("-")), "Expected stream does not equal merged stream");
    }

    @Test
    public void testConcatThreeStreams() {

        var stream1 = Stream.of(1, 2, 3, 4);
        var stream2 = Stream.of(5, 6, 7, 8);
        var stream3 = Stream.of(9, 10, 11, 12);

        var expectedStream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        var mergedStreams = Streams.concat(stream1, stream2, stream3);

        assertEquals(expectedStream.map(Object::toString).collect(Collectors.joining("-")), mergedStreams.map(Object::toString).collect(Collectors.joining("-")), "Expected stream does not equal merged stream");
    }

    @Test
    public void testConcatZeroStreams() {
        assertThrows(
            NullPointerException.class,
            () -> Streams.concat(null)
        );
    }

}
