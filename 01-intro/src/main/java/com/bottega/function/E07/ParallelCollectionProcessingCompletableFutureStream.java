package com.bottega.function.E07;

import com.bottega.function.Utils;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

import static com.bottega.function.Utils.todo;
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
        return todo();
    }

    static <T, R> CompletableFuture<List<R>> parallelAsync(Collection<T> input, Function<T, R> task, ExecutorService executor) {
        return todo();
    }

    private static <R> CompletableFuture<List<R>> reduceToList(List<CompletableFuture<R>> futures) {
        return todo();
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
