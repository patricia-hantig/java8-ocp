package com.patri.java.ocp._4_functional_programming._2_built_in_functional_interfaces;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

// Functional Interface             Param               Return Type                 Abstract method
//---------------------------------------------------------------------------------------------------
// Predicate<T>                     1 (T)               boolean                     test(T t)
// BiPredicate<T, U>                2 (T, U)            boolean                     test(T t, U u)
public class PredicateAndBiPredicateExample {
    // Predicate and BiPredicate - is used when filtering or matching

    public static void main(String[] args) {
        // instance method reference and lambda expression
        Predicate<String> p1 = String::isEmpty;
        Predicate<String> p2 = x -> x.isEmpty();
        System.out.println(p1.test(""));
        System.out.println(p2.test(""));
        System.out.println(p1.test("test"));
        System.out.println(p2.test(" "));

        // ex for BiPredicate
        BiPredicate<String, String> b1 = String::startsWith;
        BiPredicate<String, String> b2 = (string, prefix) -> string.startsWith(prefix);
        System.out.println(b1.test("chicken", "chick"));
        System.out.println(b2.test("chicken", "chick"));

        // Predicate and BiPredicate have default methods: and(), negate(), or(), isEqual()-only for Predicate
        Predicate<String> egg = s -> s.contains("egg");
        Predicate<String> brown = s -> s.contains("brown");

        Predicate<String> brownEggs = s -> s.contains("egg") && s.contains("brown");
        Predicate<String> brownEggsToo = egg.and(brown);    // equivalent with the line above
        Predicate<String> otherEggs = egg.and(brown.negate());

        System.out.println(brownEggs.test("one brown egg"));
        System.out.println(brownEggs.test("one white egg"));
        System.out.println(brownEggsToo.test("one brown egg"));
        System.out.println(brownEggsToo.test("one white egg"));
        System.out.println(otherEggs.test("one brown egg"));
        System.out.println(otherEggs.test("one white egg"));
    }
}
