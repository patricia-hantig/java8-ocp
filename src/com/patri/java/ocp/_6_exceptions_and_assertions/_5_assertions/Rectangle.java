package com.patri.java.ocp._6_exceptions_and_assertions._5_assertions;

//assertions can be used for class invariant (you assert the state of an object)

// run in terminal:
// javac src\package_name.class_name.java - to compile
// cd src
// java -ea package-name.class_name - to enable assertions
public class Rectangle {
    private int width;
    private int height;

    public Rectangle(int width, int height) {
        /*if (width < 0 || height < 0) {
            throw new IllegalArgumentException();
        }*/
        // to see the right approach for this, please uncomment the lines above
        this.width = width;
        this.height = height;
    }

    private int getArea() {
        assert isValid() : "Not a valid rectangle";
        return width * height;
    }

    private boolean isValid() { // DO NOT USE THIS APPROACH to check if it's a valid argument -> this should be done
                                // in the constructor
                                // assertions are used when you are sure of something and just want to verify it
        return (width >= 0 && height >= 0);
    }

    public static void main(String[] args) {
        Rectangle rectangle1 = new Rectangle(6, 10);
        Rectangle rectangle2 = new Rectangle(-5, 10);
        System.out.println("Area of first rectangle is: " + rectangle1.getArea());
        System.out.println("Area of second rectangle is: " + rectangle2.getArea());
    }
}
