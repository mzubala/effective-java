package com.bottega.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class HelloWorld {
    public static void main(String[] args) {
        List<List<?>> lists = Arrays.asList(new LinkedList<>(), new ArrayList<>());

        for (List<?> l: lists) {
            l.clear();
        }
    }
}
