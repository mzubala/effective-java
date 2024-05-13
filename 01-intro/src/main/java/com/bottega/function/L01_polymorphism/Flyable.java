package com.bottega.function.L01_polymorphism;

interface Flyable {
    void fly();

    default void logFlying() {
        System.out.println("About to fly");
        this.fly();
        System.out.println("I am flying");
    }
}

abstract class FlyableAsAClass { // GoF design pattern - template method

    abstract void fly();

    void logFlying() {
        System.out.println("About to fly");
        this.fly();
        System.out.println("I am flying");
    }

}
