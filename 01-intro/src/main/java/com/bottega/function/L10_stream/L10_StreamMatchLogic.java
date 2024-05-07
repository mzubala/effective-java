package com.bottega.function.L10_stream;

import java.util.stream.Stream;

class L10_StreamMatchLogic {

    public static void main(String[] args) {
        System.out.println(Stream.empty().allMatch(i -> true));
        System.out.println(Stream.empty().allMatch(i -> false));
        System.out.println(Stream.empty().noneMatch(i -> true));
        System.out.println(Stream.empty().noneMatch(i -> false));
        System.out.println(Stream.empty().anyMatch(i -> true));
        System.out.println(Stream.empty().anyMatch(i -> false));
    }
}
