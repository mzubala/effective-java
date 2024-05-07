package com.bottega.function.L05_monads;

import java.util.function.Function;

class L05_Monad {

    public static void main(String[] args) {
        Monad<Integer> monad = new Monad<>(42);
        Monad<Integer> monad2 = monad.map(i -> i + 1);
        Monad<String> monad3 = monad2.map(i -> "foo");
    }

    public static class Monad<T> {
        private final T value;

        Monad(T value) {
            this.value = value;
        }

        public <R> Monad<R> map(Function<T, R> mapper) {
            return new Monad<>(mapper.apply(value));
        }
    }
}
