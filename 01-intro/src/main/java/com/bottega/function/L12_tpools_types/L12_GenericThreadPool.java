package com.bottega.function.L12_tpools_types;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

class L12_GenericThreadPool {

    public static void main(String[] args) {
        int corePoolSize = 4;
        int queueSize = 100;
        int maximumPoolSize = 10;

        timed(() -> {
            try (var e = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
              1, TimeUnit.MINUTES,
              new LinkedBlockingQueue<>(queueSize), Thread.ofPlatform().name("mail-scheduler-",0).factory(), new ThreadPoolExecutor.CallerRunsPolicy())) {
                for (int i = 0; i < queueSize + maximumPoolSize + 1; i++) {

                    int finalI = i;
                    try {
                        e.submit(() -> {
                            System.out.printf("Processing %d on %s%n", finalI, Thread.currentThread().getName());

                            try {
                                Thread.sleep(10_000);
                            } catch (InterruptedException ex) {
                                throw new RuntimeException(ex);
                            }
                        });
                    } catch (RejectedExecutionException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            return null;
        });
    }
    // JDK < 21
    private static ThreadFactory named(String prefix) {
        return new ThreadFactory() {

            private final AtomicInteger seq = new AtomicInteger();

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, STR."\{prefix}-\{seq.incrementAndGet()}");
            }
        };
    }

    private static <T> T timed(Supplier<T> action) {
        Instant before = Instant.now();
        T result = action.get();
        Instant after = Instant.now();

        System.out.printf("Execution time: %dms%n", Duration.between(before, after).toMillis());

        return result;
    }
}
