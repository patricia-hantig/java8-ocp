package com.patri.java.ocp._9_NIO2._3_understanding_file_attributes;

import com.patri.java.ocp._8_IO._1_files_and_directories.FileSystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipal;

public class BasicFileAttributes {
    public static void main(String[] args) {
        // Metadata = is data that describes other data
        // File attributes (file metadata) = data about the file/directory within the file system and not the contents of the file

        // ■■■ Basic File Attributes

        // ■■ Reading File Attributes with isDirectory(), isRegularFile(), isSymbolicLink()
        // Files.isDirectory(Path)      = determines if a path refers to a directory or a symbolic link to a directory
        // Files.isRegularFile(Path)    = determines if a path refers to a regular file or a symbolic link to a regular file
        // Files.isSymbolicLink(Path)   = determines if a path refers to a symbolic link

        // Regular file = contains content (opposed to symbolic link, directory, resource or other non-regular file)
        // - if the symbolic link points to a real file or directory => Java will perform the check on the target of the symbolic link
        // so it's possible that isRegularFile() to return true for a symbolic link, as long as the link resolves to a regular file


        // ■ How to create a symbolic link:
        // - create symbolic link to a file:
        // open cmd as Administrator
        // in cmd run:
        // C:\WINDOWS\system32> mklink <path to where you want ro create the symbolic link file>\<name of symbolic Link file>.<extension of file - ex:txt> <path of the real file>\<filename>.<extension of file - ex:txt>
        // ex:
        // C:\WINDOWS\system32> mklink C:\Users\Patry\IdeaProjects\OCA_and_OCP\src\com\patri\java\ocp\_9_NIO2\_3_understanding_file_attributes\canine\symbolicLinkDir.txt C:\Users\Patry\IdeaProjects\OCA_and_OCP\src\com\patri\java\ocp\_9_NIO2\_3_understanding_file_attributes\canine\coyote

        // - create symbolic link to a directory:
        // open cmd as Administrator
        // in cmd run:
        // Using the '/D' option will create a symbolic link to a folder
        // C:\WINDOWS\system32> mklink /D <path to where you want ro create the symbolic link directory>\<name of symbolic Link directory> <path of the real directory>\<directory name>
        // ex:
        // C:\WINDOWS\system32> mklink /D C:\Users\Patry\IdeaProjects\OCA_and_OCP\src\com\patri\java\ocp\_9_NIO2\_3_understanding_file_attributes\canine\symbolicLinkDir C:\Users\Patry\IdeaProjects\OCA_and_OCP\src\com\patri\java\ocp\_9_NIO2\_3_understanding_file_attributes\canine\coyote


        // ex:
        // we have the following structure:
        // directory: canine -> directory: coyote -> file: fur.jpg , symbolic link to a regular file: symbolicLink.jpg
        // directory: canine -> directory: types
        // directory: canine -> symbolic link to directory: symbolicLinkDir -> file: fur.jpg , symbolic link to a regular file: symbolicLink.jpg

        // Files.isDirectory(Path) -> returns 'true' if Path refers to a directory or a symbolic link top a directory
        System.out.println(Files.isDirectory(Paths.get("src/com/patri/java/ocp/_9_NIO2/_3_understanding_file_attributes/canine/coyote/fur.jpg")));          // 'false' -> fur.jpg = file
        System.out.println(Files.isDirectory(Paths.get("src/com/patri/java/ocp/_9_NIO2/_3_understanding_file_attributes/canine/coyote/symbolicLink.jpg"))); // 'false' -> symbolicLink.jpg = symbolic link to file
        System.out.println(Files.isDirectory(Paths.get("src/com/patri/java/ocp/_9_NIO2/_3_understanding_file_attributes/canine/coyote")));                  // 'true' -> coyote = directory
        System.out.println(Files.isDirectory(Paths.get("src/com/patri/java/ocp/_9_NIO2/_3_understanding_file_attributes/canine/symbolicLinkDir")));         // 'true' -> symbolicLinkDir = symbolic link to directory
        System.out.println();

        // Files.isRegularFile(Path) -> returns 'true' if Path refers to a regular file or a symbolic link top a regular file
        System.out.println(Files.isRegularFile(Paths.get("src/com/patri/java/ocp/_9_NIO2/_3_understanding_file_attributes/canine/coyote/fur.jpg")));            // 'true' -> fur.jpg = file
        System.out.println(Files.isRegularFile(Paths.get("src/com/patri/java/ocp/_9_NIO2/_3_understanding_file_attributes/canine/coyote/symbolicLink.jpg")));   // 'true' -> symbolicLink.jpg = symbolic link to file
        System.out.println(Files.isRegularFile(Paths.get("src/com/patri/java/ocp/_9_NIO2/_3_understanding_file_attributes/canine/coyote")));                    // 'false' -> coyote = directory
        System.out.println(Files.isRegularFile(Paths.get("src/com/patri/java/ocp/_9_NIO2/_3_understanding_file_attributes/canine/symbolicLinkDir")));           // 'false' -> symbolicLinkDir = symbolic link to directory
        System.out.println();

        // Files.isSymbolicLink(Path) -> returns 'true' if Path refers to a symbolic link (could be a symbolic link to a directory or to a regular file) - regardless if the file or directory it points to exists
        System.out.println(Files.isSymbolicLink(Paths.get("src/com/patri/java/ocp/_9_NIO2/_3_understanding_file_attributes/canine/coyote/fur.jpg")));            // 'false' -> fur.jpg = file
        System.out.println(Files.isSymbolicLink(Paths.get("src/com/patri/java/ocp/_9_NIO2/_3_understanding_file_attributes/canine/coyote/symbolicLink.jpg")));   // 'true' -> symbolicLink.jpg = symbolic link to file
        System.out.println(Files.isSymbolicLink(Paths.get("src/com/patri/java/ocp/_9_NIO2/_3_understanding_file_attributes/canine/coyote")));                    // 'false' -> coyote = directory
        System.out.println(Files.isSymbolicLink(Paths.get("src/com/patri/java/ocp/_9_NIO2/_3_understanding_file_attributes/canine/symbolicLinkDir")));           // 'true' -> symbolicLinkDir = symbolic link to directory
        System.out.println();

        // - Exception Handling:
        // isDirectory(), isRegularFile() and isSymbolicLink() - DO NOT throw exception if the path does not exist
        // the following code is redundant:
        Path path = Paths.get("");
        if (Files.exists(path) && Files.isDirectory(path)) { } // Files.exists(path) - is unnecessary
        // this could be replaced with:
        if (Files.isDirectory(path)) { }


        // ■■ Check File Visibility with isHidden()
        // Files.isHidden(Path) = determines whether a file or directory is hidden within the file system
        //                      - throws IOException at runtime
        // A file is hidden:    - in Linux or Mac - the hidden file begins with period character (.)
        //                      - in Windows - hidden attribute has to be set

        // ex: file walrus.txt is hidden , fur.jpg is not hidden
        try {
            System.out.println(Files.isHidden(Paths.get("src/com/patri/java/ocp/_9_NIO2/_3_understanding_file_attributes/walrus.txt")));            // true
            System.out.println(Files.isHidden(Paths.get("src/com/patri/java/ocp/_9_NIO2/_3_understanding_file_attributes/canine/coyote/fur.jpg"))); // false
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println();


        // ■■ Testing File Accessibility with isReadable() and isExecutable()
        // Files.isReadable(Path)   = checks if the user has permission to read the contents of the file
        // Files.isExecutable(Path) = checks if the user has permission to execute a file
        // these methods do not throw exceptions if the file does not exist but they return false
        // ex:
        System.out.println(Files.isReadable(Paths.get("src/com/patri/java/ocp/_9_NIO2/_3_understanding_file_attributes/canine/coyote/fur.jpg")));
        // true - because the file exists and its contents are readable
        System.out.println(Files.isExecutable(Paths.get("src/com/patri/java/ocp/_9_NIO2/_3_understanding_file_attributes/canine/coyote/fur.jpg")));
        // true - because the file exists and is marked executable within the Windows-based system


        // ■■ Reading File Length with size()
        // Files.size(Path) = determines the size of the file in bytes (not directory)
        // throws IOException if the file does not exist or if the process is unable to read the file information
        // ex:
        try {
            System.out.println(Files.size(Paths.get("src/com/patri/java/ocp/_9_NIO2/_3_understanding_file_attributes/walrus.txt")));
            // outputs the number of bytes in the file - expressed as a long value
        } catch (IOException e) {
            System.out.println(e);
        }
        // - this method does not apply to directories - because is system dependent and undefined
        // - if you need to determine the size of a directory and it's contents -> you need to walk the directory tree


        // ■■ File modifications with getLastModifiedTime() and setLastModifiedTime()
        // - most operating systems support tracking a last-modified date/time value with each file
        // - it's faster to check a single file metadata attribute than to reload the entire contents of the file

        // Files.getLastModifiedTime(Path)          = returns a FileTime obj to accomplish this
        //          - FileTime  = is a container class that stores the date/time info about date/time (when a file was accessed, modified or created)
        //                      - has a toMillis() method -> returns the epoch time
        // Files.setLastModifiedTime(Path, FileTime) = updates the last-modified date/time of a file
        //          - FileTime  - has a static fromMillis() method -> converts from the epoch time to a FileTime obj

        // - both methods throw a checked IOException when the file is accessed or modified
        // - you don't have to modify the file to change the last-modified date/time value -> so it is considered good practice
        // to modify the attribute only when the file data changes
        // ex:
        try {
            final Path path1 = Paths.get("src/com/patri/java/ocp/_9_NIO2/_3_understanding_file_attributes/walrus.txt");
            System.out.println(Files.getLastModifiedTime(path1).toMillis());
            // reads and outputs the last-modified time value of the file
            Files.setLastModifiedTime(path1, FileTime.fromMillis(System.currentTimeMillis()));
            System.out.println(Files.getLastModifiedTime(path1).toMillis());
            // sets a last-modified date/time using the current time value
        } catch (IOException e) {
            System.out.println(e);
        }


        // ■■ Managing Ownership with getOwner() and setOwner()
        // - file systems support user-owned files and directories
        // Files.getOwner(Path)                 = returns an instance of UserPrincipal that represents the owner of the file within the file system
        // Files.setOwner(Path, UserPrincipal)  = sets the owner
        // - both methods can throw IOException - in case of issues accessing or modifying the file

        // - to set a file owner to an arbitrary user -> NIO.2 API provides a helper class UserPrincipalLookupService for finding a UserPrincipal record
        // - to use the helper class - we need to obtain an instance of a FileSystem obj
        // - we can obtain an instance of a FileSystem obj:
        //          1. call FileSystems.getDefault()
        //          2. call getFileSystem() on the Path obj with which we are working

        // ex:
        try {
            // 1. call FileSystems.getDefault()
            UserPrincipal owner = FileSystems.getDefault().getUserPrincipalLookupService().lookupPrincipalByName("patry");
            // 2.  call getFileSystem() on the Path obj with which we are working
            Path path2 = Paths.get("src/com/patri/java/ocp/_9_NIO2/_3_understanding_file_attributes/walrus.txt");
            UserPrincipal owner2 = path2.getFileSystem().getUserPrincipalLookupService().lookupPrincipalByName("patry");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ex:
        try {
            // Read owner of file
            Path path1 = Paths.get("src/com/patri/java/ocp/_9_NIO2/_3_understanding_file_attributes/walrus.txt");
            System.out.println(Files.getOwner(path1).getName());

            // Change owner of file
            UserPrincipal owner = path1.getFileSystem().getUserPrincipalLookupService().lookupPrincipalByName("simi");
            Files.setOwner(path1, owner);

            // Output the updated owner information
            System.out.println(Files.getOwner(path1).getName());
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
