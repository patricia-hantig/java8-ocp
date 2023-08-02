package com.patri.java.ocp._8_IO._4_interact_with_users;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OldWay {
    // before Java8 we used System.in to obtain text input from the user

    // just like System.out returns a PrintStream and is used to output text data to the user
    // System.in returns an InputStream and is used to retrieve text input from the user

    // ex: this application fetches a single line of text from the user and then outputs it to the user before terminating
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String userInput = reader.readLine();
        System.out.println("You entered the following: " + userInput);
    }

    // we did not close the stream - closing System.in would prevent our application from accepting user input for the remainder of the application execution
    // we use a BufferedReader to allow input that terminates with the Enter key
    // before we can apply BufferedReader we need to wrap System.in using InputStreamReader
    // it allows us to build a Reader object out of an existing InputStream instance
}
