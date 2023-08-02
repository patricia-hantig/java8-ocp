package com.patri.java.ocp._3_generics_and_collections._2_generics;

// we can declare also generic methods
// often used for static methods, but are allowed also for non-static methods
public class GenericMethods {

    //ex:
    public static <T> Crate<T> ship(T t) {
        System.out.println("Preparing " + t);
        return new Crate<T>();
    }
    // method parameter is the generic type T: <T>
    // return type is Crate<T>
    // type of param of the method: T

    public static <T> void sink(T t) {}
    // formal parameter type immediately before the return type of void

    public static <T> T identity(T t) { return t;}
    // return type being the formal parameter type

    //public static T noGood(T t) {return t;} //DOES NOT COMPILE!
    // omits the formal parameter type

    // ■ Optional Syntax for Invoking a Generic Method
    // you can call a generic method normally - the compiler will figure out which one you want
    // or you can specify the type explicitly to make it obvious what the type is:
    // Box.<String>ship("package");
    // Box.<String[]>ship(args);
}
