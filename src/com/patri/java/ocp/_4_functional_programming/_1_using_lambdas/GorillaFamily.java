package com.patri.java.ocp._4_functional_programming._1_using_lambdas;

// effectively final example
public class GorillaFamily {

    // effectively final = a variable is effectively final if you could add the final modifier to the local variable -> this means
    // that the variable is not changed from the beginning until the end - it's not reassigned with another value

    // Lambdas CAN ACCESS: instance variables, static variables, effectively final local variables, effectively final method params
    // Lambdas CANNOT ACCESS: private variables from other classes

    static String jump = "jump";        // static variable
    String walk = "walk";               // instance variable
    void everyonePlay(boolean baby) {   // effectively final method param
        String approach = "amble";      // effectively final local variables
        //approach = "run";

        play(() -> walk);                           // lambda that uses instance variable
        play(() -> baby ? "hitch a ride" : "run");  // lambda that uses effectively final method param - it's not reassigned
        play(() -> jump);                           // lambda that uses static variable
        play(() -> approach);                       // lambda that uses effectively final local variables - it's not reassigned
        // if we uncomment: approach = "run"; => COMPILE ERROR: Variables used in lambda expressions should be final or effectively final
    }

    void play(Gorilla gorilla) {
        System.out.println(gorilla.move());
    }

    public static void main(String[] args) {
        GorillaFamily gorillaFamily = new GorillaFamily();
        gorillaFamily.everyonePlay(true);
    }
}
