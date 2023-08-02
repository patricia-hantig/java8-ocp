package com.patri.java.ocp._8_IO._1_files_and_directories;

import java.io.File;

public class FileClass {

    // ■■■ Introducing the File class:

    // ■ located in java.io.File
    // ■ File class = is used to show how to access files and directories
    //              - is used to read information about existing files and directories
    //              - is used for list the contents of a directory
    //              - is used for creating/deleting files and directories
    //              - an instance of a File class - represents the pathname of a file or directory
    //              - CAN NOT read or write data within a file


    // ■■■ Creating a File object

    // ■ File object is initialized with String containing an absolute or relative path to the file/directory within the file system

    // ■ path can be:   - absolute: full path from root directory to the file/directory
    //                  - relative: the path from the current working directory to file/directory
    // ex: for absolute path: /home/smith/data/zoo.txt
    // ex: for relative path: data/zoo.txt (assuming the user's current directory is /home/smith)
    // local separator character: '/'- Linux and '\' - Windows

    public static void main(String[] args) {

        // ■ Java offers 2 options to retrieve the local separator character:

        System.out.println(System.getProperty("file.separator"));   // using a system property
        System.out.println(File.separator);                         // using a static variable defined in File class
        // both will output in my case: / (I use Windows)

        // ■ File class has 2 constructors:
        //          - public File(String pathname)
        //          - public File(String parent, String child)\

        // ex for creating a File obj and checking if the path refers to an existing file:
        // with first constructor: public File(String pathname)
        File file = new File("C:\\Users\\Patry\\IdeaProjects\\OCA_and_OCP\\src\\com\\patri\\java\\ocp\\_8_IO\\_1_files_and_directories\\zoo.txt");
        System.out.println(file.exists());
        // this ex uses the absolute path to a file and output 'true' or 'false' - if the file exists or not

        // with second constructor: public File(String parent, String child)
        File parent = new File("C:\\Users\\Patry\\IdeaProjects\\OCA_and_OCP\\src\\com\\patri\\java\\ocp");
        File child = new File(parent, "_8_IO\\_1_files_and_directories\\zoo.txt");
        System.out.println(child.exists());
        // If the parent object happened to be null, then it would be skipped and the method would revert to our single String constructor
    }
}

// ■■■ Working with a File object

// 'File' class contains a lot of useful methods for interacting with files and directories
// ■ Most used 'File' methods:
//          Method name     |                                   Description
// --------------------------------------------------------------------------------------------------------------
//      exists()            | returns true if the file/directory exists
//      getName()           | returns the name of the file/directory denoted by this path
//      getAbsolutePath()   | returns the absolute pathname string of this path
//      isDirectory()       | returns true if the file denoted by this path is a directory
//      isFile()            | returns true if the file denoted by this path is a file
//      length()            | returns the numbers of bites in the file
//      lastModified()      | returns the numbers of milliseconds since the last change
//      delete()            | deletes the file/directory (directory only if it is empty)
//      renameTo(File)      | renames the file denoted by this path
//      mkdir()             | creates the directory named by this path
//      mkdirs()            | creates the directory named by this path including nonexistent parent directories
//      getParent()         | returns the abstract pathname for parent or null
//      listFiles()         | returns a File[] - which contains all files in the directory
// --------------------------------------------------------------------------------------------------------------

// ex:
class ReadFileInformation {
    public static void main(String[] args) {
        // if the path provided did not point to a file
        File file1 = new File("C:\\Users\\Patry\\IdeaProjects\\OCA_and_OCP\\src\\com\\patri\\java\\ocp\\_8_IO\\_1_files_and_directories\\test.txt");
        // if the path provided points to a valid file
        File file2 = new File("C:\\Users\\Patry\\IdeaProjects\\OCA_and_OCP\\src\\com\\patri\\java\\ocp\\_8_IO\\_1_files_and_directories\\example.txt");
        // if the path provided points to a valid directory
        File file3 = new File("C:\\Users\\Patry\\IdeaProjects\\OCA_and_OCP\\src\\com\\patri\\java\\ocp\\_8_IO\\_1_files_and_directories");
        checkFile(file1);
        System.out.println();
        checkFile(file2);
        System.out.println();
        checkFile(file3);
    }

    private static void checkFile(File file) {
        System.out.println("File Exists: " + file.exists());
        if (file.exists()) {
            System.out.println("Absolute Path: " + file.getAbsolutePath());
            System.out.println("Is Directory: "+ file.isDirectory());
            System.out.println("Parent Path: " + file.getParent());
            if (file.isFile()) {
                System.out.println("File size: " + file.length());
                System.out.println("File last modified: " + file.lastModified());
            } else {
                for (File subfile : file.listFiles()) {
                    System.out.println("\t" + subfile.getName());
                }
            }
        }
    }
}

