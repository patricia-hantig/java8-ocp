package com.patri.java.ocp._3_generics_and_collections._4_comparator_vs_comparable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// ex: for Comparator -> we have a bunch of ducks and want to sort them by name and we also want to sort by something else
public class Duck2 implements Comparable<Duck2> {

    // suppose that we add 'weight' to our Duck2 class
    private String name;
    private int weight;

    public Duck2(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return name;
    }

    // The Duck2 class can define compareTo() in only one way -> in this case we choose: name
    @Override
    public int compareTo(Duck2 duck2) {
        return name.compareTo(duck2.name);
    }

    // if we want to sort by something else - we have to define that sort order outside the compareTo()
    public static void main(String[] args) {
        Comparator<Duck2> byWeight = new Comparator<Duck2>() {
            @Override
            public int compare(Duck2 d1, Duck2 d2) {
                return d1.getWeight() - d2.getWeight();
            }
        };

        List<Duck2> ducks = new ArrayList<>();
        ducks.add(new Duck2("Quack", 7));
        ducks.add(new Duck2("Puddles", 10));
        Collections.sort(ducks);
        System.out.println(ducks);              // [Puddles, Quack]     -> sort with Comparable
        Collections.sort(ducks, byWeight);
        System.out.println(ducks);              // [Quack, Puddles]     -> sort with Comparator

        // the comparator can be rewritten using lambda expressions:
        Comparator<Duck2> byWeight2 = (d1, d2) -> d1.getWeight() - d2.getWeight();
        Comparator<Duck2> byWeight3 = (Duck2 d1, Duck2 d2) -> d1.getWeight() - d2.getWeight();
        Comparator<Duck2> byWeight4 = (d1, d2) -> { return d1.getWeight() - d2.getWeight();};
        Comparator<Duck2> byWeight5 = (Duck2 d1, Duck2 d2) -> {return d1.getWeight() - d2.getWeight(); };

        // why the following code does not compile?
        /* Comparator<Duck2> byWeight6 = new Comparator<Duck2>() {
            @Override
            public int compareTo(Duck2 d1, Duck2 d2) {
                return d1.getWeight() - d2.getWeight();
            }
        }*/
        // Answer: because the method name is wrong! -> Comparator must implement method compare()
    }
}
