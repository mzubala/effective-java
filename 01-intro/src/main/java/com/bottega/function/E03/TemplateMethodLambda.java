package com.bottega.function.E03;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Supplier;

class TemplateMethodLambda {

    public static void main(String[] args) {
        String result = timed(() -> process("foo"));

        System.out.println(result);
    }

    // https://www.javaadvent.com/2019/12/measuring-time-from-java-to-kernel-and-back.html
    private static <T> T timed(Supplier<T> action) {
        Instant before = Instant.now();
        T result = action.get();
        Instant after = Instant.now();

        System.out.println("Execution time: %dms".formatted(Duration.between(before, after).toMillis()));

        return result;
    }

    private static <T> T process(T input) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return input;
    }
}
