package com.patri.java.ocp._6_exceptions_and_assertions._2_using_multi_catch;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

//before java 7 there were 2 approaches to deal with duplicated code
//one was to catch 'Exception' instead of the specific types - BAD approach
public class Dolphin2 {

    public static void main(String[] args) {
        try {
            Path path = Paths.get("src/com/patri/java/ocp/_6_exceptions_and_assertions/using_multi_catch/dolphinsBorn.txt");
            String text = new String(Files.readAllBytes(path));
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
//            LocalDate date = LocalDate.parse(text, formatter);
            LocalDate date = LocalDate.parse(text);
            System.out.println(date);
        } catch (Exception e) { //BAD approach
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        //to run without error uncomment lines 18, 19 and comment line 20
    }
}
