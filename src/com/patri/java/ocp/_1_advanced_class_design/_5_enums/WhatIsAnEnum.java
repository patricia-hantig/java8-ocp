package com.patri.java.ocp._1_advanced_class_design._5_enums;

public class WhatIsAnEnum {
    // ■ Enum = is a class that represents an enumeration = a fixed set of constants

    // ■ Why we need enums?
    // - with numeric constants you can pass an invalid value and you will find out only at runtime
    // - with enums you cannot create an invalid enum type without a compile error

    // ex of enums: days of week, months of the year, planets in the solar system, cards in a deck
}

// ■ How to create an enum: 'class' is replaced with 'enum' and then are listed the valid types of that enum:
// ex:
enum Season {
    WINTER, SPRING, SUMMER, FALL
}
// uppercase letter convention for constants

// ■ enum = type of class that mainly contains static members, it could also have methods: values(), name(), ordinal()
// ex: WINTER, SPRING, SUMMER, FALL - are like static final constants

// ■ How to use an enum:
class UseOfEnum {
    public static void main(String[] args) {
        Season season = Season.SUMMER;
        System.out.println(Season.SUMMER);
        System.out.println(season);
        System.out.println(season == Season.SUMMER);

        // method to get an array of all of the values:
        for (Season seas : Season.values()) {
            System.out.println(seas.name() + " " + seas.ordinal());
        }
    }
}

// ■ int and enum value can't be compared directly:
// an enum is a type and not an int
class Ex {
    public static void main(String[] args) {
        // if (Season.SUMMER == 2) {}      // DOES NOT COMPILE
    }
}

// ■ you can create an enum from a String - using valueOf() method
class Ex2 {
    public static void main(String[] args) {
        Season s1 = Season.valueOf("SUMMER");
        System.out.println(s1);                 // SUMMER
        Season s2 = Season.valueOf("summer");
        System.out.println(s2);                 // IllegalArgumentException: No enum constant Season.summer
    }
}

// ■ you can't extend an enum:
// enum ExtendedSeason extends Season {}       // DOES NOT COMPILE
// the values in an enum are all that are allowed - you cannot add more at runtime by extending the enum
