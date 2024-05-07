package com.bottega.function.E05;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.StringTemplate.STR;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

class CollectorsExercises {

    /**
     * Collect elements to a {@link List} instance
     */
    static Function<List<String>, List<String>> L1_toList() {
        return list -> list.stream().collect(Collectors.toList());
    }

    /**
     * Collect elements to a {@link LinkedList} instance
     */
    static Function<List<String>, LinkedList<String>> L2_toLinkedList() {
        return list -> list.stream().collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Collect elements to a {@link List} wrapped in {@link Collections#unmodifiableList(List)} instance
     */
    static Function<List<String>, List<String>> L3_unmodifiable() {
        return list -> list.stream()
          .collect(collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    /**
     * Collect elements to a {@link Map} instance with uppercased elements as keys and their corresponding lengths as values
     * and resolve potential collisions
     */
    static Function<List<String>, Map<String, Integer>> L4_toMap() {
        return strings -> strings.stream()
          .collect(toMap(s -> s.toUpperCase(), s -> s.length(), (l1, l2) -> {
              if (!l1.equals(l2)) {
                  throw new IllegalStateException();
              }
              return l1;
          }));
    }

    /**
     * Collect elements to a {@link TreeMap} instance with elements as keys and their corresponding lengths as values
     * and resolve potential collisions by picking any of the strings
     */
    static Function<List<String>, TreeMap<String, Integer>> L5_toTreeMap() {
        return strings -> strings.stream()
          .collect(toMap(String::toUpperCase, String::length, (l1, _) -> l1, TreeMap::new));
    }

    /**
     * Collect Map elements to a JSON String instance
     * {@link Collectors#joining(CharSequence, CharSequence, CharSequence)}
     */
    static Function<Map<String, String>, String> L6_toJson() {
        return map -> map.entrySet().stream()
          .map(pair -> STR."\"\{pair.getKey()}\":\"\{pair.getValue()}\"")
          .collect(Collectors.joining(",", "{", "}"));
    }

    /**
     * Group Strings of the same length
     * {@link Collectors#groupingBy(Function)}
     */
    static Function<List<String>, Map<Integer, List<String>>> L7_groupStrings() {
        return list -> list.stream().collect(Collectors.groupingBy(s -> s.length()));
    }

    /**
     * Group Strings of the same length to a {@link TreeMap}
     */
    static Function<List<String>, TreeMap<Integer, List<String>>> L8_groupStrings() {
        return list -> list.stream().collect(Collectors.groupingBy(String::length, TreeMap::new, toList()));

    }

    /**
     * Group Strings of the same length into a comma-delimited String
     * {@link Collectors#groupingBy(Function)}
     */
    static Function<List<String>, Map<Integer, String>> L9_groupStrings() {
        return strings -> strings.stream()
          .collect(groupingBy(String::length, Collectors.joining(",")));
    }

    private static <T> T todo() {
        throw new RuntimeException("TODO");
    }
}
