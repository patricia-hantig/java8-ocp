package com.patri.java.ocp._2_design_patterns_and_principles._2_functional_programming;

@FunctionalInterface
public interface Sprint {
    public void sprint(Animal animal);
}

// this is a functional interface because it contains exactly one abstract method

// ■ The annotation @FunctionalInterface is not required but is recommended - good practice
// - the Java compiler knows that when an interface contains exactly one abstract method -> this is a functional interface
// - if a class is marked with @FunctionalInterface annotation and contains more that one abstract method or
// no abstract methods at all -> will not compile! (compiler detects this error)
// - why is recommended to have the annotation? - so that other developers do not treat it as a simple interface and maybe add new methods to it

// ■ How an interface that extends a functional interface becomes a functional interface?
// example: which of the following interfaces are functional interfaces?

interface Run extends Sprint {}             // it's a functional interface

interface SprintFaster extends Sprint {     // it's a functional interface
    public void sprint(Animal animal);
}

interface Skip extends Sprint {             // it's a functional interface
    public default int getHopCount(Animal animal) { return 10;}
    public static void skip(int speed) {}
}

// all the 3 interfaces from above are functional interfaces:
// first - does not contain other abstract methods
// second - overrides the abstract method from the interface that it extends
// third - defines default and static methods (neither is abstract)


// example2: which of the following interfaces are functional interfaces?

interface Walk {}                       // it's NOT a functional interface

interface Dance extends Sprint {        // it's NOT a functional interface
    public void dance(Animal animal);
}

interface Crawl {                       // it's NOT a functional interface
    public void crawl();
    public int getCount();
}

interface Live extends Sprint, Jump {}  // it's NOT a functional interface

// all the 4 interfaces from above are NOT functional interfaces:
// first - doesn't have any method
// second - extends a functional interface but it adds new abstract methods
// third - has more abstract methods
// fourth - extends more than one functional interface -> it has more than one abstract method

// in all of the above examples if we try to add the annotation @FunctionalInterface -> compile error!