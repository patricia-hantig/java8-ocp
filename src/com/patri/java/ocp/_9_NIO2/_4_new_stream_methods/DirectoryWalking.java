package com.patri.java.ocp._9_NIO2._4_new_stream_methods;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryWalking {
    public static void main(String[] args) {
        // A directory can contain files and other directories
        // Every record in a file system has exactly one parent - exception root directory which sits atop everything

        // ■ File and Directory as a tree structure:
        //      c:
        //          ->   app
        //                      -> animals
        //                                  -> Bear.java
        //                                  -> Bear.class
        //                      -> employees
        //                      -> java.exe
        //          ->  zoo
        //          ->  info.txt

        // ■ Walking a directory = the process by which you start with a parent directory and iterate over all of its descendants
        //                      until some condition is met or there are no more elements over which to iterate


        // ■■■ Selecting a search strategy
        // DFS (Depth-First Search)
        // BFS (Breadth-First Search)

        // ■ we have the following graph:
        //              9
        //            /   \
        //          7       3
        //         / \     / \
        //        5   1   2   4

        // BFS: 9, 7, 3, 5, 1, 2, 4
        // DFS: 9, 7, 5, 1, 3, 2, 4

        // Streams API uses depth-first searching with a default maximum depth of Integer.MAX_VALUE
        // ■ DFS vs BFS:
        // - DFS -> require less memory
        // - BFS -> works better when the node for which you are searching is likely near the root


        // ■■■ Walking a Directory
        // Files.walk(Path) = returns a Stream<Path> obj that traverses the directory in DFS, lazy manner
        // Lazy = all the directories are created and read in the moment one directory is reached
        // - when you create a Stream<Path> obj using Files.walk() -> the contents of the directory have not yet been traversed

        // ex: iterates over a directory and outputs all the .java files from it:
        Path path = Paths.get("src/com/patri/java/ocp/_9_NIO2/_4_new_stream_methods");
        try {
            Files.walk(path)
                    .filter(p -> p.toString().endsWith(".java"))
                    .forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(e);
        }
        // In practice: you may want to set a limit to prevent your application from searching too deeply on a large directory structure and taking too much time

        // ■ Why is Integer.MAX_VALUE the Depth Limit?
        // - because most file systems do not support path values deeper than what can be stopped in an int
        // - using Integer.MAX_VALUE is like using an infinite value

        // we have another method that behaves similar to Files.walk():
        // Files.newDirectoryStream() - but the difference is that the DirectoryStream<Path< obj that it returns DOES NOT inherit from Stream class -> the stream operations can't be applied


        // ■■■ Avoiding Circular Paths
        // walk() - will not traverse symbolic link by default
        // symbolic link could lead to a cycle
        // Cycle = is an infinite circular dependency in which an entry in a directory is an ancestor of the directory

        // ex:
        // - let's suppose that we have the directory /birds/robin
        // - that contains a symbolic link called /birds/robin/allBirds that points to /birds
        // - if we try to traverse the /birds/robin directory => would result in an infinite loop - because
        // each time the allBirds subdirectory is reached -> we would go back to the parent path

        // if you need to traverse a symbolic link you could change the default behavior: NIO.2 offers the FOLLOW_LINKS option as a vararg to the walk() method
        // it is recommended to specify an appropriate depth limit when this option is used
        // walk() throws a FileSystemLoopException if a cycle is detected
    }
}
