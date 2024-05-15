package com.bottega.function.L11_collectors;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

class L11_CustomCollectors {

    public static void main(String[] args) {
        List<Integer> result = Stream.of(1, 2, null).collect(toImmutableList());
        System.out.println(result);


        List<String> userNames = List.of("John", "Jane", "Marry");
        Map<Integer, String> m1 = userNames.stream().collect(Collectors.toMap(String::length, name -> name, (l1, _) -> l1));
        System.out.println(m1);

        Map<Integer, String> m2 = userNames.stream().collect(myMapCollector(String::length, name -> name));
        System.out.println(m1);
    }

    public static <T> Collector<T, ?, List<T>> toImmutableList() {
        return new ImmutableListCollector<>();
    }

    public static <T, K, V> Collector<T, ?, Map<K, V>> myMapCollector(Function<T, K> keySelector, Function<T, V> valueSelector) {
        return new MyMapCollector<>(keySelector, valueSelector);
    }

    private static class ImmutableListCollector<T> implements Collector<T, ArrayList<T>, List<T>> {

        @Override
        public Supplier<ArrayList<T>> supplier() {
            return ArrayList::new;
        }

        @Override
        public BiConsumer<ArrayList<T>, T> accumulator() {
            return ArrayList::add;
        }

        @Override
        public BinaryOperator<ArrayList<T>> combiner() {
            return (ts, ts2) -> {
                ts.addAll(ts2);
                return ts;
            };
        }

        @Override
        public Function<ArrayList<T>, List<T>> finisher() {
            return Collections::unmodifiableList;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Set.of();
        }
    }

    private static class MyMapCollector<T, K, V> implements Collector<T, Map<K, V>, Map<K, V>> {

        private final Function<T, K> keySelector;
        private final Function<T, V> valueSelector;

        public MyMapCollector(Function<T, K> keySelector, Function<T, V> valueSelector) {
            Objects.requireNonNull(keySelector);
            Objects.requireNonNull(valueSelector);
            this.keySelector = keySelector;
            this.valueSelector = valueSelector;
        }

        @Override
        public Supplier<Map<K, V>> supplier() {
            return HashMap::new;
        }

        @Override
        public BiConsumer<Map<K, V>, T> accumulator() {
            return (acc, element) -> {
                var key = keySelector.apply(element);
                /*if(!acc.containsKey(key)) {
                    acc.put(key, valueSelector.apply(element));
                }*/
                acc.computeIfAbsent(key, _ -> valueSelector.apply(element));
            };
        }
        // Jane, John | Kate, Jane

        @Override
        public BinaryOperator<Map<K, V>> combiner() {
            return (acc1, acc2) -> {
                acc2.forEach((key, value) -> acc1.computeIfAbsent(key, _ -> value));
                return acc1;
            };
        }

        @Override
        public Function<Map<K, V>, Map<K, V>> finisher() {
            return Function.identity();
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Set.of(IDENTITY_FINISH);
        }
    }
}
