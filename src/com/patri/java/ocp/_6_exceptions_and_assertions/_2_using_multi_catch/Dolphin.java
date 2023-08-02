package com.patri.java.ocp._6_exceptions_and_assertions._2_using_multi_catch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

//contains duplicating code (e.g.: catch) - duplicating code is bad
public class Dolphin {

    public static void main(String[] args) {
        try {
            Path path = Paths.get("src/com/patri/java/ocp/_6_exceptions_and_assertions/using_multi_catch/dolphinsBorn.txt");
            String text = new String(Files.readAllBytes(path));
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
//            LocalDate date = LocalDate.parse(text, formatter);
            LocalDate date = LocalDate.parse(text);
            System.out.println(date);
        } catch (DateTimeParseException e) {
            //enters here if file .txt is empty or contains something that can't be parsed (e.g.: text)
            //or if there isn't a formatter declared
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            //enters here if the file doesn't exists or if file is declared like this
            //Path path = Paths.get("dolphinsBorn.txt");
            //the path is not ok declared to reach the file
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        //to run without error uncomment lines 17, 18 and comment line 19
    }
}
