package com.patri.java.ocp._2_design_patterns_and_principles._5_design_patterns;

public class FactoryPattern {
    // ■■■ Problem: - How to write code that creates objects in which the type of the object may not be known until runtime?

    // ■■■ Motivation: - we would like that for object creation to select which subclass to use and also to have loose coupling

    // ■■■ Solution: - the factory pattern = using a factory class to create instances of objects based on a set of input parameters
    //               - it's similar to builder pattern - but is focused on class polymorphism
}

// Factory patterns - are implemented using static methods that return objects and do not require a pointer to an instance of the factory class

// ex: A zookeeper needs to feed the animals in the zoo with different types of foods
// 'Client' class -> getFood() from 'FoodFactory' class
// 'FoodFactory' class -> produces() 'Food' class
// 'Food' class has the following types: 'Pellets' class, 'Hay' class, 'Fish' class

abstract class Food {               // abstract class

    private int quantity;
    public Food(int quantity) {
        this.quantity = quantity;
    }
    public int getQuantity() {
        return quantity;
    }
    public abstract void consumed();    // abstract method
}

class Hay extends Food {

    public Hay(int quantity) {
        super(quantity);
    }
    @Override
    public void consumed() {
        System.out.println("Hay eaten: " + getQuantity());
    }
}

class Pellets extends Food {

    public Pellets(int quantity) {
        super(quantity);
    }
    @Override
    public void consumed() {
        System.out.println("Pellets eaten: " + getQuantity());
    }
}

class Fish extends Food {

    public Fish(int quantity) {
        super(quantity);
    }
    @Override
    public void consumed() {
        System.out.println("Fish eaten: " + getQuantity());
    }
}

// FoodFactory - returns a food type based on some set of inputs
class FoodFactory {
    public static Food getFood(String animalName) {     // static method
        switch (animalName) {
            case "zebra": return new Hay(100);
            case "rabbit": return new Pellets(5);
            case "goat": return new Pellets(30);
            case "polar bear": return new Fish(10);
            default: throw new UnsupportedOperationException("Unsupported animal: " + animalName);
        }
        // Good practice to throw an exception if no matching subclass could be found
        //throw new UnsupportedOperationException("Unsupported animal: "+animalName);
    }
}

class ZooKeeper {
    public static void main(String[] args) {
        final Food food = FoodFactory.getFood("polar bear");
        food.consumed();
        // Depending on the value of animalName, we return different types of food for use in our factory
        // we have loose coupling between Food and ZooKeeper - this allows us to do changes in FoodFactory without requiring to do some changes in ZooKeeper class
    }
}

// using Factory pattern - we create loosely coupled code
// as an alternative to factory pattern we could implement a set of Animal classes and define
// getFood() method in each class that returns a Food object
// this solution tightly couples what an animal is and what food an animal eats
// for ex: if a particular food is no longer available -> all the classes that need that food need to be changed
// by using factory pattern - we create loosely coupled code that is more resistant to changes in animal feeding behaviors


// ■ Factory Pattern and Default Class Constructors
// in the example from above all of the Food class and subclass constructors are 'public'
// we can't mark the constructors 'private' - because this would prevent the FoodFactory class from creating instances of Food classes
// we can't mark the constructors 'protected' - because FoodFactory class is not a subclass of any of the Food classes
// the problem of marking them 'public' - is that any class could bypass our factory pattern and create instances of Food directly
// if we want to have control over this - we have to declare this constructors with default or package-level access (no modifier)
// why we should use default access - it forces any class outside the package into using the FoodFactory class to create an instance of a Food object
// so we wouldn't be able to create a Food object directly
// but now our FoodFactory class and all of our Food classes must be in the same package
// if we have a Food class in a different package than FoodFactory and we want to use FoodFactory to create an instance of it => we must provide a 'public' method


// ■ Best book for design patterns: book Design Patterns (Addison‐Wesley Professional, 1994), with authors Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides