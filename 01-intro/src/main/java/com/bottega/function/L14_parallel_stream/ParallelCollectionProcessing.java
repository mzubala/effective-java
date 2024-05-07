package com.bottega.function.L14_parallel_stream;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

class ParallelCollectionProcessing {

    public static void main(String[] args) throws InterruptedException {
        List<Integer> collection = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        new Thread(() -> {
            IntStream.range(0, Runtime.getRuntime().availableProcessors() + 10).boxed()
              .parallel()
              .map(i -> {
                  try {
                      Thread.sleep(100_000);
                  } catch (InterruptedException e) {
                      throw new RuntimeException(e);
                  }

                  return i;
              }).toList();
        }).start();

        Thread.sleep(100);

        List<Integer> results = timed(() -> collection.parallelStream().map(i -> process(i)).toList());

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
