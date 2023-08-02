package com.patri.java.ocp._4_functional_programming._5_stream_primitives;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CreatingPrimitiveStreams {
    public static void main(String[] args) {
        // We have three types of primitive streams:
        //■ IntStream: Used for int, short, byte, and char
        //■ LongStream: Used for long
        //■ DoubleStream: Used for double and float

        // Creating:
        //■ empty primitive streams
        DoubleStream empty = DoubleStream.empty();

        //■ we use of() for creating primitive streams with value/s
        DoubleStream oneValue = DoubleStream.of(3.14);
        DoubleStream varargs = DoubleStream.of(1.0, 1.1, 1.2);
        oneValue.forEach(System.out::println);
        varargs.forEach(System.out::println);
        System.out.println();

        //■ we use generate() and iterate for creating infinite primitive streams
        DoubleStream random = DoubleStream.generate(Math::random);
        DoubleStream fractions = DoubleStream.iterate(.5, d -> d / 2);
        //since the streams are infinite -> we added a limit intermediate operation so that the output doesn’t print values forever
        random.limit(3).forEach(System.out::println);
        fractions.limit(3).forEach(System.out::println);
        System.out.println();

        //■ for count we can use iterate() or range() or rangeClosed()
        IntStream count = IntStream.iterate(1, n -> n + 1);
        count.limit(5).forEach(System.out::print);
        System.out.println();
        IntStream range = IntStream.range(1, 6);
        range.forEach(System.out::print);
        System.out.println();
        IntStream rangeClosed = IntStream.rangeClosed(1, 5);
        rangeClosed.forEach(System.out::print);
        System.out.println();

        //■ we can create a primitive stream from another stream
        // Mapping methods between types of streams:
        // ______________________________________________________________________________________________________________________________
        // Source Stream Class   |   To create Stream   |   To create DoubleStream  |  To create IntStream  |    To create LongStream   |
        // ------------------------------------------------------------------------------------------------------------------------------
        //       Stream          |          map         |       mapToDouble         |       mapToInt        |           mapToLong       |
        //   DoubleStream        |          mapToObj    |           map             |       mapToInt        |           mapToLong       |
        //      IntStream        |          mapToObj    |       mapToDouble         |           map         |           mapToLong       |
        //     LongStream        |          mapToObj    |       mapToDouble         |       mapToInt        |               map         |
        // ______________________________________________________________________________________________________________________________

        // they have to be compatible types for this to work -> so Java requires a mapping function to be provided as a parameter
        Stream<String> objStream = Stream.of("penguin", "fish");
        IntStream intStream = objStream.mapToInt(s -> s.length());
        intStream.forEach(System.out::print);
        System.out.println();
        // This function that takes an Object(a String) -> The function returns an int: ToIntFunction

        // Function parameters when mapping between types of streams:
        // ______________________________________________________________________________________________________________________________
        // Source Stream Class   |   To create Stream   |   To create DoubleStream  |  To create IntStream  |    To create LongStream   |
        // ------------------------------------------------------------------------------------------------------------------------------
        //       Stream          |      Function        |       ToDoubleFunction    |   ToIntFunction       |       ToLongFunction      |
        //   DoubleStream        |      DoubleFunction  |   DoubleUnaryOperator     |   DoubleToIntFunction |     DoubleToLongFunction  |
        //      IntStream        |      IntFunction     |   IntToDoubleFunction     |   IntUnaryOperator    |       IntToLongFunction   |
        //     LongStream        |      LongFunction    |   LongToDoubleFunction    |   LongToIntFunction   |     LongUnaryOperator     |
        // ______________________________________________________________________________________________________________________________

        //■ we can also create a primitive stream from a regular stream using: flatMapToInt() / flatMapToDouble() / flatMapToLong()
        Stream<Integer> stream = Stream.of(1 ,2, 3);
        IntStream intStream1 = stream.flatMapToInt(x -> IntStream.of(x));
        intStream1.forEach(System.out::print);
    }
}
