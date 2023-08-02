package com.patri.java.ocp._4_functional_programming._4_streams;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TerminalOperations {
    public static void main(String[] args) {


        // terminal operations can be performed without intermediate operations, but not the other way around
        // Reductions = special type of terminal operation where all of the contents of the stream are combined into a single primitive or Object

        // Table with all terminal stream operations (alphabetically):
        //                 Method                   |       Infinite Streams        |    Return Value       |     Reduction
        // -----------------------------------------------------------------------------------------------------------------
        // allMatch(), anyMatch(), noneMatch()      |       Sometimes terminates    |       boolean         |       NO
        //              collect()                   |       Does not terminate      |       Varies          |       YES - mutual reduction
        //              count()                     |       Does not terminate      |       long            |       YES
        //       findAny(), findFirst()             |           Terminates          |       Optional<T>     |       NO
        //              forEach()                   |       Does not terminate      |       void            |       NO
        //            min(), max()                  |       Does not terminate      |       Optional<T>     |       YES
        //              reduce()                    |       Does not terminate      |       Varies          |       YES


        // ■ count()  -> method signature: long count()
        // determines the number of elements in a finite stream
        // for infinite streams -> does not terminate
        // reduction - because it looks at each element in the stream and returns a single value
        Stream<String> stream = Stream.of("monkey", "gorilla", "bonobo");
        System.out.println(stream.count());


        // ■ min() and max()  -> method signatures: Optional<T> min(<? super T> comparator) & Optional<T> max(<? super T> comparator)
        // allow you to pass a custom comparator and find the smallest or largest value in a finite stream according to that sort order
        // for infinite streams -> does not terminate
        // reduction - because it looks at each element in the stream and returns a single value
        // ex finds the animal with the fewest letters in its name
        Stream<String> stream1 = Stream.of("monkey", "ape", "bonobo");
        Optional<String> min = stream1.min((s1, s2) -> s1.length() - s2.length());
        min.ifPresent(System.out::println);
        // we use the Optional method because the method can return that the min/max was not found
        Optional<?> minEmpty = Stream.empty().min((s1, s2) -> 0);
        System.out.println(minEmpty.isPresent()); // the stream is empty -the comparator is never called and we have no value in the Optional


        // ■ findAny() and findFirst()  -> method signature: Optional<T> findAny() & Optional<T> findFirst()
        // returns an element of the stream if the stream is not empty
        // if stream is empty -> returns empty Optional
        // it works with infinite streams
        // findAny() - works with parallel streams
        // are NOT reductions - because they return something without processing all elements -> do not reduce the entire stream into one value
        Stream<String> stream2 = Stream.of("monkey", "ape", "bonobo");
        Stream<String> infinite = Stream.generate(() -> "chimp");
        stream2.findAny().ifPresent(System.out::println);
        infinite.findAny().ifPresent(System.out::println);
        //same for findFirst() - please comment lines above and uncomment lines below
        //stream2.findFirst().ifPresent(System.out::println);
        //infinite.findFirst().ifPresent(System.out::println);


        // ■ allMatch(), anyMatch() and noneMatch()  -> method signature: boolean anyMatch/allMatch/noneMatch(Predicate<? super T> predicate)
        // search a stream and return information about how the stream belongs to the predicate
        // for infinite streams: only sometimes terminates
        // are NOT reductions - because they return something without processing all elements -> do not reduce the entire stream into one value
        // ex checks whether animal names begin with letters
        List<String> list = Arrays.asList("monkey", "2", "chimp");
        Stream<String> infiniteStream = Stream.generate(() -> "chimp");
        Predicate<String> predicate = x -> Character.isLetter(x.charAt(0)); // checks if first character is a letter
        System.out.println(list.stream().anyMatch(predicate));
        System.out.println(list.stream().allMatch(predicate));
        System.out.println(list.stream().noneMatch(predicate));
        System.out.println(infiniteStream.anyMatch(predicate));
        //System.out.println(infiniteStream.allMatch(predicate)); - this doesn't end until kill
        //System.out.println(infiniteStream.noneMatch(predicate)); - this doesn't end until kill
        // we can reuse the same predicate, but we need a different stream each time


        // ■ forEach()  -> method signature: void forEach(Consumer<? super T> action)
        // looping construct
        // for infinite streams -> does not terminate
        // are NOT reductions - because we don't have a return type
        // ex printing elements of a stream
        Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
        s.forEach(System.out::print);       // System.out::print = consumer
        // you can call forEach() directly on a Collection or on a Stream
        // you can’t use a traditional 'for loop' on a stream -> why? - because it DOES NOT implement Iterable interface
        Stream s2 = Stream.of(1);
        //for (Integer i : s2);   // DOES NOT COMPILE! -> foreach not applicable to type 'java.util.stream.Stream'


        // ■ reduce()   -> method signature: 3 options:
        //                                      T reduce(T identity, BinaryOperator<T> accumulator)
        //                                      Optional<T> reduce(BinaryOperator<T> accumulator)
        //                                      <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner)
        // combines a stream into an object
        // as it's name is a reduction
        // for infinite streams -> does not terminate

        // method 1:  T reduce(T identity, BinaryOperator<T> accumulator)
        // the most common way of doing a reduction is to start with an initial value and keep merging it with the next value
        // ex concatenate an array of String s into a single String without functional programming
        String[] array = new String[] { "w", "o", "l", "f" };
        String result = "";
        for (String st: array)
            result = result + st;
        System.out.println();
        System.out.println(result);
        // ex concatenate an array of String s into a single String with functional programming
        Stream<String> stream3 = Stream.of("w", "o", "l", "f");
        String word = stream3.reduce("", (s1, c) -> s1 + c); // identity = the initial value of an empty String
                                        // accumulator (binary operator) = concatenate the String s1 to get the next value
        System.out.println(word);
        // ex concatenate an array of String s into a single String with functional programming and method reference
        Stream<String> stream4 = Stream.of("w", "o", "l", "f");
        String word2 = stream4.reduce("", String::concat); // identity = the initial value of an empty String
                                        // accumulator (binary operator) = string concatenation done with method reference
        System.out.println(word2);
        // ex for multiply all of the Integer objects in a stream
        Stream<Integer> stream5 = Stream.of(2, 3, 4);
        Integer result2 = stream5.reduce(1, (a, b) -> a * b); // binary op is the multiplication
        System.out.println(result2);

        // method 2:  Optional<T> reduce(BinaryOperator<T> accumulator)
        // in some cases identity isn't really necessary
        // if you don’t specify an identity, an Optional is returned because there might not be any data
        // There are three choices for what is in the Optional:
        //■■ If the stream is empty, an empty Optional is returned.
        //■■ If the stream has one element, it is returned.
        //■■ If the stream has multiple elements, the accumulator is applied to combine them.
        BinaryOperator<Integer> operator = (a, b) -> a * b;
        Stream<Integer> empty = Stream.empty();
        Stream<Integer> oneElement = Stream.of(3);
        Stream<Integer> manyElements = Stream.of(3, 5, 6);
        empty.reduce(operator).ifPresent(System.out::println);
        oneElement.reduce(operator).ifPresent(System.out::println);
        manyElements.reduce(operator).ifPresent(System.out::println); //it allows Java to create intermediate reductions and then combine them at the end

        // method 3:  <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner)
        Stream<Integer> manyElements2 = Stream.of(3, 5, 6);
        System.out.println(manyElements2.reduce(1, operator, operator)); // identity = 1, accumulator = operator, combiner = operator


        // ■ collect()      -> method signature:  <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner)
                                                //<R,A> R collect(Collector<? super T, A,R> collector)
        // is a special type of reduction called a mutable reduction - uses same mutable object while accumulating
        // common mutable objects include StringBuilder and ArrayList
        // mutable reduction is more efficient than reduction
        // this method lets us get data out of streams and into another form
        // for infinite streams -> does not terminate

        // method 1: <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner)
        // is used when we want to code - shows how collecting should work
        // wolf example from reduce() can be converted to use collect()
        Stream<String> stream6 = Stream.of("w", "o", "l", "f");
        StringBuilder word3 = stream6.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
        System.out.println("collect() result: "+ word3);
        // The first parameter: supplier = a Supplier that creates the object that will store the results as we collect data
        // a Supplier doesn’t take any parameters and returns a value
        // in this case, it constructs a new StringBuilder
        // The second parameter: accumulator = a BiConsumer, which takes two parameters and doesn’t return anything
        // it adds one more element to the data collection; here it appends the next String to the StringBuilder
        // The final parameter: combiner = another BiConsumer
        // it is responsible for taking two data collections and merging them (useful when we are processing in parallel)
        // this would work with StringBuilder only if we didn’t care about the order of the letters
        // In this case, the accumulator and combiner have similar logic.

        // ex2 where accumulator and combiner are different
        Stream<String> stream7 = Stream.of("w", "o", "l", "f");
        TreeSet<String> set = stream7.collect(TreeSet::new, TreeSet::add, TreeSet::addAll);
        System.out.println(set); // we use a TreeSet if we need the set to be sorted
        // Supplier: creates a new empty TreeSet obj
        // Accumulator: adds a single String from the Stream to the TreeSet
        // Combiner: adds all of the elements of one TreeSet to another in case the operations were done in parallel and need to be merged

        // method 2: <R,A> R collect(Collector<? super T, A,R> collector)
        Stream<String> stream8 = Stream.of("w", "o", "l", "f");
        TreeSet<String> set1 = stream8.collect(Collectors.toCollection(TreeSet::new));
        System.out.println(set1); // we use a TreeSet if we need the set to be sorted
        // collector: Collectors.toCollection(TreeSet::new)

        Stream<String> stream9 = Stream.of("w", "o", "l", "f");
        Set<String> set2 = stream9.collect(Collectors.toSet());
        System.out.println(set2); // You might get different output for this last one since toSet() makes no guarantees
        // as to which implementation of Set you’ll get. It is likely to be a HashSet , but you shouldn’t expect or rely on that
        // collector: Collectors.toSet()
    }
}
