package com.bottega.function.L09_threads;

class L9_MainThread {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().isVirtual());
        System.out.println(Thread.currentThread().threadId());
        System.out.println(Thread.currentThread().isDaemon());
        System.out.println(new Thread().isDaemon());
    }

}
