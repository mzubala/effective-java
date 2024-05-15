package com.bottega.function.L09_threads;

class L9_ThreadBasics {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            int i = 0;
            while (i < 10) {
                sleep(2_000);
                System.out.println("Hello from: " + Thread.currentThread().getName());
                i++;
            }
            System.out.println("Good bye from: " + Thread.currentThread().getName());
        });
        thread.start();
        //sleep(4_000);
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Good bye from: " + Thread.currentThread().getName());
        //thread.interrupt();
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
