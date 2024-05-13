package com.bottega.function.L00_generics;

import java.util.List;
import java.util.Objects;

public class Pair<T1, T2> {

    private final T1 first;
    private final T2 second;

    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    public static <X, Y> Pair<X, Y> of(X first, Y second) {
        return new Pair<>(first, second);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }

    static void printTheSize(List<?> list) {
        System.out.println(list.size());
    }

    static void printMyPair(Pair<?, ?> pair) {
        System.out.println(pair.first);
        System.out.println(pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    public T1 first() {
        return first;
    }

    public T2 second() {
        return second;
    }
}

