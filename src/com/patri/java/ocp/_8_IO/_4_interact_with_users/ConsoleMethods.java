package com.patri.java.ocp._8_IO._4_interact_with_users;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.util.Arrays;

public class ConsoleMethods {
    // System.in and System.out are similar to Console - but Console has more methods

    // ■■■ Methods from Console class

    // ■ reader() and writer()
    // - provide access to an instance of Reader and PrintWriter
    // - access to those classes is analogous to calling System.in and System.out
    // - they are used for raw access to the user input and output stream

    // ■ format() and printf()
    // ex: sample application that prints information to the user
    private static void formatAndPrintMethods() throws NumberFormatException, IOException{
        Console console = System.console();
        if (console == null) {
            throw new RuntimeException("Console not available");
        } else {
            console.writer().println("Welcome to Our Zoo!");
            console.format("Our zoo has 391 animals and employs 25 people");
            console.writer().println();
            console.printf("The zoo spans 128.91 acres");
        }
    }

    // ■ flush()
    // - forces any buffered output to be written immediately
    // - it's recommended that you call flush90 before any readLine() or readPassword() - to ensure that no data is pending during the read

    // ■ readLine()
    // - retrieves a single line of text from the user and the user presses Enter to terminate it
    // - we also have a method readLine(String format, Object... args) - display a formatted line
    // - returns a String

    // ex: reads information from the user and writes it back to the screen
    private static void readLineMethod() throws IOException {
        Console console = System.console();
        if (console == null) {
            throw new RuntimeException("Console not available");
        } else {
            console.writer().print("How excited are you about your trip today? ");
            console.flush();

            String excitementAnswer = console.readLine();
            String name = console.readLine("Please enter your name: ");

            Integer age = null;
            console.writer().print("What is your age? ");
            console.flush();
            BufferedReader reader = new BufferedReader(console.reader());
            String value = reader.readLine();
            age = Integer.valueOf(value);
            console.writer().println();

            console.format("Your name is " + name);
            console.writer().println();
            console.format("Your age is " + age);
            console.printf(" and your excitement level is " + excitementAnswer);
        }
    }

    // ■ readPassword()
    // - similar to readLine() - but echoing is disabled
    // - the user does not see the text they are typing
    // - we also have a method readPassword(String format, Object... args) - for formatted prompt
    // - returns an array of characters - why? = as you remember String values are added in the string pool
    // this means that if it would return a String - it might be available in the String pool
    // we use a character array instead - as soon as the data is read and used we can write garbage data into the array
    // this would remove the password from memory before it would be removed by garbage collection if a String value was used

    // ex: application that checks if the first password you entered is the same as the reentering password
    private static void passwordCompare() throws NumberFormatException, IOException {
        Console console = System.console();
        if (console == null) {
            throw new RuntimeException("Console not available");
        } else {
            char[] password = console.readPassword("Enter your password: ");
            console.format("Enter your password again: ");
            console.flush();
            char[] verify = console.readPassword();
            boolean match = Arrays.equals(password, verify);

            // Immediately clear password from memory
            for (int i = 0; i < verify.length; i++) {
                verify[i] = 'x';
            }
            // we could have here something like this (instead of for):
            Arrays.fill(password, 'x'); // to wipe an array's data
            // we immediately clear the character arrays that store the password as soon as they are no longer needed in the application

            console.format("Your password was " + (match ? "correct" : "incorrect"));
        }
    }
    // both methods for readPassword() are used

    public static void main(String[] args) throws NumberFormatException, IOException {
        //formatAndPrintMethods();
        //readLineMethod();
        passwordCompare();
    }
}
