package com.patri.java.ocp._3_generics_and_collections._2_generics;

import java.util.ArrayList;
import java.util.List;

// Upper Bounded Wildcard = represents any class that is of type 'type'
// or that it extends 'type'(subtype of type) - syntax: '? extends type'
public class Bounds_UpperBoundedWildcards {
    public static void main(String[] args) {
        // ex: method that adds up the total of a list of numbers
        // a generic type can't just use a subclass
        // ArrayList<Number> list = new ArrayList<Integer>();  // DOES NOT COMPILE
        // we use a wildcard:
        List<? extends Number> list = new ArrayList<Integer>();
        // upper-bounded wildcard says that any class that extends Number or Number itself
        // can be used as the formal parameter type - Integer extends Number

        // ■ When we work with upper bounds and unbounded wildcards => the list becomes logically immutable!!!
        // this means that the object cannot be modified - you cannot add elements into the list
        // but you can remove elements from the list
        // ex:
        List<? extends Bird> birds = new ArrayList<Bird>();
        // birds.add(new Sparrow());   // DOES NOT COMPILE  - we can’t add a Sparrow to List<Bird>
        // birds.add(new Bird());      // DOES NOT COMPILE  - we can’t add a Bird to List<Sparrow>
        // from Java's point of view: both scenarios are equally possible so neither is allowed
        // Java doesn’t know what type List<? extends Bird> really is
        // It could be List<Bird> or List<Sparrow> or some other generic type that hasn’t even been written yet

        // (*) for Interfaces and upper bound wildcards:
        // a variable of type List<Flyer> can be passed to either method
        List<Flyer> flyerList = new ArrayList<>();
        anyFlyer(flyerList);
        groupByFlyers(flyerList);
        // a variable of type List<Goose> can be passed only to the one with the upper bound
        List<Goose> gooseList = new ArrayList<>();
        //anyFlyer(gooseList);  // DOES NOT COMPILE     => Required: List<Flyer> - Provided: List<Goose>
        groupByFlyers(gooseList);
    }

    public static long total(List<? extends Number> list) {
        long count = 0;
        for (Number number : list) {
            count += number.longValue();
        }
        return  count;
    }
    // type erasure makes Java think that a generic type is an Object
    // so Java converts the previous code to something equivalent to the following
    public static long total2(List list) {
        long count = 0;
        for (Object obj : list) {
            Number number = (Number) obj;
            count += number.longValue();
        }
        return  count;
    }

    static class Sparrow extends Bird {}
    static class Bird {}

    // ■ Interfaces and upper bound wildcards:
    private static void anyFlyer(List<Flyer> flyer) {} // - this method lists the interface
    private static void groupByFlyers(List<? extends Flyer> flyer) {} // - this method uses an upper bound
    // Upper bounds are like anonymous classes - they use extends regardless of whether we are working with a class or an interface

    // a variable of type List<Flyer> can be passed to either method
    // a variable of type List<Goose> can be passed only to the one with the upper bound
    // see the calls at (*) in main
}

interface Flyer { void fly(); }
class HangGlider implements Flyer {
    @Override
    public void fly() {
    }
}
class Goose implements Flyer {
    @Override
    public void fly() {
    }
}