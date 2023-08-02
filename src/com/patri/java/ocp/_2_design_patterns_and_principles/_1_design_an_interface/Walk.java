package com.patri.java.ocp._2_design_patterns_and_principles._1_design_an_interface;

// an interface may extend another interface and - it inherits all abstract methods
public interface Walk {
    boolean isQuadruped();
    abstract double getMaxSpeed();
}
