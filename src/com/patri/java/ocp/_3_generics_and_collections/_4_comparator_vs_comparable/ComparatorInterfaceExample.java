package com.patri.java.ocp._3_generics_and_collections._4_comparator_vs_comparable;

public class ComparatorInterfaceExample {

    public static void main(String[] args) {

        // Comparator Interface is in java.util package
        // is a functional interface and it has only one abstract method: compare()

        // public interface Comparable<T> {
        //      public int compare(T o1, T o2);
        // }

        // The method compare() can be written as a lambda expression
        // Q:   Is Comparable a Functional Interface?
        // A:   Comparable is also a functional interface since it has also a single abstract method, but
        //      we should not use a lambda expression to override compare() because:
        //      The point of Comparable is to implement it inside the object being compared!

        // example : Duck2.java -> we have a bunch of ducks and want to sort them by name and we also want to sort by something else

        // Comparision between Comparable and Comparator
        //                      Difference                      |   Comparable      |   Comparator
        // -------------------------------------------------------------------------------------------
        // Package name                                         |   java.lang       |   java.util
        // Interface must be implemented by class comparing?    |       Yes         |       No
        //          (ex class X implements InterfaceName)       |
        // Method name in interface                             |   compareTo()     |   compare
        // Number of parameters                                 |       1           |       2
        // Common to declare using lambda expression            |       No          |       Yes
        // -------------------------------------------------------------------------------------------

        // Comparator is used for comparing multiple fields:
        // example : Squirrel.java -> shows an easier way of comparing multiple fields

        // You’ve noticed that we have ignored nulls in checking equality and comparing objects.
        // This works fine for the exam. In the real world, though, you will have to decide how to handle nulls
        // or prevent them from being in your object. It is common to decide that nulls sort before any other values.
    }
}
