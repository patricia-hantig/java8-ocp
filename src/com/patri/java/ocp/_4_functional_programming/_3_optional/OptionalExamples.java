package com.patri.java.ocp._4_functional_programming._3_optional;

import java.util.Optional;

public class OptionalExamples {
    // Optional = new type added in Java 8
    // Optional = is like a box that might have something in it or might instead be empty

    // ex: average -> methods: Optional.empty(), Optional.of()

    public static Optional<Double> average(int... scores) {
        if (scores.length == 0)
            return Optional.empty();
        int sum = 0;
        for (int score : scores)
            sum += score;
        return Optional.of((double)sum/scores.length);
    }

    public static void main(String[] args) {
        // print the box
        System.out.println(average(90, 100));
        System.out.println(average());

        // print the value from the box -> methods: Optional.isPresent(), Optional.get()
        Optional<Double> optional = average(90, 100);
        if (optional.isPresent())
            System.out.println(optional.get());

        Optional<Double> optional2 = average();
        //System.out.println(optional2.get());    // BAD without checking => Exception in thread "main" java.util.NoSuchElementException:
                                                // No value present

        // When creating an Optional, it is common to want to use empty when the value is null -> method: Optional.ofNullable()
        Integer value = 10;
        Optional o = (value == null) ? Optional.empty() : Optional.of(value);
        System.out.println(o);
        Optional opt = Optional.ofNullable(value);
        System.out.println(opt);

        // Instead of using an if statement we can specify a Consumer to be run when there is a value inside the Optional
        // -> method: Optional.ifPresent(Consumer c)
        Optional<Double> op = average(90, 100);
        op.ifPresent(System.out::println);

        // We want something done if a value is present + if the value isn’t present
        // methods: Optional.orElse(), Optional.orElseGet(), Optional.orElseThrow()
        Optional<Double> optionalDouble = average();
        System.out.println(optionalDouble.orElse(Double.NaN));              // NaN = “not a number” value
        System.out.println(optionalDouble.orElseGet(() -> Math.random()));  // use a Supplier to generate a value at runtime
        //System.out.println(optionalDouble.orElseThrow(() -> new IllegalStateException()));  // use a Supplier to create an exception
                                                                                            // and throw it at runtime

        //System.out.println(optionalDouble.orElseGet(() -> new IllegalStateException())); // DOES NOT COMPILE
        // Bad return type in lambda expression: IllegalStateException cannot be converted to Double

        Optional<Double> opt2 = average(90, 100);
        System.out.println(opt2.orElse(Double.NaN));
        System.out.println(opt2.orElseGet(() -> Math.random()));
        System.out.println(opt2.orElseThrow(() -> new IllegalStateException())); //It prints out 95 three times (the value does exists)

        // Is Optional the Same as null?
        // Before Java 8, programmers would return null instead of Optional
        // null might be a special value
        // returning an Optional is a statement in the API that there might not be a value in there
        // advantage of Optional is that you can use a functional programming style with ifPresent() and the other methods rather than
        // needing an if statement
        // you can chain Optional calls
    }
}
