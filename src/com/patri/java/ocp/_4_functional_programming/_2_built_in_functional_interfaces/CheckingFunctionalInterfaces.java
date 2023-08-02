package com.patri.java.ocp._4_functional_programming._2_built_in_functional_interfaces;

public class CheckingFunctionalInterfaces{

    // It’s really important to know the number of parameters, types, return value, and method name for each of the functional interfaces
    // Remember the "Common Functional Interfaces" table and answer the following questions:

    // ■■■■■■■■■■■■■■■■■■■■■■■■■■ What functional interface would you use in these three situations? ■■■■■■■■■■■■■■■■■■■■■■■■■■
    // ■ Returns a String without taking any parameters                            - Supplier
    // ■ Returns a Boolean and takes a String                                      - Function (Predicate returns boolean, not Boolean)
    // ■ Returns an Integer and takes two Integers                                 - BinaryOperator/BiFunction

    // ■■■■■■■■■■■■■■■■■■■■■■■■■■ What functional interface would you use to fill in the blank for these? ■■■■■■■■■■■■■■■■■■■■■■■■■■
    // _____<List> ex1 = x -> "".equals(x.get(0));              // 1 param, String -> boolean => Predicate (for Function should have 2 params)
    // _____<Long> ex2 = (Long l) -> System.out.println(l);     // 1 param, Long -> returns nothing => Consumer (it returns nothing)
    // _____ <String, String> ex3 = (s1, s2) -> false;          // 2 params, (param1, param2) -> boolean => BiPredicate (when it returns
                                                                // boolean and not Boolean -> it's always Predicate)

    // ■■■■■■■■■■■■■■■■■■■■■■■■■■ Identify the error ■■■■■■■■■■■■■■■■■■■■■■■■■■
    // ■ Function<List<String>> ex1 = x -> x.get(0); // DOES NOT COMPILE      -> Function<T, R> - has 2 params
    // ■ UnaryOperator<Long> ex2 = (Long l) -> 3.14; // DOES NOT COMPILE      -> 3.14 is double - UnaryOperator has the same return type
    // ■ Predicate ex4 = String::isEmpty; // DOES NOT COMPILE                 -> it should be Predicate<T>
}
