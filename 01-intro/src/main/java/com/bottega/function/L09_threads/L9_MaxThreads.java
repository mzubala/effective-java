package com.bottega.function.L09_threads;

import java.util.concurrent.atomic.AtomicInteger;

class L9_MaxThreads {

    public static void main(String[] args) {
        var counter = new AtomicInteger();

        while (true) {
            Thread.ofPlatform().start(() -> {
                try {
                    System.out.println(counter.incrementAndGet());
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
