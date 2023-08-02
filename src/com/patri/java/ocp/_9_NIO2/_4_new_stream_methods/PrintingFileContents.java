package com.patri.java.ocp._9_NIO2._4_new_stream_methods;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class PrintingFileContents {
    public static void main(String[] args) {
        // earlier we presented Files.readAllLines() - using it on a very large file could get us an OutOfMemoryError
        // in NIO.2 API we have another method to solve this problem: Files.lines(Path)
        // Files.lines(Path) = returns a Stream<String> object
        //                  - the contents of the file are read and processed lazily -> this means that only a small portion of the file is stored in memory at any given time

        // ex: Files.lines() <=> Files.readAllLines() - they are equivalent
        Path path = Paths.get("src/com/patri/java/ocp/_9_NIO2/_4_new_stream_methods/file.txt");
        try {
            Files.lines(path).forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println();
        // this approach is more performant on large files since it does not require the entire file to be read and stored in memory

        // ex2: search in file all the lines that start with 'WARN' and displays them
        Path path1 = Paths.get("src/com/patri/java/ocp/_9_NIO2/_4_new_stream_methods/sharks.log");
        try {
            System.out.println(Files.lines(path1)
            .filter(s -> s.startsWith("WARN"))
            .map(s -> s.substring(5))
            .collect(Collectors.toList()));
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println();
        // lambda expressions coupled with NIO.2 allow us to perform very complex file operations


        // ■ Files.readAllLines() vs. Files.lines()
        // Files.readAllLines() - returns a List
        // Files.lines()        - returns a Stream

        // ex: both compile and run without issue
        // forEach() - can be called by both Stream and Collections objects
        try {
            Files.readAllLines(Paths.get("src/com/patri/java/ocp/_9_NIO2/_4_new_stream_methods/file.txt")).forEach(System.out::println);
            // reads the entire file into memory and then performs a print operation on the resulting object
            Files.lines(Paths.get("src/com/patri/java/ocp/_9_NIO2/_4_new_stream_methods/file.txt")).forEach(System.out::println);
            // reads the lines lazily and prints them as they are being read
            // the advantage: it does not require the entire file to be stored in memory as it is being read
        } catch (IOException e) {
            System.out.println(e);
        }

        // ex2: first does not compile
        // filter() - cannot be applied to a Collection without first converting it to a Stream using the stream() method
        try {
            // Files.readAllLines(path).filter(s -> s.length() > 2).forEach(System.out::println);
            // compile error: Cannot resolve method 'filter' in 'List'
            Files.lines(path).filter(s -> s.length()>2).forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
