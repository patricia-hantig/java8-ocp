package com.patri.java.ocp._2_design_patterns_and_principles._2_functional_programming;

import java.util.function.Predicate;

public class UsingPredicateInterface {
    // sometimes functional interfaces are replaced with Predicate interface
    // Predicate interface = special interface created by Java which does not have the type specified

    // example: we created a simple functional interface to test an Animal trait - CheckTrait.java
    // public interface CheckTrait {
    //      public boolean test(Animal animal);
    // }

    // we will need to create lots of interfaces like this to use lambdas -> we want to test animals, plants, string values etc.
    // Java recognizes this common problem and it provides an interface for us: Predicate
    // public interface Predicate<T> {
    //      public boolean test(T t);
    // }
    // Predicate.java it's in java.util.function package

    // it's similar to our method except it uses type T instead of Animal
    // ==> so we would not need our own functional interface and we can have something like this:

    private static void print(Animal animal, Predicate<Animal> trait) {
        if (trait.test(animal))
            System.out.println(animal);
    }

    public static void main(String[] args) {
        print(new Animal("fish", false, true), a -> a.canHop());
        print(new Animal("kangaroo", true, false), a -> a.canHop());
    }
}
