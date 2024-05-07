package com.bottega.function.L09_threads;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

class L9_ThreadInterruptExample {

    public static void main(String[] args) throws InterruptedException {
        ConcurrentHolder concurrentHolder = new ConcurrentHolder();
        AtomicInteger seq = new AtomicInteger();
        Thread thread = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("interrupted, closing...");
                    break;
                }
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(2000));
                } catch (InterruptedException e) {
                    System.out.println("interrupted, closing...");
                    break;
                }
                concurrentHolder.process(seq.incrementAndGet());
            }
        });
        Thread thread2 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("interrupted, closing...");
                    break;
                }

                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(2000));
                } catch (InterruptedException e) {
                    System.out.println("interrupted, closing...");
                    break;
                }
                concurrentHolder.process(seq.incrementAndGet());
            }
        });
        Thread thread3 = new Thread(() -> {
            while (true) {

                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("interrupted, closing...");
                    break;
                }
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(2000));
                } catch (InterruptedException e) {
                    System.out.println("interrupted, closing...");
                    break;
                }
                concurrentHolder.process(seq.incrementAndGet());
            }
        });
        thread.start();
        thread2.start();
        thread3.start();
        Thread.sleep(10_000);
        thread.interrupt();
        Thread.sleep(5_000);
        thread2.interrupt();
        Thread.sleep(7_000);
        thread3.interrupt();
    }

    private static class ConcurrentHolder {

        private final AtomicBoolean inprogress = new AtomicBoolean(false);

        synchronized void process(int i) {
            if (inprogress.getAndSet(true)) {
                throw new IllegalStateException("concurrent holder is broken!");
            }

            System.out.printf("Processing %d on %s%n", i, Thread.currentThread().getName());
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            // ....

            inprogress.set(false);
        }
    }

    public void doSomething() {
        // ...
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        // ...
    }
}
