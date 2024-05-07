package com.bottega.function.L10_stream;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class L10_StreamAPI_Create {

    public static void main(String[] args) {
        List<Integer> list = List.of(1);
        Stream<Integer> s1 = list.stream();
        Stream<Map.Entry<String, String>> s2 = Map.of("foo", "bar").entrySet().stream();

        Stream<Integer> s3 = Stream.of(1, 2, 3);
        Stream<Object> s4 = Stream.builder()
          .add(1)
          .add(2)
          .add(3)
          .build();

        Stream<Integer> s5 = Stream.iterate(0, i -> i + 1);
        Stream<Integer> s6 = Stream.generate(() -> 42);
        Stream<Object> s7 = Stream.ofNullable(null); // analogia do Optional#ofNullable
        Stream<Integer> s8 = Stream.concat(Stream.of(1), Stream.of(2));

        IntStream s9 = IntStream.range(0, 100);
        IntStream s10 = IntStream.rangeClosed(0, 100);
    }
}
