package com.bottega.function.E07;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;

class ParallelCollectionProcessing {

    public static void main(String[] args) {
        List<Integer> collection = List.of(1, 2, 3, 4);

        List<Integer> results = timed(() -> {
            try (var e = Executors.newFixedThreadPool(4)) {
                List<Future<Integer>> futures = new ArrayList<>();
                List<Integer> result = new ArrayList<>();

                for (Integer i : collection) {
                    futures.add(e.submit(() -> process(i)));
                }

                for (Future<Integer> future : futures) {
                    try {
                        result.add(future.get());
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(ex);
                    } catch (ExecutionException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                return result;
            }
        });

        System.out.println(results);
    }

    private static <T> T process(T input) {
        try {
            System.out.println(STR."Processing \{input} on \{Thread.currentThread().getName()}");
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return input;
    }

    // https://www.javaadvent.com/2019/12/measuring-time-from-java-to-kernel-and-back.html
    private static <T> T timed(Supplier<T> action) {
        Instant before = Instant.now();
        T result = action.get();
        Instant after = Instant.now();

        System.out.println("Execution time: %dms".formatted(Duration.between(before, after).toMillis()));

        return result;
    }
}
