package com.patri.java.ocp._1_advanced_class_design._1_review_OCA;

public class StaticAndFinal {
    // final = a final variable can't be changed
    //       = a final method can't be overridden

    // static = a static variable can be used everywhere in the class - makes a variable shared at the class level
    //        = to refer to a static method we have to use the class name
    // ex: static method1() in class A => we refer to it as: A.method1();

    // static and final are allowed to be added on the class level
    // static class = nested class (you will see at the end of this chapter)
    // final class = it can't be subclassed and can't be also marked abstract
    // ex String is final class
}

// example: To which lines in the following code could you independently add static and/or final
// without introducing a compiler error?
abstract class Cat_ {
    String name = "The Cat";        // static (the variable gets to be accessed: Cat.name), final
    void clean() { }                // can't be static - because the subclass overrides it
                                    // can't be final - the subclass method would no longer be able to override it
}
class Lion_ extends Cat_ {
    void clean() { }                // can't be static - It could be added to both methods, but then you wouldn’t be inheriting the method
                                    // final can be added here since there are no subclasses of Lion
}