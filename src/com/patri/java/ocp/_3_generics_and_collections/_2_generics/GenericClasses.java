package com.patri.java.ocp._3_generics_and_collections._2_generics;

// generic classes are useful when the classes used as type param don't have a connection between them
public class GenericClasses {
    public static void main(String[] args) {
        // ■ Naming Conventions for Generics:
        // the type param can be anything you want - convention: to use a single uppercase letter
        // E = element, K = key for map, V = value for map, N = number, T = generic data type, S, U, V = multiple generic types

        // please see Crate.java
        // ex:
        Elephant elephant = new Elephant();
        Crate<Elephant> crateForElephant = new Crate<>();
        crateForElephant.packCrate(elephant);
        Elephant inNewHome = crateForElephant.emptyCrate();
        Crate<Zebra> createForZebra = new Crate<>();
        // but Elephant and Zebra are both animals => we could have a superclass/interface Animal
        // if we have:
        Robot joeBot = new Robot();
        Crate<Robot> robotCrate = new Crate<>();
        robotCrate.packCrate(joeBot);
        Robot atDestination = robotCrate.emptyCrate();
        // Elephant and Robot are not related -> we need Generics
        // before generics - we would have needed Crate to use Object class for its instance variable

        // we can also have class with more generics - ex SizeLimitedCrate.java
        Elephant elephant1 = new Elephant();
        Integer numPounds = 15_000;
        SizeLimitedCrate<Elephant, Integer> c1 = new SizeLimitedCrate<>(elephant1, numPounds);

        // ■ Type Erasure: = is the process which removes the generics from the code and replace them with "Object"
        // this process allows the code to be compatible with older versions of Java that do not use generics

        // ex: class Crate2 is type erased version for Crate.java
        // when we write: Robot r = crate.emptyCrate();
        Crate2 crate2 = new Crate2();
        Robot r = (Robot) crate2.emptyCrate(); // there is a required cast
        // the compiler adds the relevant cast so that the code works with this type of erased class
    }
}
class Crate2 {
    private Object contents;
    public Object emptyCrate() {
        return contents;
    }
    public void packCrate(Object contents) {
        this.contents = contents;
    }
}
