package com.patri.java.ocp._2_design_patterns_and_principles._3_implementing_polymorphism;

public class Whale implements LivesInOcean {
    @Override
    public void makeSound() {
        System.out.println("sing");
    }
}
