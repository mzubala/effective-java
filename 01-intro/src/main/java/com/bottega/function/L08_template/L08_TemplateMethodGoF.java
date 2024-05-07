package com.bottega.function.L08_template;

class L08_TemplateMethodGoF {

    public static void main(String[] args) {
        new HelloWorldWithLogging().run();
    }

    static class HelloWorldWithLogging extends LoggingTemplate {

        @Override
        void runInternal() {
            System.out.println("Hello World from a template!");
        }
    }

    static abstract class LoggingTemplate {
        abstract void runInternal();
        void run() {
            System.out.println("entering a method!");
            runInternal();
            System.out.println("exiting a method!");
        }

    }
}
