package com.bottega.function.L13_future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class L13_Future {

    public static void main(String[] args) {
        try (var e = Executors.newFixedThreadPool(4)) {
            Future<Integer> result = e.submit(() -> process(42));
            System.out.println("Doing something else...");

            var r = result.get();
            System.out.println(r);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
