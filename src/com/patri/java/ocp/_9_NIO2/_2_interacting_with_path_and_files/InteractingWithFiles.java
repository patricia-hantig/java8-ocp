package com.patri.java.ocp._9_NIO2._2_interacting_with_path_and_files;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InteractingWithFiles {
    public static void main(String[] args) {
        // many of the same operations available in java.io.File are available to java.nio.file.Path using a helper class java.nio.file.Files
        // many methods from Files will throw an exception if the file to which the Path refers does not exist
        // pay attention to File and Files classes: - NIO.2 Files helper class is not related to File class
        //                                          (Files operates on Path instances and File operates on File instances)

        // ■■■ Files methods by related functionality:

        // ■■ Testing a Path with exists()
        // Files.exists(Path) = takes a Path obj and returns true if it references a file that exists in the file system
        // ex:
        System.out.println(Files.exists(Paths.get("/ostrich/feathers.png")));
        System.out.println(Files.exists(Paths.get("src/com/patri/java/ocp/_9_NIO2/_2_interacting_with_path_and_files/UsingPathObjects.java"))); // this tests a file
        System.out.println(Files.exists(Paths.get("src/com/patri/java/ocp/_9_NIO2/_2_interacting_with_path_and_files")));   // this tests a directory
        System.out.println();


        // ■■ Testing Uniqueness with isSameFile()
        // Files.isSameFile(Path, Path) = checks if 2 Path objects relate to the same file within the file system
        //                              - it takes 2 Path obj as input and follows symbolic links
        //                              - the name also check if 2 Path obj refer to the same directory
        // Symbolic link = special file that serves as a reference or pointer to another file or directory

        // - the method first checks if the Path obj are equal() -> if equals() == true => isSameFile() returns true without checking to see if the file exists
        //                                                       -> if equals() == false => it locates each file and determines if the files are the same, throwing an IOException if either file does not exist
        // - the method does not compare the contents of the file
        // ex: if 2 files have identical content and attributes (one is like a copy of the other) but if they are in different locations => then the method will return false

        // ex: all the files exists within the file system and 'cobra.txt' is a symbolic link to 'snake.txt' file
        try {
            System.out.println("First Try block");
            System.out.println(Files.isSameFile(Paths.get("src/com/patri/java/ocp/_9_NIO2/_2_interacting_with_path_and_files/cobra.txt"), Paths.get("src/com/patri/java/ocp/_9_NIO2/_2_interacting_with_path_and_files/snake.txt")));

            System.out.println(Files.isSameFile(Paths.get("src/com/patri/java/ocp/_9_NIO2/_2_interacting_with_path_and_files/tree/../monkey"), Paths.get("src/com/patri/java/ocp/_9_NIO2/_2_interacting_with_path_and_files/monkey")));
            // the symbol '..' cancels out the 'tree' path of the path

            System.out.println(Files.isSameFile(Paths.get("src/com/patri/java/ocp/_9_NIO2/_2_interacting_with_path_and_files/./giraffe.log"), Paths.get("src/com/patri/java/ocp/_9_NIO2/_2_interacting_with_path_and_files/giraffe.log")));
            // symbol . leaves the path unmodified

            System.out.println(Files.isSameFile(Paths.get("src/com/patri/java/ocp/_9_NIO2/_2_interacting_with_path_and_files/flamingo/tail.data"), Paths.get("src/com/patri/java/ocp/_9_NIO2/_2_interacting_with_path_and_files/cardinal/tail.data")));
            // neither file is a symbolic link to the other ('flamingo/tail.data' and 'cardinal/tail.data')
            // even if the files have the same name and the same contents -> if they are at different locations => they are considered different files within the file system
        } catch (IOException e) {
            System.out.println(e);
            // handle file I/O exception
        }


        // ■■ Create directories with createDirectory() and createDirectories()
        // Files.createDirectory()      = creates a directory
        //                              - throws ex if the parent directory does not exist
        // Files.createDirectories()    = creates the target directory along with any nonexistent parent directories
        // - these methods can throw the checked exception IOException - when the directory cannot be created or already exists
        // - both methods accept an optional list of FileAttribute<?> values to set on the newly created directory or directories

        // ex:
        /* try {
            System.out.println("Second Try block");
            Files.createDirectory(Paths.get("src/com/patri/java/ocp/_9_NIO2/_2_interacting_with_path_and_files/newCreatedDirectory"));
            // creates new directory: 'newCreatedDirectory' in directory '_2_interacting_with_path_and_files' - if it doesn't exists -> otherwise throws exception
            Files.createDirectories(Paths.get("src/com/patri/java/ocp/_9_NIO2/_2_interacting_with_path_and_files/newDirectoryParent/newDirectory"));
            // creates new directory 'newDirectory' & his parent 'newDirectoryParent' in directory '_2_interacting_with_path_and_files' - if it doesn't exists -> otherwise throws exception
        } catch (IOException e) {
            System.out.println(e);
        } */
        // be sure the directories does not exists before you run the code from above if you don't want to get a checked IOException


        // ■■ Duplicating File Contents with copy()
        // Files.copy(Path, Path)   = copies a file/directory form one location to another
        //                          - throws checked IOException when the file/directory does not exist or cannot be read
        //                          - the files and subdirectories within the directory are not copied

        // ex: to copy the contents of a directory -> we need to create a function to traverse the directory and copy each file and subdirectory individually:
        /*try {
            Files.copy(Paths.get("src/com/patri/java/ocp/_9_NIO2/_2_interacting_with_path_and_files/panda"), Paths.get("src/com/patri/java/ocp/_9_NIO2/_2_interacting_with_path_and_files/panda-save"));
            // copy only the directory 'panda' creating new directory 'panda-save'
            Files.copy(Paths.get("src/com/patri/java/ocp/_9_NIO2/_2_interacting_with_path_and_files/panda/bamboo.txt"), Paths.get("src/com/patri/java/ocp/_9_NIO2/_2_interacting_with_path_and_files/panda-save/bamboo.txt"));
            // copy the bamboo.txt file from the directory 'panda' to the directory 'panda-save'
        } catch (IOException e) {
            System.out.println(e);
        }*/
        // be sure the directories does not exists before you run the code from above if you don't want to get a checked IOException

        // copying files and directories will traverse symbolic links - it will not overwrite a file or directory if it already exists and it will not copy file attributes
        // this could be change by providing additional options: NOFOLLOW_LINKS, REPLACE_EXISTING and COPY_ATTRIBUTES

        // ■ NIO.2.Files API contains 2 copy() methods for copying files using java.io streams:
        // Files.copy(InputStream, Path)    = it reads the contents from the stream and write the output to a file represented by a Path object
        //              source: java.io.InputStream
        //              target: Path obj
        //              - this method supports optional vararg options - because the data is being written to a file represented by a Path object
        // Files.copy(Path, OutputStream)   = it reads the contents of the file and writes the output to the stream
        //              source: Path obj
        //              target: java.io.OutputStream
        //              - this method does not support optional vararg values - because the data is being written to a stream that may not represent a file system resource.

        // ex:
        /*try (InputStream in = new FileInputStream("src/com/patri/java/ocp/_9_NIO2/_2_interacting_with_path_and_files/source-data.txt");
             OutputStream out = new FileOutputStream("src/com/patri/java/ocp/_9_NIO2/_2_interacting_with_path_and_files/output-data.txt")) {
            // Copy stream data to file
            Files.copy(in, Paths.get("src/com/patri/java/ocp/_9_NIO2/_2_interacting_with_path_and_files/wolf.txt"));
            // Copy file data to stream
            Files.copy(Paths.get("src/com/patri/java/ocp/_9_NIO2/_2_interacting_with_path_and_files/source-data.txt"), out);
        } catch (IOException e) {
            System.out.println(e);
        }*/
        // be sure the directories does not exists before you run the code from above if you don't want to get a checked IOException


        // ■■ Changing a File Location with move()
        // Files.move(Path, Path)   = moves or renames a file or directory within the file system
        //                          - throws checked IOException if the file/directory could not be found or moved
        //                          - can be applied only to non-empty directories and only if they are on the same drive
        //                          moving an empty directory across drive is supported
        //                          moving a non-empty directory across a drive will throw an NIO.2 DirectoryNotEmptyException
        // - the method will follow links, throw an exception if the file already exists and not perform an atomic move
        // - this behavior can be changed using optional values: NOFOLLOW_LINKS, REPLACE_EXISTING or ATOMIC_MOVE
        // - if the system does not support atomic moves -> thrown AtomicMoveNotSupportedException at runtime
        // ex:
        /*try {
            Files.move(Paths.get("src/com/patri/java/ocp/_9_NIO2/_2_interacting_with_path_and_files/zoo"), Paths.get("src/com/patri/java/ocp/_9_NIO2/_2_interacting_with_path_and_files/zoo-new"));
            // renames the 'zoo' directory to 'zoo-new' directory, keeping all of the original contents from the source directory
            Files.move(Paths.get("src/com/patri/java/ocp/_9_NIO2/_2_interacting_with_path_and_files/addresses.txt"), Paths.get("src/com/patri/java/ocp/_9_NIO2/_2_interacting_with_path_and_files/zoo-new/addresses2.txt"));
            // moves the addresses.txt file from the directory '_2_interacting_with_path_and_files' to the directory 'zoo-new' , and it renames it to 'addresses2.txt'
        } catch (IOException e) {
            System.out.println(e);
        }*/
        // be sure the directories does exists before you run the code from above if you don't want to get a checked IOException


        // ■■ Removing a File with delete() and deleteIfExists()
        // Files.delete(Path)   = deletes a file or empty directory within the file system
        //                      - throws the checked IOException    - if the path represents a non-empty directory - DirectoryNotEmptyException
        //                                                          - if the target of the path is a symbolic link -> the symbolic link will be deleted - not the target of the link
        // Files.deleteIfExists(Path)   = identical to delete(Path) - but it will not throw exception if the file or directory does not exist -> it will return a 'false' boolean value
        //                              - it will throw exception if the directory is not empty
        // ex:
        /*try {
            System.out.println("delete() try block");
            Files.delete(Paths.get("src/com/patri/java/ocp/_9_NIO2/_2_interacting_with_path_and_files/feathers.txt"));
            // deletes the 'feathers.txt' file and it throws a NoSuchFileException if the file or directory does not exist
            System.out.println(Files.deleteIfExists(Paths.get("src/com/patri/java/ocp/_9_NIO2/_2_interacting_with_path_and_files/pigeon")));
            // deletes the 'pigeon' directory assuming it is empty - if the pigeon directory does not exist, then will not throw an exception and prints 'false'
            System.out.println(Files.deleteIfExists(Paths.get("src/com/patri/java/ocp/_9_NIO2/_2_interacting_with_path_and_files/toBeDeleted")));
            // deletes the 'toBeDeleted' directory assuming it is empty - if the pigeon directory does not exist, then will not throw an exception and prints 'false'
        } catch (IOException e) {
            System.out.println(e);
        }*/
        // be sure to create 'toBeDelete' directory and 'feathers.txt' before you run the code from above if you don't want to get a checked IOException


        // ■■ Reading and Writing File Data with newBufferedReader() and newBufferedWriter()
        // Files.newBufferedReader(Path, Charset) = reads the file specified at the Path location using a java.io.BufferedReader obj
        // Files.newBufferedWriter(Path, Charset) = writes to a file specified at the Path location using a java.io.BufferedWriter obj
        //                                  - Charset = determines what character encoding to use to read the file
        // - Charset -> characters can be encoded in bytes in a variety of ways
        Charset.defaultCharset(); // - takes the default Charset for the JVM

        // This is the gorilla file.
        // ex for newBufferedReader():
        System.out.println("newBufferedReader() and newBufferedWriter()");
        Path path = Paths.get("src/com/patri/java/ocp/_9_NIO2/_2_interacting_with_path_and_files/animals/gopher.txt");
        try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("US-ASCII"))) {
            // Read from the stream
            String currentLine = null;
            while ((currentLine = reader.readLine()) != null)
                System.out.println(currentLine);
        } catch (IOException e) {
            System.out.println(e);
        }
        // this ex reads the contents of the file using a BufferedReader and outputs the contents to the user

        // ex for newBufferedWriter():
        Path path1 = Paths.get("src/com/patri/java/ocp/_9_NIO2/_2_interacting_with_path_and_files/animals/gorilla.txt");
        List<String> data = new ArrayList<>();
        try (BufferedWriter writer = Files.newBufferedWriter(path1, Charset.forName("UTF-16"))) {
            writer.write("Hello World");
        } catch (IOException e) {
            System.out.println(e);
        }
        // this creates a new file with the specified contents, overwriting the file if it already exists
        // - this method also supports taking additional enum values in an optional vararg, such as appending to an existing file instead of overwriting it

        // - we use try with resources -> because these methods create resources
        // - we use Buffered stream classes rather than low-level file streams -> buffered classes are much more performant


        // ■■ Reading Files with readAllLines()
        // Files.readAllLines() = reads all the lines of a text file and returns the result as an ordered List of String values
        // ex: the following code reads the lines of the file and outputs them to the user
        System.out.println("reading all lines from a file");
        Path path2 = Paths.get("src/com/patri/java/ocp/_9_NIO2/_2_interacting_with_path_and_files/animals/dogs.txt");
        try {
            final List<String> lines = Files.readAllLines(path2);
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        // - the entire file is read when readAllLines() is called -> the resulting String array stores all of the contents of the file in memory at once
        // so if the file is too large => OutOfMemoryError
    }
}
