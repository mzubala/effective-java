package com.bottega.function.L12_tpools_types;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

class L12_CachedThreadPools {

    public static void main(String[] args) {
        timed(() -> {
            try (var e = Executors.newCachedThreadPool()) {
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
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

        System.out.printf("Execution time: %dms%n", Duration.between(before, after).toMillis());

        return result;
    }
}
