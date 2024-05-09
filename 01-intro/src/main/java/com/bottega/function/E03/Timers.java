package com.bottega.function.E03;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
class Timers {

    /**
     * Log the supplier's execution time and return a value supplied by the supplier.
     * @return value supplied by the supplier
     * Hint: use withTimer method below
     */
    static <T> T timed(Supplier<T> supplier) {
        // TODO
        return null;
    }

    /**
     * Log the function's execution time and return a value returned by the function for the given arg.
     * @return function's value for the given arg
     * Hint: use withTimer method below
     */
    static <S, T> T timed(Function<S, T> function, S arg) {
        // TODO
        return null;
    }

    /**
     * @return decorated supplier that logs execution time of the given supplier when asked for its value
     */
    static <T> Supplier<T> withTimer(Supplier<T> supplier) {
        // TODO
        return null;
    }

    /**
     * @return decorated function that logs execution time of the given function when asked for its value
     */
    static <S, T> Function<S, T> withTimer(Function<S, T> function) {
        // TODO
        return null;
    }

    public static void main(String[] args) {
        log.info("First result {}", (Object) timed(Timers::process, 10));
        log.info("Second result {}", (Object) timed(Timers::process));
    }

    private static <T> T process(T input) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return input;
    }

    private static Long process() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return 42L;
    }

}
