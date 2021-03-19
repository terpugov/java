package ru.inno.java;

public class CustomException extends Exception {

    public CustomException(String message) {
        super(message);
        Thread thread = Thread.currentThread();
        System.out.println( "Exception " + thread.getName());
    }
}
