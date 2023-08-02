package com.patri.java.ocp._1_advanced_class_design._6_nested_classes;

public class LocalInnerClasses {
    // ■ Local inner class = is defined within a method
    // a Local inner class does not exist until the method is invoked and it's in scope until the method returns

    // ■ Properties:
    // - doesn't have access modifiers
    // - can't be static and can't have static fields or methods
    // - has access to all fields and methods from the enclosing class
    // - has access only to local variables of a method that are final or effectively final

    // ■ effectively final = new in Java 8 - if the code compiles with the keyword 'final' inserted before a local variable -> the variable is effectively final
    // effectively final variable = a variable that is not changed from the beginning until the end of the program
    // ex: Which variables are effectively final?
    public void isItFinal() {
        int one = 20;           // one - effectively final
        int two = one;          // two - not effectively final - the value changes
        two++;
        int three;              // three - effectively final => it is assigned only once
        if (one == 4) three = 3;
        else three = 4;
        int four = 4;           // four - not effectively final
        class Inner {}
        four = 5;
    }

    // ■ why we can use only local variables that are final or effectively final?
    // The compiler is generating a class file from your inner class.
    // A separate class has no way to refer to local variables.
    // If the local variable is final, Java can handle it by passing it to
    // the constructor of the inner class or by storing it in the class file.
    // If it weren’t effectively final, these tricks wouldn’t work because
    // the value could change after the copy was made.
}

// ex for Local inner class:
class Outer {
    private int length = 5;
    public void calculate() {
        final int width = 20;

        class Inner {                               // here is the local inner class
            public void multiply() {
                System.out.println(length * width); // instance variable and local variable
            }
        }

        Inner inner = new Inner();
        inner.multiply();
    }                                               // here ends the class's scope

    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.calculate();
    }
}
