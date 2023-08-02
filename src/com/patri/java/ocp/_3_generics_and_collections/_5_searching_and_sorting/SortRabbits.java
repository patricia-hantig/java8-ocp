package com.patri.java.ocp._3_generics_and_collections._5_searching_and_sorting;

import java.util.*;

// we have sort() method
// sort method uses compareTo() method to sort -> it expects the objects to be sorted to be Comparable

public class SortRabbits {
    static class Rabbit {
        int id;
    }

    public static void main(String[] args) {
        List<Rabbit> rabbits = new ArrayList<>();
        rabbits.add(new Rabbit());
        // Collections.sort(rabbits);       // DOES NOT COMPILE
        // why? - Rabbit class is not Comparable
        // solution - pass a Comparator to sort() (a Comparator is used when you want to specify sort order without using a compareTo() method)
        Comparator<Rabbit> comparator = (r1, r2) -> r1.id - r2.id;
        Collections.sort(rabbits, comparator);

        // a list must be sorted before searching in it
        // sort() and binarySearch() methods allow you to pass in a Comparator object when you don't want to use natural order

        // what do you think the following code will print?
        List<String> names = Arrays.asList("Fluffy", "Hoppy");              // [Fluffy, Hoppy]
        int before = Collections.binarySearch(names, "Hoppy");
        Comparator<String> comp = Comparator.reverseOrder();                // [Hoppy, Fluffy]
        int index = Collections.binarySearch(names, "Hoppy", comp);
        System.out.println(index);
        // Answer: -1
        // why? - because we do a search and the list is sorted in descending order
        System.out.println(before); //1
    }
}
