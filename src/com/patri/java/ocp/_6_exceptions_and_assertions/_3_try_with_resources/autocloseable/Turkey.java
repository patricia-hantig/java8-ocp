package com.patri.java.ocp._6_exceptions_and_assertions._3_try_with_resources.autocloseable;

public class Turkey {

    public static void main(String[] args) {
        /*try (Turkey turkey = new Turkey()) {  // DOES NOT COMPILE (Required type: AutoCloseable)
            System.out.println(turkey);
        }*/
    }
}
