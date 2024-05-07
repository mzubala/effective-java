package com.bottega.function.L09_threads;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

// Thread#stop does not work on JDK21
class L9_ThreadStopExample {

    public static void main(String[] args) throws InterruptedException {
        ConcurrentHolder concurrentHolder = new ConcurrentHolder();
        AtomicInteger seq = new AtomicInteger();
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(2000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                concurrentHolder.process(seq.incrementAndGet());
            }
        });
        Thread thread2 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(2000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                concurrentHolder.process(seq.incrementAndGet());
            }
        });
        Thread thread3 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(2000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                concurrentHolder.process(seq.incrementAndGet());
            }
        });
        thread.start();
        thread2.start();
        thread3.start();
        Thread.sleep(20_000);
        thread.stop();
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
}
