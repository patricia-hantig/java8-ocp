package com.patri.java.ocp._2_design_patterns_and_principles._2_functional_programming;

public class InvalidLambdas {

    // ■ Lambda expression Rules:
    // syntax:  input parameter -> body that calls a method and returns the result of that method

    // ■ Elements of lambda expression:
    //      ()  - required only if we have more than one input parameters (or zero) or data type is specified
    //      {}  - required when we have more than 2 statements
    //          - if present we need 'return' statement
    //      ;   - required
    //  return  - optional only when we have 'void', otherwise required

    // ■ Invalid lambdas:
    // Duck d -> d.quack()             // DOES NOT COMPILE!
    // a, d -> d.quack()               // DOES NOT COMPILE!
    // Animal a, Duck d -> d.quack()   // DOES NOT COMPILE!

    // why? each require parentheses: () - they can be omitted only if there is exactly one parameter and the data type is not specified

    // examples: all are valid
    // () -> true                                       // 0 params
    // a -> { return a.startsWith("test");}             // 1 param
    // (String a) -> a.startsWith("test")               // 1 param
    // (int x) -> {}                                    // 1 param
    // (int y) -> { return;}                            // 1 param
    // (a, b) -> a.startsWith("test")                   // 2 params
    // (String a, String b) -> a.startsWith("test")     // 2 params

    // ■ Invalid lambdas:
    // a, b -> a.startsWith("test")             // DOES NOT COMPILE! - needs '()' for parameter list - they are optional only when is only one and has no data type
    // c -> return 10;                          // DOES NOT COMPILE! - needs '{}' - we can't have 'return' without '{}'
    // a -> { return a.startsWith("test') }     // DOES NOT COMPILE! - needs ';'

    // ■ Rewritten lambdas to be valid:
    // (a, b) -> a.startsWith("test")
    // c -> {return 10; }
    // a -> { return a.startsWith("test'); }

    // * we don't have to mention the data type when we have only one parameter, but if
    // one parameter has data type => all parameters must provide data type

    // ■ Invalid lambdas for this reason:
    // (int y, z) -> { int x = 1; return y + 10; }      // DOES NOT COMPILE!
    // (String s, z) -> { return s.length() + z; }      // DOES NOT COMPILE!
    // (a, Animal b, c) -> a.getName()                  // DOES NOT COMPILE!

    // ■ Rewritten lambdas to be valid:
    // - we add or remove all data types so that these lambdas can compile
    // (y, z) -> { int x = 1; return y + 10; }
    // (String s, int z) -> { return s.length() + z; }
    // (a, b, c) -> a.getName()

    // * re-declaring an argument within a lambda is not allowed

    // ■ Invalid lambda for this reason:
    // (a, b) -> { int a = 0; return 5; }               // DOES NOT COMPILE!
    // ■ Rewritten lambda to be valid:
    // (a, b) -> { int c = 0; return 5; }
}
