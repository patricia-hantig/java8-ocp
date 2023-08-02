package com.patri.java.ocp._1_advanced_class_design._6_nested_classes;

public class ReviewTypesOfNestedClasses {
    // ■ Types of nested classes

    //                      |   Member inner class  |   Local inner class   |   Anonymous inner class   |   static nested class
    // ----------------------------------------------------------------------------------------------------------------------------------
    //  Access modifiers    |   public, protected,  |       None            |           None            |   public, protected,
    //      allowed         |   private or default  |   local to method     |      local to statement   |   private or default
    // ----------------------------------------------------------------------------------------------------------------------------------
    //  Can extend any class|                       |                       |           NO              |
    //  and any number of   |           YES         |           YES         |   must extend one class or|           YES
    //      interfaces      |                       |                       |   implement one interface |
    // ----------------------------------------------------------------------------------------------------------------------------------
    //  Can be abstract     |           YES         |           YES         |   - (no class definition) |           YES
    // ----------------------------------------------------------------------------------------------------------------------------------
    //  Can be final        |           YES         |           YES         |   - (no class definition) |           YES
    // ----------------------------------------------------------------------------------------------------------------------------------
    //  Can access instance |                       |                       |                           |           NO
    //  members of enclosing|           YES         |           YES         |           YES             |   not directly - required an
    //      class           |                       |                       |                           |   instance of enclosing class
    // ----------------------------------------------------------------------------------------------------------------------------------
    //  Can access local    |                       |           YES         |           YES             |
    //      variables of    |           NO          |       if final or     |   if final or effectively |           NO
    //  enclosing class     |                       |   effectively final   |           final           |
    // ----------------------------------------------------------------------------------------------------------------------------------
    //  Can declare static  |           NO          |           NO          |           NO              |           YES
    //      methods         |                       |                       |                           |
    // ----------------------------------------------------------------------------------------------------------------------------------
}
