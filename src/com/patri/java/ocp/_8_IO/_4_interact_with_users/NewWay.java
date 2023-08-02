package com.patri.java.ocp._8_IO._4_interact_with_users;

import java.io.Console;

public class NewWay {
    // System.in and System.out - raw streams available since the beginning
    // in java6 => was added java.io.Console class - with multiple methods

    // ■ Console    - singleton class -> means there is only one version of the object available in the JVM
    //              - created automatically by the JVM
    //              - accessed by calling: System.console() method
    //              - System.console() could return null if text interactions are not supported

    // ex:
    public static void main(String[] args) {
        Console console = System.console();
        if (console != null) {
            String userInput = console.readLine();
            console.writer().println("You entered: " + userInput);
        }
    }

    // if you try to run it from IDEA it won't work because IDEA does not support text interactions
    // Solution to run it: run it from Terminal
    // View -> Tools Operations -> Terminal
    // run the class:
    // go to directory where the java class is
    // javac NameOfTheClass.java
    // go to directory above src
    // java -cp src "classpath".NameOfTheClass

    // write what you want to display and you get something like:
    // C:\Users\Patry\IdeaProjects\OCA_and_OCP>java -cp src com.patri.java.ocp._8_IO._4_interact_with_users.NewWay
    // this is a test
    // You entered: this is a test

    // what the code does:
    // - retrieves an instance of the Console singleton and determines if the Console is available by checking it for a null value
    // - if the Console is available, it retrieves a line of input from the user using the readLine() method
    // - then it outputs the result using the Console’s built-in PrintWriter object, accessed via the writer() method
}
