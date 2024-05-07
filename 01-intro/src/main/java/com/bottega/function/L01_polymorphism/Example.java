package com.bottega.function.L01_polymorphism;

class Example {

    public static void main(String[] args) {
        var flyingFish = new FlyingFish();
        swim(flyingFish);
        fly(flyingFish);
    }

    public static void swim(Swimmable animal) {

    }

    public static void fly(Flyable animal) {

    }
}
