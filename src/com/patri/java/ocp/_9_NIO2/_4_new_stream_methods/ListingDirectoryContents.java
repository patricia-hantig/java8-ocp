package com.patri.java.ocp._9_NIO2._4_new_stream_methods;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ListingDirectoryContents {
    public static void main(String[] args) {
        // Files.list(Path) = lists the content of a directory -> only the direct children
        // list() - is similar to walk() but 1 level deep
        // list() - is similar to java.io.File.listFiles() - except that it relies on streams

        // ex:
        try {
            Path path = Paths.get("src/com/patri/java/ocp/_9_NIO2/_4_new_stream_methods");
            Files.list(path)
                    .filter(p -> !Files.isDirectory(p))
                    .map(p -> p.toAbsolutePath())
                    .forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
