package com.bottega.function.E04;

import java.time.LocalDate;
import java.time.Year;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

class Streams {

    private static void L0_cubeComposer() {
        // https://david-peter.de/cube-composer/
    }

    /**
     * Uppercase all strings in a list
     * {@link Stream#map(Function)}
     */
    static Function<List<String>, List<String>> L1_upperCaseAll() {
        return list -> list.stream()
          .map(String::toUpperCase)
          .toList();
    }

    /**
     * Uppercase all list elements and discard names containing less than 6 characters
     * {@link Stream#filter(Predicate)}
     */
    static Function<List<String>, List<String>> L2_upperCaseAllAndFilter() {
        return list -> list.stream()
          .filter(s -> s.length() >= 6)
          .map(String::toUpperCase)
          .toList();
    }

    /**
     * Find the longest name
     * {@link Stream#max(Comparator)}
     * {@link Stream#sorted()} {@link Stream#findAny()}
     */
    static Function<List<String>, String> L3_findTheLongestName() {
        return list -> list.stream().max(Comparator.comparing(String::length)).orElseThrow();
    }

    /**
     * Flatten a nested list structure
     * {@link Stream#flatMap(Function)}
     */
    // [[1], [2,3], []] -> [1,2,3]
    static Function<List<List<Integer>>, List<Integer>> L4_flatten() {
        return lists -> lists.stream()
          .flatMap(list -> list.stream())
          .toList();
    }

    /**
     * Eliminate duplicates
     * {@link Stream#distinct()}
     */
    static Function<List<Integer>, List<Integer>> L5_distinctElements() {
        return list -> list.stream().distinct().toList();
    }

    /**
     * Duplicate the elements of a list
     */
    // [1,2,3] -> [1,1,2,2,3,3]
    static Function<List<Integer>, List<Integer>> L6_duplicateElements() {
        return ints -> ints.stream()
          .flatMap(i -> Stream.of(i, i))
          .toList();
    }

    /**
     * Duplicate the elements of a list a given number of times
     * {@link Stream#generate(Supplier)}
     */
    static Function<List<Integer>, List<Integer>> L7_duplicateElementsNTimes(int givenNumberOfTimes) {
        return ints -> ints.stream()
          .flatMap(i -> Stream.generate(() -> i).limit(givenNumberOfTimes))
          .toList();
    }

    /**
     * Create a stream only with multiples of 3, starting from 0, size of 10
     * {@link Stream#iterate}
     */
    static Supplier<List<Integer>> L8_generate3s() {
        return () -> Stream.iterate(0, i -> i + 3).limit(10).toList();
    }

    /**
     * Find five consecutive leap years since 2000 (inclusive)
     * {@link Stream#iterate(Object, UnaryOperator)}
     * {@link LocalDate#isLeapYear()}
     */
    static Supplier<List<Integer>> L9_leapYears() {
        return () -> Stream.iterate(Year.of(2000), year -> year.plusYears(1))
          .filter(Year::isLeap)
          .limit(5)
          .map(Year::getValue)
          .toList();
    }

    static Supplier<List<Integer>> L9_leapYears2() {
        return () -> Stream.iterate(2000, year -> year + 1)
          .filter(Year::isLeap)
          .limit(5)
          .toList();
    }

    /**
     * Rotate a list N places to the left
     * {@link Stream#concat(Stream, Stream)}
     * {@link Stream#skip(long)}
     * {@link Stream#limit(long)}
     */
    // [1,2,3,4] -> [2,3,4,1] -> [3,4,1,2] -> [4,1,2,3] -> [1,2,3,4]
    // [1,2,3,4][1,2,3,4]
    static UnaryOperator<List<Integer>> L10_rotate(int n) {
        return list -> Stream.concat(list.stream(), list.stream())
          .skip(n % list.size())
          .limit(list.size())
          .toList();
    }

    /**
     * Check if all elements sum up to 100, if no throw an exception
     */
    static Predicate<List<Double>> L11_sum() throws IllegalStateException {
        return doubles -> doubles.stream()
          .reduce(Double::sum)
          .map(sum -> sum == 100)
          .filter(i -> i)
          .orElseThrow(IllegalStateException::new);
    }

    /**
     * Convert a {@link List} of {@link Optional} to a {@link List} of only not-empty values
     * <p>
     * Advanced challenge: use {@link Stream#flatMap(Function)}
     */
    static Function<List<Optional<Integer>>, List<Integer>> L12_filterPresent() {
        return list -> list.stream()
          .flatMap(o -> o.stream()) // JDK9+
          .toList();
    }

    static Function<List<Optional<Integer>>, List<Integer>> L12_filterPresent_jdk8() {
        return list -> list.stream()
          .filter(Optional::isPresent)
          .map(Optional::get)
          .toList();
    }

    static Function<List<Optional<Integer>>, List<Integer>> L12_filterPresent_jdk8_2() {
        return list -> list.stream()
          .flatMap(o -> o.map(Stream::of).orElse(Stream.empty()))
          .toList();
    }
}
