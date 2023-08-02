package com.patri.java.ocp._4_functional_programming._2_built_in_functional_interfaces;

import java.util.function.BiFunction;
import java.util.function.Function;

// Functional Interface             Param               Return Type                 Abstract method
//---------------------------------------------------------------------------------------------------
// Function<T, R>                   1 (T)               R                           apply(T t)
// BiFunction<T, U, R>              2 (T, U)            R                           apply(T t, U u)
public class FunctionAndBiFunctionExample {
    // Function and BiFunction - is used for turning one/two param into a value of a different/same type and it returns it

    public static void main(String[] args) {
        // ex: transforms String -> length of String (String -> int => autoboxed to Integer)
        Function<String, Integer> f1 = String::length;
        Function<String, Integer> f2 = x -> x.length();
        System.out.println(f1.apply("Ana"));
        System.out.println(f2.apply("test"));

        // ex: concat 2 strings
        BiFunction<String, String, String> b1 = String::concat;
        BiFunction<String, String, String> b2 = (string1, string2) -> string1.concat(string2);
        System.out.println(b1.apply("baby", "chick"));
        System.out.println(b2.apply("baby", "chick"));
    }
}

// Creating Your Own Functional Interfaces
// we can create built-in interfaces with 1, 2, 3, 4, etc. params
interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}

interface QuadFunction<T, U, V, W, R> {
    R apply(T t, U u, V v, W w);
}