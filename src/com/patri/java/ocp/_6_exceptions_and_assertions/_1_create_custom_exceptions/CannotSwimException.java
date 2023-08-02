package com.patri.java.ocp._6_exceptions_and_assertions._1_create_custom_exceptions;

//extend Exception -> creates a custom checked exception
//extend RuntimeException -> creates a custom runtime exception
public class CannotSwimException extends Exception {

    public CannotSwimException() {
        super();
    }

    public CannotSwimException(Exception e) {
        super(e);
    }

    public CannotSwimException(String message) {
        super(message);
    }

    public static void main(String[] args) {
        try {
            throw new CannotSwimException();
        } catch (CannotSwimException e) {
            e.printStackTrace();
        }

        try {
            throw new CannotSwimException(new RuntimeException());
        } catch (CannotSwimException e) {
            e.printStackTrace();
        }

        try {
            throw new CannotSwimException("broken fin");
        } catch (CannotSwimException e) {
            e.printStackTrace();
        }
    }
}