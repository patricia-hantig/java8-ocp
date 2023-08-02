package com.patri.java.ocp._1_advanced_class_design._6_nested_classes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnonymousInnerClasses {
    // ■ Anonymous inner class = is a local inner class that doesn't have a name
    // an Anonymous inner class is declared and instantiated in one statement - using the 'new' keyword
    // an Anonymous inner class must extend an existing class OR implement an existing interface

    // ex: an Anonymous inner class extending an existing class
    abstract class SaleTodayOnly {      // here we have an abstract class
        abstract int dollarsOff();
    }

    public int admission(int basePrice) {
        SaleTodayOnly sale = new SaleTodayOnly() {      // here we have the anonymous inner class
                                                        // code says to instantiate a new SaleTodayOnly object.
                                                        // But wait - SaleTodayOnly is abstract -> This is OK because we
                                                        //provide the class body right there — anonymously
            @Override
            int dollarsOff() {                          // method has default access
                return 3;
            };
        };
        return basePrice - sale.dollarsOff();
    }


    // ex: an Anonymous inner class implementing an interface
    interface SaleTodayOnly2 {                          // here we have an interface
        int dollarsOff();
    }

    public int admission2(int basePrice) {
        SaleTodayOnly2 sale = new SaleTodayOnly2() {    // here we have the anonymous inner class
                                                        // The anonymous inner class is the same whether you implement an interface
                                                        // or extend a class! Java figures out which one you want automatically.
            @Override
            public int dollarsOff() {                   // method has public access
                return 3;
            }
        };
        return basePrice - sale.dollarsOff();
    }

    // ex: an Anonymous inner class extending an existing class AND implementing an interface
    // you CAN'T do this with an anonymous inner class
    // remember that an anonymous inner class is just an unnamed local inner class
    // so the solution is to use a local inner class -> then you can extend a class and implement as many interfaces as you want

    // ■ you can define an anonymous inner class right where you need it -> even if that is an argument to another method
    // ex:
    interface SaleTodayOnly3 {
        int dollarsOff();
    }

    public int admission3(int basePrice, SaleTodayOnly3 sale) {
        return basePrice - sale.dollarsOff();
    }

    public int pay() {
        return admission3(5, new SaleTodayOnly3() {     // here we have the anonymous inner class
                                                                // we pass it directly to the method that needs it - we don't store it in a local variable
            @Override
            public int dollarsOff() {
                return 3;
            }
        });
    }


    // ■ inner classes as event handlers:
    // ex:
    public static void main(String[] args) {
        JButton button = new JButton("red");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // handle the button click
            }
        });
    } // This technique gives the event handler access to the instance variables in the class with which it goes
}


// ! Inner classes go against some fundamental concepts, such as reuse of classes and high cohesion
// Make sure that inner classes make sense before you use them in your code!
