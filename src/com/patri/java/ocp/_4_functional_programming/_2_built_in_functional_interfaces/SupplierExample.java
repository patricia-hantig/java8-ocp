package com.patri.java.ocp._4_functional_programming._2_built_in_functional_interfaces;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Supplier;

// Functional Interface             Param               Return Type                 Abstract method
//---------------------------------------------------------------------------------------------------
// Supplier<T>                      0                   T                           get()
public class SupplierExample {
    // Supplier - is used when you want to generate values without taking any input

    public static void main(String[] args) {
        // static method reference
        Supplier<LocalDate> s1 = LocalDate::now; // s1, s2 - intermediate variable
        // lambda expression
        Supplier<LocalDate> s2 = () -> LocalDate.now(); // return type: LocalDate
        LocalDate date1 = s1.get();
        LocalDate date2 = s2.get();
        System.out.println(date1);
        System.out.println(date2);

        // constructor reference
        Supplier<StringBuilder> s3 = StringBuilder::new; // s3, s4 - intermediate variable
        // lambda expression
        Supplier<StringBuilder> s4 = () -> new StringBuilder(); // return type: StringBuilder
        System.out.println(s3.get());
        System.out.println(s4.get());
        //prints 2 empty StringBuilders

        // constructor reference
        Supplier<ArrayList<String>> s5 = ArrayList::new;    // return type: ArrayList<String>
        // lambda expression
        ArrayList<String> string = s5.get();
        System.out.println(string);     // []
        System.out.println(s5);         //...._2_built_in_functional_interfaces.SupplierExample$$Lambda$9/1313922862@1d81eb93
    }
}
