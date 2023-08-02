package com.patri.java.ocp._6_exceptions_and_assertions._2_using_multi_catch;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class MultiCatchExamples {

    // catch (Exception1 e | Exception2 e | Exception3 e) -DOES NOT COMPILE
    // catch (Exception1 e1 | Exception2 e2 | Exception3 e3) -DOES NOT COMPILE
    // catch (Exception1 | Exception2 | Exception3 e) -OK

    // catch (FileNotFoundException | IOException e) -DOES NOT COMPILE (FileNotFoundEx is subclass of IOEx => redundant)

     /*try {
        throw new IOException();
    } catch (IOException | RuntimeException e) {
        e = new RuntimeException();  - DOES NOT COMPILE
    }*/


    //multi-catch is effectively-finale
    public static void main(String[] args) {
        try {
           //code goes here
        } catch (RuntimeException e) {
            e = new RuntimeException(); // legal, but wrong to reassign the exception to a new exception
            e.printStackTrace();
        }
    }

    //examples of multi-catch
    /*public static void doesNotCompile() {

        try {
            mightThrow();
        } catch (FileNotFoundException | IllegalStateException e) {
        } catch (InputMismatchException e | MissingResourceException e) {   // extra variable e
        } catch (SQLException | ArrayIndexOutOfBoundsException e) {         // try cannot potentially throw SQLEx
        } catch (FileNotFoundException | IllegalArgumentException e) {      // FileNotFoundEx already exists in line 42
        } catch (Exception e) {                                             // reversed with next line (IOEx -subclass of Ex)
        } catch (IOException e) {
        }
    }*/

    private static void mightThrow() throws DateTimeParseException, IOException {
    }
}
