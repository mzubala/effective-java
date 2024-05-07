package com.bottega.function.E07;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

class ParallelCollectionProcessingCompletableFutureStream {

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4);

        try (var e = Executors.newCachedThreadPool()) {
            System.out.println(parallelSync(list, i -> process(i), e));
        }
    }

    static <T, R> List<R> parallelSync(Collection<T> input, Function<T, R> task, ExecutorService executor) {
        return parallelAsync(input, task, executor).join();
    }

    static <T, R> CompletableFuture<List<R>> parallelAsync(Collection<T> input, Function<T, R> task, ExecutorService executor) {
        return input.stream()
          .map(e -> CompletableFuture.supplyAsync(() -> task.apply(e), executor))
          .collect(collectingAndThen(
            toList(),
            ParallelCollectionProcessingCompletableFutureStream::reduceToList));
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
            System.out.println(STR."Processing \{input} on \{Thread.currentThread().getName()}");
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return input;
    }
}
