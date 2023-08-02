package com.patri.java.ocp._6_exceptions_and_assertions._5_assertions;

// assertions can be used for internal invariant(you assert that a value is within a certain constraint)

// run in terminal:
// javac src\package_name.class_name.java - to compile
// cd src
// java -ea package-name.class_name - to enable assertions
public class Assertions {
    public static void main(String[] args) {
        int numGuests = -5;
        assert numGuests > 0 : "There can't be a negative number";
        System.out.println(numGuests);
    }
}
