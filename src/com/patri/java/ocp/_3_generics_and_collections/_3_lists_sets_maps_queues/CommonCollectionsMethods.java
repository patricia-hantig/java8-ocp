package com.patri.java.ocp._3_generics_and_collections._3_lists_sets_maps_queues;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommonCollectionsMethods {
    public static void main(String[] args) {
        // Collection interface contains the following methods for working with lists, sets and queues:
        // add(), remove(), isEmpty(), size(), clear(), contains()

        // ■ add()
        // method signature:  boolean add(E element)
        // inserts a new element into the Collection and returns true/false if it was successful or no
        List<String> list = new ArrayList<>();
        System.out.println(list.add("Sparrow"));    // true
        System.out.println(list.add("Sparrow"));    // true -> list allows duplicates

        Set<String> set = new HashSet<>();
        System.out.println(set.add("Sparrow"));     // true
        System.out.println(set.add("Sparrow"));     // false -> set doesn't allow duplicates
        System.out.println();

        // ■ remove()
        // method signature:    boolean remove(Object object)
        // removes a single matching value in a Collection and returns true/false if it was successful or not
        List<String> birds = new ArrayList<>();
        birds.add("hawk");                                  // [hawk]
        birds.add("hawk");                                  // [hawk, hawk]
        System.out.println(birds.remove("cardinal"));   // false
        System.out.println(birds.remove("hawk"));       // true
        System.out.println(birds);                          // [hawk]
        // there are overloaded remove() methods - if you use an index that doesn't exist - will throw IndexOutOfBoundsException
        System.out.println(birds.remove(0));         // hawk
        System.out.println(birds);                          // []
        System.out.println();

        // ■ isEmpty()
        // method signature:    boolean isEmpty()
        // checks if the Collection is empty
        System.out.println(birds.isEmpty());            // true
        System.out.println();

        // ■ size()
        // method signature:    int size()
        // returns the capacity of the Collection
        System.out.println(birds.size());               // 0
        birds.add("hawk");                              // [hawk]
        birds.add("hawk");                              // [hawk, hawk]
        System.out.println(birds.isEmpty());            // false
        System.out.println(birds.size());               // 2
        System.out.println();

        // ■ clear()
        // method signature:    void clear()
        // discard all elements of the Collection
        List<String> birds2 = new ArrayList<>();
        birds2.add("hawk");                         // [hawk]
        birds2.add("hawk");                         // [hawk, hawk]
        System.out.println(birds2.isEmpty());       // false
        System.out.println(birds2.size());          // 2
        birds2.clear();                             // []
        System.out.println(birds2.isEmpty());       // true
        System.out.println(birds2.size());          // 0
        System.out.println();

        // ■ contains()
        // method signature:    boolean contains(Object object)
        // checks if a certain value is in the Collection
        List<String> birds3 = new ArrayList<>();
        birds3.add("hawk");                             // [hawk]
        System.out.println(birds3.contains("hawk"));    // true
        System.out.println(birds3.contains("robin"));   // false
        // this method calls equals() on each element of the ArrayList to see if there are any matches
    }
}
