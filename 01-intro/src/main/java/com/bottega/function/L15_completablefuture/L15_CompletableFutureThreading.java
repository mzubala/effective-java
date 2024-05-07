package com.bottega.function.L15_completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

class L15_CompletableFutureThreading {

    public static void main(String[] args) {
        try (var e = Executors.newCachedThreadPool()) {
            CompletableFuture.supplyAsync(() -> {
                  System.out.println(Thread.currentThread().getName());
                  return 42;
              }, e)
              .thenApplyAsync(i -> {
                  System.out.println(Thread.currentThread().getName());
                  return i;
              }, e)
              .thenApplyAsync(i -> {
                  System.out.println(Thread.currentThread().getName());
                  return i;
              }, e)
              .thenApplyAsync(i -> {
                  System.out.println(Thread.currentThread().getName());
                  return i;
              }, e)
              .thenApplyAsync(i -> {
                  System.out.println(Thread.currentThread().getName());
                  return i;
              }, e)
              .thenAcceptAsync(System.out::println, e);
        }
    }
}
