package com.patri.java.ocp._1_advanced_class_design._5_enums;

public class ConstructorFieldsAndMethodsInEnums {
    // ■ Enums can have more than just values in them
    // - they can have instance variables, constructors and methods
}

// ex: Our zoo wants to keep track of traffic patterns for which seasons get the most visitors
enum Season_ {

    WINTER("Low"), SPRING("Medium"), SUMMER("High"), FALL("Medium");    // at the end we have
    // a semicolon - it is required if there is anything in the enum besides the values

    private String expectedVisitors;            // instance variable

    // the constructor must be private or protected - because it can be called only from within the enum
    private Season_(String expectedVisitors) {
        this.expectedVisitors = expectedVisitors;
    }

    // the code would not compile with a public constructor:
    // public Season_() {}     // modifier public is not allowed here

    // method
    public void printExpectedVisitors() {
        System.out.println(expectedVisitors);
    }

    public static void main(String[] args) {
        Season_.SUMMER.printExpectedVisitors();
    }
}

// ex: The first time that we ask for any of the enum values - Java constructs all of the enum values.
// After that, Java just returns the already‐constructed enum values.
// You can see why this code calls the constructor only once
enum OnlyOne {
    ONCE(true);
    private OnlyOne(boolean b) {
        System.out.println("constructing");
    }

    public static void main(String[] args) {
        OnlyOne firstCall = OnlyOne.ONCE;       // constructing
        OnlyOne secondCall = OnlyOne.ONCE;      // doesn't print anything
    }
}

// ex: our zoo has different seasonal hours. It is cold and gets dark early in the winter.
// We could keep track of the hours through instance variables, or we can let each enum value manage hours itself
enum Season2 {
    WINTER {
        @Override
        public void printHours() {
            System.out.println("9am - 3pm");
        }
    },
    SPRING {
        @Override
        public void printHours() {
            System.out.println("9am - 5pm");
        }
    },
    SUMMER {
        @Override
        public void printHours() {
            System.out.println("9am - 7pm");
        }
    },
    FALL {
        @Override
        public void printHours() {
            System.out.println("9am - 5pm");
        }
    };

    // the enum has abstract method -> each of the enum values is required to implement this method
    public abstract void printHours();
}

// or it could be like this:
enum Season3 {
    WINTER {
        @Override
        public void printHours() {
            System.out.println("short hours");
        }
    },
    SUMMER {
        @Override
        public void printHours() {
            System.out.println("long hours");
        }
    },
    FALL, SPRING;

    // default method implementation
    public void printHours() {
        System.out.println("default hours");
    };
}
// This one looks better - we only code the special cases and let the others use the enum‐provided implementation

// ■ Try to keep enums simple - just because they can have methods - this doesn't mean that it should
// When they get too long or too complex - it makes the enum hard to read!
