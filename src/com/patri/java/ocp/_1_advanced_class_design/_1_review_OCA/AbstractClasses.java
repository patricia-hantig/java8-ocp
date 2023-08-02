package com.patri.java.ocp._1_advanced_class_design._1_review_OCA;

public class AbstractClasses {
    // An abstract class = may contain or not abstract or concrete methods
    // Abstract methods - can appear only in abstract classes
    // The first concrete subclass of an abstract class is required to implement all abstract methods that were not implemented by a superclass
}

// example:
abstract class Cat {
    // _________________________ what could we have here?
    // abstract void clean();
    // void clean() {}
    // - blank - and abstract class is not required to have any methods in it
    // abstract void clean() throw RuntimeException();
}
class Lion extends Cat {
    void clean() {}
}
