package com.patri.java.ocp._2_design_patterns_and_principles._1_design_an_interface;

public interface Run extends Walk { // inherits all of the abstract methods of the parent interface
    public abstract boolean canHuntWhileRunning();
    abstract double getMaxSpeed();  // we have this method in parent interface
                                    // interface method definitions may be duplicated in a child interface without issue

    // The compiler automatically adds public to all interface methods and
    // abstract to all non‐static and non‐default methods - if the developer does not provide them
}
