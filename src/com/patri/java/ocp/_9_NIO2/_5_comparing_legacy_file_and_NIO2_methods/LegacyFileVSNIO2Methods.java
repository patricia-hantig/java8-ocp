package com.patri.java.ocp._9_NIO2._5_comparing_legacy_file_and_NIO2_methods;

public class LegacyFileVSNIO2Methods {

    // ■■■ Comparison between legacy java.io.File methods and NIO.2 methods:

    //      Legacy File Method      |       NIO.2 Method
    // ---------------------------------------------------------------------------
    //  file.exists()               |   Files.exists(path)
    //  file.getName()              |   path.getFileName()
    //  file.getAbsolutePath()      |   path.toAbsolutePath()
    //  file.isDirectory()          |   Files.isDirectory(path)
    //  file.isFile()               |   Files.isRegularFile(path)
    //  file.isHidden()             |   Files.isHidden(path)
    //  file.length()               |   Files.size(path)
    //  file.lastModified           |   Files.getLastModifiedTime(path)
    //  file.setLastModified(time)  |   Files.setLastModifiedTime(path, fileTime)
    //  file.delete()               |   Files.delete(path)
    //  file.renameTo(otherFile)    |   Files.move(path, otherPath)
    //  file.mkdir()                |   Files.createDirectory(path)
    //  file.mkdirs()               |   Files.createDirectories(path)
    //  file.listFiles()            |   Files.list(path)
    // ---------------------------------------------------------------------------

    // NIO.2 API also has some methods and features that are not available in java.io.File class
    // ex: support for symbolic links, setting a file owner etc
    // NIO.2 API is much powerful API than legacy java.io.File
}
