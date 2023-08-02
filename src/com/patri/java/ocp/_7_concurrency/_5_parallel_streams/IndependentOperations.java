package com.patri.java.ocp._7_concurrency._5_parallel_streams;

import java.util.Arrays;

//independent operation = results of one operation on one element of the stream doesn't affect the result of another element from stream
public class IndependentOperations {
    public static void main(String[] args) {
        Arrays.asList("jackal", "kangaroo", "lemur").parallelStream().map(s -> s.toUpperCase()).forEach(System.out::println);
        // mapping "jackal" to "JACKAL" can be done independently of mapping "kangaroo" to "KANGAROO" - order is not guaranteed
        Arrays.asList("jackal", "kangaroo", "lemur").parallelStream().map(s -> s.toUpperCase()).forEachOrdered(System.out::println);
        //using forEachOrdered - will display in alphabetical order
        System.out.println();

        Arrays.asList("jackal", "kangaroo", "lemur").parallelStream().map(s -> {
            System.out.println(s);
            return s.toUpperCase();
        }).forEach(System.out::println);
        // possible output: -> it's good to not use lambda expressions that could have side effects
        //jackal
        //lemur
        //kangaroo
        //LEMUR
        //JACKAL
        //KANGAROO

        //conclusion: parallel streams can process results independently although the order of the results cannot be determined
        //ahead of time
    }
}
