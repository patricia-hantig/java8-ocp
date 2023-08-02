package com.patri.java.ocp._7_concurrency._5_parallel_streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//stateful lambda expression = one whose results depends on the state that might change during the execution of a pipeline
//stateless lambda expression = one whose results doesn't depend on the state that might change during the execution of a pipeline

//stateful lambda expressions should be avoided in PARALLEL streams
public class AvoidingStatefulOperations {
    public static void main(String[] args) {
        List<Integer> data = Collections.synchronizedList(new ArrayList<>());
        Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream().map(i -> {
            data.add(i);
            return i;                                       // AVOID STATEFUL LAMBDA EXPRESSIONS
        }).forEachOrdered(i -> System.out.print(i + " "));  // always will be: 1 2 3 4 5 6

        System.out.println();
        for (Integer e: data) {                             // a stateful lambda expression, which modifies the data list in parallel
            System.out.print(e + " ");                      // produces unpredictable results at runtime
        }

        System.out.println();
        Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream().map(i -> {
            data.add(i);
            return i;
        }).forEach(i -> System.out.print(i + " "));         //possible output: 4 6 5 3 1 2
                                                            // STATEFUL LAMBDA EXPRESSION

        // with a serial stream - output will always be: 1 2 3 4 5 6
        System.out.println();
        Arrays.asList(1, 2, 3, 4, 5, 6).stream().map(i -> {
            data.add(i);
            return i;
        }).forEach(i -> System.out.print(i + " "));

        // it's recommended to use CONCURRENT COLLECTION with PARALLEL streams
        List<Integer> list = new ArrayList<>();

        System.out.println();
        Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream().map(i -> {
            list.add(i);
            return i;
        }).forEach(i -> System.out.print(i + " "));
        // if we had used a regular ArrayList rather than a synchronized one, we could have seen output such as the following:
        // 4 6 5 2 3 1
        // null 2 4 5 6 1 - in an ArrayList obj the size it's increased periodic, if 2 threads trigger the array to increase the size
        // at the same time => one value can be lost ( ex 3 was lost)
        // two tasks executing at the same time == race condition
    }
}
