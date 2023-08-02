package com.patri.java.ocp._3_generics_and_collections._4_comparator_vs_comparable;

public class ComparableInterfaceExample {

    public static void main(String[] args) {

        // Comparable Interface is in java.lang package and it has only one abstract method: compareTo()

        // public interface Comparable<T> {
        //      public int compareTo(T o);
        // }

        // any object can be Comparable
        // example : Duck.java -> we have a bunch of ducks and want to sort them by name

        // example: Animal.java -> implementation of compareTo() that compares numbers instead of Strings

        // example: Product.java -> compareTo() & equals() consistency => to solve the problem of consistency
        //                          you can use a Comparator to define the sort elsewhere
    }
}
