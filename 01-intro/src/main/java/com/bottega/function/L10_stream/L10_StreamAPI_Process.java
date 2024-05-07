package com.bottega.function.L10_stream;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

class L10_StreamAPI_Process {

    public static void main(String[] args) {
        users()
          .filter(n -> n.length() > 4)
          .forEach(System.out::println);

        System.out.println("----");

        users()
          .sorted()
          .forEach(System.out::println);

        System.out.println("----");

        users()
          .skip(1)
          .limit(2)
          .forEach(System.out::println);

        System.out.println("----");

        users()
          .dropWhile(i -> i.length() < 5)
          .forEach(System.out::println);

        System.out.println("----");

        users()
          .takeWhile(i -> i.length() < 5)
          .forEach(System.out::println);

        System.out.println("----");

        users()
          .map(i -> i.toUpperCase())
          .forEach(System.out::println);

        System.out.println("----");

        Stream.of(List.of(1), List.of(2,3), List.of(), List.of(4))
          .flatMap(list -> list.stream())
          .forEach(System.out::println);

        System.out.println("----");

        users()
          .distinct()
          .forEach(System.out::println);

        System.out.println("----");

        users()
          .sorted(Comparator.comparing(s -> s.length()))
          .forEach(System.out::println);
    }


    private static Stream<String> users() {
        return Stream.of("Adam", "John", "Kate", "Grzegorz", "Kate");
    }
}
