package com.patri.java.ocp._3_generics_and_collections._2_generics;

import java.util.ArrayList;
import java.util.List;

// Generics are not very useful if they are treated as Objects
// Bounded wildcards solve this -> restricting what types can be used in that wildcard position
public class Bounds {
    public static void main(String[] args) {
        // We have 2 types for generics:
        // ■ bounded parameter type = generic type that specifies a bound for the generic
        // ■ wildcard generic type = unknown generic type, represented by: "?"

        // 3 types of wildcards:

        // ■ unbounded wildcard
        // syntax: '?'
        // ex:
        List<?> list1 = new ArrayList<String>();
        // please see class: Bounds_UnboundedWildcards.java

        // ■ wildcard with an upper bound
        // syntax: '? extends type'
        // ex:
        List<? extends Exception> list2 = new ArrayList<RuntimeException>();
        // please see class: Bounds_UpperBoundedWildcards.java

        // ■ wildcard with an lower bound
        // syntax: '? super type'
        // ex:
        List<? super Exception> list3 = new ArrayList<Object>();
        // please see class: Bounds_LowerBoundedWildcards.java
    }
}
