package com.patri.java.ocp._2_design_patterns_and_principles._4_design_principles;

import com.patri.java.ocp._2_design_patterns_and_principles._2_functional_programming.Animal;

public class EncapsulationData {
    // ■ Encapsulation = the idea of combining fields and methods in a class so that the fields won't be directly accessed
    // so we create private instances and we access them using public getters and setters
    // the idea of encapsulation = is that no actor other than the class itself should have direct access to its data!
    // it's useful to not have objects with wrong values for the fields -> solution: encapsulation
    // this process is called invariant = a property or truth that is maintained even after the data is modified
}

// ex: design an Animal class with the following design requirements:
// - each animal has non-null & non-empty 'species' field
// - each animal has 'age' field >= 0

// example without encapsulation
class Animal1 {
    public String species;
    public int age;
}

class UseAnimal1 {
    public static void main(String[] args) {
        Animal1 animal1 = new Animal1();
        animal1.age = - 100;
        // first invariant is violated - species == null
        // second invariant is violated - age < 0
    }
}

// example with encapsulation
class Animal2 {
    private String species;
    private int age;

    public Animal2(String species) {
        this.species = species;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        if (species == null || species.trim().length() == 0)
            throw new IllegalArgumentException("Species is required");
        this.species = species;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0)
            throw new IllegalArgumentException("Age cannot be a negative number");
        this.age = age;
    }
}

class UseAnimal2 {
    public static void main(String[] args) {
        Animal2 animal2 = new Animal2("dog");
        animal2.setAge(10);

        // Animal2 wrong = new Animal2("dog");
        // wrong.setAge(-9);

        // Animal2 wrong2 = new Animal2("");
        // animal2.setSpecies("");
    }
}

// Real world scenario:
// why is it considered a good design practice to encapsulate all variables in a class?
// example:
class Bird {
    private String name;

    public String getName() {
        return name;
    }

    // at first this may look like poor encapsulation, but it works better than allowing direct access to the private variable 'name'
    public void setName(String name) {
        // this.name = name;

        // the writers of the class can update the getter or setter to have more complex rules
        // without causing the users of the class to have to recompile their code
        // so instead of the code from above, we have:
        this.name = (name == null || name.trim().length() == 0 ? null : name);
    }
    // because the method signature setName() did not change => the callers of this method would not have to modify and recompile their code

    // if we would have a public access to the field 'name' and we switched to encapsulation -> we would be forced to recompile the code
    // this is why we use encapsulation - to protect the data when rules may be added in the future
}