package com.patri.java.ocp._1_advanced_class_design._2_instanceof;

public class Examples {
    public static void main(String[] args) {
        // a instanceof B => returns true if:
        // - the reference to which a points is an instance of class B
        // - the reference to which a points is an instance of a subclass of B (directly or indirectly)
        // - the reference to which a points is an instance of a class that implements the B interface (directly or indirectly)

        // example:
        HeavyAnimal hippo = new Hippo();                // reference type is HeavyAnimal, object type is Hippo
        boolean b1 = hippo instanceof Hippo;            // true - 'hippo' is an instance of itself
        boolean b2 = hippo instanceof HeavyAnimal;      // true - 'hippo' is an instance of its superclass
        boolean b3 = hippo instanceof Elephant;         // false - 'hippo' is not an Elephant - The variable reference is HeavyAnimal, so there could be an Elephant in there.
                                                        // At runtime, Java knows that the variable is in fact pointing to a Hippo
        System.out.println(b1 + " " + b2 + " " + b3);

        // all Java classes inherit from Object -> x instanceof Object is usually true;
        // it's false only when x points to null or if we have: null instanceof Object -> null is NOT an object
        boolean b4 = hippo instanceof Object;           // true - 'hippo' is an Object (Hippo extends from Object indirectly as do all classes)
        System.out.println(b4);
        Hippo nullHippo = null;
        boolean b5 = nullHippo instanceof Object;       // false - null instanceof Object = false
        System.out.println(b5);

        Hippo anotherHippo = new Hippo();
        boolean b7 = anotherHippo instanceof HeavyAnimal; // true - 'anotherHippo' is an instance of HeavyAnimal (superclass for Hippo)
        System.out.println(b7);
        // boolean b6 = anotherHippo instanceof Elephant;  // DOES NOT COMPILE - inconvertible types (Java knows that Hippo doesn't extend Elephant directly or indirectly)

        // when instanceof is called on a class - compilation check
        // when instanceof is called on an interface - Java waits until runtime to do the check - reason: a subclass could implement that interface and the compiler wouldn’t know it
        HeavyAnimal hippo2 = new Hippo();
        boolean b6 = hippo2 instanceof Mother;
        System.out.println(b6);
        // The compiler allows the statement because we could have:
        // class MotherHippo extends Hippo implements Mother { }
    }

    // instanceof operator is commonly used to determine if an instance is a subclass of a particular object before applying an explicit cast
    // ex: consider a method that takes as input an Animal reference and performs an operation based on that animal’s type
    public void feedAnimal(Animal animal) {
        if (animal instanceof Cow) {
            ((Cow) animal).addHay();
        } else if (animal instanceof  Bird) {
            ((Bird) animal).addSeed();
        } else if (animal instanceof Lion) {
            ((Lion) animal).addMeat();
        } else {
            throw new RuntimeException("Unsupported animal");
        }
    }
    // here we needed to know if the animal was an instance of each subclass before applying the cast and calling the appropriate method
    // This is not a good way to write code - 'instanceof' and the practice of casting with if statements is not used in real life
}

class HeavyAnimal {}
class Hippo extends HeavyAnimal {}
class Elephant extends HeavyAnimal {}
interface Mother {}

class Animal {}
class Cow extends Animal {
    public void addHay() {}
}
class Bird extends Animal {
    public void addSeed() {}
}
class Lion extends Animal {
    public void addMeat() {}
}
