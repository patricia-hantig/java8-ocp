package com.patri.java.ocp._3_generics_and_collections._2_generics;

import java.util.ArrayList;
import java.util.List;

public class Bounds_PuttingAllTogether {
    public static void main(String[] args) {

        List<?> list1 = new ArrayList<A>();
        // creates an ArrayList that can hold instances of class A
        // It is stored in a variable with an unbounded wildcard
        // Any generic type can be referenced from an unbounded wildcard => OK

        List<? extends A> list2 = new ArrayList<A>();
        // tries to store a list in a variable declaration with an upper-bounded wildcard => OK
        // you can have ArrayList<A>, ArrayList<B> or ArrayList<C> stored in that reference

        List<? super A> list3 = new ArrayList<A>();
        // The lowest type you can reference is A => OK

        // List<? extends B> list4 = new ArrayList<A>();   // DOES NOT COMPILE
        // this upper-bounded wildcard allows ArrayList<B> or ArrayList<C> to be referenced
        // you have ArrayList<A> that is trying to be referenced => NOT OK (the code does not compile)

        List<? super B> list5 = new ArrayList<A>();
        // this is a lower-bounded wildcard, which allows a reference to ArrayList<A>, ArrayList<B>, or ArrayList<Object> => OK

        // List<?> list6 = new ArrayList<? extends A>();    // DOES NOT COMPILE
        // allows a reference to any generic type since it is an unbounded wildcard
        // the problem is that you need to know the type when you instantiate the ArrayList => NOT OK
    }

    <T> T method(List<? extends T> list) {
        return list.get(0);
    }
    // method1() is a normal use of generics
    // method-specific type parameter: T
    // it takes a parameter of List<T> or subclass of T
    // it returns a single object of type T
    // ex: if you call it with a List<String> param => it will have to return a String

    /* <T> <? extends T> method2(List<? extends T> list) {     // DOES NOT COMPILE
        return list.get(0);
    } */
    // it's not ok declared => return type isn't actually a type - it can't be specified using a wildcard

    /* <B extends A> B method3(List<B> list) {
        return new B();     // DOES NOT COMPILE
    } */
    // method3() does not compile
    // <B extends A> says that you want to use B as a type parameter just for this method and that it needs to extend the A class
    // Coincidentally, B is also the name of a class - evil trick
    // Within the scope of the method, B can represent classes A, B, or C, because all extend the A class
    // Since B no longer refers to the B class in the method, you can’t instantiate it

    void method4(List<? super B> list) {
    }
    // normal use of generics - you can pass the types List<B>, List<A>, or List<Object>

    /* <X> void method5(List<X super B> list) {    // DOES NOT COMPILE
    } */
    // X doesn't appear in the wildcard: should be ?

}
class A {}
class B extends A {}
class C extends B {}
