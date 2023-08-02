package com.patri.java.ocp._3_generics_and_collections._4_comparator_vs_comparable;

// example: implementation of compareTo() that compares numbers instead of Strings

public class Animal implements Comparable<Animal> {

    private int id;

    @Override
    public int compareTo(Animal animal) {
        return id - animal.id;
    }

    public static void main(String[] args) {
        Animal a1 = new Animal();
        a1.id = 5;                              // this is not a good way to set instance variables - use constructor or setter()
        Animal a2 = new Animal();
        a2.id = 7;
        System.out.println(a1.compareTo(a2));   // -2
        System.out.println(a1.compareTo(a1));   // 0
        System.out.println(a2.compareTo(a1));   // 2

        // ■ compareTo() outputs:
        // ■■   0 - when current obj == obj from method argument
        // ■■ < 0 - when current obj < obj from method argument
        // ■■ > 0 - when current obj > obj from method argument

        // => correct implementation for compareTo()

        // id - animal.id == ascending order
        // animal.id - id == descending order
    }
}

// when working with legacy code - method compareTo() requires a cast since it is passed an Object
class LegacyDuck implements Comparable {        // because we don't specify a generic type for Comparable - Java assumes that we want Object
                                                // this means that we have to cast to LegacyDuck before accessing instance variable on it
    private String name;

    @Override
    public int compareTo(Object o) {
        LegacyDuck duck = (LegacyDuck) o;       // cast because no generics
        return name.compareTo(duck.name);
    }
}
