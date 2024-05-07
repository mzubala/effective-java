package com.bottega.function.L09_threads;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

class L9_ThreadPools {

    public static void main(String[] args) {
        int threads = 4;
        timed(() -> {
            try (var e = Executors.newFixedThreadPool(threads)) {
                for (int i = 0; i < threads + 1; i++) {
                    int finalI = i;
                    e.submit(() -> {
                        System.out.printf("Processing %d on %s%n", finalI, Thread.currentThread().getName());

                        try {
                            Thread.sleep(10_000);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
                }
            }
            return null;
        });
    }

    private static <T> T timed(Supplier<T> action) {
        Instant before = Instant.now();
        T result = action.get();
        Instant after = Instant.now();

        System.out.println("Execution time: %dms".formatted(Duration.between(before, after).toMillis()));

        return result;
    }
}
