package com.bottega.function.L03_sneaky_mutability;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

class SneakyMutability {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        list.add("foo");
        list.add("bar");
        list.add("baz");

        System.out.println("original list: " + list);

        List<String> sublist = list.subList(0, 2);

        System.out.println("original sublist: " + sublist);


        List<String> transformed = Lists.transform(sublist, new Function<String, String>() {
            @Override
            public String apply(String string) {
                return string.toUpperCase();
            }
        });

        System.out.println("transformed sublist: " + transformed);

        transformed.clear();
        System.out.println("transformed: " + transformed);
        System.out.println("sub: " + sublist);
        System.out.println("list: " + list);
    }
}
