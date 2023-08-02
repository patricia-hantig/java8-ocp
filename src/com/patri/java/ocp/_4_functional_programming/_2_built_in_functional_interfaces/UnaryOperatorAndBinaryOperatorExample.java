package com.patri.java.ocp._4_functional_programming._2_built_in_functional_interfaces;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

// Functional Interface             Param               Return Type                 Abstract method
//---------------------------------------------------------------------------------------------------
// UnaryOperator<T>                 1 (T)               T                           apply(T t)
// BinaryOperator<T>                2 (T, T)            T                           apply(T t)
public class UnaryOperatorAndBinaryOperatorExample {

    // UnaryOperator and BinaryOperator - are special cases of Function and BiFunction, all type params have to be the same type
    // UnaryOperator extends Function, BinaryOperator extends BiFunction

    public static void main(String[] args) {
        UnaryOperator<String> u1 = String::toUpperCase;
        UnaryOperator<String> u2 = x -> x.toUpperCase();
        System.out.println(u1.apply("Patri"));
        System.out.println(u2.apply("Simi"));

        BinaryOperator<String> b1 = String::concat;
        BinaryOperator<String> b2 = (x, y) -> x.concat(y);
        System.out.println(b1.apply("Patri", " and Simi"));
        System.out.println(b2.apply("perfect", " couple"));
    }
}
