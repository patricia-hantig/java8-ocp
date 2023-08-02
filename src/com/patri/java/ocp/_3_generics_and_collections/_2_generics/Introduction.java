package com.patri.java.ocp._3_generics_and_collections._2_generics;

import java.util.ArrayList;
import java.util.List;

public class Introduction {
    public static void main(String[] args) {
        // Why do we need generics? - If we have a list and the required type is not specified
        // then we can hope that we populate it with the required type
        // using generics -> we make sure that we don't put something unwanted in a list

        // ex:
        List names = new ArrayList();
        names.add(new StringBuilder("Webby"));
        printNames(names);
        // we get Exception at runtime: ClassCastException: java.lang.StringBuilder cannot be cast to java.lang.String
        // we try to add a StringBuilder to a list that should contain only Strings
        // Solution: use generics => they allow you to write and use parameterized types
        // you specify that you want an ArrayList of String objects
        // and if we do that we get a compile error instead of runtime error
        List<String> names2 = new ArrayList<String>();
        // names2.add(new StringBuilder("Webby")); // DOES NOT COMPILE

        // better a compile error than a runtime error
    }

    public static void printNames(List list){
        for (int i = 0; i < list.size(); i++) {
            String name = (String) list.get(i); // class cast exception here
            System.out.println(name);
        }
    }
}
