package com.patri.java.ocp._2_design_patterns_and_principles._2_functional_programming;

// example for understanding lambda syntax
public class LambdaSyntax {

    // ■ Lambda expression Rules:
    // a -> a.canHop()      ==      (Animal a) -> { return a.canHop(); }
    // syntax:  input parameter -> body that calls a method and returns the result of that method

    // - the input parameter is consumed by a functional interface whose abstract method has
    // the same number of parameters and compatible data types
    // - the body can be consumed by a functional interface whose abstract method returns a compatible data type

    // ■ Elements of lambda expression:
    //      ()  - required only if we have more than one input parameters (or zero) or data type is specified
    //      {}  - required when we have more than 2 statements
    //          - if present we need 'return' statement
    //      ;   - required
    //  return  - optional only when we have 'void', otherwise required

    // ex: the following are valid lambda expressions:
    // () -> new Duck();            - could be used by a functional interface containing a method that takes no arguments and returns a Duck object
    // d -> { return d.quack(); }   - can be used by a functional interface that takes a Duck as input and returns whatever the return type of quack() is
    // (Duck d) -> d.quack();       - can be used by a functional interface that takes a Duck as input and returns whatever the return type of quack() is
    // (Animal a, Duck d) -> d.quack()  - can be used by a functional interface that takes as input Animal and Duck objects and returns whatever the return type of quack() is
}
