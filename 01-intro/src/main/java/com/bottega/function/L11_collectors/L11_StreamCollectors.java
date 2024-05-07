package com.bottega.function.L11_collectors;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class L11_StreamCollectors {

    public static void main(String[] args) {
        List<String> r1 = users().collect(Collectors.toList());
        List<String> r2 = users().collect(Collectors.toUnmodifiableList());
        List<String> r3 = users().toList();

        LinkedList<String> r4 = users().collect(Collectors.toCollection(() -> new LinkedList<>()));
        List<String> r5 = users().collect(Collectors.collectingAndThen(Collectors.toCollection(LinkedList::new), Collections::unmodifiableList));

        Map<String, Integer> r6 = users().collect(Collectors.toMap(i -> i, i -> i.length()));
        String r7 = users().collect(Collectors.joining());
        String r8 = users().collect(Collectors.joining(",", "prefix", "suffix"));

        Map<Integer, Set<String>> r9 = users().collect(Collectors.groupingBy(str -> str.length(), Collectors.toSet()));
    }

    private static Stream<String> users() {
        return Stream.of("Adam", "John", "Kate", "Grzegorz");
    }
}
