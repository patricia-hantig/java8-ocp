package com.patri.java.ocp._9_NIO2._3_understanding_file_attributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public class ImprovingAccessWithViews {
    public static void main(String[] args) {
        // until now we have been accessing individual file attributes with single method calls
        // it's more efficient to retrieve all file metadata attributes in a single call
        // ■ View = is a group of related attributes for a particular file system type
        //      - a file may support multiple views -> allowing you to retrieve and update sets of information about the file
        //      - better performance if we need to read multiple attributes of a file -> there are fewer round-trips between Java and the operating system

        // ■■■ Understanding Views
        // For a View - we need a Path to the file/directory + a class object (which tells the NIO.2 API method which type of view we would like to return)
        // Files includes 2 methods for accessing view information:
        // Files.readAttributes()       = returns a read-only view of the file attributes
        // Files.getFileAttributeView() = returns the attribute view and provides a direct resource for modifying file information
        // - both methods can throw IOException

        // ■ The attributes and view classes
        // ------------------------------------------------------------------------------------------------------------
        // Attributes Class     |       View Class          |                       Description
        // ------------------------------------------------------------------------------------------------------------
        // BasicFileAttributes  | BasicFileAttributeView    | basic set of attributes for all file systems
        // DosFileAttributes    | DosFileAttributeView      | attributes supported by DOS/Windows-based systems
        // PosixFileAttributes  | PosixFileAttributeView    | attributes supported by POSIX systems (UNIX, Linux, Mac)
        // ------------------------------------------------------------------------------------------------------------
        // PosixFileAttributes - inherits from BasicFileAttributes
        // DosFileAttributeView - inherits from BasicFileAttributeView
        // Classes BasicFileAttributes and BasicFileAttributeView: - include methods like creationTime(), lastModifiedTime() etc


        // ■■■ Reading Attributes
        // Files.readAttributes(Path, Class<A>) = returns read-only versions of a file view

        // ■ BasicFileAttributes
        // all attributes classes extend from BasicFileAttributes -> it contains attributes common to all supported file systems
        // ex:
        Path path = Paths.get("src/com/patri/java/ocp/_9_NIO2/_3_understanding_file_attributes/turtles/sea.txt");
        try {
            BasicFileAttributes data = Files.readAttributes(path, BasicFileAttributes.class);
            System.out.println("Is path a directory? " + data.isDirectory());
            System.out.println("Is path a regular file? " + data.isRegularFile());
            System.out.println("Is path a symbolic link? " + data.isSymbolicLink());
            System.out.println("Path not a file, directory nor symbolic link? " + data.isOther());
            // isOther() - is used to check for paths that are not files, directories, or symbolic links, such as paths that refer to resources or devices in some file systems
            System.out.println("Size in bytes: " + data.size());

            System.out.println("Creation date/time: " + data.creationTime());
            System.out.println("Last modified date/time " + data.lastModifiedTime());
            System.out.println("Last accessed date/time " + data.lastAccessTime());
            // creationTime(), lastModifiedTime() and lastAccessTime() - return date/time information about the file

            System.out.println("Unique file identifier (if available) " + data.fileKey());
            // fileKey() - returns a file system value that represents a unique identifier for the file within the file system or null if it is not supported by the file system
        } catch (IOException e) {
            System.out.println(e);
        }


        // ■■■ Modifying Attributes
        // Files.getFileAttributeView(Path, Class<V>) = returns a view object that we can use to update the file system-dependent attributes
        // we can use the view object to read the associated file system attributes -> calling readAttributes() on the view object

        // ■ BasicFileAttributeView
        // BasicFileAttributeView is used to modify the file's set of date/time values
        // we cannot modify the other basic attributes directly -> this would change the property of the file system object
        // ex: we cannot change the size of the object without modifying its contents, we can't set a property to change a directory into a file

        // ex: application that reads the file's basic attributes and increments the file's last-modified date/time values by 10 sec
        try {
            BasicFileAttributeView view = Files.getFileAttributeView(path, BasicFileAttributeView.class);
            BasicFileAttributes data = view.readAttributes();

            FileTime lastModifiedTime = FileTime.fromMillis(data.lastModifiedTime().toMillis() + 10000);
            view.setTimes(lastModifiedTime, null, null);
            // setTimes(time, null, null) - would change only the last-modified date/time, leaving the other file date/time values unaffected

            System.out.println("Last modified date/time " + data.lastModifiedTime());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
