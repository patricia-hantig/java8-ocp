package com.patri.java.ocp._3_generics_and_collections._6_new_in_Java8;

import java.util.Arrays;
import java.util.List;

public class LoopingThroughACollection {
    public static void main(String[] args) {

        // ex: print out values one per line
        List<String> cats = Arrays.asList("Annie", "Ripley");

        // loop through a collection can be done in the following ways:
        // ■ with an enhanced for loop
        for (String cat: cats)
            System.out.println(cat);

        // ■ using a foreach loop with lambda expression
        cats.forEach(c -> System.out.println(c)); // we have used a Consumer that takes a single parameter and doesn't return anything

        // ■ using a foreach loop with method reference - this is new addition in Java8
        cats.forEach(System.out::println);
    }
}
