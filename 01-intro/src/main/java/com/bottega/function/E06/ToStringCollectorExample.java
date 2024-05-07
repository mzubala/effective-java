package com.bottega.function.E06;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

class ToStringCollectorExample {

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
      implements Collector<T, StringBuilder, String> {

        @Override
        public Supplier<StringBuilder> supplier() {
            return StringBuilder::new;
        }

        @Override
        public BiConsumer<StringBuilder, T> accumulator() {
            return (acc, t) -> {
                if (acc.isEmpty()) {
                    acc.append(t);
                } else {
                    acc.append(delimiter).append(t);
                }
            };
        }

        @Override
        public BinaryOperator<StringBuilder> combiner() {
            return (sb1, sb2) -> {
                if (sb1.isEmpty()) {
                    sb1.append(sb2);
                } else {
                    sb1.append(delimiter).append(sb2);
                }

                return sb1;
            };
        }

        @Override
        public Function<StringBuilder, String> finisher() {
            return sb -> prefix + sb + suffix;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Set.of();
        }
    }
}
