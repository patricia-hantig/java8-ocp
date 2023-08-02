package com.patri.java.ocp._2_design_patterns_and_principles._1_design_an_interface;

// interface = abstract data type, similar to a class that defines a list of
//              public abstract methods that any class implementing the interface must provide
// an interface may include: constants (public static final variables), default methods, static methods
public interface Fly {

    public int getWingSpan() throws Exception; // this method declares an exception
                            // because of method overriding - this does not require
                            // the exception to be declared in the overridden method

    public static final int MAX_SPEED = 100; // constant static variable available anywhere within our application

    public default void land() {            // default method
        System.out.println("Animal is landing");
    }

    public static double calculateSpeed(float distance, double time) {  // static member available without an instance of the interface
        return distance/time;
    }
}
