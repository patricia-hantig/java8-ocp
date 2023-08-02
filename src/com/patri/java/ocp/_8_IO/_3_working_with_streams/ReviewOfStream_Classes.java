package com.patri.java.ocp._8_IO._3_working_with_streams;

public class ReviewOfStream_Classes {
    // ■■■ Diagram of java.io classes - the ones discussed in this chapter
    // on the left side we have abstract parent classes
    // on the right side we have low-level streams and high-level streams

    // Abstract class           low-level stream
    // <InputStream>        ->  FileInputStream
    //
    //                          high-level stream           high-level stream
    //                      ->  FilterInputStream       ->  BufferedInputStream
    //
    //                          high-level stream
    //                      ->  ObjectInputStream

    // Abstract class           high-level stream
    // <Reader>             ->  BufferedReader
    //
    //                          high-level stream           low-level stream
    //                      ->  InputStreamReader       ->  FileReader

    // Abstract class           low-level stream
    // <OutputStream>       ->  FileOutputStream
    //
    //                          high-level stream           high-level stream
    //                      ->  FilterOutputStream      ->  BufferedOutputStream
    //
    //                                                      high-level stream
    //                                                  ->  PrintStream
    //
    //                          high-level stream
    //                      ->  ObjectOutputStream

    // Abstract class           high-level stream
    // <Writer>             ->  BufferedWriter
    //
    //                          high-level stream           low-level stream
    //                      ->  OutputStreamWriter      ->  FileWriter
    //
    //                      ->  high-level stream
    //                          PrintWriter

    // ■ InputStreamReader - takes an InputStream instance and returns a Reader object
    // ■ OutputStreamWriter - takes an OutputStream instance and returns a Writer object
    // these classes convert dat between character and byte streams

    // ■ FilterInputStream and FilterOutputStream - have superclass Filter class and they filter or transform data
}
