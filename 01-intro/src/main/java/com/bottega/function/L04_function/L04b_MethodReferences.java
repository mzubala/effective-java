package com.bottega.function.L04_function;

import java.util.function.Function;
import java.util.function.Supplier;

class L04b_MethodReferences {

    public static void main(String[] args) {
        Function<Integer, String> f1 = L04b_MethodReferences::foo; // static method reference
        System.out.println(f1.apply(42));

        var instance = new L04b_MethodReferences();
        Function<Integer, String> f2 = instance::bar; // instance method reference
        System.out.println(f2.apply(42));

        Supplier<L04b_MethodReferences> constructor = L04b_MethodReferences::new; // constructor reference
        var instance2 = constructor.get();
    }

    public L04b_MethodReferences() {
        System.out.println("Calling the constructor");
    }

    static String foo(Integer x) {
        return "foo " + x;
    }

    String bar(Integer x) {
        return "bar " + x;
    }


}
