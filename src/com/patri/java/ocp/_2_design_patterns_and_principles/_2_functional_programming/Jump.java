package com.patri.java.ocp._2_design_patterns_and_principles._2_functional_programming;

import com.patri.java.ocp._3_generics_and_collections._4_comparator_vs_comparable.Animal;

@FunctionalInterface
public interface Jump {
    public void jump (Animal animal);
}
