package com.patri.java.ocp._8_IO._2_introducing_streams;

public class StreamFundamentals {

    // we use Data Streams -> not Stream API from chapter 4 Functional Programming
    // Data Streams are used for I/O processing
    // I/O = how data is accessed:
    //          - reading the data from a resource = input
    //          - writing the data to a resource = output
    // I/O Streams ARE NOT new Stream API


    // ■ Stream Fundamentals
    // the content of a file can be accessed or written using a STREAM
    // Stream = a long and never-ending “stream of water” with data presented one “wave” at a time
    //          (a "list" of data elements presented sequentially - a resource)
    // a stream is something so large that all of the data contained in it could not possibly fit into memory

    // - visual representation of a stream:
    // ...01001010011100101110101000 | 01100001 | 00100000 | 00001110011100100100...
    // <- to head of stream               |          |       to end of the stream ->
    //                   reading a byte into a block |
    //                                               |
    //                                          next block
    // some stream classes read or write data as individual byte values or as individual characters or strings of characters
    // some stream classes read or write groups of bytes or characters at a time - those with the word Buffered in their name

    // Java has 3 built-in streams: System.in, System.err and System.out
}
