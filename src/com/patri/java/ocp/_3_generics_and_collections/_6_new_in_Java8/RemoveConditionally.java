package com.patri.java.ocp._3_generics_and_collections._6_new_in_Java8;

import java.util.ArrayList;
import java.util.List;

// new addition in Java 8: removeIf()
public class RemoveConditionally {
    public static void main(String[] args) {
        // method signature for removeIf():
        // boolean removeIf(Predicate<?super E> filter)
        // Predicate = is a lambda that takes one parameter and returns a boolean

        // example:
        List<String> list = new ArrayList<>();
        list.add("Magician");
        list.add("Assistant");
        System.out.println(list);
        list.removeIf(s -> s.startsWith("A"));
        System.out.println(list);

        // the lambda expression can't be replaced with method references!
        // why? - because startsWith takes a parameter that isn't 's'
    }

}
