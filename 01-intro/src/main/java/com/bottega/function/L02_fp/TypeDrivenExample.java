package com.bottega.function.L02_fp;

import java.util.Optional;

class TypeDrivenExample {

    public static void main(String[] args) {
        User user = findUser(42);
        if (user != null) {
            //...
        }

        Optional<User> userOptional = findUserOptional(42);
    }

    public static Optional<User> findUserOptional(int id) {
        return Optional.ofNullable(new User());
    }

    /**
     * Sometimes returns a user and sometimes null
     */
    public static User findUser(int id) {
        return null;
    }

    record User() {

    }
}
