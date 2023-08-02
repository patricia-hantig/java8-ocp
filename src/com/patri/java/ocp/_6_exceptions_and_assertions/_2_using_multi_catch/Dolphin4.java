package com.patri.java.ocp._6_exceptions_and_assertions._2_using_multi_catch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

//in java 7 it was introduced multi-catch: multiple exceptions in the same catch block
public class Dolphin4 {

    public static void main(String[] args) {
        try {
            Path path = Paths.get("src/com/patri/java/ocp/_6_exceptions_and_assertions/using_multi_catch/dolphinsBorn.txt");
            String text = new String(Files.readAllBytes(path));
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
//            LocalDate date = LocalDate.parse(text, formatter);
            LocalDate date = LocalDate.parse(text);
            System.out.println(date);
        } catch (DateTimeParseException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        //to run without error uncomment lines 17, 18 and comment line 19
    }
}
