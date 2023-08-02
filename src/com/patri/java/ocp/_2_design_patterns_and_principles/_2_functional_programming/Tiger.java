package com.patri.java.ocp._2_design_patterns_and_principles._2_functional_programming;

public class Tiger implements Sprint {
    @Override
    public void sprint(Animal animal) {
        System.out.println("Animal is sprinting fast! " + animal.toString());
    }
}
