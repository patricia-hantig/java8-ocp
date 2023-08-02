package com.patri.java.ocp._2_design_patterns_and_principles._5_design_patterns;

import java.util.Arrays;
import java.util.List;

public class BuilderPattern {
    // ■■■ Problem: - How to create an object that requires numerous values to be set at the time the object is instantiated?

    // ■■■ Motivation: - we could instantiate within the constructor all the values, but the constructor would become very big and
    //                  the users who reference our object would also be required to update their constructor calls each time the object is modified
    //                  so we would have a class that would be difficult to use and maintain => anti-pattern
    //                 - we could add a new constructor each time we add a new parameter, but having to many constructors can be difficult to manage
    //                 - one solution could be to use setters but this doesn't work for immutable objects
    // anti-pattern = common solution to a reoccurring problem that leads to difficult-to-use code
    // the reason instantiating in the constructor is an anti-pattern = each time the class is modified, even though the developer is doing
    // just a minor change the class could eventually grow out of control

    // ■■■ Solution: - the builder pattern = the parameters are passed to a builder object (often through method chaining) and
    //                 an object is generated with a build() call
    //               - it is often used with immutable objects since immutable objects do not have setter methods, but it can be used with mutable objects as well
}

// ex AnimalBuilder for Animal class:
class AnimalBuilder {                           // class is mutable
    private String species;
    private int age;
    private List<String> favoriteFoods;

    public AnimalBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    public AnimalBuilder setSpecies(String species) {
        this.species = species;
        return this;
    }

    public AnimalBuilder setFavoriteFoods(List<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
        return this;
    }
    // the setter methods return an instance of the builder object this

    public Animal build() {     // the result of the build() method is an immutable object
        return new Animal(species, age, favoriteFoods);
    }
}
// using the builder pattern is analog to taking a mutable object and making it immutable

class UsesOfBuilderObject {
    public static void main(String[] args) {
        AnimalBuilder duckBuilder = new AnimalBuilder();
        duckBuilder.setAge(4)
                .setFavoriteFoods(Arrays.asList("grass", "fish"))
                .setSpecies("duck");

        Animal duck = duckBuilder.build();
        System.out.println(duck.getSpecies() + " " + duck.getAge() + " " + duck.getFavoriteFood(0) + " " + duck.getFavoriteFood(1));

        Animal flamingo = new AnimalBuilder().setSpecies("flamingo")
                .setFavoriteFoods(Arrays.asList("algae", "insects"))
                .build();
        System.out.println(flamingo.getSpecies() + " " + flamingo.getFavoriteFood(0) + " " + flamingo.getFavoriteFood(1));

        // in the second example: flamingo - age is not set - it may not be required
        // we could certainly write our build() method to throw an exception if certain required fields are not set
        // the build() method may also set default values if the user fails to specify on the builder object
    }
}

// ■ Why to use builder pattern?
// Over time, this approach leads to more maintainable code
// if a new optional field is added to the Animal class - AnimalBuilder class will not need to be changed

// ■ Builder pattern uses tight coupling principle
// tight coupling principle = classes are highly dependent - one minor change in one could impact the other one
// loose coupling principle = developing coupled classes with minimum dependencies on one another - preferred in practice
// why? : tight coupling principle is required in Builder pattern - because callers of the AnimalBuilder class never have to use the Animal class constructor directly

// in practice: the builder class is a static inner class within the target class or within the same Java package
// - one advantage of packing them together is that: if one is changed, then the other can be quickly updated
// - another advantage is that writers of the target class can then choose to make the constructor a private or default package
// ex: if the Animal class did not have a public constructor - programs calling it from other packages would be required to use the
// AnimalBuilder class to create instances of Animal