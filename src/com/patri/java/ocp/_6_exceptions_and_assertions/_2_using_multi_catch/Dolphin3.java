package com.patri.java.ocp._6_exceptions_and_assertions._2_using_multi_catch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

//before java 7 there were 2 approaches to deal with duplicated code
//another one was to extract duplicate code into a helper method
public class Dolphin3 {

    public static void main(String[] args) {
        try {
            Path path = Paths.get("src/com/patri/java/ocp/_6_exceptions_and_assertions/using_multi_catch/dolphinsBorn.txt");
            String text = new String(Files.readAllBytes(path));
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
//            LocalDate date = LocalDate.parse(text, formatter);
            LocalDate date = LocalDate.parse(text);
            System.out.println(date);
        } catch (DateTimeParseException e) {
            handleException(e);
        } catch (IOException e) {
            handleException(e);
        }
        //to run without error uncomment lines 19, 20 and comment line 21
    }

    private static void handleException(Exception e) {
        e.printStackTrace();
        throw new RuntimeException(e);
    }
}
