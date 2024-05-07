package com.bottega.function.L09_threads;

class L9_ThreadBasics {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true) {
                sleep(2_000);
                System.out.println("Hello from: " + Thread.currentThread().getName());
            }
        });
        thread.start();
        sleep(10000);
        thread.stop();
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
