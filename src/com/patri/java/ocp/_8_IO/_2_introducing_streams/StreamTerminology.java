package com.patri.java.ocp._8_IO._2_introducing_streams;

import java.io.*;

public class StreamTerminology {

    public static void main(String[] args) {

        // java.io API provides a lot of classes for creating, accessing and manipulating streams

        // ■■■ Byte Streams & Character Streams
        // java.io API defines 2 sets of classes for reading and writing streams: Stream and Reader/Writer

        // ex: FileInputStream & FileReader - both define a stream that reads a file
        // the difference:
        // - Stream classes -> are used for inputting and outputting binary/byte data
        // - Reader/Writer classes -> are used for inputting and outputting character/String data
        // even though readers/writers do not contain the word 'stream' in their class name - they are streams

        // java.io API - all 'stream' classes have the word 'InputStream' or 'OutputSteam' in their name
        //             - all 'Reader/Writer' classes have the world 'Reader' or 'Writer' in their name

        // ■ why use character streams?
        // byte stream classes can be used to input and output all types of binary data, including strings
        // you can write all the code using byte stream classes, but the advantages are that for example
        // you can use a Writer class to output String value to a file without the need to worry about the underlying byte encoding to the file


        // ■■■ Input & Output
        // - each 'Input Stream' has a corresponding 'Output Stream'
        // - each 'Reader' has a corresponding 'Writer'
        // ex: FileInputStream <-> FileOutputStream
        // ex: FileReader <-> FileWriter
        // exceptions: PrintWriter (there is not PrintReader) and PrintStream (there is no InputStream)


        // ■■■ Low-Level & High-Level Streams
        // - Low-level stream = connects directly with the source of data;
        //                      = process the raw data or resource and is accessed in a direct way
        //                      ex: FileInputStream - low-level stream
        // - High-level stream = built on top of another stream using wrapping
        //                      wrapping - the process by which an instance is passed to the constructor of another class
        //                              and operations on the resulting instance are filtered and applied to the original instance
        //                      ex: BufferedReader in the following ex
        try (
                BufferedReader bufferedReader = new BufferedReader(
                        new FileReader("C:\\Users\\Patry\\IdeaProjects\\OCA_and_OCP\\src\\com\\patri\\java\\ocp\\_8_IO\\_2_introducing_streams\\zoo-data.txt"))) {
            System.out.println(bufferedReader.readLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // in this example BufferedReader = high-level stream that takes a FileReader as input
        // and FileReader = low-level stream
        // readLine() = added method in high-level stream

        // ex: high-level streams can takes other high-level streams as input
        // in this example we are working with Byte streams so
        // in order to read the content of a byte stream - we need to create a file with byte content

        // create a file with byte content
        // we are using a try-with-resources because we need to close our resource - objectOutputStream (we will lear about this later)
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("C:\\Users\\Patry\\IdeaProjects\\OCA_and_OCP\\src\\com\\patri\\java\\ocp\\_8_IO\\_2_introducing_streams\\zoo-data2.txt")))) {
            objectOutputStream.writeObject("Simi + Patry");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // read from a file with byte content
        try (
                ObjectInputStream objectStream = new ObjectInputStream(
                        new BufferedInputStream(
                                new FileInputStream("C:\\Users\\Patry\\IdeaProjects\\OCA_and_OCP\\src\\com\\patri\\java\\ocp\\_8_IO\\_2_introducing_streams\\zoo-data2.txt")))) {
            System.out.println(objectStream.readObject());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        // in this example FileInputStream is the low-level stream that interacts directly with the file and it is wrapped
        // by a high-level BufferedInputStream - which improves performance and
        // the entire object is wrapped by a high-level ObjectInputStream - which allows to filter the data as Java objects

        // ■ Buffered classes - use them when working with files
        // - they read or write data in groups (rather than a single byte or character at a time)
        // unless you are doing something very specialized in your application - you should always wrap a file stream with a Buffered class in practice
        // why? - Buffered streams tend to perform so well in practice because file systems are geared for sequential disk access
        // The more sequential bytes you read at a time, the fewer round-trips between the Java process and the file system, improving the access of your application


        // ■■■ Stream Base Classes
        // java.io defines 4 abstract classes that are parents for all stream classes:
        // - InputStream
        // - OutputStream
        // - Reader
        // - Writer

        // ex: ObjectInputStream - child for InputStream
        // ex: PrintStream - child for OutputStream

        // the constructors of high-level streams take a reference to the abstract class
        // ex: BufferedWriter takes a Writer object as input - which allows to take any subclass of Writer

        // using the abstract parent class as input - the high-level classes can be used without concern for the particular underlying stream subclass

        // ex: why the following examples do not compile? - when we use wrappers - both streams MUST have the same abstract parent
        /* try {

            1. new BufferedInputStream(new FileReader("...\\zoo-data.txt"));        // DOES NOT COMPILE
            why? - InputStream and Reader can't be mixed
            2. new BufferedWriter(new FileOutputStream("...\\zoo-data.txt"));       // DOES NOT COMPILE
            why? - Writer and OutputStream can't be mixed
            3. new ObjectInputStream(new FileOutputStream("...\\zoo-data.txt"));    // DOES NOT COMPILE
            why? - Although it is possible to read data from an InputStream and write it to an OutputStream, wrapping the stream is not the way to do so
            4. new BufferedInputStream(new InputStream());                          // DOES NOT COMPILE
            why? - InputStream is an abstract class - you cannot instantiate an instance of it

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } */


        // ■■■ Review of java.io class

        // ■ java.io class properties
        // - a class with the word 'InputStream'/'OutputStream' -> reads/writes binary data
        // - a class with the word 'Reader'/'Writer' -> reads/writes character/String data
        // - most input classes have output classes
        // - low-level stream -> connects directly to the source of data
        // - high-level stream -> is built on top of another stream using wrapping
        // - a 'Buffered' class -> reads/writes data in groups of bytes/characters and often improves performance in sequential file systems
        // - when we use wrapping -> both streams must have the same abstract parent

        // ■ java.io stream classes
        //      Class Name      |   Low/High level  |   Description
        // ----------------------------------------------------------------------------------------------------------------------------
        // InputStream          |       -           | the abstract class for all 'InputStream' classes
        // OutputStream         |       -           | the abstract class for all 'OutputStream' classes
        // Reader               |       -           | the abstract class for all 'Reader' classes
        // Writer               |       -           | the abstract class for all 'Writer' classes
        // FileInputStream      |       Low         | Reads file data as bytes
        // FileOutputStream     |       Low         | Writes file data as bytes
        // FileReader           |       Low         | Reads file data as characters
        // FileWriter           |       Low         | Writes file data as characters
        // BufferedReader       |       High        | Reads character data from a Reader in a buffered manner
        // BufferedWriter       |       High        | Writes character data from a Reader in a buffered manner
        // ObjectInputStream    |       High        | Deserializes Java data type from InputStream
        // ObjectOutputStream   |       High        | Serializes Java data type in OutputStream
        // InputStreamReader    |       High        | Reads character data from an OutputStream
        // OutputStreamWriter   |       High        | Writes character data in an OutputStream
        // PrintStream          |       High        | Writes formatted representations of Java objects to a binary stream
        // PrintWriter          |       High        | Writes formatted representations of Java objects to a text-based output stream
        // ----------------------------------------------------------------------------------------------------------------------------

    }
}
