package com.patri.java.ocp._3_generics_and_collections._6_new_in_Java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

// new addition in Java 8: method references - makes the code more compact
public class MethodReferences {

    // method reference = reducing some of the code by simply mentioning the name of the method
    // ex: DuckHelper.java

    // When you can use method references?

    // For better understanding next info is necessary:
    // functional interfaces: Predicate, Consumer and Supplier
    // Predicate - takes a single parameter of any type and returns a boolean
    // Consumer - takes a single parameter of any type and has a void return type
    // Supplier - doesn't take any parameters and returns any type

    // ■ for static methods
    // ex:
    // method references
    Consumer<List<Integer>> methodReferences1 = Collections::sort; // we call a method with one parameter
            // and Java knows that it should create a lambda with one parameter and pass it to the method
    // lambda expression
    Consumer<List<Integer>> lambda1 = l -> Collections.sort(l);

    // ■ for calls of instance methods on a specific instance
    // ex:
    // method references
    String str = "abc";
    Predicate<String> methodReferences2 = str::startsWith;
    // lambda expression
    Predicate<String> lambda2 = s -> str.startsWith(s);

    // ■ for calls of instance methods on instance to be determined at runtime
    // ex:
    // method references
    Predicate<String> methodReferences3 = String::isEmpty; // it looks like a static method but it isn't and
            // Java uses the parameter supplied at runtime as the instance on which the method is called
    // lambda expression
    Predicate<String> lambda3 = s -> s.isEmpty();

    // ■ for constructors
    // ex:
    // method references
    Supplier<ArrayList> methodReferences4 = ArrayList::new;
    // lambda expression
    Supplier<ArrayList> lambda4 = () -> new ArrayList();
    // constructor reference = is a special type of method reference that uses 'new' instead of a method and it creates a new object

}
