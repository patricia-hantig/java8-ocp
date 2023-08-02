package com.patri.java.ocp._9_NIO2._1_introduction;

public class Introduction {
    // NIO.2 = Non-blocking Input/Output API - New I/O
    // NIO.2 - is a replacement form java.io.File
    // NIO.2 will allow us to do a lot more with files and directories than java.io API

    // ■■ Difference between IO, NIO, NIO.2
    // IO   - java.io API was discussed in last chapter
    //      - uses byte streams to interact with file data
    // NIO  - was introduced in Java 1.4 (Non-blocking I/O)
    //      - introduced concepts of buffers and channels in place of java.io streams
    //      - the idea: you load the data from a file channel into a temporary buffer
    //      (which can be read forward and backward without blocking the underlying resource)
    //      - was not popular and it wasn't used
    // NIO.2- was a replacement for IO, new in Java 7
    //      - more intuitive and more feature-rich API for working with files

    // ■■■ Introducing Path
    // - is an interface
    // package: java.nio.file.Path
    // Path object = represents a hierarchical path on the storage system to a file or directory
    // - Path is similar to java.io.File
    // Path & File  - both refer to a file or a directory
    //              - both refer to an absolute or relative path
    //              - Path - contains support for symbolic link
    // Symbolic link = special file that serves as a reference or pointer to another file or directory

    // ■ Why Path is an interface?
    // - the reason why Path is an interface and not a class is that creating a file/directory is considered a system-dependent task in NIO.2
    // - when we obtain a Path object in NIO.2 -> JVM gives us an object that handles system-specific details for the current platform
    // !!! - so using factory pattern -> the same code would work on different platforms
    // ex: Linux system = case sensitive, Windows system = not case sensitive

    // ■■■ Creating Instances with Factory and Helper Classes
    // - a factory class can be implemented using static methods to create instances of another class
    // ex: you can create an instance of a Path interface using a static method from Paths factory class
    // - NIO.2 has Helper classes like: java.nio.file.Files - whose purpose is to operate on instances of Path objects
    // - the helper classes are similar to factory classes, but are focused on manipulating or creating new objects from existing instances
    // - factory classes are focused on object creation

    // ■ NIO.2 class and interface relationships:
    //              creates                 creates         converts
    // FileSystems -----------> FileSystem ----------> Path <----------> File
    //                                  creates             converts
    //                           Paths ---------->  Path ----------> URI
    //                                                ^
    //                                                | uses
    //                                              Files

}
