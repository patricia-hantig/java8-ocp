package com.patri.java.ocp._9_NIO2._2_interacting_with_path_and_files;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UsingPathObjects {
    public static void main(String[] args) {

        // Path include methods: toFile() and toURI() - used to convert Path objects to other types of resources
        // many of the methods in the Path interface transform the path value and return a new Path object
        // we have method chaining
        // ex:
        Paths.get("/zoo/../home").getParent().normalize().toAbsolutePath();

        // ■■■ Path methods by related functionality:


        // ■■ Viewing Path with toString(), getNameCount(), getName()

        // - these methods are used to retrieve basic information about path representative
        // toString()       = returns a String representation of the entire path (is the only method from Path interface that returns a String)
        // getNameCount()   = returns the number of elements in the path
        // getName()        = returns a reference to each element from path -> it returns the component of the Path as a new Path object, NOT a String

        // ex:
        Path path = Paths.get("/land/hippo/harry.happy");
        System.out.println("The Path Name is: " + path); // PrintStream/PrintWriter printing an object automatically invokes toString()
        for (int i = 0; i < path.getNameCount(); i++) {
            System.out.println("    Element " + i + " is: " + path.getName(i));
        }
        // '/' - is not included in the list of names
        // if the Path object represents the root element itself => getNameCount() will be 0
        // if the path will be a relative path -> we will get the same output
        // getName(int) is a zero-indexed method

        Path path2 = Paths.get("land/hippo/harry.happy");
        System.out.println("The Path Name is: " + path2); // PrintStream/PrintWriter printing an object automatically invokes toString()
        for (int i = 0; i < path2.getNameCount(); i++) {
            System.out.println("    Element " + i + " is: " + path2.getName(i));
        }
        System.out.println();


        // ■■ Accessing Path Components with getFileName(), getParent(), getRoot()

        // - these methods are used to retrieve specific sub-elements of a Path object
        // getFileName()    = returns a Path instance representing the filename, which is the fastest element from the root (returns a new Path instance)
        // getParent()      = returns a Path instance representing the parent path or null if there is no such parent
        //                  (if the instance of the Path obj is relative - the method will stop at the to-level element in the Path obj,
        //                  it does not traverse relative directories outside the working directory)
        // getRoot()        = returns the root element for the Path object or null if path is relative

        // ex: we use printPathInfo(Path path) method
        printPathInfo(Paths.get("/zoo/armadillo/shells.txt"));
        System.out.println();
        printPathInfo(Paths.get("armadillo/shells.txt")); // with relative path
        System.out.println();


        // ■■ Checking Path Type with isAbsolute() and toAbsolutePath()

        // if a path is absolute or relative - is system dependent
        // isAbsolute()     = returns true if the path the obj references is absolute and false if the path the obj references is relative
        // toAbsolutePath() = converts a  relative Path obj to an absolute Path obj by joining the current working directory;
        //                    if the Path is already absolute -> the method returns an equivalent copy of it => new Path obj

        // ex:
        Path path1 = Paths.get("C:\\birds\\egret.txt");
        System.out.println("Path is Absolute? " + path1.isAbsolute());
        System.out.println("Absolute Path1: " + path1.toAbsolutePath());

        Path path3 = Paths.get("birds/condor.txt");
        System.out.println("Path is Absolute? " + path3.isAbsolute());
        System.out.println("Absolute Path1: " + path3.toAbsolutePath());

        System.out.println(Paths.get("/stripes/zebra.exe").isAbsolute());
        System.out.println(Paths.get("c:/goats/Food.java").isAbsolute());
        // output on Windows: false, true
        // output on Mac/Linux: true, false


        // ■■ Creating a New Path with subpath()

        // subpath(int, int) = returns a relative subpath of the Path obj referenced by inclusive start index and exclusive end index
        // it's useful for constructing a new relative path from a particular parent path element to another path element

        // ex:
        Path path4 = Paths.get("/mammal/carnivore/raccoon.image");
        System.out.println("Path is: " + path);
        System.out.println("Subpath from 0 to 3 is: " + path4.subpath(0, 3));
        System.out.println("Subpath from 1 to 3 is: " + path4.subpath(1, 3));
        System.out.println("Subpath from 1 to 2 is: " + path4.subpath(1, 2));

        // subpath() and getName(int) - are similar because they both return a Path obj that represents a component of an existing Path
        // the difference is that subpath() - may include multiple path components / getName(int) - only include one component

        // System.out.println("Subpath from 0 to 4 is: " + path4.subpath(0, 4));    // exception at runtime: java.lang.IllegalArgumentException
        // System.out.println("Subpath from 1 to 1 is: " + path4.subpath(1, 1));    // exception at runtime: java.lang.IllegalArgumentException


        // ■■ Using Path Symbols
        // " . "    = current directory
        // " .. "   = parent directory
        // ex:
        // ../bear.txt      - refers to file bear.txt in the parent of the current directory
        // ./penguin.txt    - refers to file penguin.txt in the current directory
        // ../../lion.data  - refers to file lion.txt that is two directories up from the current working directory


        // ■■ Deriving a Path with relativize()

        // relativize(Path) = constructs the relative path from one Path obj to another

        // ex:
        Path path5 = Paths.get("fish.txt");
        Path path6 = Paths.get("birds.txt");
        System.out.println(path5.relativize(path6));    // ..\birds.txt
        System.out.println(path6.relativize(path5));    // ..\fish.txt
        // if both path values are relative -> relativize() computes the paths as if they are in the same current working directory
        Path path7 = Paths.get("E:\\habitat");
        Path path8 = Paths.get("E:\\sanctuary\\raven");
        System.out.println(path7.relativize(path8));    // ..\sanctuary\raven
        System.out.println(path8.relativize(path7));    // ..\..\habitat
        // if both path values are absolute -> relativize() computes the relative path from one absolute location to another, regardless of the current working directory

        // Compatible Path Types for relativize():
        // relativize() requires that both paths to be absolute or both relative, otherwise it will throw IllegalArgumentException
        // ex:
        Path path9 = Paths.get("/primate/chimpanzee");
        Path path10 = Paths.get("bananas.txt");
        // System.out.println(path9.relativize(path10));    // exception at runtime: java.lang.IllegalArgumentException: 'other' is different type of Path
        // on Windows, if absolute Paths are used - both paths must have the same root directory or drive letter
        // ex:
        Path path11 = Paths.get("C:\\primate\\chimpanzee");
        Path path12 = Paths.get("D:\\storage\\bananas.txt");
        // System.out.println(path11.relativize(path12));   // exception at runtime: IllegalArgumentException: 'other' has different root


        // ■■ Joining Path objects with resolve()

        // resolve(Path)    = creates a new Path by joining an existing path to the current path
        //                  - the object on which the resolve() is invoked becomes the basis of the new Path obj with the input argument being appended onto the Path

        // ex: resolve() to an absolute path and a relative path
        final Path path13 = Paths.get("/cats/../panther");
        final Path path14 = Paths.get("food");
        System.out.println(path13.resolve(path14));     // \cats\..\panther\food
        final Path path15 = Paths.get("/turkey/food");
        final Path path16 = Paths.get("/tiger/cage");
        System.out.println(path15.resolve(path16));     // \tiger\cage
        // mixing absolute and relative paths with resolve()
        // - if an absolute path is provided as input to the method such as path15.resolve(path16) -> path15 will be ignored and a copy of path16 would be returned


        // ■■ Cleaning up a Path with normalize()
        // normalize(Path) = eliminates the redundancies in the path
        // ex:
        Path path17 = Paths.get("E:\\data");
        Path path18 = Paths.get("E:\\user\\home");
        Path relativePath = path17.relativize(path18);
        System.out.println(relativePath);                   // ..\ user\home
        System.out.println(path17.resolve(relativePath));   // E:\data\..\ user\home
        // as you can see it contains a redundancy
        System.out.println(path17.resolve(relativePath).normalize());   // E:\ user\home
        // like relativize(), the normalize() method does not check that the file actually exists


        // ■■ Checking for File existence with toRealPath()
        // toRealPath(Path) = takes a Path obj that may or may not point to an existing file within the file system and returns a reference to a real path within the fle system
        //                  - similar with toAbsolutePath() but it also verifies that the file referenced by the path actually exists
        //                  and thus it throws a checked IOException if the file can't be find
        //                  - it supports the NOFOLLOW_LINKS option
        //                  - removes redundant path elements

        // ex: we have the symbolic link from food.source to food.txt
        //      /zebra/food.source -> /horse/food.txt
        // assuming that our current working directory is /horse/schedule
        try {
            System.out.println(Paths.get("/zebra/food.source").toRealPath());   // /horse/food.txt
            System.out.println(Paths.get(".././food.txt").toRealPath());        // /horse/food.txt
        } catch (IOException ex) {
            // handle IOEx
        }
        // the absolute and relative paths both resolve to the same absolute file, as the symbolic link points to a real file within the file system

        // ex: we can also use the toRealPath() method to gain access to the current working directory
        try {
            System.out.println(Paths.get(".").toRealPath());        // C:\Users\Patry\IdeaProjects\OCA_and_OCP
        } catch (IOException ex) {
            // handle IOEx
        }
    }

    public static void printPathInfo(Path path) {
        System.out.println("Filename is: " + path.getFileName());
        System.out.println("Root is: " + path.getRoot());

        Path currentParent = path;
        while ((currentParent = currentParent.getParent()) != null ) {
            System.out.println("    Current parent is: " + currentParent);
        }
    }
}
