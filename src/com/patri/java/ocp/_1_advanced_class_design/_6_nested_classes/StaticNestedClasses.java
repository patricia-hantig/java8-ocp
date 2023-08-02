package com.patri.java.ocp._1_advanced_class_design._6_nested_classes;

public class StaticNestedClasses {
    // ■ Static nested class = is not an inner class
    // Static nested class = is defined at the same level as static variables

    // ■ Properties:
    // - it can be instantiated without an object of the enclosing class
    // - it can't access the instance variables without an explicit object of the enclosing class
    // ex: new OuterClass().var - allows access to variable var
    // - it can be private, protected, public or default
    // - the enclosing class can refer to the fields and methods of the static nested class
    // - can declare static methods

    // ex:
    static class Nested {
        private int price = 6;
    }

    public static void main(String[] args) {
        Nested nested = new Nested();           // instantiates the nested class
        System.out.println(nested.price);       // because class is static, you do not need an instance of enclosing class 'StaticNestedClasses' in order to use it
                                                // you are allowed to access private instance variables - using an explicit object of the enclosing class (NOT directly!)
    }
}

// ■ importing a static nested class - it's done using a regular import
// ex:
/*
package bird;
public class Toucan {
    public static class Beak {}
}

package watcher;
import bird.Toucan.Beak;            // regular import ok
import static bird.Toucan.Beak;     // because it is static we can write like this
public class BirdWatcher {
    Beak beak;
}
 */
// Either one will compile
