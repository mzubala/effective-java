package com.bottega.function.L06_jvm_omit_stacktrace;

import java.util.concurrent.atomic.AtomicInteger;

class L06_JvmOmitStackTrace {

    // -XX:-OmitStackTraceInFastThrow
    public static void main(String[] args) {
        var counter = new AtomicInteger();
        while (true) {
            System.out.println("iteration: " + counter.incrementAndGet());
            try {
                String foo = null;
                foo.toUpperCase();
            } catch (Exception ex) {
                ex.printStackTrace();
                if (ex.getStackTrace().length == 0) {
                    break;
                }
            }
        }
    }
}
