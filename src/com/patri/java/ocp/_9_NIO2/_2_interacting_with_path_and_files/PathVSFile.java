package com.patri.java.ocp._9_NIO2._2_interacting_with_path_and_files;

public class PathVSFile {
    // ■ Path object vs Actual File
    // Path obj = is not a file, it is a representation of a location within the file system
    // - most operations available in the Path and Paths classes can be accomplished regardless if the file that the Path obj refers exists or not
    // ex: retrieving the parent or root directory of a Path object does not require the file to exist
    // - some methods from Path and Paths classes do require the file to exist or will throw checked exceptions
}
