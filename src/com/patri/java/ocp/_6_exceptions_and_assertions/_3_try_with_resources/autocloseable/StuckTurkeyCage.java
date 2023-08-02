package com.patri.java.ocp._6_exceptions_and_assertions._3_try_with_resources.autocloseable;

public class StuckTurkeyCage implements AutoCloseable {

    @Override
    public void close() throws Exception {      //it is better to throw a much specific exception
        System.out.println("Cage door does not close");
    }

    public static void main(String[] args) throws Exception {
        try (StuckTurkeyCage stuckTurkeyCage = new StuckTurkeyCage()) { //without 'throws Exception' at main
                                                // it DOES NOT COMPILE (checked ex must be handled or declared)
            System.out.println("put turkeys in");
        }
    }
}
