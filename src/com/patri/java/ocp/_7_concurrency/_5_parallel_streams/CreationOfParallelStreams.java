package com.patri.java.ocp._7_concurrency._5_parallel_streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

// One of the most powerful features of the Streams API is that it has built-in concurrency support

// serial stream = stream in which the results are ordered with only one entry processed at a time
// parallel stream = stream in which the results are concurrent processed using multi-threading

// using parallel streams can improve performance and changes results

// 2 ways to create parallel streams: from an existing serial stream, from a Java collection class
public class CreationOfParallelStreams {

    public static void main(String[] args) {
        //creating parallel stream from existing stream
        Stream<Integer> stream1 = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
        stream1.forEach(System.out::print); // 123456
        System.out.println();

        Stream<Integer> stream2 = Arrays.asList(1, 2, 3, 4, 5, 6).stream(); // we cannot use "stream1" because is already closed
                                                                            // (forEach() = terminal operation - will close the stream)
        Stream<Integer> parallelStream = stream2.parallel();  // "parallelStream" points to the same object as "stream2"
        // parallel() modifies the same stream object!!!!!!! => we don't have a new object there

        parallelStream.forEach(System.out::print); // random order of elements of stream "stream2"
        // the line below doesn't throw exception if you comment the line above (forEach() = terminal operation - will close the stream)
        //stream2.forEach(System.out::print); // parallel() = intermediate operation => will print the same as above
        System.out.println();

        // the check to see if streams are parallel is done with - isParallel() method
        System.out.println("Stream1 is parallel? " + stream1.isParallel());
        System.out.println("Stream2 is parallel? " + stream2.isParallel());
        System.out.println("ParallelStream is parallel? " + parallelStream.isParallel());
        System.out.println();

        //creating parallel stream from Java collection class
        Stream<Integer> parallelStream2 = Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream();
        parallelStream2.forEach(System.out::print);
        System.out.println();
        System.out.println("ParallelStream2 is parallel? " + parallelStream2.isParallel());
        System.out.println();

        // Some operations on streams preserve the "parallel()" attribute
        // concat() is parallel if either "serialStream" or "parallel" is parallel
        Stream<Integer> serialStream = Stream.of(1, 2, 3);
        Stream<Integer> parallel = Stream.of(1, 2, 3).parallel();
        Stream<Integer> concat = Stream.concat(serialStream, parallel);
        System.out.println("Concat is parallel? " + concat.isParallel());
        System.out.println();

        // Some operations on streams doesn't preserve the "parallel()" attribute
        // flatMap() creates a new Stream which is not parallel by default
        // even if it is made of parallel streams
        Stream<?> emptyStream = Stream.of().parallel();
        Stream<String> penguinStream = Stream.of("Penguin").parallel();
        Stream<String> gorillaGiraffeStream = Stream.of("Gorilla", "Giraffe").parallel();
        Stream<Stream<?>> animals = Stream.of(emptyStream, penguinStream, gorillaGiraffeStream);
        animals.flatMap(l -> l).forEach(System.out::println);
        System.out.println("Animals is parallel? " + animals.isParallel());
        System.out.println();

        Stream<List<?>> zoo = Stream.of(Arrays.asList(), Arrays.asList("Penguin"), Arrays.asList("Gorilla", "Giraffe"));
        zoo.flatMap(l -> l.stream().parallel()).forEach(System.out::println);
        System.out.println("Zoo is parallel? " + zoo.isParallel());
    }
}
