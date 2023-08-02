package com.patri.java.ocp._3_generics_and_collections._2_generics;

import java.util.ArrayList;
import java.util.List;

// Unbounded wildcard = represents any data type - syntax: '?'
public class Bounds_UnboundedWildcards {
    // ex: we want a method that looks through a list of any type

    public static void printListWrong(List<Object> list) {
        for (Object x : list) {
            System.out.println(x);
        }
    }

    public static void main(String[] args) {
        List<String> keywords = new ArrayList<>();
        keywords.add("java");
        // printListWrong(keywords);    // DOES NOT COMPILE
        // Problem: - Required type: List<Object>, Provided: List<String>
        // String is subclass of Object => but List<String> cannot be assigned to List<Object>

        // why List<String> is not List<Object> ?
        // answer:
        List<Integer> numbers = new ArrayList<>(); // promise that only Integer objects will appear in numbers
        numbers.add(new Integer(42));
        // List<Object> objects = numbers; // DOES NOT COMPILE
        //objects.add("forty two");             // would break that promise by putting a String in there since numbers and objects are references to the same object
        //System.out.println(numbers.get(1));

        // Solution: we don't need a list of objects, we need a list of "whatever"
        // so we use: List<?> - which is a list of anything, not Objects
        List<String> keywordsList = new ArrayList<>();
        keywordsList.add("java");
        printListGood(keywordsList); // printListGood() takes any type of list as a parameter and keywordsList is of type List<String>
        // We have a match! => List<String> is a list of anything (anything is a String here)

        // ■ Storing the Wrong Objects—Arrays vs. ArrayLists
        // we can't write: List<Object> list = new ArrayList<String>(); - because Java is trying to protect us from runtime exception
        // but we can write:
        Object[] o = new String[0];
        Integer[] numbers2 = {new Integer(42)};
        Object[] objects = numbers2;
        objects[0] = "forty two";       // throws ArrayStoreException
        // although this code compiles => it throws exception at runtime
        // with arrays -> Java knows the type that is allowed in the array
        // just because we've assigned an Integer[] to an Object[] - it doesn't change the fact that Java knows it is really an Integer[]
        // because of type erasure - we have no such protection for an ArrayList
    }

    public static void printListGood(List<?> list) {
        for (Object x : list) {
            System.out.println(x);
        }
    }
}
