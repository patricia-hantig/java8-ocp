package com.patri.java.ocp._3_generics_and_collections._3_lists_sets_maps_queues;

import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class UsingSetInterface {
    public static void main(String[] args) {
        // Set = is a collection that doesn't allow duplicate entries and the elements are not ordered

        // ■ ordered vs sorted
        // ordered = the collection has a special order, that doesn't take in consideration the value of elements
        // sorted = the collection is ordered based on the value of the elements

        // ■ Set implementations
        // ■■ HashSet = stores its elements in a hash table => uses the hashCode() method of the objects to retrieve them more efficiently
        // hashCode() - splits objects in different buckets
        // +++ adding elements in set and checking if an element is in the set : O(1)
        // --- the order is not guaranteed

        // ■■ TreeSet = has its elements in a sorted tree structure
        // +++ is always in sorted order
        // --- adding elements in set and checking if an element is in the set : O(log n)
        // implements a special interface: NavigableSet Interface

        // HashSet is more complicated in reality because it has empty rows
        // HashSet is faster than TreeSet

        // ■ Set methods
        // no extra methods, only the ones that we use for Collection interface
        // add() returns true only if the elements are not in set

        // ex: HashSet
        Set<Integer> set = new HashSet<>();
        boolean b1 = set.add(66);               // true
        System.out.println(b1);
        boolean b2 = set.add(10);               // true
        System.out.println(b2);
        boolean b3 = set.add(66);               // false
        System.out.println(b3);
        boolean b4 = set.add(8);                // true
        System.out.println(b4);
        for (Integer integer : set)
            System.out.print(integer + ", ");    // 66, 8, 10,  -> in this case: not sorted order, not the order in which we add the elements (arbitrary order)
        // equals() method is used to determine equality
        // hashCode() method is used to know which bucket to look in so that Java doesn’t have to look through the whole set to find out if an object is there
        // the best case is that hash codes are unique -> Java has to call equals() on only one object
        // the worst case is that all implementations return the same hashCode() -> Java has to call equals() on every element of the set

        // ex: TreeSet
        Set<Integer> treeSet = new TreeSet<>();
        boolean c1 = treeSet.add(66);               // true
        System.out.println(c1);
        boolean c2 = treeSet.add(10);               // true
        System.out.println(c2);
        boolean c3 = treeSet.add(66);               // false
        System.out.println(c3);
        boolean c4 = treeSet.add(8);                // true
        System.out.println(c4);
        for (Integer integer : treeSet)
            System.out.print(integer + ", ");       // 8, 10, 66, -> natural sorted order - because numbers implements Comparable interface - which is used for sorting
        System.out.println();

        // ■ NavigableSet Interface - TreeSet implements this interface, NavigableSet implements SortedSet interface
        // NavigableSet methods:
        //      Method      |              Description
        // --------------------------------------------------------------------------------------
        //  E lower(E e)    |   Returns greatest element that is < e, or null if no such element
        //  E floor(E e)    |   Returns greatest element that is <= e, or null if no such element
        //  E ceiling(E e)  |   Returns smallest element that is >= e, or null if no such element
        //  E higher(E e)   |   Returns smallest element that is > e, or null if no such element

        NavigableSet<Integer> navigableSet = new TreeSet<>();
        for (int i = 1; i <= 20; i++)
            navigableSet.add(i);
        System.out.println(navigableSet.lower(10));     // 9    - highest element that is less than 10
        System.out.println(navigableSet.floor(10));     // 10   - highest element that is no higher than 10
        System.out.println(navigableSet.ceiling(20));   // 20   - lowest element greater than or equal to 20
        System.out.println(navigableSet.higher(20));    // null - lowest element greater than 20
    }
}
