package com.bottega.function.L05_monads;

import java.util.Optional;

class L05_OptionalAPI {

    public static void main(String[] args) {
        String s = findUser(42)
          .filter(u -> u.name().length() > 20)
          .map(i -> i.name())
          .orElseGet(() -> getFallbackValue());

        System.out.println(s);

        // map vs flatmap
        findUser(42).map(i -> i.name());
        findUser(42).flatMap(i -> Optional.ofNullable(i.name()));

        // create
        Optional<Integer> o1 = Optional.empty();
        Optional<String> o2 = Optional.ofNullable("foo");
        Optional<String> o3 = Optional.of("bar");

        // processing
        Optional<String> o4 = findUser(42).map(u -> u.name());
        Optional<User> o5 = findUser(42).flatMap(n -> findUserByName(n.name()));
        Optional<User> o6 = findUser(42).filter(u -> !u.name().isEmpty());
        Optional<User> o7 = findUser(42).or(() -> findUser(43));

        // unpack
        findUser(42).ifPresent(System.out::println);
        findUser(42).ifPresentOrElse(System.out::println, () -> System.out.println("empty!"));
        String r1 = findUser(42).map(u -> u.name()).orElse("fallback");
        String r2 = findUser(42).map(u -> u.name()).orElseGet(() -> getFallbackValue());
        User r3 = findUser(42).orElseThrow(IllegalStateException::new);
        User r4 = findUser(42).orElseThrow();
        User r5 = findUser(42).get();
    }

    private static String getFallbackValue() {
        System.out.println("calculating fallback value...");
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "fallback";
    }

    public static Optional<User> findUser(int id) {
        return id == 42
          ? Optional.of(new User("Adam"))
          : Optional.empty();
    }

    public static Optional<User> findUserByName(String name) {
        return Optional.of(new User("Adam"));
    }

    record User(String name) {
    }
}


