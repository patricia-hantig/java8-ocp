package com.patri.java.ocp._7_concurrency._5_parallel_streams;

import java.util.Arrays;
import java.util.stream.Stream;

public class ProcessingTasksInParallel {

    public static void main(String[] args) {
        // example of serial stream
        Stream<Integer> serialStream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
        serialStream.forEach(System.out::print);    // always will print "123456" (the results are ordered and predictable
        System.out.println();                       // because we are using a serial stream)


        // example of parallel stream
        Stream<Integer> parallelStream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
        parallelStream.parallel().forEach(System.out::print);   // possible result: "465123" (forEach() on parallel stream = submitting
        System.out.println();                                   //  multiple Runnable lambda expressions to a pooled thread executor)

        // ordering forEach() result for parallel streams
        // forEachOrdered() - forces a parallel stream to process the results in order at the cost of performance
        Stream<Integer> anotherParallelStream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
        anotherParallelStream.parallel().forEachOrdered(System.out::print);
        System.out.println();

        // why to use this method?
        // you can call this method in a section of your application that takes both serial and parallel streams and you need to be sure
        // that the results are processed in a particular order
    }
}
