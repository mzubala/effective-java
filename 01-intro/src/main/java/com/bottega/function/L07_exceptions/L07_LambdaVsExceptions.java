package com.bottega.function.L07_exceptions;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

class L07_LambdaVsExceptions {

    public static void main(String[] args) {
        List<Integer> result = Stream.of(1, 2, 3)
          .map(sneaky(i -> process(i)))
          .toList();
    }

    // https://docs.oracle.com/javase/specs/jls/se8/html/jls-18.html
    // Otherwise, if the bound set contains throws αi, and the proper upper bounds of αi are, at most, Exception, Throwable, and Object, then Ti = RuntimeException.
    private static <T extends Exception, R> R rethrow(Exception ex) throws T {
        throw (T) ex;
    }

    private static <T, R> Function<T, R> sneaky(ThrowingFunction<T, R> function) {
        return t -> {
            try {
                return function.apply(t);
            } catch (Exception e) {
                return rethrow(e);
            }
        };
    }

    private static <T, R> Function<T, R> unchecked(ThrowingFunction<T, R> function) {
        return t -> {
            try {
                return function.apply(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    interface ThrowingFunction<T, R> {
        R apply(T t) throws Exception;
    }

    private static <T> T process(T input) throws Exception {
        if (true) {
            throw new IOException();
        }
        return input;
    }
}
