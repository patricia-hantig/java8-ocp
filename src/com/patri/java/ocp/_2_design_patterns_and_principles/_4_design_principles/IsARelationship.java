package com.patri.java.ocp._2_design_patterns_and_principles._4_design_principles;

public class IsARelationship {
    // Is-a relationship = property of an object being an instance of a data type
    // Is-a relationship = inheritance test - determine when an object is an instance of a particular class, superclass or interface

    // A instanceof B => any child of A or any grandchild of A is an instance of B
}

// good design for is-a:
class Pet {
    public String name;
    public int age;
    public void cuddle() {
        System.out.println("Cuddle");
    }
}
class Cat extends Pet {} // a Cat is-a Pet

// poor design for is-a:
class Tiger extends Pet {} // a Tiger is-a Pet -> wrong - Pet has a cuddle() method - you don't want to cuddle a tiger

// still poor design for is-a:
class Feline {}
class Pet_ extends Feline {}
class Tiger_ extends Feline {}
class Cat_ extends Pet_ {} // now this class structure works and is consistent
class Dog_ extends Pet_ {} // but if we add a Dog_ class we have a problem => Dog is-a Pet -> Dog is-a Feline => false!

// good design for is-a:
class Animal__ {}
interface Pet__ {} // Pet is now separate from the class inheritance model
class Feline__ extends Animal__ {}                  // Feline is-a Animal
class Dog__ extends Animal__ implements Pet__ {}    // Dog is-a Animal, Dog is-a Pet
class Cat__ extends Feline__ implements Pet__ {}    // Cat is-a Feline, Cat is-a Animal, Cat is-a Pet
class Tiger__ extends Feline__ {}                   // Tiger is-a Feline, Tiger is-a Animal
