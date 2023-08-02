package com.patri.java.ocp._1_advanced_class_design._4_annotating_overridden_methods;

import java.util.ArrayList;

public class ToStringMethodExamples {
    public static void main(java.lang.String[] args) {
        // Java automatically calls the toString() method if you try to print out an object
        // some classes supply a more readable implementation of toString() compared to others
        System.out.println(new ArrayList<>());      // []
        System.out.println(new String[0]);          // [Ljava.lang.String;@1b6d3586
        // ArrayList has an implementation of toString() - which listed the contents of the ArrayList
    }
}

// ex: providing our own implementation for toString()
class Hippo {
    private String name;
    private double weight;

    public Hippo(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    /*public String toString() {
        return name;
    }*/
    public String toString() {
        return "Name: " + name + ", Weight: " + weight;
    }

    public static void main(String[] args) {
        Hippo hippo = new Hippo("Harry", 3100);
        System.out.println(hippo);
        // Harry - using the first implementation for toString()
        // Name: Harry, Weight: 3100.0 - using the second implementation for toString()
    }
}

// ■ The easy way to write toString() methods: (real world scenario)
// use the open source library: Apache Commons Lang (http://commons.apache.org/proper/commons-lang/)

//to return all the instance variables in a String:
/* public String toString() {
        return ToStringBuilder.reflectionToString(this);
   }
 */
// calling our Hippo class with this toString() outputs something like this:
// toString.Hippo@12da89a7[name=Harry,weight=3100.0]

// - Reflection = is a technique used in Java to look at information about the class at runtime
// this lets the ToStringBuilder class determine what are all of the instance variables and to construct a String with each

//to return 'only' all the instance variables in a String:
/* public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
   }
 */
// calling our Hippo class with this toString() outputs something like this:
// Hippo[name=Harry,weight=3100.0]

// There are other styles that let you choose to omit the class names or the instance variable names