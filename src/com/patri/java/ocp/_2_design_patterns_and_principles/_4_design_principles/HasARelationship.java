package com.patri.java.ocp._2_design_patterns_and_principles._4_design_principles;

public class HasARelationship {
    // Has-a Relationship = property of an object having a named data object or primitive as a member
    // Has-a Relationship = composition test

    // A has-a B => any child of A has-a B - !!! but not the private members of A (private members are not inherited)
}

// example for has-a:
class Bird_ {               // Bird has-a Beak - any child of Bird must also have a Beak -> if a parent has-a object as a protected or public member, then any child must have that object as a member
    public Beak beak;
    public Foot rightFoot;
    public Foot leftFoot;
}
class Beak {
    public String color;
    public double length;
}
class Foot {
    public int length;
}

// Uncovering problems with the Data Model using Is-a and Has-a
class Tail {}
class Primate {                     // Primate has-a Tail
    protected Tail tail;
}
class Monkey extends Primate {}     // Monkey is-a Primate, Monkey has-a Tail
class Chimpanzee extends Primate {} // Chimpanzee is-a Primate, Chimpanzee has-a Tail => wrong
// solution: we should remove the Tail property from the Primate class - since not all primates have tails
