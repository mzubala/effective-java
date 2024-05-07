package com.bottega.function.E06;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

class ToStringCollectorLazyExample {

    public static void main(String[] args) {
        String s1 = Stream.of(1, 2, 3).collect(toStringCollector());
        String s2 = Stream.of(1, 2, 3).collect(toStringCollector(","));
        String s3 = Stream.of(1, 2, 3).collect(toStringCollector(",", "(", ")"));

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }

    private static <T> Collector<T, ?, String> toStringCollector() {
        return toStringCollector("");
    }

    private static <T> Collector<T, ?, String> toStringCollector(String delimeter) {
        return toStringCollector(delimeter, "", "");
    }

    private static <T> Collector<T, ?, String> toStringCollector(String delimeter, String prefix, String suffix) {
        return new ToStringCollector<>(delimeter, prefix, suffix);
    }

    private static <T> T todo() {
        throw new RuntimeException("TODO");
    }

    private record ToStringCollector<T>(String delimiter, String prefix, String suffix)
      implements Collector<T, List<String>, String> {

        @Override
        public Supplier<List<String>> supplier() {
            return ArrayList::new;
        }

        @Override
        public BiConsumer<List<String>, T> accumulator() {
            return (strings, t) -> strings.add(t.toString());
        }

        @Override
        public BinaryOperator<List<String>> combiner() {
            return (strings, strings2) -> {
                strings.addAll(strings2);
                return strings;
            };
        }

        @Override
        public Function<List<String>, String> finisher() {
            return list -> {
                StringJoiner joiner = new StringJoiner(delimiter);
                for (String s : list) {
                    joiner.add(s);
                }

                return prefix + joiner.toString() + suffix;
            };
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Set.of();
        }
    }
}
