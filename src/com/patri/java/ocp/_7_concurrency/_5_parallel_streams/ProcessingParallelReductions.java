package com.patri.java.ocp._7_concurrency._5_parallel_streams;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//parallel reductions = reductions operations on parallel streams
//reductions = special type of operations where all the content of the stream is combined within a primitive/object
public class ProcessingParallelReductions {
    public static void main(String[] args) {

        //================================ORDER-BASED TASKS================================

        //the order is not guaranteed with parallel streams
        //methods like findAny() have unexpected results
        //using serial stream
        System.out.println(Arrays.asList(1, 2, 3, 4, 5, 6).stream().findAny().get()); // will always print: 1
        //using parallel stream - the JVM can create any number of threads to process the stream
        System.out.println(Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream().findAny().get()); //possible output: 4 (any value from stream)

        //the stream operations that are order-based (findFirst(), limit(), or skip()) - may perform more slowly in a parallel environment
        //BUT they will return always the same output (serial/parallel stream)
        System.out.println(Arrays.asList(1, 2, 3, 4, 5, 6).stream().skip(5).limit(2).findFirst().get());        // will always print: 6
        System.out.println(Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream().skip(5).limit(2).findFirst().get());// will always print: 6


        //================================COMBINING RESULTS WITH reduce()================================

        //reduce() - combines a stream within an object
        //reduce(identity parameter, accumulator parameter, combiner parameter)
        // <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner)

        // example string concat using reduce() with serial stream
        System.out.println(Arrays.asList('w', 'o', 'l', 'f')
                .stream()
                .reduce("", (c, s1) -> c + s1, (s2, s3) -> s2 + s3)); // always outputs: "wolf"
        // example string concat using reduce() with parallel stream (with associative accumulator)
        System.out.println(Arrays.asList('p', 'a', 't', 'r', 'i', 'c', 'i', 'a')
                .parallelStream()
                .reduce("", (c, s1) -> c + s1, (s2, s3) -> s2 + s3)); // applying the reduction to pairs of elements within the stream
                                                                    // always outputs: "patricia" - because reduce() uses the next principles

        //Principles for reduce arguments to follow the order we want EVEN if we use parallel streams:
        // 1. identity: identity must be defined such that for all elements in the stream u , combiner.apply(identity, u) == u
        // 2. accumulator: accumulator operator op must be associative and stateless => (a op b) op c == a op (b op c)
        // 3. combiner: combiner operator must also be associative and stateless => for all u and t combiner.apply(u,accumulator.apply(identity,t)) == accumulator.apply(u,t)

        // example parallel stream with non-associative accumulator (subtracting numbers is not an associative operation)
        System.out.println(Arrays.asList(1, 2, 3, 4, 5, 6)
                .parallelStream()
                .reduce(0, (a, b) -> (a - b))); // possible output: "3" , "-21"
                                                        // accumulator function violates the associativity property

        // example serial stream with non-associative accumulator => it doesn't matter if accumulator is non-associative
        System.out.println(Arrays.asList(1, 2, 3, 4, 5, 6)
                .stream()
                .reduce(0, (a, b) -> (a - b))); // always outputs: "-21"

        // example parallel stream with associative accumulator (addition numbers is an associative operation)
        System.out.println(Arrays.asList(1, 2, 3, 4, 5, 6)
                .parallelStream()
                .reduce(0, (a, b) -> (a + b))); // always outputs: "21"

        //example parallel stream with param identity that doesn't have an identity value
        System.out.println(Arrays.asList("w", "o", "l", "f")
                .parallelStream()
                .reduce("X", String::concat));  // possible output: "XwXoXlXf" - the identity is applied to multiple elements in the stream
                                                        // resulting in very unexpected data

        // RECOMMENDED to use reduce() with 3 params for parallel streams!!! (even though both reduce() with 1 param and 2 params
        // supports parallel streams)


        //================================COMBINING RESULTS WITH collect()================================

        // collect() - special type of reduction (mutual reduction = instead of identity we have supplier)
        // collect() with 3 params for parallel streams
        // collect(supplier parameter, accumulator parameter, combiner parameter) - accumulator and combiner MUST be associative and stateless

        // example collect(supplier, accumulator, combiner) on parallel stream
        Stream<String> stream = Stream.of("w", "o", "l", "f").parallel();
        SortedSet<String> set = stream.collect(ConcurrentSkipListSet::new, Set::add, Set::addAll);
        System.out.println(set);    // always outputs: "[f, l, o, w]" - elements from ConcurrentSkipListSet - are sorted according to
                                    // natural oder

        // collect() with 1 param for parallel streams ( collect(collector))
        Stream<String> stream2 = Stream.of("w", "o", "l", "f").parallel();
        Set<String> set2 = stream2.collect(Collectors.toSet());
        System.out.println(set2);   // [f, w, l, o]
        // using parallel reductions with a collector requires additional considerations:
        // 1. stream is parallel
        // 2. parameter of the collect() has the Collector.Characteristics.CONCURRENT characteristic
        // 3. either the stream is unordered or the collector has the characteristic Collector.Characteristics.UNORDERED

        // every class that implements Collector - has method characteristics() -> returns a set of available attributes for the collector
        // Collectors.toSet() does have the UNORDERED characteristic, but it does not have the CONCURRENT characteristic => the example
        // above will not be performed as concurrent reduction

        // class Collectors has 2 methods that are both UNORDERED and CONCURRENT: toConcurrentMap() and groupingByConcurrent()
        // example for parallel stream and parallel reduction using toConcurrentMap()
        Stream<String> one = Stream.of("lions", "tigers", "bears").parallel();
        ConcurrentMap<Integer, String> mapOne = one.collect(Collectors.toConcurrentMap(String::length, k -> k, (s1, s2) -> s1 + ", " + s2));
        System.out.println(mapOne);                 // {5=bears, lions, 6=tigers}
        System.out.println(mapOne.getClass());      // class java.util.concurrent.ConcurrentHashMap

        // example for parallel stream and parallel reduction using groupingByConcurrent()
        Stream<String> two = Stream.of("lions", "tigers", "bears").parallel();
        ConcurrentMap<Integer, List<String>> mapTwo = two.collect(Collectors.groupingByConcurrent(String::length));
        System.out.println(mapTwo);                 // {5=[lions, bears], 6=[tigers]}
        System.out.println(mapTwo.getClass());      // class java.util.concurrent.ConcurrentHashMap

        //recommended parallel processing
        // a stream will perform reductions in a parallel (as opposed to single-threaded) is often difficult in practice
        // alternatively is OK that JVM uses parallel structures
        // for ex: use a groupingByConcurrent on a collector on a parallel stream rather than using groupBy() on a collector => better
        // performance at runtime


    }
}
