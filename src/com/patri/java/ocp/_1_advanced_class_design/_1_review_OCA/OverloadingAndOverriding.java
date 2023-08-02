package com.patri.java.ocp._1_advanced_class_design._1_review_OCA;

public class OverloadingAndOverriding {
    // method signature = method name + parameter list
    // overriding = same signature
    // overloading = same method name but different parameter list (different type or number)
}

// example:
class Bobcat {
    public void findDen() {}
}

class BobcatKitten extends Bobcat {
    public void findDen() {}                            // override
    public void findDen(boolean b) {}                   // overload
    public int findden() throws Exception { return 0; } // not override or overload
}

// if there are multiple overloaded methods -> Java chooses the closest match:
// 1. exact match by type
// 2. matching a superclass type
// 3. converting to a larger primitive type
// 4. converting to an autoboxed type
// 5. varargs

// overridden method should follow the next rules:
// 1. access modifier must be the same or more accessible
// 2. return type must be the same or more restrictive
// 3. if there are thrown checked exceptions - only the same exceptions or subclasses of those exceptions are allowed to be thrown

// if the methods are static -> method is hidden (not overridden)