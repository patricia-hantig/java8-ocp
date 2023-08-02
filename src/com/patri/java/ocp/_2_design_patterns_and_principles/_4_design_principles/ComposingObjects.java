package com.patri.java.ocp._2_design_patterns_and_principles._4_design_principles;

public class ComposingObjects {
    // Object composition = is the property of constructing a class using references to other classes in order to reuse the functionality of the other classes
    // Object composition = alternative to inheritance and is often used to simulate polymorphic behavior that cannot be achieved via single inheritance
}

// example:
class Flippers {
    public void flap() {
        System.out.println("The flippers flap back and forth");
    }
}
class WebbedFeet {
    public void kick() {
        System.out.println("The webbed feet kick to and fro");
    }
}

// trying to relate these objects using inheritance does not make sense
// solution: compose a new class that contains both of these objects and delegates its methods to them
class Penguin {
    private final Flippers flippers;
    private final WebbedFeet webbedFeet;

    public Penguin() {
        this.flippers = new Flippers();
        this.webbedFeet = new WebbedFeet();
    }

    public void flap() {
        this.flippers.flap();
    }

    public void kick() {
        this.webbedFeet.kick();
    }
}

// the new class Penguin is composed of instances of Flippers and WebbedFeet
// one of the advantages of object composition over inheritance is that it tends to promote greater code reuse
// by using object composition - you gain access to other classes and methods ( which is hard to obtain via single-inheritance)

// Object composition still requires you to explicitly expose the methods and values manually,
// while inheritance includes protected and public members automatically

// using method overloading to determine dynamically which method to select at runtime is an extremely powerful tool for building intelligent classes

// both object composition and inheritance have an important place in developing good code, and in many cases it may be difficult to decide which path to choose
