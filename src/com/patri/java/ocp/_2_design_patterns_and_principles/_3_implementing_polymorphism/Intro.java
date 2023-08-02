package com.patri.java.ocp._2_design_patterns_and_principles._3_implementing_polymorphism;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import com.sun.xml.internal.ws.api.server.AbstractInstanceResolver;

public class Intro {
    public static void main(String[] args) {

        // ■■ Polymorphism = the ability of an interface to support multiple forms
        // in Java - multiple types of objects are allowed to be passed to a single method or class
        // example to show this purpose: LivesInOcean.java + Dolphin.java + Whale.java + Oceanographer.java


        // ■■ Polymorphism = one object can take many forms
        // example to show this polymorphic property: Primate.java, HasTail.java, Lemur.java


        // ■■ Distinguishing between an Object and a Reference:
        // in Java all objects are accessed by reference - we as developers don't have direct access to the memory of the object itself

        // ■ object = entity that exists in memory, allocated by the Java runtime environment
        // ■ reference = the address of the object's variables and methods are stored
        // regardless of the type of the reference that we have -> the object in memory does not change

        // ex: if all objects inherit java.lang.Object we can reassign it to Object:
        Lemur lemur = new Lemur();
        Object lemurAsObject = lemur;
        // Lemur object has been assigned a reference with a different type
        // the Lemur object itself has not changed and still exists as a Lemur object in memory
        // what has changed is our ability to access methods within the Lemur class with the lemurAsObject reference
        // without an explicit cast back to Lemur - we no longer have access to Lemur properties of the object

        // We have the next 2 rules:
        // 1. The type of the object - determines which properties exist within the object in memory
        // 2. The type of the reference to the object - determines which methods and variables are accessible to the Java program

        // * if you change a reference of an object to a new reference type - you get new properties of the object, but
        // those properties existed before the reference change occurred
        // example to clarify this:
        // we have:         Lemur object in memory ( age = 10, hasHair(), isTailStriped() )
        //          Lemur object exists in memory regardless of which reference is pointing to it
        // HasTail reference has access only to isTailStriped() method from Lemur object
        // Lemur reference has access to age, hasHair() and isTailStriped() method from Lemur object
        // Primate reference has access only to hasHair() method from Lemur object


        // ■■ Casting Object References
        // once we changed the reference type for object Lemur - we no longer have access to Lemur properties of the object and its methods
        // to get those back - we cast the object back to the specific class that it came from

        // ex:
        Primate primate = lemur;    // primate uses the lemur object but its type is Primate
        // to go back to Lemur object if we do this:
        // Lemur lemur2 = primate; // DOES NOT COMPILE - it won't work - without explicit cast
        // solution: use the explicit cast
        Lemur lemur2 = (Lemur) primate; // we cast the object to a subclass of the object Primate
        // in this way we gain access to all the methods available to the Lemur class
        System.out.println(lemur2.age);

        // ■ Rules for casting variables:
        // 1. Casting an object from a subclass to a superclass DOES NOT REQUIRE an explicit cast
        // 2. Casting an object from a superclass to a subclass REQUIRES an explicit cast
        // 3. The compiler will not allow casts to unrelated types
        // 4. If the code compiles without issue an exception may be thrown at runtime if the object being cast is not actually an instance of that class
    }

}

// ex for rule 3:
class Bird {}

class Fish {
    public static void main(String[] args) {
        Fish fish = new Fish();
        // Bird bird = (Fish)fish; // DOES NOT COMPILE - because classes Fish and Bird are not related through any class hierarchy
    }
}

// Even though 2 classes share a related hierarchy - that doesn’t mean an instance of one can automatically be cast to another
// ex:
class Rodent {}

class Capybara extends  Rodent {
    public static void main(String[] args) {
        Rodent rodent = new Rodent();
        // Capybara capybara = (Capybara) rodent;  // throws ClassCastException at runtime
        // java.lang.ClassCastException: Rodent cannot be cast to Capybara
        // the object being referenced is not an instance of the Capybara class
        // to avoid the exception:
        if (rodent instanceof Capybara) {
            Capybara capybara = (Capybara) rodent;
        }
    }
}