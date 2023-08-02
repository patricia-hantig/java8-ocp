package com.patri.java.ocp._6_exceptions_and_assertions._5_assertions;

//assertions can be used for control flow invariants (you assert that a line of code that you think is unreachable is never reached)

// run in terminal:
// javac src\package_name.class_name.java - to compile
// cd src
// java -ea package-name.class_name - to enable assertions
public class TestSeasons {

    public static void test(Seasons seasons) {
        switch (seasons) {
            case SPRING:
            case FALL:
                System.out.println("Shorter hours");
                break;
            case SUMMER:
                System.out.println("Longer hours");
                break;
            default:
                assert false : "Invalid season";
        }
    }

    public static void main(String[] args) {
        test(Seasons.SPRING);
        test(Seasons.SUMMER);
        test(Seasons.FALL);
        test(Seasons.WINTER); //if assertions are enabled it should throw AssertionError: Invalid season

        //assertions should not alter the outcome
        int x = 10;
        assert ++x > 10; // NOT A GOOD DESIGN

    }
}
