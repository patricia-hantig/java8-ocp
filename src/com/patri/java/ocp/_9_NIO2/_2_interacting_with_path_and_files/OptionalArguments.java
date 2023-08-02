package com.patri.java.ocp._9_NIO2._2_interacting_with_path_and_files;

public class OptionalArguments {
    // in NIO.2 we have many methods for interacting with files and directories
    // many of the methods take additional options flags in the form of a vararg

    // ■ Common optional arguments in NIO.2: (the description applies to both files and directories)
    //      Enum Value  |           Usage           |           Description
    // ------------------------------------------------------------------------------------------------
    // NOFOLLOW_LINKS   | Test file existing        | if provided, symbolic links will not be traversed
    //                  | Read file data            | useful for performing operations on symbolic links
    //                  | Copy file                 |
    //                  | Move file                 |
    // ------------------------------------------------------------------------------------------------
    // FOLLOW_LINKS     | Traverse a directory tree | if provided, symbolic links will be traversed
    // ------------------------------------------------------------------------------------------------
    // COPY_ATTRIBUTES  | Copy file                 | if provided, all metadata about a file
    //                  |                           | will be copied with it
    // ------------------------------------------------------------------------------------------------
    // REPLACE_EXISTING | Copy file                 | if provided and the target file exists, it will
    //                  | Move file                 | be replaced, otherwise exception will be thrown
    // ------------------------------------------------------------------------------------------------
    // ATOMIC_MOVE      | Move file                 | any process using the file sees only a complete
    //                  |                           | record, may throw exception if the feature is not
    //                  |                           | supported by the file system

    // ATOMIC_MOVE -> atomic operation = any operation that is performed as a single unit of execution
    // - an atomic move is one in which any process monitoring the file system never sees an incomplete or partially written file

    // ■ NIO.2 Varargs and encapsulation:
    // why the authors of Java used a vararg to pass these values to NIO.2 methods, despite the fact that many methods take only one possible enum value?
    // - they were trying to future-proof the method
    // - By using a vararg, the existing method calls are insulated from changes in future versions of Java in which additional values may be added
    // - this would be a loosely coupled approach
}
