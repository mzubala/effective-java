package com.bottega.function.L09_threads;

public class L9_Volatile {
    volatile static boolean running = true;

    public static void main(String[] args) throws InterruptedException {
        Thread readerThread = new Thread(() -> {
            while (running) {

            }
            System.out.println("Reader Thread finished.");
        });

        readerThread.start();

        Thread.sleep(1000); // Delay to ensure the reader thread starts properly
        running = false;
        System.out.println("Set running to false.");

        readerThread.join();
    }
}

