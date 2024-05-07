package com.bottega.function.L05_monads;

import java.util.Optional;

class L05_Optional {

    public static void main(String[] args) {
        String r1 = getUppercasedNameOrDefaultDeclarative(42);
        String r2 = getUppercasedNameOrDefaultImperative(42);
    }

    public static String getUppercasedNameOrDefaultImperative(int id) {
        String r;
        User user = findUser(42).orElse(null);
        if (user != null) {
            String name = user.name();
            if (name != null) {
                String upperCased = name.toUpperCase();
                if (upperCased != null) {
                    r = upperCased;
                } else {
                    r = "DEFAULT";
                }
            } else {
                r = "DEFAULT";
            }
        } else {
            r = "DEFAULT";
        }

        return r;
    }

    public static String getUppercasedNameOrDefaultDeclarative(int id) {
        return findUser(42)
          .map(User::name)
          .map(String::toUpperCase)
          .orElse("DEFAULT");
    }

    public static Optional<User> findUser(int id) {
        return Optional.of(new User("Adam"));
    }

    record User(String name) {
    }
}


