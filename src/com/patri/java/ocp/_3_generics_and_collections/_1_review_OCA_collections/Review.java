package com.patri.java.ocp._3_generics_and_collections._1_review_OCA_collections;

import java.util.*;
import java.util.stream.Collectors;

public class Review {
    public static void main(String[] args) {
        // Java Collections Frameworks includes classes that implement List, Set, Map and Queue
        // array ex int[] - is not part of the Collections Framework

        // ■■ Array and ArrayList
        // ArrayList = obj that contains other objects, cannot contain primitives
        // Array = built-in data structure that contains other objects or primitives

        // ex: list.size(), list.get(elem)
        List<String> list = new ArrayList<>();  // empty list
        list.add("Fluffy");
        list.add("Webby");
        System.out.println(list);

        // ex: array.length, array[elem]
        String[] array = new String[list.size()];   // empty array
        array[0] = list.get(1);
        array[1] = list.get(0);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "-");
        }
        System.out.println();

        // ■ converting array -> List         => Arrays.asList(array)
        // List is a resizable array
        // asList() makes it not resizable
        String[] array1 = {"gerbil", "mouse"};      // [gerbil, mouse]
        for (int i = 0; i < array1.length; i++)
            System.out.print(array1[i] + " ");      // gerbil mouse
        System.out.println();
        List<String> listFromArray = Arrays.asList(array1); // returns fixed size list
        System.out.println(listFromArray);          // [gerbil, mouse]
        listFromArray.set(1, "test");
        System.out.println(listFromArray);          // [gerbil, test]

        // ■ converting ArrayList -> array    => list.toArray()
        array1[0] = "new";
        for (int i = 0; i < array1.length; i++)
            System.out.print(array1[i] + " ");      // new test
        // changes in array or List are reflected in both - they are backed by the same data
        System.out.println();
        String[] array2 = (String[]) listFromArray.toArray();
        for (int i = 0; i < array2.length; i++)
            System.out.print(array2[i] + " ");      // new test
        System.out.println();
        // listFromArray.remove(1);  - this throws UnsupportedOperationException -> listFromArray is not resizable
        // because it is backed by the underlying array - it was converted to array

        // ■■ Searching and Sorting
        // array:       Arrays.sort(array)  &   Arrays.binarySearch(array, value)
        // ArrayList:   Collections.sort(list)  &   Collections.binarySearch(list, value)
        // ex array:
        int[] numbers = {6, 9, 1, 8};
        Arrays.sort(numbers);
        for (int i = 0; i < numbers.length; i++)
            System.out.print(numbers[i] + " ");
        System.out.println();
        System.out.println(Arrays.binarySearch(numbers, 6));
        System.out.println(Arrays.binarySearch(numbers, 3));
        // ex List:
        List<Integer> nrs = Arrays.asList(9, 7, 5, 3);
        Collections.sort(nrs);
        System.out.println(nrs);
        System.out.println(Collections.binarySearch(nrs, 3));
        System.out.println(Collections.binarySearch(nrs, 2));

        // ■■ Wrapper classes and Autoboxing
        // each primitive has a corresponding wrapper class
        // Autoboxing = automatically converts a primitive to the corresponding wrapper class
        // Unboxing = automatically converts a wrapper class back to a primitive

        // Wrapper classes:
        // Primitive type   |   Wrapper class   |   Example of initializing
        // --------------------------------------------------------------------
        //      boolean     |       Boolean     |   new Boolean(true)
        //      byte        |       Byte        |   new Byte((byte) 1)
        //      short       |       Short       |   new Short((short) 1)
        //      int         |       Integer     |   new Integer(1)
        //      long        |       Long        |   new Long(1)
        //      float       |       Float       |   new Float(1.0)
        //      double      |       Double      |   new Double(1.0)
        //      char        |       Character   |   new Character('c')

        List<Integer> theNumbers = new ArrayList<Integer>();
        theNumbers.add(1);                          // autoboxing
        System.out.println(theNumbers);
        theNumbers.add(new Integer(3));
        System.out.println(theNumbers);
        theNumbers.add(new Integer(5));
        System.out.println(theNumbers);
        theNumbers.remove(1);               // remove(index)
        System.out.println(theNumbers);
        theNumbers.remove(new Integer(5));  // remove(value of object)
        System.out.println(theNumbers);
        int num = theNumbers.get(0);                // unboxing
        System.out.println(num);

        // ■■ Diamond Operator
        // before Java 5 you could have write code like this
        List names = new ArrayList();   // - you don't know that there are expected only String objects rather than StringBuilder or something else
        // in Java 5 - new feature: generics
        List<String> names2 = new ArrayList<String>();
        // in Java 7 - generics got a bit shorten -> diamond operator(<>)
        List<String> names3 = new ArrayList<>();

        // the following 2 are the same:
        HashMap<String, HashMap<String, String>> map1 = new HashMap<String, HashMap<String, String>>();
        HashMap<String, HashMap<String, String>> map2 = new HashMap<>();
        // the second one doesn't contain redundant information

        // diamond operator isn't limited to one-line declarations
        // ex: <> used with an instance variable and a local variable
        class Doggies {
            List<String> names;
            Doggies() {
                names = new ArrayList<>();          // matches instance variable declaration
            }
            public void copyNames() {
                ArrayList<String> copyOfNames;
                copyOfNames = new ArrayList<>();    // matches local variable declaration
            }
        }

    }
}
