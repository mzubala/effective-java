package com.bottega.function.L09_threads;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

class L9_Synchro {

    static AtomicInteger value = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        while(i < 50) {
            new Thread(() -> {
                for(int j = 0; j<10; j++) {
                    System.out.println(STR."\{Thread.currentThread().getName()} Initial value is \{value}");
                    try {
                        Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    //value = value + 1;
                    value.incrementAndGet();
                    System.out.println(STR."\{Thread.currentThread().getName()} After value is \{value}");
                }
            }).start();
            i++;
        }
        Thread.sleep(15_000);
        System.out.println("Final value is " + value);
    }

    private synchronized static void increment() {
        value.incrementAndGet();
    }

}
