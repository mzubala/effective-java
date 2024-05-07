package com.bottega.function.L10_stream;

import java.util.stream.Stream;

class L10_StreamAPIFoundations {

    public static void main(String[] args) {
        Stream<Integer> s = Stream.of(1, 2, 3);
        s.forEach(System.out::println);
        s.forEach(System.out::println); // stream has already been operated upon or closed
    }
}
