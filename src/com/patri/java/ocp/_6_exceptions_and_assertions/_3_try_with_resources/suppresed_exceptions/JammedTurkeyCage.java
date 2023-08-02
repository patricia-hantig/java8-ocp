package com.patri.java.ocp._6_exceptions_and_assertions._3_try_with_resources.suppresed_exceptions;

public class JammedTurkeyCage implements AutoCloseable {
    @Override
    public void close() throws IllegalStateException {  //this throws an unchecked exception
        throw new IllegalStateException("Cage door does not close");
    }

    public static void main(String[] args) {

        System.out.println("======================first=========================");

        try (JammedTurkeyCage turkeyCage = new JammedTurkeyCage()) {
            System.out.println("Put turkeys in");
        } catch (IllegalStateException e) {
            System.out.println("caught: " + e.getMessage());
        }

        System.out.println("======================second=========================");

        // what happens if the try block also throws an exception? ->suppressed exceptions
        try (JammedTurkeyCage turkeyCage = new JammedTurkeyCage()) {
            throw new IllegalStateException("turkeys ran off");         //throws the primary exception

            // here JammedTurkeyCage ends and close() automatically adds the exception from close() as a suppressed exception

        } catch (IllegalStateException e) {                             //catches the primary exception
            System.out.println("caught (primary exception): " + e.getMessage());    //prints the message from primary exception
            for (Throwable t : e.getSuppressed())                        //loops through suppressed exceptions and prints them
                System.out.println("caught (suppressed exception): " + t.getMessage());
        }

        System.out.println("======================third=========================");

        // catch looks for matches on the primary exception => if it doesn't find them: throws exception
        /*try (JammedTurkeyCage turkeyCage = new JammedTurkeyCage()) {
            throw new RuntimeException("turkeys ran off");
        } catch (IllegalStateException e) {
            System.out.println("caught: " + e.getMessage());
        }*/

        System.out.println("======================fourth=========================");

        // what happens if 2 exceptions are thrown when closing resources => resources are closed in reverse order of declaring
        try (JammedTurkeyCage turkeyCage1 = new JammedTurkeyCage();         //t1 has the suppressed exception
             JammedTurkeyCage turkeyCage2 = new JammedTurkeyCage()) {       //t2 has the primary exception
            System.out.println("turkeys entered cages");
        } catch (IllegalStateException e) {                             //catches the primary exception
            System.out.println("caught (primary exception for closing t2): " + e.getMessage());    //prints the message from primary exception
            for (Throwable t : e.getSuppressed())                        //loops through suppressed exceptions and prints them
                System.out.println("caught (suppressed exception for closing t1): " + t.getMessage());
        }

        System.out.println("======================fifth=========================");

        // try() throws an exception - primary exception
        // Java tries to close the resource and adds a suppressed exception to it
        // now we have a problem: the finally() runs after all this => since finally throws exception
        // the previous exception is lost => ~BAD PROGRAMMING!!!!~ we don't want to lose exceptions
        try (JammedTurkeyCage turkeyCage = new JammedTurkeyCage()) {
            throw new IllegalStateException("turkeys ran off");
        } finally {
            throw new RuntimeException("and we couldn't find them");
        }
    }
}
