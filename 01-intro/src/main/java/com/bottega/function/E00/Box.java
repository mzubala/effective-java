package com.bottega.function.E00;

import javax.annotation.Nullable;

/**
 * Create a generic box class that allows to hold single element of any type.
 * Define following operations on the box class:
 * 1. factory method creating empty box (constructor should be private)
 * 2. factory method creating box with an element
 * 3. put - puts a new element into the box, throws IllegalStateException if it's not empty
 * 4. empty - returns an element in the box and makes it empty, throws IllegalStateException if the box is empty
 * 5. isEmpty - returns true if a box is empty
 *
 * No other methods should be defined. Try using TDD when implementing this task.
 */
public class Box<T> {

    @Nullable
    private T element;

    private Box(T element) {
        this.element = element;
    }

    public Box() {
        element = null;
    }

    public static <T> Box<T> create(T element) {
        return new Box<>(element);
    }

    public static <T> Box<T> create() {
        return new Box<>();
    }

    public boolean isEmpty() {
        return element == null;
    }

    public T empty() {
        if(isEmpty()) {
            throw new IllegalStateException("The box is already empty so it cannot be emptied.");
        }
        var tmp = element;
        element = null;
        return tmp;
    }

    public void put(T element) {
        if(!isEmpty()) {
            throw new IllegalStateException("The box is not empty so it cannot be filled with a new element.");
        }
        this.element = element;
    }

}
