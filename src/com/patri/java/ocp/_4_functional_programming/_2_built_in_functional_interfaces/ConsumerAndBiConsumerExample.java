package com.patri.java.ocp._4_functional_programming._2_built_in_functional_interfaces;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

// Functional Interface             Param               Return Type                 Abstract method
//---------------------------------------------------------------------------------------------------
// Consumer<T>                      1 (T)               void                        accept(T t)
// BiConsumer<T, U>                 2 (T, U)            void                        accept(T t, U u)
public class ConsumerAndBiConsumerExample {
    // Consumer and BiConsumer - is used when you want to do something with 1 or 2 parameter/s but not return anything

    public static void main(String[] args) {
        // instance method reference and lambda expression
        Consumer<String> c1 = System.out::println;          //parameter type: String
        Consumer<String> c2 = x -> System.out.println(x);
        c1.accept("Patricia");
        c2.accept("Laura");

        // ex BiConsumer with different param types
        Map<String, Integer> map = new HashMap<>();
        BiConsumer<String, Integer> b1 = map::put;
        BiConsumer<String, Integer> b2 = (k, v) -> map.put(k, v);
        b1.accept("Chicken", 7);
        b2.accept("chick", 1);
        System.out.println(map);

        // ex BiConsumer with same param types
        Map<String, String> map2 = new HashMap<>();
        BiConsumer<String, String> b3 = map2::put;
        BiConsumer<String, String> b4 = (k, v) -> map2.put(k, v);
        b3.accept("chicken", "Cluck");
        b4.accept("chick", "Tweep");
        System.out.println(map2);
    }
}
