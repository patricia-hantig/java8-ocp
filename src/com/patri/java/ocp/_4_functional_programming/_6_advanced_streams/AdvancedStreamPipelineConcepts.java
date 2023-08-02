package com.patri.java.ocp._4_functional_programming._6_advanced_streams;

import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class AdvancedStreamPipelineConcepts {
    public static void main(String[] args) {

        // ■■■ Linking streams to the underlying data
        // ex: if we have a list with 3 elements and create a stream from that list -> the stream will have 3 elements
        List<String> cats = new ArrayList<>();  // we create the list
        cats.add("Annie");
        cats.add("Ripley");                     // here the list has 2 elements
        Stream<String> stream = cats.stream();  // we request to create the stream from the list
                                                // !!! streams are lazily evaluated - the stream is not actually created here
        cats.add("KC");                         // we add a new elem to the stream
        System.out.println(stream.count());     // here actually runs the stream pipeline (looking at the source and seeing 3 elements)

        // ■■■ Chaining Optionals
        // ex
        Optional optional = Optional.empty();
        threeDigit(optional);   // outer if - returns false
        threeDigit2(optional);  // part 3 - returns false

        Optional optional1 = Optional.of(4);
        threeDigit(optional1);  // inner if - returns false
        threeDigit2(optional1); // part 2 - returns false

        Optional optional2 = Optional.of(123);
        threeDigit(optional2);  // both if - return true
        threeDigit2(optional2); // all parts - return true

        // ex2: we want to get an Optional<Integer> representing the length of the String contained in another Optional
        Optional<String> optional3 = Optional.of("3455778");
        Optional<Integer> result = optional3.map(String::length);
        // with lambda
        //Optional<Integer> result = optional3.map((String t) -> t.length());
        result.ifPresent(System.out::println);

        // ex 3: difference between map() and flatMap()
        Optional<String> optional4 = Optional.of("345577887679");
        //!!! Optional<Integer> result2 = optional4.map(AdvancedStreamPipelineConcepts::calculator); //DOES NOT COMPILE
        // The problem is that calculator() returns Optional<Integer>
        // The map() method adds another Optional, giving us Optional<Optional<Integer>>
        // solution is to call flatMap() instead:
        Optional<Integer> result2 = optional4.flatMap(AdvancedStreamPipelineConcepts::calculator);
        System.out.println(result2);
        // flat() removes the unnecessary layer => Chaining calls to flatMap() is useful when you want to transform one Optional type to another

        // ■■■ Checked Exceptions and Functional Interfaces
        // ex:
        //getACheckedException();
        // uncomment the line above to get the checked exception


        // ■■■ Collecting results

        // ■ Collecting using Basic Collectors
        // many of the collectors work in the same way
        // ex for joining()
        //                  Collector                                       Description                         Return value when passed to collect()
        // --------------------------------------------------------------------------------------------------------------------------------------------
        // joining()                                                creates a single String using cs as a               String
        // joining(CharSequence cs)                                 delimiter between elements if one is specified
        Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
        String resultNow = ohMy.collect(Collectors.joining(", "));
        System.out.println(resultNow);
        // All elements of the stream are merged into a String with the specified delimiter between each element
        // It is very important to pass the Collector to the collect method -> it exists to help collect elements
        // A Collector doesn’t do anything on its own

        // ex2: What is the average length of the three animal names? ex for averagingInt()
        //                  Collector                                       Description                         Return value when passed to collect()
        // --------------------------------------------------------------------------------------------------------------------------------------------
        // averagingDouble(ToDoubleFunction f)                      calculates the average
        // averagingInt(ToIntFunction f)                            for our 3 core                                      Double
        // averagingLong(ToLongFunction f)                          primitive types
        Stream<String> ohMy2 = Stream.of("lions", "tigers", "bears");
        Double resultNow2 = ohMy2.collect(Collectors.averagingInt(String::length));
        System.out.println(resultNow2);
        // We pass a collector to collect() and it performs the average for us

        // ex3: for toCollection()
        //                  Collector                                       Description                         Return value when passed to collect()
        // --------------------------------------------------------------------------------------------------------------------------------------------
        // toCollection(Supplier s)                                 creates a Collection of the specified type          Collection
        Stream<String> ohMy3 = Stream.of("lions", "tigers", "bears");
        TreeSet<String> resultNow3 = ohMy3.filter(s -> s.startsWith("t"))
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println(resultNow3);
        // This time we have all three parts of the stream pipeline:
        // source for the stream: Stream.of()
        // intermediate operation: filter()
        // terminal operation: collect() - which creates a TreeSet

        // ex4: for counting()
        //                  Collector                                       Description                         Return value when passed to collect()
        // --------------------------------------------------------------------------------------------------------------------------------------------
        // counting()                                               counts nr of elements                               Long
        Stream<String> ohMy4 = Stream.of("lions", "tigers", "bears");
        Long numberOfElements = ohMy4.collect(Collectors.counting());
        System.out.println(numberOfElements);

        // ex4: for minBy()
        //                  Collector                                       Description                         Return value when passed to collect()
        // --------------------------------------------------------------------------------------------------------------------------------------------
        // maxBy(Comparator c)                                      finds the largest/smallest element                  Optional<T>
        // minBy(Comparator c)
        Stream<String> ohMy5 = Stream.of("aLotOfLions", "tigers", "bears");
        Optional<String> smallestElement = ohMy5.collect(minBy((s1, s2) -> s1.length() - s2.length()));
        smallestElement.ifPresent(System.out::println);


        // ex4: for summarizingInt()
        //                  Collector                                       Description                         Return value when passed to collect()
        // --------------------------------------------------------------------------------------------------------------------------------------------
        // summarizingDouble(ToDoubleFunction f)                    calculates averages, mins, maxs and so on           DoubleSummaryStatistics
        // summarizingInt(ToIntFunction f)                                                                              IntSummaryStatistics
        // summarizingLong(ToLongFunction f)                                                                            LongSummaryStatistics
        Stream<String> ohMy6 = Stream.of("lions", "tigers", "bears");
        IntSummaryStatistics statistics = ohMy6.collect(Collectors.summarizingInt(s -> s.length()));
        System.out.println(statistics);

        // ex4: for summingInt()
        //                  Collector                                       Description                         Return value when passed to collect()
        // --------------------------------------------------------------------------------------------------------------------------------------------
        // summingDouble(ToDoubleFunction f)                        calculates the sun for 3 core                       Double
        // summingInt(ToIntFunction f)                              primitive types                                     Int
        // summingLong(ToLongFunction f)                                                                                Long
        Stream<String> ohMy7 = Stream.of("lions", "tigers", "bears");
        int sumOfElements = ohMy7.collect(Collectors.summingInt(String::length));
        System.out.println(sumOfElements);

        // ex4: for toList()
        //                  Collector                                       Description                         Return value when passed to collect()
        // --------------------------------------------------------------------------------------------------------------------------------------------
        // toList()                                                 creates an arbitrary type of list or set            List
        // toSet()                                                                                                      Set
        Stream<String> ohMy8 = Stream.of("lions", "tigers", "bears");
        List<String> listOfElements = ohMy8.collect(Collectors.toList());
        System.out.println(listOfElements.toString());
        for (String element: listOfElements) {
            System.out.println(element);
        }


        // ■ Collecting into Maps
        // when we create a map from a stream, we have to specify 2 functions:
        // 1 function for keys - how to create the key
        // 1 function for values - how to create the value

        //                  Collector                                       Description                         Return value when passed to collect()
        // --------------------------------------------------------------------------------------------------------------------------------------------
        // toMap(Function k, Function v)                            creates a ma using functions to map the
        // toMap(Function k, Function v, BinaryOp m)                keys, values, an optional merge function            Map
        // toMap(Function k, Function v, BinaryOp m, Supplier s)    and an optional type

        // ex: create a map from a stream
        Stream<String> source = Stream.of("lions", "tigers", "bears");
        Map<String, Integer> map = source.collect(Collectors.toMap(s -> s, String::length));
        System.out.println(map);
        // function for keys: s -> s = we use the provided String as the key
        // function for values: String::length = we use the length of the String as the value
        // ! Returning the same value passed into a lambda is a common operation, so Java provides a method for it
        // s -> s = Function.identity() - you can choose which one you want

        // ex2: if we want to do the reverse and map the length of name to the name
        // try 1:
        /*Stream<String> source1 = Stream.of("lions", "tigers", "bears");
        Map<Integer, String> map1 = source1.collect(Collectors.toMap(String::length, k -> k));
        System.out.println(map1);*/
        // problem!
        // Exception in thread "main" java.lang.IllegalStateException: Duplicate key lions
        // Two of the animal names are the same length
        // Let’s suppose that our requirement is to create a comma-separated String with the animal names
        Stream<String> source3 = Stream.of("lions", "tigers", "bears");
        Map<Integer, String> map3 = source3.collect(Collectors.toMap(String::length, k -> k, (s1, s2) -> s1 + "," + s2 ));
        System.out.println(map3);
        System.out.println(map3.getClass());

        // ex 3: Suppose that we want to return a TreeMap instead => we change the type and add a constructor reference as a parameter
        Stream<String> source4 = Stream.of("lions", "tigers", "bears");
        TreeMap<Integer, String> map4 = source4.collect(Collectors.toMap(String::length, k -> k, (s1, s2) -> s1 + "," + s2, TreeMap::new));
        System.out.println(map4);
        System.out.println(map4.getClass());


        // ■ Collecting using Grouping, Partitioning and Mapping

        // Grouping - group using one criteria (ex: by length)
        //                  Collector                                       Description                         Return value when passed to collect()
        // --------------------------------------------------------------------------------------------------------------------------------------------
        // groupingBy(Function f)                                   creates a map grouping by the
        // groupingBy(Function f, Collector c)                      specified function with the optional                Map<K, List<T>>
        // groupingBy(Function f, Supplier s, Collector c)          type and optional downstream collector

        // ex grouping names by their length
        Stream<String> source5 = Stream.of("lions", "tigers", "bears");
        Map<Integer, List<String>> map5 = source5.collect(groupingBy(String::length));
        System.out.println(map5);
        // groupingBy() collector tells collect() that it should group all of the elements of the stream into lists, organizing them by the function provided
        // Suppose that we don’t want a List as the value in the map and prefer a Set instead
        // we pass a downstream collector  = is a second collector that does something special with the values
        Stream<String> source6 = Stream.of("lions", "tigers", "bears");
        Map<Integer, Set<String>> map6 = source6.collect(groupingBy(String::length, Collectors.toSet()));
        System.out.println(map6);
        // We can even change the type of Map returned through yet another parameter
        Stream<String> source7 = Stream.of("lions", "tigers", "bears");
        TreeMap<Integer, Set<String>> map7 = source7.collect(groupingBy(String::length, TreeMap::new, Collectors.toSet()));
        System.out.println(map7);
        System.out.println(map7.getClass());
        // What if we want to change the type of Map returned but leave the type of values alone as a List?
        Stream<String> source8 = Stream.of("lions", "tigers", "bears");
        TreeMap<Integer, List<String>> map8 = source8.collect(groupingBy(String::length, TreeMap::new, Collectors.toList()));
        System.out.println(map8);

        // Partitioning - special case of grouping -> we have only 2 groups: true and false
        // partitioning = splitting a list into 2 parts
        //                  Collector                                       Description                         Return value when passed to collect()
        // --------------------------------------------------------------------------------------------------------------------------------------------
        // partitioningBy(Predicate p)                              creates a map grouping by the specified predicate
        // partitioningBy(Predicate p, Collector c)                 with the optional further downstream collector      Map<Boolean, List<T>>

        // ex:
        Stream<String> newSource = Stream.of("lions", "tigers", "bears");
        Map<Boolean, List<String>> newMap = newSource.collect(Collectors.partitioningBy(s -> s.length() <= 5));
        System.out.println(newMap);
        // s -> s.length() <= 5 : Predicate
        Stream<String> newSource1 = Stream.of("lions", "tigers", "bears");
        Map<Boolean, List<String>> newMap1 = newSource1.collect(Collectors.partitioningBy(s -> s.length() <= 7));
        System.out.println(newMap1);
        // we can change the type List to something else
        Stream<String> newSource2 = Stream.of("lions", "tigers", "bears");
        Map<Boolean, Set<String>> newMap2 = newSource2.collect(Collectors.partitioningBy(s -> s.length() <= 7, Collectors.toSet()));
        System.out.println(newMap2);
        // we can't change the type of Map => but because we have only 2 keys in the map it doesn't matter which type of map is
        // instead of using the downstream collector to specify the type, we can use any of the collectors that we’ve already shown
        // ex: we can group by the length of the animal name to see how many of each length we have
        Stream<String> newSource3 = Stream.of("lions", "tigers", "bears");
        Map<Integer, Long> newMap3 = newSource3.collect(groupingBy(String::length, Collectors.counting()));
        System.out.println(newMap3);

        // Mapping - ets us go down a level and add another collector
        //                  Collector                                       Description                         Return value when passed to collect()
        // --------------------------------------------------------------------------------------------------------------------------------------------
        // mapping(Function fc Collector c)                         adds another level of collectors                    Collector

        // ex: we wanted to get the first letter of the first animal alphabetically of each length
        Stream<String> test = Stream.of("lions", "tigers", "bears");
        Map<Integer, Optional<Character>> testMap = test.collect(
                Collectors.groupingBy(
                        String::length,
                        Collectors.mapping(s -> s.charAt(0),
                                Collectors.minBy(Comparator.<Character>naturalOrder()))));
        System.out.println(testMap);

        Stream<String> test2 = Stream.of("lions", "tigers", "bears");
        Map<Integer, Optional<Character>> mapTest2 = test2.collect(
                groupingBy( String::length, mapping(s -> s.charAt(0),
                                minBy(Comparator.<Character>naturalOrder()))));
        System.out.println(mapTest2);
    }

    private static void getACheckedException() {
        try {
            AdvancedStreamPipelineConcepts.create().stream().count(); // works

            //Supplier<List<String>> s = AdvancedStreamPipelineConcepts::create; //DOES NOT COMPILE!!
            // Unhandled exception: java.io.IOException
            // The PROBLEM = the lambda to which this method reference expands does declare an exception
                            // the supplier does not allow checked exception
            // SOLUTION:
            // sol 1: catch the exception and turn it into an unchecked exception
            Supplier<List<String>> s = () -> {
                try {
                    return AdvancedStreamPipelineConcepts.create();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            };
            // sol 2: Another alternative is to create a wrapper method with the try/catch
            // Now we can use the safe wrapper in our Supplier without issue
            Supplier<List<String>> s2 = AdvancedStreamPipelineConcepts::createSafe;
            // Conclusion: to use checked exceptions and Functional Interfaces we have to catch the exception and transform it into an unchecked exception
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ■■■ Chaining Optionals
    // ex: Suppose that you are given an Optional<Integer> and you have to print the value if it is a three-digit number
    // without functional programming:
    private static void threeDigit(Optional<Integer> optional) {
        if (optional.isPresent()) {         // outer if
            Integer num = optional.get();
            String string = "" + num;
            if (string.length() == 3)       // inner if
                System.out.println(string);
        }
    }
    // with functional programming:
    private static void threeDigit2(Optional<Integer> optional) {
        optional.map(n -> "" + n)                   // part 1
                .filter(s -> s.length() == 3)       // part 2
                .ifPresent(System.out::println);    // part3
    }

    // ex 3: What if we had a helper method that did the logic of calculating something for us and it had the signature
    static Optional<Integer> calculator(String s) {
        return Optional.of(s.length());
    }

    // ■■■ Checked Exceptions and Functional Interfaces
    // functional interfaces does not declare checked exceptions => this is OK
    // we have a problem when working with methods that declare checked exceptions
    // ex we have a method that throws a checked exception
    private static List<String> create() throws IOException {
        throw new IOException();
    }

    // for ex:
    // sol 2: Another alternative is to create a wrapper method with the try/catch
    private static List<String> createSafe() {
        try {
            return AdvancedStreamPipelineConcepts.create();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // ■■■ Collecting results
    // we use Collector to transform a Stream into a collection(String, list, map etc)

    // Examples of grouping/partitioning collectors:
    // ____________________________________________________________________________________________________________________________________________
    //                  Collector                                       Description                         Return value when passed to collect()
    // --------------------------------------------------------------------------------------------------------------------------------------------
    // averagingDouble(ToDoubleFunction f)                      calculates the average
    // averagingInt(ToIntFunction f)                            for our 3 core                                      Double
    // averagingLong(ToLongFunction f)                          primitive types
    // --------------------------------------------------------------------------------------------------------------------------------------------
    // counting()                                               counts nr of elements                               Long
    // --------------------------------------------------------------------------------------------------------------------------------------------
    // groupingBy(Function f)                                   creates a map grouping by the
    // groupingBy(Function f, Collector c)                      specified function with the optional                Map<K, List<T>>
    // groupingBy(Function f, Supplier s, Collector c)          type and optional downstream collector
    // --------------------------------------------------------------------------------------------------------------------------------------------
    // joining()                                                creates a single String using cs as a               String
    // joining(CharSequence cs)                                 delimiter between elements if one is specified
    // --------------------------------------------------------------------------------------------------------------------------------------------
    // maxBy(Comparator c)                                      finds the largest/smallest element                  Optional<T>
    // minBy(Comparator c)
    // --------------------------------------------------------------------------------------------------------------------------------------------
    // mapping(Function fc Collector c)                         adds another level of collectors                    Collector
    // --------------------------------------------------------------------------------------------------------------------------------------------
    // partitioningBy(Predicate p)                              creates a map grouping by the specified predicate
    // partitioningBy(Predicate p, Collector c)                 with the optional further downstream collector      Map<Boolean, List<T>>
    // --------------------------------------------------------------------------------------------------------------------------------------------
    // summarizingDouble(ToDoubleFunction f)                    calculates averages, mins, maxs and so on           DoubleSummaryStatistics
    // summarizingInt(ToIntFunction f)                                                                              IntSummaryStatistics
    // summarizingLong(ToLongFunction f)                                                                            LongSummaryStatistics
    // --------------------------------------------------------------------------------------------------------------------------------------------
    // summingDouble(ToDoubleFunction f)                        calculates the sun for 3 core                       Double
    // summingInt(ToIntFunction f)                              primitive types                                     Int
    // summingLong(ToLongFunction f)                                                                                Long
    // --------------------------------------------------------------------------------------------------------------------------------------------
    // toList()                                                 creates an arbitrary type of list or set            List
    // toSet()                                                                                                      Set
    // --------------------------------------------------------------------------------------------------------------------------------------------
    // toCollection(Supplier s)                                 creates a Collection of the specified type          Collection
    // --------------------------------------------------------------------------------------------------------------------------------------------
    // toMap(Function k, Function v)                            creates a ma using functions to map the
    // toMap(Function k, Function v, BinaryOp m)                keys, values, an optional merge function            Map
    // toMap(Function k, Function v, BinaryOp m, Supplier s)    and an optional type
    // ____________________________________________________________________________________________________________________________________________

}
