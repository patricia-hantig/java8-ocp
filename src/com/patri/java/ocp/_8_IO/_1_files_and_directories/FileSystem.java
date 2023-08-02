package com.patri.java.ocp._8_IO._1_files_and_directories;

public class FileSystem {
    // ■ Why Java I/O?
    // to save data so that information is not lost every time the program is terminated -> USE FILES

    // ■ We describe how the files and directories are organized within a file system

    // ■ File = is a record within a file system which stores user and system data
    //          - files are organized using directories
    // ■ Directory = is a record within a file system that contains files or other directories
    //          - root directory = topmost directory in the file system (Windows: c:\ , Linux: /)
    // ■ File system = used to interact with files
    //          - it is in charge of reading and writing data within a computer
    // ■ Path = is a String and represents the location of a file within a file system
    //          - each file system has its own path separator (ex: '/' or '\')
    //          - ex of path: c:/user/home/zoo.txt
}
