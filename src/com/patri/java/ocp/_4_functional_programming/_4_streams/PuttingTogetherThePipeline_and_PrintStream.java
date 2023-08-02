package com.patri.java.ocp._4_functional_programming._4_streams;

import java.util.*;
import java.util.stream.Stream;

public class PuttingTogetherThePipeline_and_PrintStream {

    //Streams allow you to use chaining and express what you want to accomplish rather than how to do so

    public static void main(String[] args) {
        //ex: we wanted to get the first two names alphabetically that are four characters long
        // in java 7:
        List<String> list = Arrays.asList("Toby", "Anna", "Leroy", "Alex");
        List<String> filtered = new ArrayList<>();
        for (String name: list) {
            if (name.length() == 4) filtered.add(name);
        }
        Collections.sort(filtered);
        Iterator<String> iterator = filtered.iterator();
        if (iterator.hasNext()) System.out.println(iterator.next());
        if (iterator.hasNext()) System.out.println(iterator.next());

        // in java 8:
        List<String> list1 = Arrays.asList("Toby", "Anna", "Leroy", "Alex");
        list1.stream()
                .filter(n -> n.length() == 4)
                .sorted()
                .limit(2)
                .forEach(System.out::println);
        //                                        ___________________________________
        // how it works: pipeline:      stream -> | filter() -> sorted() -> limit() | -> forEach()
        // Data flow:
        //1. stream() sends Toby to filter(). filter() sees that the length is good and sends Toby
        //  to sorted(). sorted() can’t sort yet because it needs all of the data, so it holds Toby.
        //2. stream() sends Anna to filter(). filter() sees that the length is good and sends Anna
        //  to sorted(). sorted() can’t sort yet because it needs all of the data, so it holds Anna.
        //3. stream() sends Leroy to filter(). filter() sees that the length is not a match, and it
        //  takes Leroy out of the assembly line processing.
        //4. stream() sends Alex to filter(). filter() sees that the length is good and sends Alex
        //  to sorted(). sorted() can’t sort yet because it needs all of the data, so it holds Alex. It
        //  turns out sorted() does have all of the required data, but it doesn’t know it yet.
        //5. The foreman lets sorted() know that it is time to sort and the sort occurs.
        //6. sorted() sends Alex to limit(). limit() remembers that it has seen one element and
        //  sends Alex to forEach(), printing Alex.
        //7. sorted() sends Anna to limit(). limit() remembers that it has seen two elements
        //  and sends Anna to forEach(), printing Anna.
        //8. limit() has now seen all of the elements that are needed and tells the foreman. The
        //  foreman stops the line, and no more processing occurs in the pipeline.


        // ex 2
        /*Stream.generate(() -> "Elsa")
                .filter(n -> n.length() == 4)
                .sorted()    // hangs until you kill the program or it throws an exception after running out of memory
                             // sorted() has to wait until everything to sort is present
                .limit(2)
                .forEach(System.out::println); // Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
         */


        // ex 3
        Stream.generate(() -> "Elsa")
                .filter(n -> n.length() == 4)
                .limit(2)
                .sorted()
                .forEach(System.out::println);


        // ex 4
        /*Stream.generate(() -> "Olaf Lazisson")
                .filter(n -> n.length() == 4)
                .limit(2)
                .sorted()
                .forEach(System.out::println); // hangs until we kill the program -> The filter doesn’t allow anything,
                                                // so limit() never sees two elements
         */


        // Peeking behind the Scenes
        Stream<Integer> infinite = Stream.iterate(1, x -> x + 1);
        infinite.limit(5)
                .filter(x -> x % 2 == 1)
                .forEach(System.out::print);    // print: 135
        System.out.println();

        Stream<Integer> infinite2 = Stream.iterate(1, x -> x + 1);
        infinite2.limit(5)
                .peek(System.out::print)        // print: 12345
                .filter(x -> x % 2 == 1)
                .forEach(System.out::print);    // print: 135 => print final 11233455
        System.out.println();

        Stream<Integer> infinite3 = Stream.iterate(1, x -> x + 1);
        infinite3.filter(x -> x % 2 == 1)
                .limit(5)
                .forEach(System.out::print);    // print: 13579
        System.out.println();

        Stream<Integer> infinite4 = Stream.iterate(1, x -> x + 1);
        infinite4.filter(x -> x % 2 == 1)
                .peek(System.out::print)        // print: 13579
                .limit(5)
                .forEach(System.out::print);    // print: 13579 => print final 1133557799
        System.out.println();

        //==========================================================================================
        // Print stream - after we print a stream it can no longer be used!
        //                          Option                            Works for Infinite Streams?     Destructive?
        //--------------------------------------------------------------------------------------------------------------
        //  s.forEach(System.out::println);                                     NO                          YES
        //  System.out.println(s.collect(Collectors.toList()));                 NO                          YES
        //  s.peek(System.out::println).count();                                NO                          NO
        //  s.limit(5).forEach(System.out::println);                            YES                         YES
    }
}
