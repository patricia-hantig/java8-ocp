package com.patri.java.ocp._4_functional_programming._4_streams;

import java.util.*;
import java.util.stream.Stream;

public class IntermediateOperations {
    public static void main(String[] args) {

        // Intermediate Operations always return a STREAM
        // they deal with infinite streams -> because they return an infinite stream

        // ■ filter()  -> method signature: Stream<T> filter(Predicate<? super T> predicate)
        // returns a Stream with elements that match a given expression
        // ex: filter all elements that begin with the letter m
        Stream<String> stream = Stream.of("monkey", "gorilla", "bonobo");
        stream.filter(x -> x.startsWith("m")).forEach(System.out::print);
        System.out.println();


        // ■ distinct()  -> method signature: Stream<T> distinct()
        // returns a stream with duplicate values removed
        // Java uses equals() to determine whether the objects are the same
        Stream<String> stream1 = Stream.of("duck", "duck", "duck", "goose");
        stream1.distinct().forEach(System.out::print);
        System.out.println();


        // ■ limit() & skip()  -> method signature: Stream<T> limit(int maxSize)
                                                //  Stream<T> skip(int n)
        // returns a smaller stream or could make a finite stream out of an infinite stream
        // ex for making a finite stream out of an infinite one
        Stream<Integer> stream2 = Stream.iterate(1, n -> n + 1);
        stream2.skip(5).limit(2).forEach(System.out::print);
        System.out.println();


        // ■ map()  -> method signature: <R> Stream<R> map(Function<? super T, ? extends R> mapper)
        // creates a one-to-one mapping from the elements in the stream to the elements of the next step in the stream
        // it uses the lambda expression to figure out the type passed to that function and the one returned
        // the return type is the stream that gets returned
        // this method is used for transforming data
        // ex : converting a list of String objects to a list of Integers representing their lengths
        Stream<String> stream3 = Stream.of("monkey", "gorilla", "bonobo");
        stream3.map(String::length).forEach(System.out::print); // String::length is the mapper(function) = x -> x.length


        // ■ flatMap()  -> method signature: <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)
        // takes each element in the stream and makes any elements it contains top-level elements in a single stream
        // is helpful when you want to remove empty elements from a stream or you want to combine a stream of lists
        // the method signature says that it returns a Stream of the type that the function contains at a lower level
        // conclusion: all elements are listed to be at the same top-level in the stream
        // ex get all of the animals into the same level and getting rid of the empty list
        List<String> zero = Arrays.asList();
        List<String> one = Arrays.asList("Bonobo");
        List<String> two = Arrays.asList("Mama Gorilla", "Baby Gorilla");
        Stream<List<String>> animals = Stream.of(one, two, zero);
        animals.flatMap(l -> l.stream()).forEach(System.out::println); // l -> l.stream() == Collection::stream
        // (to replace with method reference put the mouse over the lambda expr and Alt+Shift+Enter)
        // it removed the empty list completely and changed all elements of each list to be at the top level of the stream


        // ■ sorted()  -> method signature: Stream<T> sorted()
                                        //  Stream<T> sorted(Comparator<? super T> comparator)
        // returns a stream with the elements sorted (uses natural ordering unless we specify a comparator)
        // ex with signature: Stream<T> sorted()
        Stream<String> stream4 = Stream.of("brown-", "bear-");
        stream4.sorted().forEach(System.out::print);
        System.out.println();
        // ex with signature: Stream<T> sorted(Comparator<? super T> comparator)
        Stream<String> stream5 = Stream.of("brown bear-", "grizzly-");
        stream5.sorted(Comparator.reverseOrder()).forEach(System.out::print);
        System.out.println();
        //stream5.sorted(Comparator::reverseOrder) // DOES NOT COMPILE
        // Comparator is a functional interface -> we can use method references or lambdas to implement it
        // The Comparator interface implements one method that takes two String parameters and returns an int
        // Comparator::reverseOrder doesn’t do that -> It is a reference to a function that takes zero parameters and returns a Comparator
        // This is not compatible with the interface -> This means that we have to use a method and not a method reference


        // ■ peek()  -> method signature: Stream<T> peek(Consumer<? super T> action)
        // is useful for debugging because it allows us to perform a stream operation without actually changing the stream
        // The most common use for peek() is to output the contents of the stream as it goes by
        // ex: we made a typo and counted bears beginning with the letter g instead of b
        // we don't know why the count is 1 instead of 2 -> We can add a peek() to find out why
        Stream<String> stream6 = Stream.of("black bear", "brown bear", "grizzly");
        long count = stream6.filter(s->s.startsWith("g")).peek(System.out::println).count();
        System.out.println(count);
        // Danger: Changing State with peek()
        // ex
        // stream pipeline that doesn’t use peek()
        List<Integer> numbers = new ArrayList<>();
        List<Character> letters = new ArrayList<>();
        numbers.add(1);
        letters.add('a');
        Stream<List<?>> stream7 = Stream.of(numbers, letters);
        stream7.map(List::size).forEach(System.out::print); // 11
        System.out.println();
        //add a proper peek() operation
        StringBuilder builder = new StringBuilder();
        Stream<List<?>> good = Stream.of(numbers, letters);
        good.peek(l -> builder.append(l)).map(List::size).forEach(System.out::print); // 11
        System.out.println(builder); // [1][a] peek() updates a StringBuilder variable that doesn’t affect the result of the stream pipeline
        // ex for bad peek() operation
        Stream<List<?>> bad = Stream.of(numbers, letters);
        bad.peek(l -> l.remove(0)).map(List::size).forEach(System.out::print); // 00 peek() is modifying the data structure that is used in the stream


    }
}
