package com.bottega.function.L16_loom;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

class L16_LoomDemo {

    // os threads: 10 -> 5016ms
    // os threads: 100 -> 5023ms
    // os threads: 1000 -> 5078ms
    // os threads: 2000 -> 5144ms
    // os threads: 4000 -> 5365ms
    // os threads: 8000 -> 6120ms
    // os threads: 16000 -> 10056ms (no log: 8428ms)

    // virtual threads: 10 -> 5020ms
    // virtual threads: 100 -> 5026ms
    // virtual threads: 1000 -> 5047ms
    // virtual threads: 2000 -> 5063ms
    // virtual threads: 4000 -> 5095ms
    // virtual threads: 8000 -> 5164ms
    // virtual threads: 16000 -> 5691ms
    // virtual threads: 32000 -> 5976ms
    // virtual threads: 64000 -> 6204ms
    // virtual threads: 128000 -> 6826ms
    // virtual threads: 256000 -> 8391ms
    // virtual threads: 512000 -> 11327ms
    // virtual threads: 1024000 -> 20289ms
    // virtual threads: 2048000 -> 32704ms
    // virtual threads: 4096000 -> 54801ms (no log: 50933ms)
    public static void main(String[] args) {
        try (var e = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Integer> result = timed(() -> IntStream.range(0, 4096000).boxed()
              .map(i -> CompletableFuture.supplyAsync(() -> process(i), e))
              .toList().stream()
              .collect(collectingAndThen(toList(), l -> reduceToList(l)))
              .join());
        }
    }

    private static <R> CompletableFuture<List<R>> reduceToList(List<CompletableFuture<R>> futures) {
        var result = CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new))
          .thenApply(_ -> futures.stream().map(CompletableFuture::join).toList());

        for (CompletableFuture<R> f : futures) {
            f.whenComplete((_, throwable) -> {
                if (throwable != null) {
                    result.completeExceptionally(throwable);
                }
            });
        }

        return result;
    }

    private static <T> T process(T input) {
        try {
//            System.out.println(STR."Processing \{input} on \{Thread.currentThread().getName()}");
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return input;
    }

    private static <T> T timed(Supplier<T> action) {
        var before = Instant.now();
        T result = action.get();
        var after = Instant.now();

        System.out.println("Execution time: %dms".formatted(Duration.between(before, after).toMillis()));

        return result;
    }
}
