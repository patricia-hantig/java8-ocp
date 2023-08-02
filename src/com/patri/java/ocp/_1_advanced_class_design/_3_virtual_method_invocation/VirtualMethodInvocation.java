package com.patri.java.ocp._1_advanced_class_design._3_virtual_method_invocation;

public class VirtualMethodInvocation {
    // virtual methods = regular non-static methods

    // virtual method = the method that Java calls at runtime, knowing the object type (the overridden method from an abstract/regular class)
    // ex:
    public void feedAnimal(Animal animal) {
        animal.feed();      // calls the feed() method based on the type of animal at runtime
        // we have here: virtual method invocation
        // feedAnimal() method doesn’t need to change when we add a new Animal subclass
        // we have an Animal instance - but Java didn't call feed() on the Animal class
        // Java looked at the actual type of animal at runtime and called feed() on that
    }
}

abstract class Animal {
    public abstract void feed();
}
class Cow extends Animal {
    @Override
    public void feed() {
        addHay();
    }
    private void addHay() { }
}
class Bird extends Animal {
    @Override
    public void feed() {
        addSeed();
    }
    private void addSeed() { }
}
class Lion extends Animal {
    @Override
    public void feed() {
        addMeat();
    }
    private void addMeat() { }
}

// for instance variables is different:
// if a method in an abstract class prints an instance variable from the abstract class
// if we call that method - it will print the value of the instance variable from the abstract class, even if the variable is hidden (changed in a concrete class)
// ex:
abstract class Animal2 {
    String name = "???";
    public void printName() {
        System.out.println(name);
    }
}
class Lion2 extends Animal2 {
    String name = "Lion";
}
class PlayWithAnimal {
    public static void main(String[] args) {
        Animal2 animal = new Lion2();
        animal.printName();             // ??? - The name declared in Lion would only be used if name was referred to from Lion (or a subclass of Lion.)

        Lion2 lion = new Lion2();
        lion.printName();               // ???
        System.out.println(lion.name);  // Lion - we use the instance variable 'name' from Lion
    }
}

// what does the following ex print?
abstract class Animal3 {
    public void careFor() {
        play();
    }
    public void play() {
        System.out.println("pet animal");
    }
}
class Lion3 extends Animal3 {
    public void play() {
        System.out.println("toss in meat");
    }
}
class PlayWithAnimal2 {
    public static void main(String[] args) {
        Animal3 animal3 = new Lion3();
        animal3.careFor();
    }
}

// The answer is: toss in meat - calls the method from the concrete class
// You don't want to pet a Lion!

// Conclusion:
// virtual method invocation:   - calls the method from the concrete class (overridden method)
//                              - for an instance variable - we use the value from the abstract class of the variable (not the hidden value of the variable)