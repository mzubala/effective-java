package com.bottega.function.L15_completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

class L15_CompletableFutureAPI {

    public static void main(String[] args) {
        var e = Executors.newCachedThreadPool();

        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> process(1), e);
        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> process(2), e);

        cf1.applyToEitherAsync(cf2, i -> i, e)
          .thenAcceptAsync(r -> System.out.println(STR."First observed: \{r}"), e);

        cf1.thenCombineAsync(cf2, Integer::sum, e)
          .thenAcceptAsync(r -> System.out.println(STR."Sum: \{r}"), e);

        cf1
          .thenComposeAsync(i -> CompletableFuture.supplyAsync(() -> process(42), e), e)
          .thenAcceptAsync(r -> System.out.println(STR."Composed: \{r}"), e);

        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(cf1, cf2);
        CompletableFuture<Void> allOf = CompletableFuture.allOf(cf1, cf2);
    }

    private static <T> T process(T input) {
        try {
            System.out.println(STR."Processing \{input} on \{Thread.currentThread().getName()}");
            Thread.sleep(ThreadLocalRandom.current().nextInt(5000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return input;
    }
}
