package com.patri.java.ocp._4_functional_programming._5_stream_primitives;

import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class OptionalWithPrimitiveStreams {
    public static void main(String[] args) {

        // when we learned about Optional we had an example with average
        // we can calculate the average much simple, using primitive streams and Optional for primitive streams: OptionalDouble
        IntStream stream = IntStream.rangeClosed(1, 10);
        OptionalDouble optional = stream.average(); // OptionalDouble = primitive stream | Optional<Double> = needed for Wrapper Double
        optional.ifPresent(System.out::println);
        System.out.println(optional.getAsDouble());
        System.out.println(optional.orElseGet(() -> Double.NaN));

        //■ instead of get() we have: getAsDouble(), getAsInt(), getAsLong()
        //■ orElseGet() method uses instead of Supplier: DoubleSupplier, IntSupplier, LongSupplier
        //■ max() and min() methods returns: OptionalDouble, OptionalInt, OptionalLong
        //■ sum() method returns: double, int, long
        //■ avg() method always returns: OptionalDouble

        LongStream longs = LongStream.of(5, 10);
        long sum = longs.sum();
        System.out.println(sum);

        DoubleStream doubles = DoubleStream.generate(() -> Math.PI);
        OptionalDouble min = doubles.limit(3).min();
        System.out.println(min.getAsDouble());
    }
}
