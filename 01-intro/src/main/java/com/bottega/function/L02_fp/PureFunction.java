package com.bottega.function.L02_fp;

import java.util.ArrayList;
import java.util.List;

class PureFunction {

    private static List<String> prefixes = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(checkIfValid("foo", prefixes));
    }

    public static boolean checkIfValid(String string) {
        return prefixes.stream().anyMatch(string::startsWith);
    }

    public static boolean checkIfValid(String string, List<String> prefixes) {
        return prefixes.stream().anyMatch(string::startsWith);
    }
}
