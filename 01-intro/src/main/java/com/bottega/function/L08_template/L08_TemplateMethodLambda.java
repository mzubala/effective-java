package com.bottega.function.L08_template;

class L08_TemplateMethodLambda {

    public static void main(String[] args) {
        runWithLogging(() -> System.out.println("Hello World from a template!"));
    }

    static void runWithLogging(Runnable runnable) {
        System.out.println("entering a method!");
        runnable.run();
        System.out.println("exiting a method!");
    }
}
