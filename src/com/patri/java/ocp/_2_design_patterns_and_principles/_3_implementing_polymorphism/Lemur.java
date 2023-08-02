package com.patri.java.ocp._2_design_patterns_and_principles._3_implementing_polymorphism;

public class Lemur extends Primate implements HasTail {
    public int age = 10;

    @Override
    public boolean isTailStriped() {
        return false;
    }

    public static void main(String[] args) {
        Lemur lemur = new Lemur();          // only one object is created(Lemur)
        System.out.println(lemur.age);

        HasTail hasTail = lemur;            // Lemur object is passed as an instance of an interface it implements
        System.out.println(hasTail.isTailStriped());
        // System.out.println(hasTail.age);    // DOES NOT COMPILE - hasTail reference has access only to methods from HasTail interface

        Primate primate = lemur;            // Lemur object is passed as an instance of its superclass
        System.out.println(primate.hasHair());
        // System.out.println(primate.isTailStriped());    // DOES NOT COMPILE - primate reference has access only to methods defined in Primate class
    }
}
