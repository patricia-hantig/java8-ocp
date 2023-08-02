package com.patri.java.ocp._2_design_patterns_and_principles._2_functional_programming;

// example for implementing functional interfaces with lambdas:
public class FindMatchingAnimals {
    private static void print(Animal animal, CheckTrait trait) {
        if (trait.test(animal))
            System.out.println(animal);
    }

    public static void main(String[] args) {
        print(new Animal("fish", false, true), a -> a.canHop());
        print(new Animal("kangaroo", true, false), a -> a.canHop());

        // equivalent with the following code:

        Animal fish = new Animal("fish", false, true);
        Animal kangaroo = new Animal("kangaroo", true, false);
        CheckTrait lambda1 = a -> a.canHop();

        System.out.println();
        print(fish, lambda1);
        print(kangaroo, lambda1);
    }

    // lambda expression: a -> a.canHop();
    // meaning = Java should call a method with an Animal parameter that returns a boolean value that's the result of a.canHop()
    // we are passing this lambda as the second parameter of the print() method
    // print() expects a CheckTrait -> since we are passing a lambda instead - Java treats CheckTrait as a functional interface
    // and tries to map it to the single abstract method:
    // boolean test(Animal animal); - since this method takes an Animal -> the lambda param has to be an Animal
    //                              - since this method returns a boolean -> the lambda returns a boolean
}
