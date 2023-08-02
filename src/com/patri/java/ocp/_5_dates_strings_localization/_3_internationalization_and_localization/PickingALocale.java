package com.patri.java.ocp._5_dates_strings_localization._3_internationalization_and_localization;

import java.util.Locale;

public class PickingALocale {
    public static void main(String[] args) {
        // Locale = a specific geographical, political or cultural region
        // we will show only languages and countries
        // Locale class is located in java.util package

        // ■ find the user's current locale
        Locale locale = Locale.getDefault();
        System.out.println(locale); // en_US = English, United States

        // ■ Locale formats:
        // language only or language + country
        // ! language = lowercase letters and country - uppercase letters
        // US - can have a language without a country, but not reverse
        // enUS - missing underscore
        // US_en - the country and languages are reversed
        // EN - language must be lowercase

        // ■ Creating locale - 3 methods
        // using constants from Locale class
        System.out.println(Locale.GERMAN); // de - language
        System.out.println(Locale.GERMANY); // de_DE - country
        // using the constructors - you can pass just the language or both language and country
        System.out.println(new Locale("fr")); // fr - language
        System.out.println(new Locale("hi", "IN")); // hi_IN - country
        // using Builder Design Pattern - set all the properties that you want and then build it at the end
        // the properties can be set in any order
        Locale l1 = new Locale.Builder()
                .setLanguage("en")
                .setRegion("US")
                .build();
        System.out.println(l1);             // en_US
        Locale l2 = new Locale.Builder()
                .setRegion("US")
                .setLanguage("en")
                .build();
        System.out.println(l2);             // en_US
        // how not to use Local.Builder
        Locale l3 = new Locale.Builder()
                .setRegion("us")
                .setLanguage("EN")
                .build();
        System.out.println(l3);             // en_US

        // ■ set a new default in Java
        System.out.println(Locale.getDefault());    // en_US
        Locale loc = new Locale("fr");
        Locale.setDefault(loc);                     // change the default
        System.out.println(Locale.getDefault());    // fr
        // try it - the Locale changes only for that one Java program; it does not change any settings on your computer
        // in practice setDefault is used extremely rarely
    }
}
