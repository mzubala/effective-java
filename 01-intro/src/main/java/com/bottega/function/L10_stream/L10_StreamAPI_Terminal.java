package com.bottega.function.L10_stream;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class L10_StreamAPI_Terminal {

    public static void main(String[] args) {
        users().forEach(System.out::println);

        System.out.println("---");

        List<String> r1 = users().collect(Collectors.toList());
        List<String> r2 = users().toList();
        Optional<String> r3 = users().findFirst();
        Optional<String> r4 = users().findAny();
        Optional<String> r5 = users().reduce((s1, s2) -> s1 + s2);
        String r6 = users().reduce("", (s1, s2) -> s1 + s2);
        boolean r7 = users().allMatch(i -> i.length() > 0);
        boolean r8 = users().noneMatch(i -> i.length() > 0);
        boolean r9 = users().anyMatch(i -> i.length() > 0);
        Optional<String> r10 = users().min(Comparator.comparing(String::length));
        Optional<String> r11 = users().max(Comparator.comparing(String::length));
    }

    private static Stream<String> users() {
        return Stream.of("Adam", "John", "Kate", "Grzegorz", "Kate");
    }
}
