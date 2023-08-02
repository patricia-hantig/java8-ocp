package com.patri.java.ocp._3_generics_and_collections._2_generics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Upper Bounded Wildcard = represents any class that is of type 'type'
// or supertype of type - syntax: '? super type'
public class Bounds_LowerBoundedWildcards {
    public static void main(String[] args) {
        // ex: write a method that adds a string "Quack" in 2 lists:
        List<String> strings = new ArrayList<String>();
        strings.add("tweet");
        List<Object> objects = new ArrayList<Object>(strings);
        addSound(strings);
        addSound(objects);
        // Problem: we want to pass a List<String> and a List<Object> to the same method
    }

    // the first 3 does not solve the problem:
    private static void addSound1(List<?> list) {   // can pass a List<String> or List<Object>
        //list.add("Quack");  // NOT OK: unbounded generics are immutable
    }

    private static void addSound2(List<? extends Object> list) {    // can pass a List<String> or List<Object>
        //list.add("Quack");  // NOT OK: upper-bounded generics are immutable
    }

    private static void addSound3(List<Object> list) {    // can't pass a List<String>, but can pass a List<Object>
        list.add("Quack");
    }

    // Solution: use a lower bound wildcard
    private static void addSound(List<? super String> list) {    // can pass a List<String> or List<Object>
        list.add("Quack");  // OK
    }
    // using lower bound we are telling Java that the list will be a list of String objects or a
    // list of some objects that are a superclass of String

    // ■ Understand Generic Supertypes
    public void genericSuperTypes() {
        // When you have subclasses and superclasses, lower bounds can get tricky:
        List<? super IOException> exceptions = new ArrayList<Exception>();  // references a List that could be List<IOException> or List<Exception> or List<Object>
        // Exception is superclass for IOException and RuntimeException
        // IOException is superclass for FileNotFoundException
        exceptions.add(new IOException());      // this is OK - IOException can be added to any of those types - it can be added to a list of IOException, Exception or Object
        exceptions.add(new FileNotFoundException());    // this is also OK - FileNotFoundException can also be added to any of those three types
        // FileNotFoundException is a subclass of IOException and I know that the keyword says super, but
        // Java says “Well, FileNotFoundException also happens to be an IOException, so everything is fine.”
        // exceptions.add(new Exception());    // DOES NOT COMPILE  - we could have a List<IOException> and an Exception object wouldn’t fit in there
    }

    public static void addCats(List<? super Cat> cats) { // it refers to a List that could be List<Cats> or List<Animal> or List<Object>
        cats.add(new Cat());
        // cats.add(new Animal());     // we can't add Animal because the list could be List<Cat> -> we cannot have an animal which can be a dog in a list of cats
        // cats.add(new Dog());        // we can't add Dog because the list could be List<Cat> -> we cannot have a dog in a list of cats
        cats.add(new GrumpyCat());
    }
    // this example proves why unbounded and upper-bounded generics are immutable -> we can't add other objects in them, because we are not sure that what we add is ok
}
class Animal {}
class Dog extends Animal {}
class Cat extends Animal {}
class GrumpyCat extends Cat {}
