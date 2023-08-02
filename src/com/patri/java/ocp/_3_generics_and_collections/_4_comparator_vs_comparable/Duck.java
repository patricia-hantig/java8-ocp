package com.patri.java.ocp._3_generics_and_collections._4_comparator_vs_comparable;

// ex for Comparable: we have a bunch of ducks and want to sort them by name

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// any class that implements Comparable must override compareTo()
public class Duck implements Comparable<Duck> {

    private String name;
    public Duck(String name) {
        this.name = name;
    }

    @Override
    public String toString() {              // use readable output
        return name;
    }

    @Override
    public int compareTo(Duck duck) {
        return name.compareTo(duck.name);   // call String's compareTo
    }

    public static void main(String[] args) {
        List<Duck> ducks = new ArrayList<>();
        ducks.add(new Duck("Quack"));
        ducks.add(new Duck("Puddles"));
        Collections.sort(ducks);            // sort by name
        System.out.println(ducks);          // [Puddles, Quack]
    }

    // Duck class implements Comparable -> it's a comparable object
    // Duck class overrides toString() - so we can see useful output when printing duck names
    // Duck class implements compareTo() - because we are comparing Strings we use the existent String compareTo() method

    // ■ compareTo() outputs:
    // ■■   0 - when current obj == obj from method argument
    // ■■ < 0 - when current obj < obj from method argument
    // ■■ > 0 - when current obj > obj from method argument

    // in this example we compare Strings - order is defined according to the Unicode character mapping
}
