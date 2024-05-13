package com.bottega.function.L04_function;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

class L04_FunctionalInterfaces {

    /*
    (String foo, Integer bar) -> {

        // ...
        return foo + bar;
     }

    (foo, bar) -> {
        // ...
        return foo + bar;
    }

    (@Nullable var foo, var bar) -> {
        // ...
        return foo + bar;
    }

    (foo, bar) -> foo + bar;

    foo -> foo + "";

    () -> 42;
     */
    public static void main(String[] args) {
        List<Integer> list = List.of(1);
        var intToString = new com.google.common.base.Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return integer.toString();
            }
        };

        Function<Integer, String> intToStringUsingLambdas = (i) -> i.toString();

        List<String> transformed = Lists.transform(list, integer -> integer.toString());

        Function<Integer, String> f1 = i -> i.toString();
        Function<Integer, String> f11 = i -> {
            var prefix = "xxxx";
            return prefix + i.toString();
        };
        Consumer<Integer> f2 = s -> System.out.println(s); // Function<T, Void>
        Function<Integer, Void> f21 = (s) -> {
            System.out.println(s);
            return null;
        };
        Supplier<Integer> f3 = () -> 42;                   // Function<Void, T>
        Supplier<Integer> f33 = () -> {
            var value = 42;
            return value;
        };
        Runnable f4 = () -> {
            System.out.println("I'm running");
            System.out.println("I'm running again");
        };                            // Function<Void, Void>
        Callable<Integer> f5 = () -> 42;                   // Function<Void, T>
        UnaryOperator<Integer> f6 = i -> i + 1;            // Function<T,T>
        Predicate<String> f7 = str -> str.isEmpty();       // Function<T, Boolean>

        BiFunction<Long, Long, Long> f8 = (i1, i2) -> i1 + i2;
        BinaryOperator<Long> f9 = (aLong, aLong2) -> Long.max(aLong, aLong2); // BiFunction<T,T,T>

        f4.run();
    }
}


interface F1 {

    String f1(Integer i);

}

interface F2 {

    String cdsdfgs(Integer s);

}

interface F3 {

    String dsfgsd(Integer s);

}
