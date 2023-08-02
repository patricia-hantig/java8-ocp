package com.patri.java.ocp._3_generics_and_collections._2_generics;

// to introduce a generic - we have to declare a formal type parameter in <>
public class Crate<T> {
    private T contents;
    public T emptyCrate() {
        return contents;
    }
    public void packCrate(T contents) {
        this.contents = contents;
    }

    // the generic type T is available within Crate class
    // when you instantiate the class -> you tell the compiler what T should be for that instance
}
