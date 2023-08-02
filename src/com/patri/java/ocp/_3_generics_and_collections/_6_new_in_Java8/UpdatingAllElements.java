package com.patri.java.ocp._3_generics_and_collections._6_new_in_Java8;

import java.util.Arrays;
import java.util.List;

// new addition in Java 8: replaceAll()
public class UpdatingAllElements {
    public static void main(String[] args) {
        // method signature for replaceAll():
        // void replaceAll(UnaryOperator<E> operator)
        // UnaryOperator = takes one parameter and returns a value of the same type

        // ex:
        List<Integer> list = Arrays.asList(1, 2, 3);
        System.out.println(list);
        list.replaceAll(x -> x * 2);  // the lambda is applied to each element in the list
        System.out.println(list);
    }
}
