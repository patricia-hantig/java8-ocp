package com.patri.java.ocp._4_functional_programming._4_streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CreatingStreamSources {
    // Stream interface is located inside "java.util.streams" interface

    public static void main(String[] args) {

        // ex: creation of finite streams
        Stream<String> empty = Stream.empty();
        System.out.println(empty.count());

        Stream<Integer> oneElement = Stream.of(1);
        System.out.println(oneElement.count());

        Stream<Integer> fromArray = Stream.of(1, 2, 3);
        System.out.println(fromArray.count());

        // streams are new (only added in java8) so most code uses lists
        // convert from list to stream
        List<String> list = Arrays.asList("a", "b", "c");
        Stream<String> fromList = list.stream();
        Stream<String> fromListParallel = list.parallelStream(); // stream that is allowed to process elements in parallel
        System.out.println(fromList.count());

        // we can't have infinite lists
        // we can have infinite streams => Streams more powerful than Lists
        // ex of infinite stream
        //Stream<Double> randoms = Stream.generate(Math::random);
        //randoms.forEach(System.out::println);
        Stream<Integer> oddNumbers = Stream.iterate(1, n -> n + 2);
        Stream<Integer> oddNumbers2 = Stream.iterate(1, n -> n + 2).limit(200); // limit() -> infinite stream turn into finite
        oddNumbers2.forEach(System.out::println);
        System.out.println(oddNumbers2); // java.util.stream.SliceOps$1@568db2f2
    }

}
