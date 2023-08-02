package com.patri.java.ocp._2_design_patterns_and_principles._1_design_an_interface;

public class Eagle implements Fly {
    @Override
    public int getWingSpan() {  // this method declares an exception in the interface
        return 15;              // because of method overriding - this does not require
    }                           // the exception to be declared in the overridden method

    @Override
    public void land() {
        System.out.println("Eagle is diving fast");
    }
}
