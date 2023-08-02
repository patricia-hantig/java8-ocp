package com.patri.java.ocp._5_dates_strings_localization._2_strings;

// String = sequence of characters
public class WorkingWithStrings {

    public static void main(String[] args) {

        // ■ String characteristics:
        // String class is final and String objects are immutable - their value cannot be changed
        // string literals are stored in string pool
        // string literals can be compared with ==, however it's better to compare with equals() - because String obj created via constructor will not match when using it with ==

        String s1 = "bunny";
        String s2 = "bunny";
        String s3 = new String("bunny");
        System.out.println(s1 == s2);           // true  - s1 and s2 point to the same literal in string pool
        System.out.println(s1 == s3);           // false - because the literal s1 and the string object created point to different objects in memory
        System.out.println(s1.equals(s3));      // true  - values of s1 and s3 are the same even though the location in memory is not the same

        // string concatenation: + => it just creates a new string from the 2 original strings
        // a string concatenated with anything is a string
        String s4 = "1" + 2 + 3;
        String s5 = 1 + 2 + "3";
        System.out.println(s4);     // 123
        System.out.println(s5);     // 33

        // common String methods: trim(), length(), charAt(), indexOf(), substring(), toUpperCase(), toLowerCase(), replace(), contains(), startsWith()
        String s = "abcde ";
        System.out.println(s.trim().length());                      // 5
        System.out.println(s.charAt(4));                            // e
        System.out.println(s.indexOf('e'));                         // 4
        System.out.println(s.indexOf("de"));                        // 3
        System.out.println(s.substring(2, 4).toUpperCase());        // CD
        System.out.println(s.replace('a', '1'));  // 1bcde
        System.out.println(s.contains("DE"));                       // false
        System.out.println(s.startsWith("a"));                      // true

        // String is immutable - so we have StringBuilder - which is mutable - it's value can change and increase in capacity
        // if multiple threads are updating the same object - you should use StringBuffer rather than StringBuilder
        // ■ StringBuilder common methods: append(), length(), indexOf(), charAt(), reverse(), toString(), insert(), delete(), substring()
        StringBuilder b = new StringBuilder();
        b.append(12345).append('-');
        System.out.println(b.length());         // 6
        System.out.println(b.indexOf("-"));     // 5
        System.out.println(b.charAt(2));        // 3

        StringBuilder b2 = b.reverse();
        System.out.println(b.toString());       // -54321
        System.out.println(b == b2);            // true - it's the same object

        StringBuilder stringBuilder = new StringBuilder("abcde");
        stringBuilder.insert(1, '-').delete(3, 4);
        System.out.println(stringBuilder);                  // a-bde
        System.out.println(stringBuilder.substring(2, 4));  // bd

        // ■ Comparing String, StringBuilder and StringBuffer
        // Characteristic       |   String  |   StringBuilder   | StringBuffer
        // -------------------------------------------------------------------
        // Immutable?           |   Yes     |       No          |       No
        // Pooled?              |   Yes     |       No          |       No
        // Thread-safe?         |   Yes     |       No          |       Yes
        // Can change size?     |   No      |       Yes         |       Yes
    }
}
