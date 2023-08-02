package com.patri.java.ocp._2_design_patterns_and_principles._1_design_an_interface;

public class Lion implements Run { // the class implementing the interface must provide the proper modifiers for overridden methods
    // Lion class must provide concrete implementations of all inherited abstract methods from both Walk and Run interfaces

    @Override
    public boolean canHuntWhileRunning() {
        return true;
    }

    @Override
    public boolean isQuadruped() {
        return true;
    }

    @Override
    public double getMaxSpeed() {
        return 100;
    }
}

// interface cannot extend a class
// public interface Sleep extends Lion {}   // DOES NOT COMPILE

// class cannot extend an interface
// public class Tiger extends Walk {}       // DOES NOT COMPILE

// a class can implement more interfaces
class Frog implements Swim, Hop {}

// interfaces may not be marked final

// interfaces cannot be instantiated directly

// Java fails to compile if a class or interface inherits 2 default methods with the same
// signature and doesn't provide its own implementation