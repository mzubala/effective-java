package com.bottega.function.L11_collectors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

class L11_CustomCollectors {

    public static void main(String[] args) {
        List<Integer> result = Stream.of(1, 2, null).collect(toImmutableList());
        System.out.println(result);
    }

    public static <T> Collector<T, ?, List<T>> toImmutableList() {
        return new ImmutableListCollector<>();
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
}
