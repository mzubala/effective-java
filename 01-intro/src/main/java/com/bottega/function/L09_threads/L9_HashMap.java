package com.bottega.function.L09_threads;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class L9_HashMap {
    public static void main(String[] args) throws InterruptedException {
        final Map<Integer, String> map = new ConcurrentHashMap<>();
        final int NUM_THREADS = 100;
        Thread[] threads = new Thread[NUM_THREADS];

        for (int i = 0; i < NUM_THREADS; i++) {
            final int threadId = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    map.put(threadId * 100 + j, "Value " + (threadId * 100 + j));
                }
            });
            threads[i].start();
        }

        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i].join();
        }

        System.out.println("Final size of map: " + map.size());
    }
}
