package com.patri.java.ocp._1_advanced_class_design._4_annotating_overridden_methods;

public class OverriddenMethods {
    // annotation = extra information about the program - is a type of metadata
    // @Override = used to show the intend for this method to override one method

    // @Override is used:
    // ■ to override method in a subclass
    // ■ to override method in a class that implements an interface
    // ■ to override a method from Object class: hashCode(), equals(), toString()
}

// ex:
class Bobcat {
    public void findDen() { }
}
class BobcatMother extends Bobcat {
    @Override
    public void findDen() { }

    // @Override
    // public void findDen(boolean b) { }      // DOES NOT COMPILE - here we have an overloaded method
}

// we can't override a field - only for methods