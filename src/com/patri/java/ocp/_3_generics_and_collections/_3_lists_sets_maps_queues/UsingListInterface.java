package com.patri.java.ocp._3_generics_and_collections._3_lists_sets_maps_queues;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UsingListInterface {
    public static void main(String[] args) {
        // List = ordered collection that can contain duplicates and elements are inserted at specific position (index)

        // ■ O - order of magnitude = complexity = performance of algorithms
        // O(1) = constant time - it doesn't matter how large the collection is, the answer will always take teh same time to return
        //                      - ex for O(1) - takes the same to return string literal "Panda" as returning the last element of an array
        // O(log n) = logarithmic time - not looking at all elements from the collection
        //                      - ex for O(log n) - binary search
        // O(n) = linear time - looping through all the elements of the collection
        //                      - ex for O(n) - returning number of elements matching some match from a collection
        // O(n^2) = n square time - many times looping through the collection
        //                      - ex for O(n^2) - putting every pair of pandas together to see if they'll share an exhibit

        // ■ List implementations
        // ■■ ArrayList = resizable array - when elements are added the ArrayList grows automatically
        // +++ accessing an element from ArrayList is quicker than
        // --- adding or removing an element from an ArrayList
        // * adding/removing an element at the end : O(1)
        // * adding/removing an element inside : O(n) => O(n) + O(1) = O(n) - create a bigger array + copy old array into new array + add element
        // * accessing using position(index) : O(1)
        // * accessing using value : O(n)
        // when you aren't sure which collection to use - use an ArrayList
        // ArrayList is a good choice when you are reading more often that writing

        // ■■ LinkedList = special list which implements both List and Queue (it's double-ended queue) => so it has besides the methods
        // for List also the methods for adding/removing from the beginning/end of the list
        // +++ access, add and remove from beginning/end of the list in constant time : O(1)
        // --- access an arbitrary element (using index) : O(n)

        // ■■ Vector - old code = similar to ArrayList, but slower and thread safe

        // ■■ Stack - old code = extends Vector - it's used for LIFO
        // instead of Stack we are using now ArrayDeque - you will learn more about it in Queue section

        // ■ List methods
        // the methods are for working with indexes and we also have the methods for Collection
        //              Method              |                           Description
        // --------------------------------------------------------------------------------------------
        // void get(E element)              |   adds element to end
        // void add(int index, E element)   |   adds element at index and moves the rest toward the end
        // E get(int index)                 |   returns element at index
        // int indexOf(Object o)            |   returns first matching index or -1 it not found
        // int lastIndexOf(Object o)        |   returns last matching index or -1 it not found
        // void remove(int index)           |   removes element at index and moves the rest toward the front
        // E set(int index, E element)      |   replaces element at index and returns original

        // ex for list methods:
        List<String> list = new ArrayList<>();
        list.add("SD");
        System.out.println(list);           // [SD]
        list.add(0, "NY");
        System.out.println(list);           // [NY, SD]
        list.set(1, "FL");
        System.out.println(list);           // [NY, FL]
        list.remove("NY");
        System.out.println(list);           // [FL]
        list.remove(0);
        System.out.println(list);           // []

        list.add("OH");
        System.out.println(list);           // [OH]
        list.add("CO");
        System.out.println(list);           // [OH, CO]
        list.add("NJ");
        System.out.println(list);           // [OH, CO, NJ]
        String state = list.get(0);
        System.out.println(state);          // OH
        int index = list.indexOf("NJ");
        System.out.println(index);          // 2

        // the output would be the same if you tried these examples with LinkedList, Vector or Stack - but the code would be less efficient

        // ■ Looping through a List
        // using enhanced for loop :
        for (String string : list) {
            System.out.print(string + " ");
        }
        System.out.println();

        // before Java 5:
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            String string = (String) iterator.next();
            System.out.print(string + " ");
        }
        // or using a hybrid way: Iterator with generics
        Iterator<String> iterator2 = list.iterator();
        while (iterator2.hasNext()) {
            String string = iterator2.next();   // you get rid of the cast
            System.out.print(string + " ");
        }
    }
}
