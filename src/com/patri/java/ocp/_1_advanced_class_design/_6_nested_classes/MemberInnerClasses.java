package com.patri.java.ocp._1_advanced_class_design._6_nested_classes;

public class MemberInnerClasses {
    // ■ Member inner class = is defined at the member level of a class (same level as the methods, instance variables and constructors)

    // ■ Properties:
    // - can be public, private, protected or default
    // - can extend any class or implement interfaces
    // - can be abstract or final
    // - cannot declare static fields or methods
    // - can access members of the outer class including private members

    // ex:
    private String greeting = "Hi";

    protected class Inner {
        public int repeat = 3;
        public void go() {
            for (int i = 0; i < repeat; i++)
                System.out.println(greeting);
        }
    }

    public void callInner() {
        Inner inner = new Inner();
        inner.go();
    }

    public static void main(String[] args) {
        MemberInnerClasses outer = new MemberInnerClasses();
        outer.callInner();

        // another way to instantiate Inner class
        MemberInnerClasses outer2 = new MemberInnerClasses();
        Inner inner2 = outer2.new Inner();                      // create the inner class
        inner2.go();
    }

    // ■ compiling MemberInnerClasses.java class -> creates 2 class files: MemberInnerClasses.class and MemberInnerClasses$Inner.class
}

// ■ inner classes can have the same variable name as the outer classes - we use 'this' to say which class we want to access
// ex:
class A {
    private int x = 10;

    class B {
        private int x = 20;

        class C {
            private int x = 30;

            public void allTheX() {
                System.out.println(x);          // 30 - instance variable on the current class
                System.out.println(this.x);     // 30 - instance variable on the current class
                System.out.println(B.this.x);   // 20 - instance variable on the class B
                System.out.println(A.this.x);   // 10 - instance variable on the class A
            }
        }
    }

    public static void main(String[] args) {
        A a = new A();
        A.B b = a.new B();
        A.B.C c = b.new C();
        c.allTheX();
    }
}

// ■ a class can have also inner interfaces that are used within an inner class
// ex:
class CaseOfThePrivateInterface {
    private interface Secret {          // the interface can (not must) be private - this means
                                        // that the interface can only be referred to within the current outer class
        public void shh();
    }

    class DontTell implements Secret {

        @Override
        public void shh() { }
    }
}