package com.patri.java.ocp._9_NIO2._1_introduction;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreatingPaths {
    public static void main(String[] args) throws URISyntaxException {
        // ■■■ Creating Paths
        // - because Path is an interface -> we will need a factory class to create the instance

        // ■ Using the Paths class

        // 1. the most easiest way to obtain a Path object is using java.nio.files.Paths factory class
        // - to obtain a reference to a file or directory we could call the static method:
        // Paths.getPath(String)    or      Paths.get(String, String...)

        // ex: using - Paths.getPath(String)
        Path path1 = Paths.get("pandas/cuddly.png");    // creates a Path reference to a relative file in the current working directory
        Path path2 = Paths.get("c:\\zooinfo\\November\\employees.txt");     // creates a Path reference to an absolute file in a Windows-based system
        Path path3 = Paths.get("/home/zoodirectory");    // creates a Path reference to an absolute directory in a Linux or Mac-based system

        // ex: using - Paths.get(String, String...)
        // - this allows you to create a Path from a list of String values in which the path.separator of the operating system is automatically inserted between elements
        // get the file separator:
        System.out.println(System.getProperty("path.separator"));
        Path path4 = Paths.get("pandas", "cuddly.png");
        Path path5 = Paths.get("c:", "zooinfo", "November" , "employee.txt");
        Path path6 = Paths.get("/", "zoodirectory");
        // advantage of using this method: it is more robust when manually constructing path values, because it inserts the path separator for us

        // - absolute vs relative Path
        // the Path is absolute = if it starts with slash(/-Linux or Mac) or if it starts with a drive letter (-Windows)
        // ex: /bird/parrot     - absolute path
        // ex: C:\bird\emu      - absolute path
        // OTHERWISE Path is relative
        // ex: ..\eagle

        // - take care with Path vs Paths
        // Paths path7 = Paths.get("/alligator/swim.txt");      // DOES NOT COMPILE
        // Path path8 = Path.get("/crocodile/food.txt");        // DOES NOT COMPILE
        // Path = represents the instance with which you want to work
        // Paths = is the factory class containing methods for creating Path instances

        // 2. another way to construct a Path is using an URI value
        // URI = Uniform resource identifier - is a string that identifies a resource
        // URI = schema + path value
        // schema - identifies the resource type ex: file://, http://, https://, ftp://
        // we use java.net.URI

        // ex:
        // Path path7 = Paths.get(new URI("file://pandas/cuddly.png"));    // THROWS EXCEPTION AT RUNTIME
        // URIs must reference absolute paths at runtime - otherwise exception at runtime
        // URI class has an method to check if the path is absolute: isAbsolute()

        Path path8 = Paths.get(new URI("file:///c:/zoo-info/November/employees.txt"));
        Path path9 = Paths.get(new URI("file:///home/zoodirectory"));
        Path path10 = Paths.get(new URI("http://www.google.com"));
        Path path11 = Paths.get(new URI("ftp://username:password@ftp.the-ftp-server.com"));

        // Path has a method to convert Path to URI - toURI()
        URI uri = path10.toUri();


        // ■ Accessing the FileSystem object
        // the method Path.getPath() is shorthand for method getPath() from FileSystem class
        // java.nio.file.FileSystem
        // FileSystem has a protected constructor -> so we will use FileSystems to obtain an instance of FileSystem
        // ex:
        Path path12 = FileSystems.getDefault().getPath("pandas/cuddly.png");
        Path path13 = FileSystems.getDefault().getPath("c:","zooinfo","November",  "employees.txt");
        Path path14 = FileSystems.getDefault().getPath("/home/zoodirector");
        // we could also use a remote file:
        FileSystem fileSystem = FileSystems.getFileSystem(new URI("http://www.selikoff.net"));
        Path path = fileSystem.getPath("duck.txt");


        // ■ Legacy File Instances
        // when Path was added in Java7 -> java.io.File was updated with the method toPath()
        // toPath() operates on an instance File variable
        File file = new File("pandas/cuddly.png");
        Path path15 = file.toPath();
        // for backward compatibility we have also method toFile() - to return a File instance
        Path path16 = Paths.get("cuddly.png");
        File file1 = path16.toFile();
    }
}
