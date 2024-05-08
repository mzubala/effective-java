package com.bottega.function.E01;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

import static com.bottega.function.Utils.todo;

class FunctionalInterfaces {

    /**
     * @return a supplier that always returns a string 'test'
     */
    static Supplier<String> L1_constantSupplier() {
        return todo();
    }

    /**
     * @return a function that checks if a long is even
     */
    static Predicate<Long> L2_evenPredicate() {
        return todo();
    }


    /**
     * Functional interface that represents a 3 argument function
     */
    interface L3_TriFunction<In1, In2, In3, Out> {

    }

    /**
     * @return a function that first calculates an absolute value of its argument, then calculates a square root.
     * <p>
     * Do not implement any logic yourself - use existing functions from Math class plus function composition.
     */
    static Function<Double, Double> L4_absSqrt() {
        return todo();
    }

    /**
     * @return a consumer that prints a value passed to it converted to lower case to a given stream
     */
    static Consumer<String> L5_printer(PrintStream stream) {
        return todo();
    }

    static BiFunction<String, String, User> L6_userFactory() {
        return todo();
    }

    /**
     * @return a supplier of the name of a given user
     */
    static Supplier<String> L7_userNameSupplier(User u) {
        return todo();
    }

    /**
     * @return function converted to a consumer
     */
    static <T, S> Consumer<T> L8_toConsumer(Function<T, S> f) {
        return todo();
    }

    /**
     * @return function taking a user and returning its name
     */
    static Function<User, String> L9_userNameGetter() {
        return todo();
    }

    /**
     * @return a comparator that compares users by their last name (asc order) and first name (asc order) in case last names are equal.
     */
    static Comparator<User> L10_lastNameAndFirstNameComparator() {
        return todo();
    }

}

record User(String firstName, String lastName) {

    String name() {
        return firstName.concat(" ").concat(lastName);
    }

    int compareFirstNameTo(User other) {
        return firstName.compareTo(other.firstName());
    }

    int compareLastNameTo(User other) {
        return lastName.compareTo(other.lastName());
    }

}
