package com.patri.java.ocp._3_generics_and_collections._5_searching_and_sorting;

// there are some collections that require classes to implement Comparable
// unlike sorting, they don’t check that you have actually implemented Comparable at compile time

import com.patri.java.ocp._3_generics_and_collections._4_comparator_vs_comparable.Duck;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

// example: we have a Rabbit class that doesn't implement Comparable - we try to add it to a TreeSet
public class UseTreeSet {
    static class Rabbit {
        int id;
    }

    public static void main(String[] args) {
        Set<Duck>  ducks = new TreeSet<>();     // Duck class implements Comparable
        ducks.add(new Duck("Puddles"));  // TreeSet is able to sort it into the proper position in the set
        Set<Rabbit> rabbits = new TreeSet<>();
        rabbits.add(new Rabbit());      // throws an exception: Rabbit cannot be cast to java.lang.Comparable
        // when TreeSet tries to sort it, Java discovers that Rabbit does not implement Comparable

        // you can tell collections that require sorting that you wish to use a specific Comparator
        // example:
        Set<Rabbit> rabbit1 = new TreeSet<>(new Comparator<Rabbit>() {
            @Override
            public int compare(Rabbit r1, Rabbit r2) {
                return r1.id = r2.id;
            }
        });
        rabbit1.add(new Rabbit());
        // now Java knows that you want to sort by id and all is well
        // Comparators are helpful because they let you separate sort order from the object to be sorted
    }
}
