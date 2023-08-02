package com.patri.java.ocp._3_generics_and_collections._2_generics;

import java.util.ArrayList;
import java.util.List;

// Legacy code = older code
// here we have code that uses Java 1.4 or lower - which does not use generics
// collections written without generics = raw collections
// using generics -> we have compile time safety
public class LegacyCode {

    private static void printDragons(List<Dragon> dragons) {
        for (Dragon dragon : dragons) {     // ClassCastException
            System.out.println(dragon);
        }
    }
    // Java doesn't know that there is a problem - but it knows that there MIGHT be a problem
    // Java knows that raw types are usually troubles -> we have a compiler warning
    // usually we get ClassCastExceptions - because we try to add obj of different type in lists of a defined type

    public static void main(String[] args) {
        // this code has compiler warnings in it
        /*List unicorns = new ArrayList();    // problem is that this part of main() method does not use generics
        unicorns.add(new Unicorn());
        printDragons(unicorns);     // this method uses raw type*/

        // this code has compiler warnings in it
        java.util.List<Unicorn> unicorns1 = new java.util.ArrayList<>(); // main() method correctly uses generics
        addUnicorn(unicorns1);      // problem is that it calls a legacy method that claims to add a Unicorn cu a list, but it adds in fact a Dragon
        Unicorn unicorn = unicorns1.get(0); // this line tries to put that Dragon in a Unicorn reference -> we get a ClassCastException

        // this code has compiler error in it rather than a runtime error
        java.util.List numbers = new java.util.ArrayList();     // here we create a raw list
        numbers.add(5);                 // here we add int to the list - it works because Java automatically autoboxes to an Integer
        // int result = numbers.get(0);    // DOES NOT COMPILE - because we aren't using generics Java doesn't know that the list contains Integer - it just knows we have Object - Object can't be unboxed to an int
    }

    private static void addUnicorn(List unicorn) {
        unicorn.add(new Dragon());
    }

    // ■ Compiler warnings - to see compiler warning => Recompile with -Xlint:unchecked for details

    // for above examples we get something like this:

    // $ javac -Xlint:unchecked *.java
    // LegacyDragons.java:9: warning: [unchecked] unchecked call to add(E) as a member
    // of the raw type List
    //      unicorns.add(new Unicorn());
    //                  ^
    //      where E is a type-variable:
    //          E extends Object declared in interface List
    // LegacyDragons.java:11: warning: [unchecked] unchecked method invocation: method
    // printDragons in class LegacyDragons is applied to given types
    //      printDragons(unicorns);
    //                  ^
    //      required: List<Dragon>
    //      found: List
    // LegacyDragons.java:11: warning: [unchecked] unchecked conversion
    //      printDragons(unicorns);
    //                  ^
    //      required: List<Dragon>
    //      found: List
    //3 warnings
}
class Dragon {}
class Unicorn {}
