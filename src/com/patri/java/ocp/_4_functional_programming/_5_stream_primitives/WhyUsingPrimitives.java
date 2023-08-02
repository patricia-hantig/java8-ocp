package com.patri.java.ocp._4_functional_programming._5_stream_primitives;

import java.util.OptionalDouble;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WhyUsingPrimitives {
    // Until now we have been using wrapper classes when we needed primitives to go into streams

    public static void main(String[] args) {
        // ex: calculate the sum of numbers in a finite stream
        // with Wrapper
        Stream<Integer> stream = Stream.of(1, 2, 3);
        System.out.println(stream.reduce(0, (s, n) -> s + n)); //the reduction does the sum
        // with Primitive Stream
        Stream<Integer> stream1 = Stream.of(1, 2, 3);
        System.out.println(stream1.mapToInt(x -> x).sum()); // mapToInt - Stream<Integer> is converted to IntStream
                                                            // IntStream calculates the sum for us
        // The primitive streams know how to perform certain common operations automatically

        // ex2: what if we have to compute an average -> we need to divide the sum by the number of elements
        //The problem is that streams allow only one pass -> with Stream Primitives we have a method that calculates the average
        IntStream intStream = IntStream.of(1, 2, 3);
        OptionalDouble average = intStream.average();
        System.out.println(average.getAsDouble());
    }
}
