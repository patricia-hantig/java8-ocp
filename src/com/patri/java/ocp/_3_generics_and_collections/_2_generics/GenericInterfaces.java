package com.patri.java.ocp._3_generics_and_collections._2_generics;

// similar to Generic classes

// ex: Shippable.java - generic interface

// There are 3 ways to implement a generic interface:

// ■ specify the generic type in the class
class ShippableRobotCrate implements Shippable<Robot> {
    @Override
    public void ship(Robot robot) {
    }
}

// ■ create a generic class - the first concrete class allows the caller to specify the type of the generic
class ShippableAbstractCrate<U> implements Shippable<U> {
    @Override
    public void ship(U u) {
    }
}

// ■ a class without generics
class ShippableCrate implements Shippable {
    @Override
    public void ship(Object o) {
    }
}

public class GenericInterfaces {
    public static void main(String[] args) {
        // ■ What You CAN’T Do with Generic Types - most of the limitation are due to type erasure
        // - call the constructor: new T() - it's not allowed because at runtime it will create a new Object()
        // - create an array of that static type - you would be creating an array of Objects
        // - call instanceof - not allowed because at runtime List<Integer> and List<String> look the same to Java - thanks to type erasure
        // - use a primitive type as a generic type param - we use the Wrapper
        // - create a static variable as a generic type parameter - not allowed because type is linked to the instance of the class
    }
}
