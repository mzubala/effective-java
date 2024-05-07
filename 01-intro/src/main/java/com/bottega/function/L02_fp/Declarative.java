package com.bottega.function.L02_fp;

import java.util.Arrays;
import java.util.List;

class Declarative {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);

        // imperative
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        // declarative
        list.forEach(System.out::println);
    }
}
