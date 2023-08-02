package com.patri.java.ocp._2_design_patterns_and_principles._5_design_patterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImmutablePattern {
    // ■■■ Problem: - How to create read-only objects that can be shared and used by multiple classes?

    // ■■■ Motivation: - sometimes we want to create objects that can be shared across multiple classes and
    //                  we don't want their value to be modified for security reasons

    // ■■■ Solution: - create objects whose state does not change after they are created and can be easily
    //                  shared across multiple classes
    //               - immutable objects go hand in hand with encapsulation, but there are no setters
    //               - immutable objects are thread-safe
    // ex: String - immutable class
}

// ■ Rules for immutable objects:
// 1. We use a constructor to set all properties of the object -> all of the data is set upon creation
// 2. All of the instance variables are marked as private and final
// 3. We don't have setters (since the instance variables are final) - the variables can't be accessed directly
// 4. Referenced mutable objects can't be modified or accessed directly -> the class should not have mutable objects
// 5. Methods can't be overridden -> mark the class to be final

// mutable object = object that can be changed
// if the class has a mutable object (ex: an object that can be changed, not a String) -> neither of the object's references CAN'T be public
// ex for rule 4:
// let's say we have an immutable Animal object - which contains a reference to a List of the animal's favorite foods:
final class Animal_ {
    private final List<String> favoriteFoods;

    public Animal_(List<String> favoriteFoods) {
        if (favoriteFoods == null) {
            throw new RuntimeException("favoriteFoods is required");
        }
        this.favoriteFoods = new ArrayList<String>(favoriteFoods);
    }

    public List<String> getFavoriteFoods() {    // MAKES CLASS MUTABLE!
        return favoriteFoods;
    }

    // the problem in the above example is that the user has direct access to the list defined in the instance of Animal
    // he can modify the items in the list, for ex: deleting all of the items by calling getFavoriteFoods().clear

    // Solution: ! You should never share references to a mutable object contained within an immutable object

    // if the user does need access to the data in the list -> create wrapper methods to iterate over the data or
    // create a one-time copy of the data that is returned to the user and never stored as part of the object
    // Collections API include Collections.unmodifiableList()

    // The key is that none of the methods that you create should modify the mutable object
}

// rule 5 prevents someone from creating a subclass of your class in which a previously immutable value now appears mutable
// for ex: they could override a method that modifies a different variable in the subclass -> hiding the private variable defined in the parent class

// example of immutable Animal class:

final class Animal {        // final class - rule 5: methods can't be overridden

    private final String species;               // private final instance variable - rule 2
    private final int age;                      // private final instance variable - rule 2
    private final List<String> favoriteFoods;   // private final instance variable - rule 2 + mutable object 'List' that doesn't have public references - rule 4

    public Animal(String species, int age, List<String> favoriteFoods) {    // in constructor we set all the properties of the object - rule 1
        this.species = species;
        this.age = age;
        if (favoriteFoods == null) {            // mutable object in constructor - but it doesn't have public references - rule 4
            throw new RuntimeException("favoriteFoods is required");
        }
        this.favoriteFoods = new ArrayList<String>(favoriteFoods);
    }

    // we don't have setters - rule 3

    public String getSpecies() {
        return species;
    }

    public int getAge() {
        return age;
    }

    public String getFavoriteFood(int index) {      // for mutable object 'List' - String is immutable, so we don’t have to worry about any of the String objects being modified
        return favoriteFoods.get(index);
    }

    public int getFavoriteFoodCount() {             // returns int - using this int value we cannot access the mutable object 'List'
        return favoriteFoods.size();
    }

    // we shouldn't have getters which return the mutable object
    /*public List<String> getFavoriteFoods() {      // this makes class mutable!
        return favoriteFoods;
    }*/
}


// ■ How to handle mutable objects in the constructor if immutable objects:

// in the example from above we created a new ArrayList in the Animal constructor
// this is important to prevent the class that creates the object from maintaining a reference to the mutable object 'List'
// if we have done: this.favoriteFoods = favoriteFoods; - the caller that creates the object is using the same reference as the immutable object -> so it has the ability to change the 'List'

// ! It is important when creating immutable objects that any mutable input arguments is copied to the instance instead of being used directly


// ■ "Modifying" an Immutable Object:
// How do we modify immutable objects if they are inherently unmodifiable? - WE CAN'T!
// what we can do is to create a new immutable object that contains the same info as the original object + what we want to change

class Examples {
    public static void main(String[] args) {
        // ex: this happens every time we combine 2 strings
        String firstName = "Grace";                 // firstName - immutable
        String fullName = firstName + " Hopper";    // fullName - immutable
        System.out.println(firstName + " " + fullName);

        // ex: increase the age of an Animal
        // the following code creates 2 Animal instances - the second using a copy of the data from the first instance

        // create a new Animal instance
        Animal lion = new Animal("lion", 5, Arrays.asList("meat", "more meat"));

        // create a new Animal instance using the data from the first instance
        List<String> favoriteFoods = new ArrayList<String>();
        for (int i = 0; i < lion.getFavoriteFoodCount(); i++) {
            favoriteFoods.add(lion.getFavoriteFood(i));
        }
        // Since we did not have direct access to the favoriteFoods mutable List, we had to copy it using the methods available in the immutable class

        Animal updatedLion = new Animal(lion.getSpecies(), lion.getAge() + 1, favoriteFoods);

        System.out.println(lion.getSpecies() + ", " + lion.getAge() + " years & favorite food: " + lion.getFavoriteFood(0) + ", " + lion.getFavoriteFood(1));
        System.out.println(updatedLion.getSpecies() + ", " + updatedLion.getAge() + " years & favorite food: " + updatedLion.getFavoriteFood(0) + ", " + updatedLion.getFavoriteFood(1));
    }
}
