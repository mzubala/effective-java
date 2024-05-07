package com.bottega.function.L15_completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class L15_CompletableFutureCancellation {

    public static void main(String[] args) {
        try (var e = Executors.newFixedThreadPool(1)) {
            Future<?> result = CompletableFuture.runAsync(() -> {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        System.out.println("interrupted, closing...");
                        break;
                    }
                    System.out.println("processing...");
                }
            }, e);
            new Thread(() -> {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                result.cancel(true); // DOES NOT WORK AS EXPECTED!
            }).start();
        }
    }

    public static void futureCancel() {
        try (var e = Executors.newFixedThreadPool(1)) {
            Future<?> result = e.submit(() -> {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        System.out.println("interrupted, closing...");
                        break;
                    }
                    System.out.println("processing...");
                }
            });
            new Thread(() -> {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                result.cancel(true);
            }).start();
        }
    }
}
