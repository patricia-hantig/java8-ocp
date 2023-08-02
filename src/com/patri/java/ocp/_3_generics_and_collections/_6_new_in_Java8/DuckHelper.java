package com.patri.java.ocp._3_generics_and_collections._6_new_in_Java8;

import java.util.Comparator;

// example for method references
public class DuckHelper {

    public static int compareByWeight(Duck d1, Duck d2) {
        return d1.getWeight() - d2.getWeight();
    }

    public static int compareByName(Duck d1, Duck d2) {
        return d1.getName().compareTo(d2.getName());
    }

    // if we want to sort by weight -> we have the next Comparator:
    Comparator<Duck> byWeight = (d1, d2) -> DuckHelper.compareByWeight(d1, d2);

    // with Java 8 method references we can rewrite it like:
    Comparator<Duck> byWeight2 = DuckHelper::compareByWeight;
    // '::' -  tells Java to pass the parameters automatically into compareByWeight()

    // DuckHelper::compareByWeight - returns a functional interface and not an int
    // '::' - is like lambdas and it is used for deferred execution
    // Deferred execution = code is specified now but runs later - when the method that uses lambda expression is used!
}
