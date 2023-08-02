package com.patri.java.ocp._2_design_patterns_and_principles._3_implementing_polymorphism;

public class Oceanographer {
    public void checkSound(LivesInOcean animal) { // this method accepts any object that implements LivesInOcean interface
        animal.makeSound();
    }

    public static void main(String[] args) {
        Oceanographer oceanographer = new Oceanographer();
        oceanographer.checkSound(new Dolphin());
        oceanographer.checkSound(new Whale());
    }
}
