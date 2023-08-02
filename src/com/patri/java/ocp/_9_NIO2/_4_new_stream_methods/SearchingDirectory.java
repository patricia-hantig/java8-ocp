package com.patri.java.ocp._9_NIO2._4_new_stream_methods;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class SearchingDirectory {
    public static void main(String[] args) {
        // Files.find(Path, int, BiPredicate) = similar with Files.walk() but it requires the depth value to be explicitly set and a BiPredicate to filter the data
        // find() - also supports the FOLLOW_LINK vararg option
        // BiPredicate = is an interface that takes 2 generic objects and returns a boolean value: (T, U) -> returns boolean; T = Path, U = BasicFileAttributes

        // ex:
        Path path = Paths.get("src/com/patri/java/ocp/_9_NIO2/_4_new_stream_methods");
        long dateFilter = 1420070400000l;   // 31 Dec 2014
        long dateFilter2 = 1607979132781l;  // 14 Dec 2020

        try {
            Stream<Path> stream = Files.find(path, 10,
                    (p, a) -> p.toString().endsWith(".java")
                    && a.lastModifiedTime().toMillis() > dateFilter2);
            stream.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(e);
        }

        // this ex is similar to the one from DirectoryWalking but it is more advanced
        // it also sets the directory depth limit to search to 10 as opposed to relying on the default Integer.MAX_VALUE value that Files.walk() uses
    }
}
